package com.tujuhsembilan.xmartjava.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.xmartjava.entity.Barang;
import com.tujuhsembilan.xmartjava.exception.ResourceNotFoundException;
import com.tujuhsembilan.xmartjava.repository.BarangRepo;

@Service
public class BarangServiceImpl implements IBarangService {
    @Autowired
    private BarangRepo barangRepo;

    public Barang findById(String id) {
        return barangRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Barang dengan RFID " + id + " tidak ditemukan"));
    }

    public List<Barang> findAll() {
        return barangRepo.findAll();
    }

    public Barang create(Barang barang) {
        barang.setRfid(UUID.randomUUID().toString());
        return barangRepo.save(barang);
    }

    public Barang edit(Barang barang) {
        return barangRepo.save(barang);
    }

    public void deleteById(String id) {
        barangRepo.deleteById(id);
    }
}