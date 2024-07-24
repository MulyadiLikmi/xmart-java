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

import com.tujuhsembilan.xmartjava.entity.Barang;
import com.tujuhsembilan.xmartjava.service.IBarangService;

@RestController
@RequestMapping("/api")
public class BarangController {
    @Autowired
    private IBarangService barangService;

    @GetMapping("/barang")
    public List<Barang> findAll() {
        return barangService.findAll();
    }

    @GetMapping("/barang/{id}")
    public Barang findById(@PathVariable("id") String id) {
        return barangService.findById(id);
    }

    @PostMapping("/barang")
    public Barang create(@RequestBody Barang barang) {
        return barangService.create(barang);
    }

    @PutMapping("/barang")
    public Barang edit(@RequestBody Barang barang) {
        return barangService.edit(barang);
    }

    @DeleteMapping("/barang/{id}")
    public void deleteById(@PathVariable("id") String id) {
        barangService.deleteById(id);
    }
}