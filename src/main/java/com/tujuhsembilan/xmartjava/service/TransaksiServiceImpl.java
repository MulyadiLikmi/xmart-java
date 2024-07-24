package com.tujuhsembilan.xmartjava.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.xmartjava.entity.Transaksi;
import com.tujuhsembilan.xmartjava.entity.TransaksiSplit;
import com.tujuhsembilan.xmartjava.exception.ResourceNotFoundException;
import com.tujuhsembilan.xmartjava.repository.TransaksiRepo;
import com.tujuhsembilan.xmartjava.repository.TransaksiSplitRepo;

@Service
public class TransaksiServiceImpl implements ITransaksiService {
    @Autowired
    private TransaksiRepo transaksiRepo;

    @Autowired
    private TransaksiSplitRepo transaksiSplitRepo;

    // cargo
    public Transaksi findById(String id) {
        return transaksiRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaksi dengan id " + id + " tidak ditemukan"));
    }

    public List<Transaksi> findAll() {
        return transaksiRepo.findAll();
    }

    public List<TransaksiSplit> findAllTransaksiSplit() {
        return transaksiSplitRepo.findAllTransaksiSplit();
    }

    public Transaksi create(Transaksi transaksi) {
        transaksi.setId(UUID.randomUUID().toString());
        return transaksiRepo.save(transaksi);
    }

    public Transaksi edit(Transaksi transaksi) {
        return transaksiRepo.save(transaksi);
    }

    public void deleteById(String id) {
        transaksiRepo.deleteById(id);
    }
}