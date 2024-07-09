package challenge.alura.forohub.domain.topic;

public record DatosActualizaTopico(
        String titulo,
        String mensaje,
        boolean estado,
        Long idCurso
) {
}
