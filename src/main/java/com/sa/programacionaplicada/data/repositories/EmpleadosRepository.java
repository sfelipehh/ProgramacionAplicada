package com.sa.programacionaplicada.data.repositories;

import com.sa.programacionaplicada.data.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmpleadosRepository extends CrudRepository<Empleado, Long> {
    @Query(nativeQuery = true, value = "select * from empleado e where e.id=:idEmpleado and e.sede_id=:idSede")
    Empleado findByIdAndSede(@Param("idEmpleado") Long idEmpleado, @Param("idSede") Long idSede);
}