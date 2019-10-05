package br.com.codenation.centralerros.service;

import br.com.codenation.centralerros.entity.Usuario;
import br.com.codenation.centralerros.exception.ExistException;
import br.com.codenation.centralerros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl  {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        validarEmailExists(usuario.getEmail());

        Usuario user = usuario;
        user.setToken(UUID.randomUUID().toString());
        user.setSenha(passwordEncoder().encode(usuario.getSenha()));
        user = usuarioRepository.saveAndFlush(user);

        return user;
    }

    private void validarEmailExists(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) throw new ExistException("Email já cadastrado");
    }

    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
