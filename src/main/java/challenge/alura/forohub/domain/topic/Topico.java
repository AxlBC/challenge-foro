package challenge.alura.forohub.domain.topic;

import challenge.alura.forohub.domain.answer.Respuesta;
import challenge.alura.forohub.domain.course.Curso;
import challenge.alura.forohub.domain.user.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; //autor
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Respuesta> respuestas;


    public Topico(String titulo, String mensaje, LocalDateTime fecha, Usuario usuario, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fecha;
        this.usuario = usuario;
        this.curso = curso;
        this.estado = true;
    }

    public void actualizarDatos(DatosActualizaTopico datos, Curso curso) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.estado() != null) {
            this.estado = datos.estado();
        }
        if (curso != null) {
            this.curso = curso;
        }
    }
}
