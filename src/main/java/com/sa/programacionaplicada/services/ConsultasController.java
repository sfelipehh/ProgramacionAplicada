package com.sa.programacionaplicada.services;/*Author:sfeli*/

import com.sa.programacionaplicada.data.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/consultas")
@CrossOrigin(exposedHeaders = {"Type"}, origins = {"*"})
public class ConsultasController extends SuperRequestControllerRepos {

    @GetMapping(path = "/getSedeById")
    public ResponseEntity<Optional<Sede>> getSedeById(@RequestParam Long id){
        Optional<Sede> sedeOptional = sedesRepository.findById(id);
        if (sedeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Type",Sede.class.getSimpleName())
                    .body(sedeOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/getSedes")
    public ResponseEntity<Iterable<Sede>> getSedes(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Type",Sede.class.getSimpleName())
                .body(sedesRepository.findAll());
    }

    @GetMapping(path = "/getLocalidadById")
    public ResponseEntity<Optional<Localidad>> getLocalidadById(@RequestParam Long id){
        Optional<Localidad> optionalLocalidad = localidadesRepository.findById(id);
        if (optionalLocalidad.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Type",Localidad.class.getSimpleName())
                    .body(optionalLocalidad);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/getLocalidades")
    public ResponseEntity<Iterable<Localidad>> getLocalidades(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Type",Localidad.class.getSimpleName())
                .body(localidadesRepository.findAll());
    }

    @GetMapping(path = "/getCuadrillaById")
    public  ResponseEntity<Optional<Cuadrilla>> getCuadrillaById(@RequestParam Long id){
        Optional<Cuadrilla> cuadrillaOptional = cuadrillasRepository.findById(id);
        if (cuadrillaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Type",Cuadrilla.class.getSimpleName())
                    .body(cuadrillaOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/getCuadrillas")
    public ResponseEntity<Iterable<Cuadrilla>> getCuadrillas(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Type",Cuadrilla.class.getSimpleName())
                .body(cuadrillasRepository.findAll());
    }

    @GetMapping(path = "/getEmpleadoById")
    public ResponseEntity<Optional<Empleado>> getEmpleadoById(@RequestParam Long id){
        Optional<Empleado> empleadoOptional = empleadosRepository.findById(id);
        if (empleadoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Type",Empleado.class.getSimpleName())
                    .body(empleadoOptional);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/getEmpleados")
    public ResponseEntity<Iterable<Empleado>> getEmpleados(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Type",Empleado.class.getSimpleName())
                .body(empleadosRepository.findAll());
    }

    @GetMapping(path = "/getEmpleadosByAny")
    public ResponseEntity<Iterable<Empleado>> getEmpleadosByAny(
            @RequestParam(required = false) Long idSede,
            @RequestParam(required = false) Long idCuadrilla) {
        System.out.println("idSede "+ idSede +" idCuadrilla " + idCuadrilla);
        if (idSede == null && idCuadrilla == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Type",Empleado.class.getSimpleName())
                .body(empleadosRepository.findEmpleadosByAny(idSede,idCuadrilla));
    }

    @GetMapping(path = "/getEventoGastoById")
    public ResponseEntity<Optional<EventoDeGasto>> getEventoGastoById(@RequestParam Long id){
        Optional<EventoDeGasto> optionalEventoDeGasto = eventosDeGastoRepository.findById(id);
        if (optionalEventoDeGasto.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Type", EventoDeGasto.class.getSimpleName())
                    .body(optionalEventoDeGasto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/getEventosGasto")
    public ResponseEntity<Iterable<EventoDeGasto>> getEventosGasto(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Type",EventoDeGasto.class.getSimpleName())
                .body(eventosDeGastoRepository.findAll());
    }
    @GetMapping(path = "/getEventoGastoByAny")
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
