package com.ecomerce.projecte.entities;

import com.ecomerce.projecte.entities.enums.TierType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="providers")
@Getter @Setter
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long phoneNumber;

    @Column(nullable = false)
    private TierType tier;

    @OneToOne(mappedBy = "provider")
    private User user;

}
