package com.informaticonfig.spring.app1.integradorspringboot.Servicio;

import com.informaticonfig.spring.app1.integradorspringboot.Modelo.Alumno;
import com.informaticonfig.spring.app1.integradorspringboot.Modelo.Materia;
import com.informaticonfig.spring.app1.integradorspringboot.Repositorio.RepositoryAlumno;
import com.informaticonfig.spring.app1.integradorspringboot.Repositorio.RepositoryMateria;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceInscripcion {
    private final RepositoryAlumno repositorioAlumno;
    private final RepositoryMateria repositorioMateria;

    @Autowired
    public ServiceInscripcion(RepositoryAlumno repositorioAlumno, RepositoryMateria repositorioMateria) {
        this.repositorioAlumno = repositorioAlumno;
        this.repositorioMateria = repositorioMateria;
    }

    @Transactional
    public Materia inscribirAlumno(Long idMateria, Long idAlumno) {
        Materia materia = repositorioMateria.findById(idMateria)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada")); // Podrías usar tu RecursoNoEncontradoException

        Alumno alumno = repositorioAlumno.findById(idAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        if (materia.getAlumnos().size() >= materia.getCupo()) {
            throw new RuntimeException("No hay cupos disponibles para esta materia.");
        }

        if (materia.getAlumnos().contains(alumno)) {
            throw new RuntimeException("El alumno ya está inscripto en esta materia.");
        }

        materia.getAlumnos().add(alumno);

        return repositorioMateria.save(materia);
    }
}

