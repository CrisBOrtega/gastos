package com.example.ur.services;

import com.example.ur.entities.Sindicato;
import com.example.ur.repositories.SindicatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SindicatoService {

    private final SindicatoRepository sindicatoRepository;

    @Autowired
    public SindicatoService(SindicatoRepository sindicatoRepository) {
        this.sindicatoRepository = sindicatoRepository;
    }

    public Iterable<Sindicato> findAll() {
        return sindicatoRepository.findAll();
    }

    public Optional<Sindicato> findById(Long id) {
        return sindicatoRepository.findById(id);
    }

    public Sindicato save(Sindicato sindicato) {
        return sindicatoRepository.save(sindicato);
    }

    public void deleteById(Long id) {
        sindicatoRepository.deleteById(id);
    }
}
