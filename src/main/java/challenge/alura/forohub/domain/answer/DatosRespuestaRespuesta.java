package challenge.alura.forohub.domain.answer;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(
        Long id,
        String titulo,
        String solucion,
        LocalDateTime fechaCreacion,
        String topico,
        String usuario
) {
    public DatosRespuestaRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getTitulo(), respuesta.getSolucion(), respuesta.getFechaCreacion(),
                respuesta.getTopico().getTitulo(), respuesta.getUsuario().getNombre());
    }
}
