package com.sa.programacionaplicada.data.repositories;

import com.sa.programacionaplicada.data.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpleadosRepository extends CrudRepository<Empleado, Long> {
    @Query(nativeQuery = true, value = "select * from empleado e where e.id=:idEmpleado and e.sede_id=:idSede")
    Optional<Empleado> findByIdAndSede(@Param("idEmpleado") Long idEmpleado, @Param("idSede") Long idSede);
    @Query(nativeQuery = true, value = "select * from empleado e where e.id=:idEmpleado and e.cuadrilla_id=:idCuadrilla")
    Optional<Empleado> findByIdAndCuadrilla(@Param("idEmpleado") Long idEmpleado, @Param("idCuadrilla") Long idCuadrilla);

}