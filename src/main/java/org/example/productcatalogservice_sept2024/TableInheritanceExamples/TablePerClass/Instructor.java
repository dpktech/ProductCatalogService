package org.example.productcatalogservice_sept2024.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name ="tpc_instructor")
public class Instructor extends User {
    private String company;
}
