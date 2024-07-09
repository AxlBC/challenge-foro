package challenge.alura.forohub.domain.answer;

import challenge.alura.forohub.domain.topic.ITopicoRepository;
import challenge.alura.forohub.domain.user.IUsuarioRepository;
import challenge.alura.forohub.infra.errores.ValidacionDeIntegridad;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MDRService {

    private final ITopicoRepository topicoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IRespuestaRepository respuestaRepository;

    @Autowired
    public MDRService(ITopicoRepository topicoRepository, IUsuarioRepository usuarioRepository,
                      IRespuestaRepository respuestaRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.respuestaRepository = respuestaRepository;
    }

    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> registrarRespuesta(DatosRegistroRespuesta datos, UriComponentsBuilder uriComponentsBuilder) {
        // Verifica que el tópico y usuario existan
        if (topicoRepository.findById(datos.idTopico()).isEmpty()) {
            throw new ValidacionDeIntegridad("No se ha encontrado ningún tópico con ese id.");
        }
        if (usuarioRepository.findById(datos.idUsuario()).isEmpty()) {
            throw new ValidacionDeIntegridad("No se ha encontrado ningún usuario con ese id.");
        }

        var topico = topicoRepository.getReferenceById(datos.idTopico());
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var respuesta = new Respuesta(datos.titulo(), datos.solucion(), datos.fechaCreacion(), topico, usuario);
        respuestaRepository.save(respuesta);

        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaRespuesta(respuesta));
    }

    public ResponseEntity<Page<DatosListadoRespuesta>> listadoRespuesta(Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAllRespuesta(paginacion).map(DatosListadoRespuesta::new));
    }

    public ResponseEntity<DatosRespuestaRespuesta> retornarDatosRespuesta(Long id) {
        return ResponseEntity.ok(new DatosRespuestaRespuesta(respuestaRepository.getReferenceById(id)));
    }

    @Transactional
    public ResponseEntity<DatosRespuestaRespuesta> actualizarRespuesta(Long id, DatosActualizaRespuesta datos) {
        var respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarDatos(datos);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta));
    }

    @Transactional
    public ResponseEntity eliminarRespuesta(Long id) {
        respuestaRepository.deleteRespuestaById(id);
        return ResponseEntity.noContent().build();
    }
}
