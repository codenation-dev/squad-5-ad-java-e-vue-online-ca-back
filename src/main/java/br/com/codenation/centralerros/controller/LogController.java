package br.com.codenation.centralerros.controller;

import br.com.codenation.centralerros.dto.LogDTO;
import br.com.codenation.centralerros.dto.LogRespondeDTO;
import br.com.codenation.centralerros.entity.Usuario;
import br.com.codenation.centralerros.mapper.LogMapper;
import br.com.codenation.centralerros.service.LogService;
import br.com.codenation.centralerros.service.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "log")
@Api(tags = {"Log"}, description = "Endpoint para gerenciar logs")
public class LogController {

    private LogService logService;
    private LogMapper mapper;
    private SecurityService securityService;

    @ApiOperation(value = "Enviar logs com token")
    @PostMapping
    private LogRespondeDTO add(@RequestParam(value = "token")String token, @Valid @RequestBody LogDTO dto) {
        return mapper.map(logService.add(token, dto));
    }

    @GetMapping
    private List<LogRespondeDTO> findByAll(){
        Usuario usuario = securityService.getUserAuthenticated();
        return logService.findByAll();
    }

}
