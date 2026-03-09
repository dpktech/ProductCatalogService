package org.example.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

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