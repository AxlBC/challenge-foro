package challenge.alura.forohub.domain.user;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String correo
) {
    public DatosRespuestaUsuario (Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreo());
    }
}
