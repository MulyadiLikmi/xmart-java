package com.tujuhsembilan.xmartjava.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer implements Serializable {

    @Id
    private String qrCode;
    private String nama;
    private String wallet;
}