package com.example.ur.repositories;

import com.example.ur.entities.Sindicato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SindicatoRepository extends CrudRepository<Sindicato, Long> {
}
