package br.com.codenation.centralerros.controller;

import br.com.codenation.centralerros.entity.Usuario;
import br.com.codenation.centralerros.repository.UsuarioRepository;
import br.com.codenation.centralerros.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Usuario usuario) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.save(usuario));
    }

}
