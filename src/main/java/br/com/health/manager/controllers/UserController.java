package br.com.health.manager.controllers;

import br.com.health.manager.domain.entities.user.dtos.UsuarioAddDto;
import br.com.health.manager.domain.entities.user.dtos.UsuarioRetornoDto;
import br.com.health.manager.domain.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioRetornoDto> saveUser(@RequestBody @Valid UsuarioAddDto usuarioRetornoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(usuarioRetornoDto));
    }
}
