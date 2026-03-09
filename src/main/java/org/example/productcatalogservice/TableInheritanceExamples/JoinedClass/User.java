package org.example.productcatalogservice.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name="jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    private String email;

    @Id
    private Long id;
}
