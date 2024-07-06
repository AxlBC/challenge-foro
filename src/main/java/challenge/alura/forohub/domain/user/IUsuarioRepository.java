package challenge.alura.forohub.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
