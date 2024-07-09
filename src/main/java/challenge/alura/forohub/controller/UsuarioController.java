package challenge.alura.forohub.controller;

import challenge.alura.forohub.domain.user.DatosListadoUsuario;
import challenge.alura.forohub.domain.user.DatosRegistroUsuario;
import challenge.alura.forohub.domain.user.DatosRespuestaUsuario;
import challenge.alura.forohub.domain.user.MDUService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final MDUService service;

    @Autowired
    public UsuarioController(MDUService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registraUsuario(@RequestBody @Valid DatosRegistroUsuario datos,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        return service.resgistraUsuario(datos, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarios(@PageableDefault Pageable paginacion) {
        return service.listadoUsuario(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoUsuario> retornaDatosUsuario(@PathVariable Long id) {
        return service.retornaDatosUsuario(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@PathVariable Long id,
                                                                   @RequestBody @Valid DatosRegistroUsuario datos) {
        return service.actualizarUsuario(id, datos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        return service.eliminarUsuario(id);
    }
}
