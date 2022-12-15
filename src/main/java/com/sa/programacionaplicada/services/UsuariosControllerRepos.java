package com.sa.programacionaplicada.services;/*Author:sfeli*/

import com.sa.programacionaplicada.data.entities.Perfil;
import com.sa.programacionaplicada.data.entities.Usuario;
import com.sa.programacionaplicada.data.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/usuarios")
public class UsuariosControllerRepos extends SuperRequestControllerRepos{


    @GetMapping(path = "/get")
    public @ResponseBody Iterable<Usuario> getUsuarios(){
        return usuariosRepository.findAll();
    }
    @GetMapping(path = "/getById")
    public Optional<Usuario> getUsuarioById(@RequestParam Long id){
        return usuariosRepository.findById(id);
    }
    @PostMapping(path = "/post")
    public ResponseEntity<String> CrearUsuario(@RequestBody Usuario newUsuario) throws URISyntaxException {
        if (newUsuario.getPerfil() == null){
            newUsuario.setPerfil(new Perfil());
        }else {
            perfilesRepository.save(newUsuario.getPerfil());
        }
        Usuario saved = usuariosRepository.save(newUsuario);
        perfilesRepository.save(saved.getPerfil());
        return ResponseEntity
                .created(new URI("/usuarios/getById?id=%d".formatted(saved.getId())))
                .build();
    }

    @PatchMapping(path = "/patch")
    public ResponseEntity<String> ModificarUsuario(@RequestBody Usuario alterUsuario) throws URISyntaxException {
        if (alterUsuario.getId() != null){
            usuariosRepository.findById(alterUsuario.getId()).ifPresent(actual ->{
                if (alterUsuario.getNombreUsuario() != null) actual.setNombreUsuario(alterUsuario.getNombreUsuario());
                if (alterUsuario.getPassword() != null) actual.setPassword(alterUsuario.getPassword());
                if (alterUsuario.getPerfil() != null ) {
                    Perfil alterPerfil = alterUsuario.getPerfil();
                    Perfil actualPerfil = actual.getPerfil();
                    actualPerfil.setRevision(alterPerfil.isRevision());
                    actualPerfil.setRegistro(alterPerfil.isRegistro());
                    perfilesRepository.save(actualPerfil);
                }
                usuariosRepository.save(actual);
            });
            return ResponseEntity.accepted()
                    .location(new URI("/usuarios/getById?id=%d".formatted(alterUsuario.getId())))
                    .build();
        }
        return ResponseEntity.notFound().build();
    }
}

