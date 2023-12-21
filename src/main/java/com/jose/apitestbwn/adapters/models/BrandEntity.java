package com.jose.apitestbwn.adapters.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BRAND")
public class BrandEntity {

    @Id
    private int id;
    private String name;
}
