package challenge.alura.forohub.domain.answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRespuestaRepository extends JpaRepository<Respuesta, Long> {
    @Query("""
            SELECT r FROM Respuesta r
            ORDER BY r.fechaCreacion DESC
            """)
    Page<Respuesta> findAllRespuesta(Pageable paginacion);

    @Modifying
    @Query("""
            DELETE FROM Respuesta r Where r.id = :id
            """)
    void deleteRespuestaById(Long id);
}
