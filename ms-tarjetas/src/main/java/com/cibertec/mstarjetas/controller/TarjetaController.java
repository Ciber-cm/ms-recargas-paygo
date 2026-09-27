package com.cibertec.mstarjetas.controller;

import com.cibertec.mstarjetas.model.Tarjeta;
import com.cibertec.mstarjetas.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaRepository tarjetaRepository;


    @GetMapping
    public List<Tarjeta> listarTarjetas() {
        return tarjetaRepository.findAll();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> obtenerPorId(@PathVariable String id) {
        return tarjetaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Tarjeta> registrarTarjeta(@RequestBody Tarjeta tarjeta) {
        Tarjeta guardada = tarjetaRepository.save(tarjeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

}
