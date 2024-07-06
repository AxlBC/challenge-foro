package challenge.alura.forohub.domain.topic;

import challenge.alura.forohub.domain.answer.Respuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fechaCreacion,
        @NotNull
        Long idUsuario,
        @NotNull
        Long idCurso
) {
}
