package org.example.productcatalogservice_sept2024.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.Entity;

@Entity(name ="msc_mentor")
public class Mentor extends User {
    private Long hours;
}
