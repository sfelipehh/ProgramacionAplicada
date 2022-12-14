package com.sa.programacionaplicada.data.repositories;

import com.sa.programacionaplicada.data.entities.Localidad;
import org.springframework.data.repository.CrudRepository;

public interface LocalidadesRepository extends CrudRepository<Localidad, Long> {
}