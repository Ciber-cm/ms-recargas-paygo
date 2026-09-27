package com.paygo.ms_riesgo.controller;

import com.paygo.ms_riesgo.entity.Analisis;
import com.paygo.ms_riesgo.repository.AnalisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/analisis")
public class AnalisisController {

    @Autowired
    private AnalisisRepository repository;

    @GetMapping
    public List<Analisis> listarTodos() {
        return repository.findAll();
    }
}
