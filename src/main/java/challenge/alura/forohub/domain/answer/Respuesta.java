package challenge.alura.forohub.domain.answer;

import challenge.alura.forohub.domain.topic.Topico;
import challenge.alura.forohub.domain.user.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String solucion;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Respuesta(String titulo, String solucion, LocalDateTime fechaCreacion, Topico topico, Usuario usuario) {
        this.titulo = titulo;
        this.solucion = solucion;
        this.fechaCreacion = fechaCreacion;
        this.topico = topico;
        this.usuario = usuario;
    }

    public void actualizarDatos(DatosActualizaRespuesta datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.solucion() != null) {
            this.solucion = datos.solucion();
        }
    }
}
