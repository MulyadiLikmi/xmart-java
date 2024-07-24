package com.tujuhsembilan.xmartjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tujuhsembilan.xmartjava.entity.Transaksi;

public interface TransaksiRepo extends JpaRepository<Transaksi, String> {

}