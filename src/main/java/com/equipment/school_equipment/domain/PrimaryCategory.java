package com.equipment.school_equipment.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PrimaryCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String categoryName;
}
