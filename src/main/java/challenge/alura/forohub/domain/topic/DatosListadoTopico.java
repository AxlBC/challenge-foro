package challenge.alura.forohub.domain.topic;

import challenge.alura.forohub.domain.user.DatosListadoUsuario;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        Boolean estado,
        String usuario,
        String curso,
        LocalDateTime fechaCreacion
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstado(), topico.getUsuario().getNombre(),
                topico.getCurso().getNombre(), topico.getFechaCreacion());
    }
}
