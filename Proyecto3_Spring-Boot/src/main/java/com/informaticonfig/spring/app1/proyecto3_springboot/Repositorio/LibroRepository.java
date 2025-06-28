package com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio;

import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
