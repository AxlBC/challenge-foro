package challenge.alura.forohub.domain.course;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank
        String nombre,
        @NotBlank
        Categoria categoria
) {
}
