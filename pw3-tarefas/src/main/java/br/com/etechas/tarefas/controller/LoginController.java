package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.dto.LoginRequestDto;
import br.com.etechas.tarefas.dto.LoginResponseDTO;
import br.com.etechas.tarefas.security.JWTHolder;
import br.com.etechas.tarefas.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDto loginRequest) {
        var autenticado = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()));

        String token = jwtService.generateToken((UserDetails) autenticado.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}

