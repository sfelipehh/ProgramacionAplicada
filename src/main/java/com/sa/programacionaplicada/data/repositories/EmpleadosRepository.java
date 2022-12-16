package com.sa.programacionaplicada.data.repositories;

import com.sa.programacionaplicada.data.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface EmpleadosRepository extends CrudRepository<Empleado, Long> {
    @Query(nativeQuery = true, value = "select * from empleado e where e.id=:idEmpleado and e.sede_id=:idSede")
    Optional<Empleado> findEmpleadoByIdAndSede(@Param("idEmpleado") Long idEmpleado, @Param("idSede") Long idSede);
    @Query(nativeQuery = true, value = "select * from empleado e where e.id=:idEmpleado and e.cuadrilla_id=:idCuadrilla")
    Optional<Empleado> findEmpleadoByIdAndCuadrilla(@Param("idEmpleado") Long idEmpleado, @Param("idCuadrilla") Long idCuadrilla);

    @Query(nativeQuery = true, value = "select * from empleado e where " +
            "(" +
            "(:idSede is null or e.sede_id = :idSede) and " +
            "(:idCuadrilla is null or e.cuadrilla_id = :idCuadrilla) " +
            ")")
    List<Empleado> findEmpleadosByAny(
            @Nullable @Param("idSede") Long idSede,
            @Nullable @Param("idCuadrilla") Long idCuadrilla);
}