package com.tujuhsembilan.xmartjava.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Barang implements Serializable {
    @Id
    private String rfid;
    private String namaBarang;
    private BigDecimal hargaSatuan;
}
