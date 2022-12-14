package com.sa.programacionaplicada.data.repositories;

import com.sa.programacionaplicada.data.entities.Cuadrilla;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CuadrillasRepository extends CrudRepository<Cuadrilla, Long> {

    @Query(nativeQuery = true, value = "select * from cuadrilla c where c.id=:idCuadrilla and c.sede_id=:idSede")
    Optional<Cuadrilla> findByIdAndSede(@Param("idCuadrilla") Long idCuadrilla, @Param("idSede") Long idSede);
}