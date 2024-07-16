package challenge.alura.forohub.controller;

import challenge.alura.forohub.domain.answer.DatosActualizaRespuesta;
import challenge.alura.forohub.domain.answer.DatosListadoRespuesta;
import challenge.alura.forohub.domain.answer.DatosRespuestaRespuesta;
import challenge.alura.forohub.domain.answer.DatosRegistroRespuesta;
import challenge.alura.forohub.domain.answer.MDRService;
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
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    private final MDRService service;

    @Autowired
    public RespuestaController(MDRService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registraRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos,
                                                                     UriComponentsBuilder uriComponentsBuilder) {
        return service.registrarRespuesta(datos, uriComponentsBuilder);
    }

    // Listar por id del tópico?
    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listadoRespuesta(@PageableDefault Pageable paginacion) {
        return service.listadoRespuesta(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta> retornaDatosRespuesta(@PathVariable Long id) {
        return service.retornarDatosRespuesta(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta> actualizarDatosRespuesta(@PathVariable Long id,
                                                                         @RequestBody @Valid DatosActualizaRespuesta datos) {
        return service.actualizarRespuesta(id, datos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        return service.eliminarRespuesta(id);
    }
}

