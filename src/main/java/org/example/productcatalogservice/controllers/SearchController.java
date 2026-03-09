package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.SearchProductDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchProductDto searchProductDto) {
        return searchService.searchProducts(searchProductDto.getQuery(),searchProductDto.getPageNumber(),searchProductDto.getPageSize(),searchProductDto.getSortParamList());
    }
}
