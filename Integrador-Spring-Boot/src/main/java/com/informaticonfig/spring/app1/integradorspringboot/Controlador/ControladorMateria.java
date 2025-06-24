package com.informaticonfig.spring.app1.integradorspringboot.Controlador;

import com.informaticonfig.spring.app1.integradorspringboot.Modelo.Materia;
import com.informaticonfig.spring.app1.integradorspringboot.Repositorio.RepositoryMateria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/materias")
public class ControladorMateria {
    private final RepositoryMateria repositoryMateria;

    @Autowired
    public ControladorMateria(RepositoryMateria repositoryMateria) {
        this.repositoryMateria = repositoryMateria;
    }

    @PostMapping
    public Materia crearMateria(@RequestBody Materia materiaGuadar) {
        return  repositoryMateria.save(materiaGuadar);
    }

    @GetMapping
    public List<Materia> listarMaterias() {
        return repositoryMateria.findAll();
    }

}
