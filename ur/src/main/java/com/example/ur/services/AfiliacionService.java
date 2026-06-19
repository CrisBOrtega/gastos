package com.example.ur.services;

import com.example.ur.entities.Afiliacion;
import com.example.ur.repositories.AfiliacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AfiliacionService {

    private final AfiliacionRepository afiliacionRepository;

    @Autowired
    public AfiliacionService(AfiliacionRepository afiliacionRepository) {
        this.afiliacionRepository = afiliacionRepository;
    }

    public Iterable<Afiliacion> findAll() {
        return afiliacionRepository.findAll();
    }

    public Optional<Afiliacion> findById(Long id) {
        return afiliacionRepository.findById(id);
    }

    public Afiliacion save(Afiliacion afiliacion) {
        return afiliacionRepository.save(afiliacion);
    }

    public void deleteById(Long id) {
        afiliacionRepository.deleteById(id);
    }
}
