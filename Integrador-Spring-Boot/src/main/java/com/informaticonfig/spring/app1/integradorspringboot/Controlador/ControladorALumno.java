package com.informaticonfig.spring.app1.integradorspringboot.Controlador;

import com.informaticonfig.spring.app1.integradorspringboot.Modelo.Alumno;
import com.informaticonfig.spring.app1.integradorspringboot.Repositorio.RepositoryAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/alumnos")
public class ControladorALumno {
    private final RepositoryAlumno repositoryAlumno;

    @Autowired
    public ControladorALumno(RepositoryAlumno repositoryAlumno) {
        this.repositoryAlumno = repositoryAlumno;
    }

    @PostMapping
    public Alumno crearAlumno(@RequestBody Alumno alumnoGuardar) {
       return repositoryAlumno.save(alumnoGuardar);
    }

    @GetMapping
    public List<Alumno> listarAlumnos() {
        return repositoryAlumno.findAll();
    }
}
