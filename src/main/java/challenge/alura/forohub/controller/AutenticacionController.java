package challenge.alura.forohub.controller;

import challenge.alura.forohub.domain.user.DatosAutenticacionUsuario;
import challenge.alura.forohub.domain.user.Usuario;
import challenge.alura.forohub.infra.security.DatosJWTToken;
import challenge.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authentication;
    private final TokenService tokenService;

    @Autowired
    public AutenticacionController(AuthenticationManager authentication, TokenService tokenService) {
        this.authentication = authentication;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datos.nombre(), datos.clave());
        var usuarioAutenticado = authentication.authenticate(authenticationToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
