package challenge.alura.forohub.domain.user;

import challenge.alura.forohub.domain.topic.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
//    @OneToMany
//    private Perfil perfiles;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Topico> topics;

    public Usuario(DatosRegistroUsuario usuario) {
        this.nombre = usuario.nombre();
        this.correo = usuario.correo();
        this.contrasena = usuario.contrasena();
    }
}
