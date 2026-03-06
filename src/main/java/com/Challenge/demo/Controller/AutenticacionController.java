package com.Challenge.demo.Controller;

import com.Challenge.demo.domain.usuario.DatosAutenticacionUsuario;
import com.Challenge.demo.domain.usuario.Usuario;
import com.Challenge.demo.infra.security.DatosJWTToken;
import com.Challenge.demo.infra.security.TokenService; // <--- ESTE ES EL IMPORT CORRECTO
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService; // Inyecta tu servicio de tokens

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());

        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);

        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}