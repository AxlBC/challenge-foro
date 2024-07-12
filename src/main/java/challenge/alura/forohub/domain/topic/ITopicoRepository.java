package challenge.alura.forohub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico, Long> {

    // Devuelve una lista de todos los tópicos (resueltos/sin resolver)
    @Query("""
            SELECT t FROM Topico t
            ORDER BY t.fechaCreacion DESC
            """)
    Page<Topico> findAllTopic(Pageable paginacion);

    // Devuelve una lista de todos los tópicos resueltos
    @Query("""
            SELECT t FROM Topico t
            WHERE t.estado = FALSE
            ORDER BY t.fechaCreacion DESC
            """)
    Page<Topico> findTopicoByResuelto(Pageable paginacion);

    // Devuelve una lista de todos los tópicos sin resolver
    @Query("""
            SELECT t FROM Topico t
            WHERE t.estado = TRUE
            ORDER BY t.fechaCreacion DESC
            """)
    Page<Topico> findTopicoBySinResolver(Pageable paginacion);

    @Query("""
            SELECT t FROM Topico t
            WHERE t.usuario.activo = TRUE
            ORDER BY t.fechaCreacion DESC
            """)
    Page<Topico> findTopicoByUsuarioActivo(Pageable paginacion);

    @Modifying
    @Query("DELETE FROM Topico t WHERE t.id = :id")
    void deleteTopicoById(@Param("id") Long id);



//    @Query("""
//            SELECT t FROM Topico t
//            WHERE YEAR(t.fechaCreacion) = :anio
//            """)
//    Page<Topico> findAllTopicByYear(Pageable paginacion, int anio);
}
