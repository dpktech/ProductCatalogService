package org.example.productcatalogservice_sept2024.services;

import org.example.productcatalogservice_sept2024.dtos.UserDto;
import org.example.productcatalogservice_sept2024.models.Product;
import org.example.productcatalogservice_sept2024.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("sps")
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent())  return optionalProduct.get();

        return null;
     }


    public Product getProductBasedOnUserRole(Long productId, Long userId) {
        Product product = productRepo.findById(productId).get();
        UserDto userDto = restTemplate.getForEntity("http://userservice/users/{userId}", UserDto.class,userId).getBody();
        if(userDto != null) {
            return product;
        }

        return null;
    }

    @Override
    public Product createProduct(Product product) {
      return productRepo.save(product);
    }


//    public List<Product> getProductsBetweenRange(Double start,Double end) {
//        return productRepo.findProductByAmountBetween(start,end);
//    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
