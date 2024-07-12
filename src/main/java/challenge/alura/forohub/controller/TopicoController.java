package challenge.alura.forohub.controller;

import challenge.alura.forohub.domain.topic.*;

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
@RequestMapping("/topico")
public class TopicoController {

    private final MDTService service;

    @Autowired
    public TopicoController(ITopicoRepository topicRepository, MDTService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registraTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        var response = service.registrarTopico(datosRegistroTopico);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(response.idTopico()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTodoTopico(@PageableDefault Pageable paginacion) {
        return service.listadoTodoTopico(paginacion);
    }

    @GetMapping("/resuelto")
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicoResuelto(@PageableDefault Pageable paginacion) {
        return service.listadoTopicoResuelto(paginacion);
    }

    @GetMapping("/sin-resolver")
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicoSinResolver(@PageableDefault Pageable paginacion) {
        return service.listadoTopicoSinResolver(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        return service.retornarDatosTopico(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarDatosTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizaTopico datos) {
        return ResponseEntity.ok(service.actualizarTopico(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        return service.eliminarTopico(id);
    }
}
