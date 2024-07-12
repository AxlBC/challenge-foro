package challenge.alura.forohub.domain.user;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        Boolean activo
) {
    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getActivo());
    }
}
