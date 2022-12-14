package com.sa.programacionaplicada.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Boolean consulta;
    private Boolean revision;
    private Boolean registro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isConsulta() {
        return consulta;
    }

    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    public boolean isRevision() {
        return revision;
    }

    public void setRevision(boolean revision) {
        this.revision = revision;
    }

    public boolean isRegistro() {
        return registro;
    }

    public void setRegistro(boolean registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", consulta=" + consulta +
                ", revision=" + revision +
                ", registro=" + registro +
                '}';
    }
}
