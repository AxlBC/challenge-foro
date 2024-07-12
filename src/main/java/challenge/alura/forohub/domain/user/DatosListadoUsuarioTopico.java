package challenge.alura.forohub.domain.user;

import challenge.alura.forohub.domain.topic.DatosListadoTopico;

import java.util.List;

public record DatosListadoUsuarioTopico(
        Long id,
        String nombre,
        List<DatosListadoTopico> topicos
) {
    public DatosListadoUsuarioTopico(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getTopicos().stream()
                .map(DatosListadoTopico::new)
                .toList());
    }
}
