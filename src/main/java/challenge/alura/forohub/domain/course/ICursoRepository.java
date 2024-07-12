package challenge.alura.forohub.domain.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ICursoRepository extends JpaRepository<Curso, Long> {

    // Seleccionar todos los cursos (Activos o inactivos)
    @Query("""
            SELECT c FROM Curso c
            ORDER BY c.nombre ASC
            """)
    Page<Curso> findAllCurso(Pageable paginacion);

    // Seleccionar solo los cursos activos
    @Query("""
            SELECT c FROM Curso c
            WHERE c.activo = TRUE
            """)
    Page<Curso> findAllCursoActivo(Pageable paginacion);

    // Seleccionar solo los cursos activos
    @Query("""
            SELECT c FROM Curso c
            WHERE c.activo = FALSE
            """)
    Page<Curso> findAllCursoInactivo(Pageable paginacion);

    @Modifying
    @Query("""
            DELETE FROM Curso c WHERE c.id = :id
            """)
    void deleteCursoById(Long id);

}
