package com.example.ur.repositories;

import com.example.ur.entities.Afiliacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfiliacionRepository extends CrudRepository<Afiliacion, Long> {
}
