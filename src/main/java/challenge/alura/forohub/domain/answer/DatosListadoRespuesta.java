package challenge.alura.forohub.domain.answer;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(
        Long id,
        String titulo,
        LocalDateTime fechaCreacion,
        String topico,
        String usuario
) {
    public DatosListadoRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getTitulo(), respuesta.getFechaCreacion(), respuesta.getTopico().getTitulo(),
                respuesta.getUsuario().getNombre());
    }
}
