package challenge.alura.forohub.domain.topic;

import challenge.alura.forohub.domain.course.Curso;
import challenge.alura.forohub.domain.course.ICursoRepository;
import challenge.alura.forohub.domain.user.IUsuarioRepository;
import challenge.alura.forohub.infra.errores.ValidacionDeIntegridad;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MDTService { // (MDTService = Manejo de Datos Topico Service)
    private final ITopicoRepository topicoRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ICursoRepository cursoRepository;

    @Autowired
    public MDTService(ITopicoRepository topicoRepository, IUsuarioRepository usuarioRepository,
                      ICursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    /* -----------------------------------------------------------------------------------------------------------------
    *  Agregar validaciones
    *  -----------------------------------------------------------------------------------------------------------------
    * */

    @Transactional
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datos) {
        // Creación del tópico
        if (usuarioRepository.findById(datos.idUsuario()).isEmpty()) {
            throw new ValidacionDeIntegridad("No se ha encontrado ningún usuario con ese id.");
        }

        if (cursoRepository.findById(datos.idCurso()).isEmpty()) {
            throw new ValidacionDeIntegridad("No se ha encontrado ningún curso con ese id.");
        }

        // Validaciones extras
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var curso = cursoRepository.getReferenceById(datos.idCurso());

        if (!curso.getActivo()) {
            throw new ValidacionDeIntegridad("No se pueden publicar más tópicos en este curso porque ha sido desactivado.");
        }

        if (!usuario.getActivo()) {
            throw new ValidacionDeIntegridad("No puede publicar un nuevo tópico si su cuenta se encuentra inactiva.");
        }

        var topico = new Topico(datos.titulo(), datos.mensaje(), datos.fechaCreacion(), usuario, curso);
        topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    public ResponseEntity<Page<DatosListadoTopico>> listadoTodoTopico(Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAllTopic(paginacion).map(DatosListadoTopico::new));
    }

    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicoResuelto(Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findTopicoByResuelto(paginacion).map(DatosListadoTopico::new));
    }

    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicoSinResolver(Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findTopicoBySinResolver(paginacion).map(DatosListadoTopico::new));
    }

    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(Long id) {
        return ResponseEntity.ok(new DatosRespuestaTopico(topicoRepository.getReferenceById(id)));
    }

    @Transactional
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizaTopico datos) {
        if (topicoRepository.findById(id).isEmpty()) {
            throw new ValidacionDeIntegridad("No se ha encontrado ningún tópico con ese id.");
        }

        if (datos.idCurso() != null && cursoRepository.findById(datos.idCurso()).isEmpty()) {
            throw new ValidacionDeIntegridad("No se ha encontrado ningún curso con ese id.");
        }

        var topico = topicoRepository.getReferenceById(id);
        Curso curso = null;

        if (datos.idCurso() == null) {
            curso = topico.getCurso();
        } else {
            curso = cursoRepository.getReferenceById(datos.idCurso());
        }

        topico.actualizarDatos(datos, curso);

        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public ResponseEntity eliminarTopico(Long id) {
        var topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            topicoRepository.deleteTopicoById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
