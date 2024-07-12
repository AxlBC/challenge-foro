package challenge.alura.forohub.domain.course;

public record DatosRespuestaCurso(
        Long idCurso,
        String nombre,
        Categoria caegoria,
        Boolean estado
) {
    public DatosRespuestaCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria(), curso.getActivo());
    }
}
