package com.tujuhsembilan.xmartjava.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class TransaksiSplit implements Serializable {
    @Id
    private String id;
    private String nama;
    private String namaBarang;
    private BigDecimal hargaSatuan;
    private Integer jumlah;
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPesan;
}