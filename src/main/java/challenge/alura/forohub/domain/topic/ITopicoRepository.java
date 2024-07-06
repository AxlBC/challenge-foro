package challenge.alura.forohub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
            SELECT t FROM Topico t
            ORDER BY t.fechaCreacion ASC
            """)
    Page<Topico> findAllTopic(Pageable paginacion);

    @Modifying
    @Query("DELETE FROM Topico t WHERE t.id = :id")
    void deleteTopicoById(@Param("id") Long id);

//    @Query("""
//            SELECT t FROM Topico t
//            WHERE YEAR(t.fechaCreacion) = :anio
//            """)
//    Page<Topico> findAllTopicByYear(Pageable paginacion, int anio);
}
