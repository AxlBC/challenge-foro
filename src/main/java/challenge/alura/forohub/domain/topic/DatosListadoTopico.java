package challenge.alura.forohub.domain.topic;

import challenge.alura.forohub.domain.answer.Respuesta;
import challenge.alura.forohub.domain.user.DatosListadoUsuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        Boolean estado,
        String usuario,
        String curso,
        LocalDateTime fechaCreacion,
        List<DatosListadoRespuestaTopico> respuestas
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstado(), topico.getUsuario().getNombre(),
                topico.getCurso().getNombre(), topico.getFechaCreacion(), topico.getRespuestas().stream()
                        .map(DatosListadoRespuestaTopico::new)
                        .toList());
    }
}
