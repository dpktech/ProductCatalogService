package org.example.productcatalogservice.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.Entity;

@Entity(name ="msc_ta")
public class Ta extends User {
    private Double ratings;
}
