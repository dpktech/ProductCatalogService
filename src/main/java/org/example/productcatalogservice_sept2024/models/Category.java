package org.example.productcatalogservice_sept2024.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    //@Fetch(FetchMode.SELECT)
    //@BatchSize(size = 2)
    @JsonBackReference
    private List<Product> products;
}


//N = 2
//SIZE=2
//N/SIZE = 1


//N = 100, INITITALLY =101
//SIZE = 50
//N/SIZE = 2
//TOTAL = 3