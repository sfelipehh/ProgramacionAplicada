package com.sa.programacionaplicada.services;/*Author:sfeli*/

import com.sa.programacionaplicada.data.entities.*;
import jdk.jfr.Event;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/consultas")
@CrossOrigin
public class ConsultasController extends SuperRequestControllerRepos {

    @GetMapping(path = "/getSedeById")
    public @ResponseBody Optional<Sede> getSedeById(@RequestParam Long id){
        return sedesRepository.findById(id);
    }

    @GetMapping(path = "/getSedes")
    public @ResponseBody Iterable<Sede> getSedes(){
        return sedesRepository.findAll();
    }

    @GetMapping(path = "/getLocalidadById")
    public @ResponseBody Optional<Localidad> getLocalidadById(@RequestParam Long id){
        return localidadesRepository.findById(id);
    }

    @GetMapping(path = "/getLocalidades")
    public @ResponseBody Iterable<Localidad> getLocalidades(){
        return localidadesRepository.findAll();
    }

    @GetMapping(path = "/getCuadrillaById")
    public @ResponseBody Optional<Cuadrilla> getCuadrillaById(@RequestParam Long id){
        return cuadrillasRepository.findById(id);
    }

    @GetMapping(path = "/getCuadrillas")
    public @ResponseBody Iterable<Cuadrilla> getCuadrillas(){
        return cuadrillasRepository.findAll();
    }

    @GetMapping(path = "/getEmpleadoById")
    public @ResponseBody Optional<Empleado> getEmpleadoById(@RequestParam Long id){
        return empleadosRepository.findById(id);
    }

    @GetMapping(path = "/getEmpleados")
    public @ResponseBody Iterable<Empleado> getEmpleados(){
        return empleadosRepository.findAll();
    }

    @GetMapping(path = "/getEventoGastoById")
    public @ResponseBody Optional<EventoDeGasto> getEventoGastoById(@RequestParam Long id){
        return eventosDeGastoRepository.findById(id);
    }

    @GetMapping(path = "/getEventosGasto")
    public @ResponseBody Iterable<EventoDeGasto> getEventosGasto(){
        return eventosDeGastoRepository.findAll();
    }
    @GetMapping(path = "/getEventosGastoByAny")
    public @ResponseBody Iterable<EventoDeGasto> getEventosGastoByAny(
            @RequestParam(required = false) Long idCuadrilla,
            @RequestParam(required = false) Long idEmpleado,
            @RequestParam(required = false) Boolean aprobado) {
        if (idCuadrilla==null && idEmpleado==null && aprobado==null){
            return null;
        }
        return eventosDeGastoRepository.findEventoDeGastoByAny(idCuadrilla,idEmpleado,aprobado);
    }
}
