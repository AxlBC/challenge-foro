package challenge.alura.forohub.controller;

import challenge.alura.forohub.domain.course.*;
import challenge.alura.forohub.domain.topic.DatosListadoTopico;
import challenge.alura.forohub.domain.topic.DatosRespuestaTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final ICursoRepository cursoRepository;
    private final MDCService service;

    @Autowired
    public CursoController(ICursoRepository cursoRepository, MDCService service) {
        this.cursoRepository = cursoRepository;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registraCurso(@RequestBody @Valid DatosRegistroCurso datos,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        var response = service.registrarCurso(datos);
        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(response.idCurso()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>> listadoCursoActivo(@PageableDefault Pageable paginacion) {
        return service.listadoCursoActivos(paginacion);
    }

    @GetMapping("/inactivo")
    public ResponseEntity<Page<DatosListadoCurso>> listadoCursoInactivo(@PageableDefault Pageable paginacion) {
        return service.listadoCursoInactivos(paginacion);
    }

    // http://localhost:8080/curso/6
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> retornarDatosCurso(@PathVariable Long id) {
        return service.retornarDatosCurso(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> actualizarDatosCurso(@PathVariable Long id, @RequestBody @Valid DatosActualizaCurso datos) {
        return ResponseEntity.ok(service.actualizarCurso(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarCurs(@PathVariable Long id) {
        return service.eliminarCurso(id);
    }
}
