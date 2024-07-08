package challenge.alura.forohub.domain.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ICursoRepository extends JpaRepository<Curso, Long> {
    @Query("""
            SELECT c FROM Curso c
            ORDER BY c.nombre ASC
            """)
    Page<Curso> findAllCurso(Pageable paginacion);

    @Modifying
    @Query("""
            DELETE FROM Curso c WHERE c.id = :id
            """)
    void deleteCursoById(Long id);
}
