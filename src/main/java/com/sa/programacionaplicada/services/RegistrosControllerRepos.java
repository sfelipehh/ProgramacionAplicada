package com.sa.programacionaplicada.services;/*Author:sfeli*/

import com.sa.programacionaplicada.data.entities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/registros")
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.PATCH})
public class RegistrosControllerRepos extends SuperRequestControllerRepos {

    @PostMapping(path = "/setSede")
    public ResponseEntity<String> CrearSede(@RequestBody Sede newSede) throws URISyntaxException {
        Sede saved = sedesRepository.save(newSede);
        AdministradorSede administradorSede = saved.getAdministradorSede();
        administradorSede.setSede(saved);
        administradorSedeRepository.save(administradorSede);
        return ResponseEntity.accepted().
                location(new URI("/consultas/getSedeById?id=%d".formatted(saved.getId()))).build();
    }

    @PatchMapping(path = "/setSede")
    public ResponseEntity<String> ModificarSede(@RequestBody Sede alterSede) throws URISyntaxException {
        if (alterSede.getId()!=null){
            sedesRepository.findById(alterSede.getId()).ifPresent(actual -> {
                if (alterSede.getNombre()!=null) actual.setNombre(alterSede.getNombre());
                if (!alterSede.getCuadrillas().isEmpty()) actual.setCuadrillas(alterSede.getCuadrillas());
                if (!alterSede.getCuadrillas().isEmpty()) actual.setCuadrillas(alterSede.getCuadrillas());
                if (alterSede.getDireccion()!=null) actual.setDireccion(alterSede.getDireccion());
                if (alterSede.getAdministradorSede()!=null) {
                    AdministradorSede alterAdministrador = alterSede.getAdministradorSede();
                    AdministradorSede actualAdministrador = actual.getAdministradorSede();
                    if (alterAdministrador.getEmpleado() != null && alterAdministrador.getEmpleado().getId() !=null) {
                        actualAdministrador.setEmpleado(
                                empleadosRepository.findByIdAndSede(
                                        alterAdministrador.getEmpleado().getId(),
                                        actualAdministrador.getSede().getId()
                                )
                        );
                        administradorSedeRepository.save(actualAdministrador);
                    }
                }
                if (!alterSede.getEmpleados().isEmpty()) actual.setEmpleados(alterSede.getEmpleados());
                sedesRepository.save(actual);
            });
            return ResponseEntity.accepted().
                    location(new URI("/consultas/getSedeById?id=%d".formatted(alterSede.getId()))).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/setEmpleado")
    public ResponseEntity<String> CrearEmpleado(@RequestBody Empleado newEmpleado) throws URISyntaxException {
        Empleado saved = empleadosRepository.save(newEmpleado);
        return ResponseEntity.accepted().location(new URI("/consultas/getEmpleadoById?id=%d".formatted(saved.getId()))).build();
    }

    @PatchMapping(path = "/setEmpleado")
    public ResponseEntity<String> ModificarEmpleado(@RequestBody Empleado alterEmpleado) throws URISyntaxException {
        if (alterEmpleado.getId() != null) {
            empleadosRepository.findById(alterEmpleado.getId()).ifPresent(actual -> {
                if (alterEmpleado.getDNI() != null) actual.setDNI(alterEmpleado.getDNI());
                if (alterEmpleado.getNombres() != null) actual.setNombres(alterEmpleado.getNombres());
                if (alterEmpleado.getApellidos() != null) actual.setApellidos(alterEmpleado.getApellidos());
                if (alterEmpleado.getCelular() != null) actual.setCelular(alterEmpleado.getCelular());
                if (alterEmpleado.getEmail() != null) actual.setEmail(alterEmpleado.getEmail());
                if (alterEmpleado.getFechaNacimiento() != null) actual.setFechaNacimiento(alterEmpleado.getFechaNacimiento());
                if (alterEmpleado.getCupoAsignado() != null) actual.setCupoAsignado(alterEmpleado.getCupoAsignado());
                if (alterEmpleado.getCuadrilla() != null) actual.setCuadrilla(alterEmpleado.getCuadrilla());
                if (alterEmpleado.getSede() !=null) actual.setSede(alterEmpleado.getSede());
                if (!alterEmpleado.getEventosDeGasto().isEmpty()) actual.setEventosDeGasto(alterEmpleado.getEventosDeGasto());
                empleadosRepository.save(actual);
            });
            return ResponseEntity.accepted().location(new URI("/consultas/getEmpleadoById?id=%d".formatted(alterEmpleado.getId()))).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/setCuadrilla")
    public ResponseEntity<String> CrearCuadrilla(@RequestBody Cuadrilla cuadrilla) throws URISyntaxException {
        Cuadrilla saved = cuadrillasRepository.save(cuadrilla);
        return ResponseEntity.accepted().location(new URI("/consultas/getCuadrillaById?id=%d".formatted(saved.getId()))).build();
    }

    @PatchMapping(path = "/setCuadrilla")
    public ResponseEntity<String> ModificarCuadrilla(@RequestBody Cuadrilla alterCuadrilla) throws URISyntaxException {
        if (alterCuadrilla.getId() != null) {
            cuadrillasRepository.findById(alterCuadrilla.getId()).ifPresent(actual ->{
                if (alterCuadrilla.getNombre() !=null) actual.setNombre(alterCuadrilla.getNombre());
                if (alterCuadrilla.getCantidadEmpleados() != null) actual.setCantidadEmpleados(alterCuadrilla.getCantidadEmpleados());
                if (alterCuadrilla.getCupoAsignado() != null) actual.setCupoAsignado(alterCuadrilla.getCupoAsignado());
                if (alterCuadrilla.getSede() != null) actual.setSede(alterCuadrilla.getSede());
                if (!alterCuadrilla.getLocalidades().isEmpty()) actual.setLocalidades(alterCuadrilla.getLocalidades());
                if (!alterCuadrilla.getEmpleados().isEmpty()) actual.setEmpleados(alterCuadrilla.getEmpleados());
                cuadrillasRepository.save(actual);
            });
            return ResponseEntity.accepted().location(new URI("/consultas/getCuadrillaById?id=%d".formatted(alterCuadrilla.getId()))).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/setLocalidad")
    public ResponseEntity<String> CrearLocalidad(@RequestBody Localidad localidad) throws URISyntaxException {
        Localidad saved = localidadesRepository.save(localidad);
        return ResponseEntity.accepted().location(new URI("/consultas/getLocalidadById?id=%d".formatted(saved.getId()))).build();
    }

    @PatchMapping(path = "/setLocalidad")
    public ResponseEntity<String> ModificarLocalidad(@RequestBody Localidad alterLocalidad) throws URISyntaxException {
        if (alterLocalidad.getId() != null) {
            localidadesRepository.findById(alterLocalidad.getId()).ifPresent(actual -> {
                if (alterLocalidad.getNombre() != null) actual.setNombre(alterLocalidad.getNombre());
                if (alterLocalidad.getCalleInicio() != null) actual.setCalleInicio(alterLocalidad.getCalleInicio());
                if (alterLocalidad.getCalleFin() != null) actual.setCalleFin(alterLocalidad.getCalleFin());
                if (alterLocalidad.getCarreraInicio() != null) actual.setCarreraInicio(alterLocalidad.getCarreraInicio());
                if (alterLocalidad.getCarreraFin() != null) actual.setCarreraFin(alterLocalidad.getCarreraFin());
                if (alterLocalidad.getSede() != null) actual.setSede(alterLocalidad.getSede());
                //if (alterLocalidad.getSede() != null) actual.setSede(sedesRepository.findById(alterLocalidad.getSede().getId()).get());
                if (alterLocalidad.getCuadrilla() != null) actual.setCuadrilla(alterLocalidad.getCuadrilla());
            });
            return ResponseEntity.accepted().location(new URI("/consultas/getLocalidadById?id=%d".formatted(alterLocalidad.getId()))).build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PATCH}, path = "/setEventoGasto")
    public ResponseEntity<String> CrearEventoDeGasto(@RequestBody EventoDeGasto eventoDeGasto) throws URISyntaxException {
        EventoDeGasto saved = eventosDeGastoRepository.save(eventoDeGasto);
        return ResponseEntity.accepted().location(new URI("/consultar/getEventoGastoById?id=%d".formatted(saved.getId()))).build();
    }
}
