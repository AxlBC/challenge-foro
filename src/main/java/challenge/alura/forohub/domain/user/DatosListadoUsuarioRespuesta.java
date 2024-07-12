package challenge.alura.forohub.domain.user;

import challenge.alura.forohub.domain.answer.DatosListadoRespuesta;

import java.util.List;

public record DatosListadoUsuarioRespuesta(
        Long id,
        String nombre,
        List<DatosListadoRespuesta> respuestas
) {
    public DatosListadoUsuarioRespuesta(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getRespuestas().stream()
                .map(DatosListadoRespuesta::new)
                .toList());
    }
}
