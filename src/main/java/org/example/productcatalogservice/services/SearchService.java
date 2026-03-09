package org.example.productcatalogservice.services;

import org.example.productcatalogservice.dtos.SortParam;
import org.example.productcatalogservice.dtos.SortType;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService {
    @Autowired
    private ProductRepo productRepo;


    @Override
    public Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParams) {
       // Sort sort = Sort.by("amount").descending().and(Sort.by("id").descending());

        Sort sort = null;
        if(!sortParams.isEmpty()) {
            if (sortParams.get(0).getSortType().equals(SortType.ASC))
                sort = Sort.by(sortParams.get(0).getAttribute());
            else
                sort = Sort.by(sortParams.get(0).getAttribute()).descending();


            for (int i = 1; i < sortParams.size(); i++) {
                if (sortParams.get(i).getSortType().equals(SortType.ASC))
                    sort  = sort.and(Sort.by(sortParams.get(i).getAttribute()));
                else
                    sort = sort.and(Sort.by(sortParams.get(i).getAttribute()).descending());
            }
        }

        Page<Product> products = productRepo.findProductByTitleEquals(query, PageRequest.of(pageNumber,pageSize,sort));
        return  products;
    }
}
//{
//        "query" : "laptop",
//        "pageNumber" : 0,
//        "pageSize" : 6,
//        "sortParamList" : [
//        {
//        "attribute" : "amount",
//        "sortType" : "DESC"
//        },
//        {
//        "attribute" : "id",
//        "sortType" : "DESC"
//        }
//        ]
//        }