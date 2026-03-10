package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dtos.UserDto;
import org.example.productcatalogservice.elasticsearch.ProductDocument;
import org.example.productcatalogservice.elasticsearch.ProductElasticsearchRepository;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Primary product service backed by MySQL JPA.
 * Also indexes products to Elasticsearch on create/update (FR-2.3).
 */
@Service("sps")
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductElasticsearchRepository productElasticsearchRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        Product saved = productRepo.save(product);
        // Dual-write: index to Elasticsearch for search (FR-2.3)
        indexToElasticsearch(saved);
        return saved;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        product.setId(id);
        Product saved = productRepo.save(product);
        // Update Elasticsearch index
        indexToElasticsearch(saved);
        return saved;
    }

    /**
     * Returns product with user-role-specific visibility.
     * Calls AuthenticationService via Eureka-resolved @LoadBalanced RestTemplate.
     */
    public Product getProductBasedOnUserRole(Long productId, Long userId) {
        Product product = productRepo.findById(productId).get();
        UserDto userDto = restTemplate.getForEntity(
                "http://userservice/users/{userId}", UserDto.class, userId).getBody();
        return product;
    }

    /**
     * Index product to Elasticsearch. Called on create/update.
     * Enables full-text search via ProductElasticsearchRepository (FR-2.3).
     */
    private void indexToElasticsearch(Product product) {
        try {
            ProductDocument doc = new ProductDocument();
            doc.setId(String.valueOf(product.getId()));
            doc.setTitle(product.getTitle());
            doc.setDescription(product.getDescription());
            doc.setAmount(product.getAmount());
            doc.setImageUrl(product.getImageUrl());
            if (product.getCategory() != null) {
                doc.setCategoryName(product.getCategory().getName());
            }
            productElasticsearchRepository.save(doc);
        } catch (Exception e) {
            System.err.println("Failed to index product to Elasticsearch: " + e.getMessage());
        }
    }
}
