package org.example.productcatalogservice_sept2024.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_sept2024.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void testQueries() {
        //List<Product> productList = productRepo.findProductByAmountBetween(10000D,200000D);
//        List<Product> productList = productRepo.findProductByIsPrimeSpecificTrue();
//        for(Product product : productList) {
//            System.out.println(product.getDescription()+" "+product.getIsPrimeSpecific());
//        }

        String desc = productRepo.findCategoryNameFromProductId(5000L);
        System.out.println(desc);
    }

}