package com.informaticonfig.spring.app1.proyecto4.Repository;

import com.informaticonfig.spring.app1.proyecto4.Modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
