package com.informaticonfig.spring.app1.proyecto4.Repository;

import com.informaticonfig.spring.app1.proyecto4.Modelo.Evento;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Reserva;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    int countByEvento(Evento evento);

    boolean existsByUsuarioAndEvento(Usuario usuario, Evento evento);

    List<Reserva> findByUsuario(Usuario usuario);

    List<Reserva> findByUsuarioId(Long usuarioId);
}
