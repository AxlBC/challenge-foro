package challenge.alura.forohub.domain.topic;

public record DatosActualizaTopico(
        String titulo,
        String mensaje,
        Boolean estado,
        Long idCurso
) {
}
