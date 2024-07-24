package com.tujuhsembilan.xmartjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.xmartjava.entity.Transaksi;
import com.tujuhsembilan.xmartjava.entity.TransaksiSplit;
import com.tujuhsembilan.xmartjava.service.ITransaksiService;

@RestController
@RequestMapping("/api")
public class TransaksiController {
    @Autowired
    private ITransaksiService transaksiService;

    @GetMapping("/transaksi")
    public List<Transaksi> findAll() {
        return transaksiService.findAll();
    }

    @GetMapping("/transaksi-split")
    public List<TransaksiSplit> findAllTransaksiSplits() {
        return transaksiService.findAllTransaksiSplit();
    }

    @GetMapping("/transaksi/{id}")
    public Transaksi findById(@PathVariable("id") String id) {
        return transaksiService.findById(id);
    }

    @PostMapping("/transaksi")
    public Transaksi create(@RequestBody Transaksi transaksi) {
        return transaksiService.create(transaksi);
    }

    @PutMapping("/transaksi")
    public Transaksi edit(@RequestBody Transaksi transaksi) {
        return transaksiService.edit(transaksi);
    }

    @DeleteMapping("/transaksi/{id}")
    public void deleteById(@PathVariable("id") String id) {
        transaksiService.deleteById(id);
    }
}