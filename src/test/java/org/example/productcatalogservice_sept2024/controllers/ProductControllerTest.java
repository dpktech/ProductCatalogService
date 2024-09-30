package org.example.productcatalogservice_sept2024.controllers;

import org.example.productcatalogservice_sept2024.dtos.ProductDto;
import org.example.productcatalogservice_sept2024.models.Product;
import org.example.productcatalogservice_sept2024.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_WithValidProductId_RunSuccessfully() {
        //Arrange
        Long productId = 2L;
        Product product = new Product();
        product.setId(productId);
        product.setTitle("Iphone16");
        when(productService.getProductById(productId))
                .thenReturn(product);


        //Act
        ResponseEntity<ProductDto> response =
                productController.getProductById(productId);

        //Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(productId,response.getBody().getId());
        assertEquals("Iphone16",response.getBody().getTitle());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void TestGetProductById_WithInvalidId_ResultsInRuntimeException() {
        //Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                ()->productController.getProductById(-1L));

        assertEquals("Product not found",exception.getMessage());
    }

    @Test
    public void TestGetProductById_WhenProductServiceThrowsException() {
        //Assert
        when(productService.getProductById(any(Long.class)))
                .thenThrow(new RuntimeException("kuch toh phata hai !!"));

        assertThrows(RuntimeException.class,
                ()->productController.getProductById(1000L));
    }

}