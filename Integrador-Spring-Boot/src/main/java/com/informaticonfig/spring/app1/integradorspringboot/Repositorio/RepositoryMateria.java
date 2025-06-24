package com.informaticonfig.spring.app1.integradorspringboot.Repositorio;

import com.informaticonfig.spring.app1.integradorspringboot.Modelo.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMateria extends JpaRepository<Materia, Long> {
}
