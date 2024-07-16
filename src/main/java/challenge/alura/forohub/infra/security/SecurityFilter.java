package challenge.alura.forohub.infra.security;

import challenge.alura.forohub.domain.user.IUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public SecurityFilter(TokenService tokenService, IUsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtener el toke del header
        var authHeader = request.getHeader("Authorization");
//        System.out.println(authHeader);
        if (authHeader != null) {
            System.out.println("entramos al if");
            if (!new AntPathRequestMatcher("/usuario/registro").matches(request)) {
                var token = authHeader.replace("Bearer ", "");
                System.out.println(token);
                var subject = tokenService.getSubject(token);
                if (subject != null) {
                    // Token válido
                    var usuario = usuarioRepository.findByNombre(subject);
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                            usuario.getAuthorities()); // Forzamos ese inicio de sesión
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
//        System.out.println("termina SecurityFilter");

//        if (new AntPathRequestMatcher("/login").matches(request) ||
//                new AntPathRequestMatcher("/usuario/registro").matches(request)) {
//            filterChain.doFilter(request, response);
//        } else {
//            filterChain.doFilter(request, response);
//        }
    }
}
