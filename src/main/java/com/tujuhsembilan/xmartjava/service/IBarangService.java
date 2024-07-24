package com.tujuhsembilan.xmartjava.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tujuhsembilan.xmartjava.entity.Barang;

@Service
public interface IBarangService {
    Barang findById(String id);

    List<Barang> findAll();

    Barang create(Barang barang);

    Barang edit(Barang barang);

    void deleteById(String id);
}
