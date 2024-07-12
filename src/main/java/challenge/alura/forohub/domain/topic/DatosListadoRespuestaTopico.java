package challenge.alura.forohub.domain.topic;

import challenge.alura.forohub.domain.answer.Respuesta;

public record DatosListadoRespuestaTopico(
        Long id,
        String titulo
) {
    public DatosListadoRespuestaTopico(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getTitulo());
    }
}
