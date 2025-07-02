package com.informaticonfig.spring.app1.proyecto4.Service;

import com.informaticonfig.spring.app1.proyecto4.Modelo.Evento;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Reserva;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Usuario;
import com.informaticonfig.spring.app1.proyecto4.Repository.EventoRepository;
import com.informaticonfig.spring.app1.proyecto4.Repository.ReservaRepository;
import com.informaticonfig.spring.app1.proyecto4.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservaService {
    private final EventoRepository eventoRepository;
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ReservaService(EventoRepository eventoRepository, ReservaRepository reservaRepository, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
    }
    @Transactional
    public Reserva crearReserva(Long usuarioId, Long eventoId) {
      Usuario usuario = usuarioRepository.findById(usuarioId)

              .orElseThrow(()-> new NoSuchElementException("No existe el usuario con el id: " + usuarioId));
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró un evento con el ID: " + eventoId));

        if (evento.getFecha().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("No se puede reservar en un evento que ya ha finalizado.");
        }

        int reservasActuales = reservaRepository.countByEvento(evento);
        if (reservasActuales >= evento.getCapacidad()) {
            throw new IllegalStateException("No quedan lugares disponibles para este evento.");
        }

        if (reservaRepository.existsByUsuarioAndEvento(usuario, evento)) {
            throw new IllegalStateException("Este usuario ya tiene una reserva para este evento.");
        }

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setUsuario(usuario);
        nuevaReserva.setEvento(evento);
        nuevaReserva.setFecha(LocalDate.now());

        return reservaRepository.save(nuevaReserva);

    }
    @Transactional
    public List<Reserva> obtenerReservasPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }
}
