package com.diningReview.DiningReview.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="USERS")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="userName")
    private String userName;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Column(name = "PEANUT_ALLERGY")
    private boolean isPeanutAllergy;
    @Column(name = "EGG_ALLERGY")
    private boolean isEggAllergy;
    @Column(name = "DAIRY_ALLERGY")
    private boolean isDairyAllergy;

}
