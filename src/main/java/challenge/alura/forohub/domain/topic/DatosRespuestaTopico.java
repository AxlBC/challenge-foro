package challenge.alura.forohub.domain.topic;

import challenge.alura.forohub.domain.course.Categoria;
import challenge.alura.forohub.domain.course.Curso;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRespuestaTopico(
        Long idTopico,
        String titulo,
        String mensaje,
        Boolean estado,
        String usuario,
        String curso,
        Categoria categoria,
        LocalDateTime fechaCreacion,
        List<DatosListadoRespuestaTopico> respuestas
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstado(),
                topico.getUsuario().getNombre(), topico.getCurso().getNombre(), topico.getCurso().getCategoria(),
                topico.getFechaCreacion(), topico.getRespuestas().stream()
                        .map(DatosListadoRespuestaTopico::new).toList());
    }
}
