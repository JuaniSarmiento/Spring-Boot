package com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio;

import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
