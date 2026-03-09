package org.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SearchProductDto {
    private String query;
    private Integer pageSize;
    private Integer pageNumber;
    private List<SortParam> sortParamList = new ArrayList<>();
}
