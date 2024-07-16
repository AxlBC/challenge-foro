package challenge.alura.forohub.controller;

import challenge.alura.forohub.domain.user.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    private final MDUService service;

    @Autowired
    public UsuarioController(MDUService service) {
        this.service = service;
    }

    @PostMapping("/registro")
    public ResponseEntity<DatosRespuestaUsuario> registraUsuario(@RequestBody @Valid DatosRegistroUsuario datos,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        return service.resgistraUsuario(datos, uriComponentsBuilder);
    }

//    @GetMapping("/todos")
//    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuario(@PageableDefault Pageable paginacion) {
//        return service.listadoTodoUsuario(paginacion);
//    }

    @GetMapping()
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarioActivo(@PageableDefault Pageable paginacion) {
        return service.listadoUsuarioActivo(paginacion);
    }

    @GetMapping("/inactivo")
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarioInactivo(@PageableDefault Pageable paginacion) {
        return service.listadoUsuarioInactivo(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoUsuario> retornaDatosUsuario(@PathVariable Long id) {
        return service.retornaDatosUsuario(id);
    }

    @GetMapping("/{id}/topicos")
    public ResponseEntity<DatosListadoUsuarioTopico> retornaDatosUsuarioTopico(@PathVariable Long id) {
        return service.retornaDatosUsuarioTopico(id);
    }

    @GetMapping("/{id}/respuestas")
    public ResponseEntity<DatosListadoUsuarioRespuesta> retornaDatosUsuarioRespuesta(@PathVariable Long id) {
        return service.retornaDatosUsuarioRespuesta(id);
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
