package com.informaticonfig.spring.app1.proyecto2springboot.Repositorio;

import com.informaticonfig.spring.app1.proyecto2springboot.Modelo.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseñaRepository extends JpaRepository<Reseña, Long> {
}
