package com.ecomerce.projecte.entities;

import com.ecomerce.projecte.entities.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;    

    private String lastName;

    @Column(nullable = false)
    private UserType userType;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String profilePicture;

    @OneToOne(mappedBy = "user")
    private Provider provider;




}
