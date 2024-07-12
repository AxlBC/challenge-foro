package challenge.alura.forohub.domain.user;

import challenge.alura.forohub.domain.topic.ITopicoRepository;
import challenge.alura.forohub.domain.topic.Topico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MDUService {

    private final IUsuarioRepository usuarioRepository;
    private final ITopicoRepository topicoRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public MDUService(IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, ITopicoRepository topicoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> resgistraUsuario(DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario(datos);
        usuario.setClave(passwordEncoder.encode(datos.clave()));
        usuarioRepository.save(usuario);
        
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        
        return ResponseEntity.created(url).body(new DatosRespuestaUsuario(usuario));
    }

    public ResponseEntity<Page<DatosListadoUsuario>> listadoTodoUsuario(Pageable paginacion) {
//        return ResponseEntity.ok(usuarioRepository.findAllUsuario(paginacion).map(DatosListadoUsuario::new));
        return ResponseEntity.ok(usuarioRepository.findAllUsuario(paginacion).map(DatosListadoUsuario::new));
    }

    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarioActivo(Pageable paginacion) {
//        return ResponseEntity.ok(usuarioRepository.findAllUsuario(paginacion).map(DatosListadoUsuario::new));
        return ResponseEntity.ok(usuarioRepository.findByUsuarioActivo(paginacion).map(DatosListadoUsuario::new));
    }

    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarioInactivo(Pageable paginacion) {
//        return ResponseEntity.ok(usuarioRepository.findAllUsuario(paginacion).map(DatosListadoUsuario::new));
        return ResponseEntity.ok(usuarioRepository.findByUsuarioInactivo(paginacion).map(DatosListadoUsuario::new));
    }

    public ResponseEntity<DatosListadoUsuario> retornaDatosUsuario(Long id) {
        return ResponseEntity.ok(new DatosListadoUsuario(usuarioRepository.getReferenceById(id)));
    }

    public ResponseEntity<DatosListadoUsuarioTopico> retornaDatosUsuarioTopico(Long id) {
        return ResponseEntity.ok(new DatosListadoUsuarioTopico(usuarioRepository.getReferenceById(id)));
    }

    public ResponseEntity<DatosListadoUsuarioRespuesta> retornaDatosUsuarioRespuesta(Long id) {
        return ResponseEntity.ok(new DatosListadoUsuarioRespuesta(usuarioRepository.getReferenceById(id)));
    }

    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(Long id, DatosRegistroUsuario datos) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.actualizarDatos(datos);

        if (datos.clave() != null) {
            usuario.setClave(passwordEncoder.encode(datos.clave()));
        }

        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    @Transactional
    public ResponseEntity eliminarUsuario(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }
}
