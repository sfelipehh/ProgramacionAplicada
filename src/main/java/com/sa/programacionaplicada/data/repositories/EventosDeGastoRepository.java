package com.sa.programacionaplicada.data.repositories;

import com.sa.programacionaplicada.data.entities.EventoDeGasto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Collection;

public interface EventosDeGastoRepository extends CrudRepository<EventoDeGasto, Long> {

    @Query(nativeQuery = true,
            value = "select * from evento_de_gasto ev where " +
                    "(" +
                    "(:idCuadrilla is null or ev.cuadrilla_id=:idCuadrilla) or " +
                    "(:idEmpleado is null or ev.empleado_id=:idEmpleado) or " +
                    "(:aprobado is null or ev.aprobado=:aprobado)" +
                    ")")
    Collection<EventoDeGasto> findEventoDeGastoByAny(
            @Nullable @Param("idCuadrilla") Long idCuadrilla,
            @Nullable @Param("idEmpleado") Long idEmpleado,
            @Nullable @Param("aprobado") Boolean aprobado);
}