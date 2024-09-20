package org.example.productcatalogservice_sept2024.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name ="st_ta")
@DiscriminatorValue(value = "1")
public class Ta extends User {
    private Double ratings;
}
