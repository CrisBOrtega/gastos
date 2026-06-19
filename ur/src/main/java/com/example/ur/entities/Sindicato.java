package com.example.ur.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "sindicatos")
public class Sindicato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String codigo;

    @OneToMany(mappedBy = "sindicato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Afiliacion> afiliaciones = new ArrayList<>();

    // Constructors
    public Sindicato() {
    }

    public Sindicato(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Afiliacion> getAfiliaciones() {
        return afiliaciones;
    }

    public void setAfiliaciones(List<Afiliacion> afiliaciones) {
        this.afiliaciones = afiliaciones;
    }
}
