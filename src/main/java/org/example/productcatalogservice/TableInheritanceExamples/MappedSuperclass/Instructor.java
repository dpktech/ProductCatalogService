package org.example.productcatalogservice.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.Entity;

@Entity(name ="msc_instructor")
public class Instructor extends User {
    private String company;
}
