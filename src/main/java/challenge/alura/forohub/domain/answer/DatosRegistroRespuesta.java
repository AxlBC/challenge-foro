package challenge.alura.forohub.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotBlank
        String titulo,
        @NotBlank
        String solucion,
        @NotNull
        LocalDateTime fechaCreacion,
        @NotNull
        Long idTopico,
        @NotNull
        Long idUsuario
) {
}
