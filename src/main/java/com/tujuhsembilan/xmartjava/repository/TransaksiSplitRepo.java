package com.tujuhsembilan.xmartjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tujuhsembilan.xmartjava.entity.TransaksiSplit;

public interface TransaksiSplitRepo extends JpaRepository<TransaksiSplit, String> {
    @Query(value = "SELECT id, nama, nama_barang, transaksi_customer.harga_satuan, jumlah, waktu_pesan  FROM \r\n" + //
            "\t(SELECT * FROM transaksi JOIN customer ON transaksi.qr_code = customer.qr_code) \r\n" + //
            "AS transaksi_customer JOIN barang ON transaksi_customer.rfid = barang.rfid ORDER BY waktu_pesan", nativeQuery = true)
    List<TransaksiSplit> findAllTransaksiSplit();
}