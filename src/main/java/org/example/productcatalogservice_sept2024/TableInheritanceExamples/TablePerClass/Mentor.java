package org.example.productcatalogservice_sept2024.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name ="tpc_mentor")
public class Mentor extends User {
    private Long hours;
}
