package challenge.alura.forohub.controller;

import challenge.alura.forohub.domain.topic.*;
import jakarta.transaction.Transactional;
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

    private final ITopicoRepository topicRepository;
    private final MDTService service;

    @Autowired
    public TopicoController(ITopicoRepository topicRepository, MDTService service) {
        this.topicRepository = topicRepository;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registraTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                               UriComponentsBuilder uriComponentsBuilder) {
//        System.out.println(datosRegistroTopico);
//        topicRepository.save(new Topico(datosRegistroTopico));
        var response = service.registrarTopico(datosRegistroTopico);
        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(response.idTopico()).toUri();
//        return ResponseEntity.ok(response);
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(@PageableDefault Pageable paginacion) {
        return service.listadoTopico(paginacion);
//        return ResponseEntity.ok(topicRepository.findAllTopic(paginacion).map(DatosListadoTopico::new));
    }

//    @GetMapping("/{fecha}")
//    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicoPorAnio(@PathVariable int anio, @PageableDefault Pageable paginacion,
//                                                                         UriComponentsBuilder uriComponentsBuilder) {
//        URI url = uriComponentsBuilder.path("/topic/{fecha}").buildAndExpand(response.idTopico()).toUri();
////        return ResponseEntity.ok(topicRepository.findAllTopicByYear(paginacion, anio).map(DatosListadoTopico::new));
////        return ResponseEntity.ok(topicRepository.findAll(pageable).map(DatosListadoTopico:new));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {
        return service.retornarDatosTopico(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarDatosTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizaTopico datos) {
        var response = service.actualizarTopico(id, datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        return service.eliminarTopico(id);
    }
}
