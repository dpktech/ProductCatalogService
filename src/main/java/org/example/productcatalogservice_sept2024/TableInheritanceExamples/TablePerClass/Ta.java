package org.example.productcatalogservice_sept2024.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name ="tpc_ta")
public class Ta extends User{
    private Double ratings;
}
