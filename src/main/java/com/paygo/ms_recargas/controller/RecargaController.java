package com.paygo.ms_recargas.controller;
import com.paygo.ms_recargas.entity.Recarga;
import com.paygo.ms_recargas.service.RecargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recargas")
public class RecargaController {
    @Autowired private RecargaService service;
    @PostMapping
    public Recarga crear(@RequestBody Recarga r){ return service.registrar(r); }
    @GetMapping
    public List<Recarga> listar(){ return service.repo.findAll(); }
}