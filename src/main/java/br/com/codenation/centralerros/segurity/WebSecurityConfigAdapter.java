package br.com.codenation.centralerros.segurity;

import br.com.codenation.centralerros.dto.UsuarioCustomDTO;
import br.com.codenation.centralerros.entity.Usuario;
import br.com.codenation.centralerros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UsuarioRepository usuarioRepository) throws Exception {

        if (usuarioRepository.count() == 0 ) {
            Usuario usuario = new Usuario();
            usuario.setEmail("teste@gmail.com");
            usuario.setSenha(passwordEncoder().encode("123"));
            usuario.setNome("teste");
            usuario.setToken(UUID.randomUUID().toString());
            usuarioRepository.saveAndFlush(usuario);
        }

        builder.userDetailsService(login -> new UsuarioCustomDTO(usuarioRepository.findByEmail(login)))
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
