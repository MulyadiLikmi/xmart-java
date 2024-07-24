package com.tujuhsembilan.xmartjava.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tujuhsembilan.xmartjava.entity.Transaksi;
import com.tujuhsembilan.xmartjava.entity.TransaksiSplit;

@Service
public interface ITransaksiService {
    Transaksi findById(String id);

    List<Transaksi> findAll();

    List<TransaksiSplit> findAllTransaksiSplit();

    Transaksi create(Transaksi transaksi);

    Transaksi edit(Transaksi transaksi);

    void deleteById(String id);
}
