package org.example.productcatalogservice.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Elasticsearch document for full-text product search (FR-2.3).
 * Mirrors the Product entity but stored in Elasticsearch index.
 */
@Document(indexName = "products")
@Getter
@Setter
public class ProductDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Keyword)
    private String categoryName;

    @Field(type = FieldType.Double)
    private Double amount;

    @Field(type = FieldType.Keyword)
    private String imageUrl;
}
