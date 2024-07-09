package challenge.alura.forohub.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByNombre(String username);

    @Query("""
            SELECT u FROM Usuario u
            """)
    Page<Usuario> findAllUsuario(Pageable paginacion);
}
