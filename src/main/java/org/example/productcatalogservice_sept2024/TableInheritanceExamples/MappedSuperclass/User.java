package org.example.productcatalogservice_sept2024.TableInheritanceExamples.MappedSuperclass;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {
    private String email;

    @Id
    private Long id;
}
