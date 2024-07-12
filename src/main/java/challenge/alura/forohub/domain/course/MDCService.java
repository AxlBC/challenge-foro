package challenge.alura.forohub.domain.course;

import challenge.alura.forohub.infra.errores.ValidacionDeIntegridad;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MDCService { // (MDCService = Manejo de Datos Curso Service)
    private final ICursoRepository cursoRepository;

    @Autowired
    public MDCService(ICursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public DatosRespuestaCurso registrarCurso(DatosRegistroCurso datos) {
        var curso = new Curso(datos);
        cursoRepository.save(curso);
        return new DatosRespuestaCurso(curso);
    }

    public ResponseEntity<Page<DatosListadoCurso>> listadoCursoActivos(Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findAllCursoActivo(paginacion).map(DatosListadoCurso::new));
    }

    public ResponseEntity<Page<DatosListadoCurso>> listadoCursoInactivos(Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findAllCursoInactivo(paginacion).map(DatosListadoCurso::new));
    }

    public ResponseEntity<DatosRespuestaCurso> retornarDatosCurso(Long id) {
        return ResponseEntity.ok(new DatosRespuestaCurso(cursoRepository.getReferenceById(id)));
    }

    @Transactional
    public DatosRespuestaCurso actualizarCurso(Long id, DatosActualizaCurso datos) {
        if (!cursoRepository.findById(id).isPresent()) {
            throw new ValidacionDeIntegridad("No se ha encontrado ningún curso con ese id.");
        }

        var curso = cursoRepository.getReferenceById(id);
        curso.actualizarCurso(datos);
        return new DatosRespuestaCurso(curso);
    }

    @Transactional
    public ResponseEntity eliminarCurso(Long id) {
        var curso = cursoRepository.getReferenceById(id);
        curso.desactivarCurso();
        return ResponseEntity.noContent().build();
    }
}
