package challenge.alura.forohub.domain.user;

import challenge.alura.forohub.domain.answer.Respuesta;
import challenge.alura.forohub.domain.topic.Topico;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private Boolean activo;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Topico> topicos;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Respuesta> respuestas;

    public Usuario(DatosRegistroUsuario usuario) {
        this.nombre = usuario.nombre();
        this.correo = usuario.correo();
        this.clave = usuario.clave();
        this.activo = true;
    }

    public void actualizarDatos(DatosRegistroUsuario datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.correo() != null) {
            this.correo = datos.correo();
        }
        if (datos.clave() != null) {
            this.clave = datos.clave();
        }
    }

    public void desactivarUsuario() {
        this.activo = false;
    }

    public void activarUsuario() {
        this.activo = true;
    }


    // Métodos de la interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
