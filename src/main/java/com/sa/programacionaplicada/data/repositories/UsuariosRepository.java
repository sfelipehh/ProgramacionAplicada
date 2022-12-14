package com.sa.programacionaplicada.data.repositories;

import com.sa.programacionaplicada.data.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuariosRepository extends CrudRepository<Usuario, Long> {
}