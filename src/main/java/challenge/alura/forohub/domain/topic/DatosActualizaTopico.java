package challenge.alura.forohub.domain.topic;

import jakarta.validation.constraints.NotNull;

public record DatosActualizaTopico(
        String titulo,
        String mensaje,
        boolean estado,
        Long idCurso
) {
}
