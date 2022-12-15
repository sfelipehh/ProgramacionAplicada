package com.sa.programacionaplicada.services;/*Author:sfeli*/

import com.sa.programacionaplicada.data.repositories.EventosDeGastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/aprobaciones")
public class AprobacionEventosGastoController {

    @Autowired
    EventosDeGastoRepository eventosDeGastoRepository;

    @PatchMapping(path = "/eventoGasto")
    public ResponseEntity<String> aprobarEventoGasto(@RequestParam(name = "ids") List<Long> idsEventosGasto){
        if (!idsEventosGasto.isEmpty()){
            for (Long id:
                    idsEventosGasto) {
                eventosDeGastoRepository.findById(id).ifPresent(eventoDeGasto -> {
                    eventoDeGasto.setAprobado(true);
                    eventosDeGastoRepository.save(eventoDeGasto);
                });
            }
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"List of Ids Empty")).build();
    }
}
