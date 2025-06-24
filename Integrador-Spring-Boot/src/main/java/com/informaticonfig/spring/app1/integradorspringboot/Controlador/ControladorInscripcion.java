package com.informaticonfig.spring.app1.integradorspringboot.Controlador;

import com.informaticonfig.spring.app1.integradorspringboot.Modelo.Materia;
import com.informaticonfig.spring.app1.integradorspringboot.Servicio.ServiceInscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorInscripcion {
    private final ServiceInscripcion serviceInscripcion;

    @Autowired
    public ControladorInscripcion(ServiceInscripcion serviceInscripcion) {
        this.serviceInscripcion = serviceInscripcion;
    }
    @PostMapping("/materias/{idMateria}/inscribir/{idAlumno}")
    public Materia inscribir(@PathVariable Long idMateria, @PathVariable Long idAlumno) {
        return serviceInscripcion.inscribirAlumno(idMateria, idAlumno);
    }
}
