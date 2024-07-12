package challenge.alura.forohub.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByNombre(String username);

    // Encontrar todos los usuarios
    @Query("""
            SELECT u FROM Usuario u
            """)
    Page<Usuario> findAllUsuario(Pageable paginacion);

    // Encontrar solo los usuarios activos
    @Query("""
            SELECT u FROM Usuario u
            WHERE u.activo = TRUE
            """)
    Page<Usuario> findByUsuarioActivo(Pageable paginacion);

    // Encontrar solo los usuarios inactivos
    @Query("""
            SELECT u FROM Usuario u
            WHERE u.activo = FALSE
            """)
    Page<Usuario> findByUsuarioInactivo(Pageable paginacion);
}
