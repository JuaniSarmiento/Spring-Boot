package com.informaticonfig.spring.app1.modulo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private ALumnoRepository aLumnoRepository;

    @Autowired
    public AlumnoController(ALumnoRepository aLumnoRepository) {
        this.aLumnoRepository = aLumnoRepository;
    }
    @GetMapping
    public List<Alumno> getAlumnos() {
        return aLumnoRepository.findAll();
    }
    @PostMapping
    public Alumno createAlumno(@RequestBody Alumno alumno) {
        return aLumnoRepository.save(alumno);
    }
    @GetMapping("/{id}")
        public Optional<Alumno> getAlumno(@PathVariable Long id) {
            return aLumnoRepository.findById(id);
        }
}
