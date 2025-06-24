package com.informaticonfig.spring.app1.integradorspringboot.Repositorio;

import com.informaticonfig.spring.app1.integradorspringboot.Modelo.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAlumno extends JpaRepository<Alumno, Long> {
}
