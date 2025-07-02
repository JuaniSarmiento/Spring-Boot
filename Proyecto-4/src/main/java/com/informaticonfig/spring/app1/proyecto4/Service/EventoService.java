package com.informaticonfig.spring.app1.proyecto4.Service;


import com.informaticonfig.spring.app1.proyecto4.Modelo.Evento;
import com.informaticonfig.spring.app1.proyecto4.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service // ¡Importantísimo! Le dice a Spring que esto es un componente de servicio.
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    /**
     * Devuelve una lista de todos los eventos.
     * @return Lista de eventos.
     */
    @Transactional(readOnly = true) // Como es una operación de solo lectura, es una buena práctica indicarlo.
    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    /**
     * Busca un evento por su ID.
     * @param id El ID del evento a buscar.
     * @return El evento encontrado.
     * @throws NoSuchElementException si no se encuentra el evento.
     */
    @Transactional(readOnly = true)
    public Evento obtenerEventoPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Evento no encontrado con ID: " + id));
    }

    /**
     * Guarda un nuevo evento en la base de datos.
     * @param evento El evento a crear.
     * @return El evento guardado con su ID.
     */
    @Transactional // Esta operación sí modifica la base de datos.
    public Evento crearEvento(Evento evento) {
        // Acá, en el futuro, podrías poner lógica de negocio.
        // Por ejemplo: verificar que la fecha del evento no sea en el pasado.
        // O que el nombre no esté repetido.
        return eventoRepository.save(evento);
    }

    // Acá podrías agregar métodos para actualizar y borrar eventos...
}