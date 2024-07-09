package challenge.alura.forohub.domain.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MDUService {

    private final IUsuarioRepository usuarioRepository;
    
    @Autowired
    public MDUService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> resgistraUsuario(DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(datos);
        usuarioRepository.save(usuario);
        
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        
        return ResponseEntity.created(url).body(new DatosRespuestaUsuario(usuario));
    }

    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuario(Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAllUsuario(paginacion).map(DatosListadoUsuario::new));
    }

    public ResponseEntity<DatosListadoUsuario> retornaDatosUsuario(Long id) {
        return ResponseEntity.ok(new DatosListadoUsuario(usuarioRepository.getReferenceById(id)));
    }

    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(Long id, DatosRegistroUsuario datos) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.actualizarDatos(datos);

        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    @Transactional
    public ResponseEntity eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
