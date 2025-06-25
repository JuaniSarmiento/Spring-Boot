package com.informaticonfig.spring.app1.proyecto2springboot.Repositorio;

import com.informaticonfig.spring.app1.proyecto2springboot.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
