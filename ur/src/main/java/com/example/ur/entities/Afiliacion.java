package com.example.ur.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "afiliaciones")
public class Afiliacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaAfiliacion;

    private Long usuarioId;

    @ManyToOne
    @jakarta.persistence.JoinColumn(name = "sindicato_id")
    private Sindicato sindicato;

    // Constructors
    public Afiliacion() {
    }

    public Afiliacion(LocalDate fechaAfiliacion, Long usuarioId, Sindicato sindicato) {
        this.fechaAfiliacion = fechaAfiliacion;
        this.usuarioId = usuarioId;
        this.sindicato = sindicato;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(LocalDate fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Sindicato getSindicato() {
        return sindicato;
    }

    public void setSindicato(Sindicato sindicato) {
        this.sindicato = sindicato;
    }
}
