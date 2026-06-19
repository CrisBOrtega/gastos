package com.example.ur.controllers;

import com.example.ur.entities.Sindicato;
import com.example.ur.services.SindicatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/sindicatos")
public class SindicatoController {

    private final SindicatoService sindicatoService;

    @Autowired
    public SindicatoController(SindicatoService sindicatoService) {
        this.sindicatoService = sindicatoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Sindicato>> getAllSindicatos() {
        return ResponseEntity.ok(sindicatoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sindicato> getSindicatoById(@PathVariable Long id) {
        Optional<Sindicato> sindicato = sindicatoService.findById(id);
        return sindicato.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sindicato> createSindicato(@RequestBody Sindicato sindicato) {
        Sindicato savedSindicato = sindicatoService.save(sindicato);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSindicato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sindicato> updateSindicato(@PathVariable Long id, @RequestBody Sindicato sindicatoDetails) {
        Optional<Sindicato> optionalSindicato = sindicatoService.findById(id);
        if (optionalSindicato.isPresent()) {
            Sindicato sindicato = optionalSindicato.get();
            sindicato.setNombre(sindicatoDetails.getNombre());
            sindicato.setCodigo(sindicatoDetails.getCodigo());
            Sindicato updatedSindicato = sindicatoService.save(sindicato);
            return ResponseEntity.ok(updatedSindicato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSindicato(@PathVariable Long id) {
        if (sindicatoService.findById(id).isPresent()) {
            sindicatoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
