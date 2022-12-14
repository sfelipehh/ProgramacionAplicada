package com.sa.programacionaplicada.services;/*Author:sfeli*/

import com.sa.programacionaplicada.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;


public class SuperRequestControllerRepos {

    @Autowired
    protected AdministradorSedeRepository administradorSedeRepository;
    @Autowired
    protected CuadrillasRepository cuadrillasRepository;
    @Autowired
    protected EmpleadosRepository empleadosRepository;
    @Autowired
    protected EventosDeGastoRepository eventosDeGastoRepository;
    @Autowired
    protected LocalidadesRepository localidadesRepository;
    @Autowired
    protected PerfilesRepository perfilesRepository;
    @Autowired
    protected SedesRepository sedesRepository;
    @Autowired
    protected SupervisorCuadrillaRepository supervisorCuadrillaRepository;
    @Autowired
    protected UsuariosRepository usuariosRepository;
}
