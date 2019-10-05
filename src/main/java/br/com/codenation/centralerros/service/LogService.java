package br.com.codenation.centralerros.service;

import br.com.codenation.centralerros.dto.LogDTO;
import br.com.codenation.centralerros.dto.LogRespondeDTO;
import br.com.codenation.centralerros.entity.Log;
import br.com.codenation.centralerros.entity.Usuario;
import br.com.codenation.centralerros.mapper.LogMapper;
import br.com.codenation.centralerros.repository.LogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class LogService {

    private UsuarioServiceImpl usuarioService;
    private LogMapper mapper;
    private LogRepository logRepository;

    public Log add(String token, LogDTO dto) {
        Usuario usuario = usuarioService.findByToken(token)
                                        .orElseThrow(() -> new  IllegalArgumentException("Token invalido"));

        Log log = mapper.map(dto);
        log.setUsuario(usuario);
        return logRepository.saveAndFlush(log);
    }

    public List<LogRespondeDTO> findByAll(){
        return mapper.map(logRepository.findAll());
    }

}
