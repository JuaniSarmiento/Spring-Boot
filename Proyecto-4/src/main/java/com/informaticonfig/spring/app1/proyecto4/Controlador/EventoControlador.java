package com.informaticonfig.spring.app1.proyecto4.Controlador;

import com.informaticonfig.spring.app1.proyecto4.Dto.EventoDto;
import com.informaticonfig.spring.app1.proyecto4.Mapper.EventoMapper;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Evento;
import com.informaticonfig.spring.app1.proyecto4.Repository.EventoRepository;
import com.informaticonfig.spring.app1.proyecto4.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "http://localhost:5173")
public class EventoControlador {
    private final EventoService eventoService;
    private final EventoMapper eventoMapper;
    @Autowired
    public EventoControlador(EventoService eventoService, EventoMapper eventoMapper) {
        this.eventoService = eventoService;
        this.eventoMapper = eventoMapper;
    }
    @GetMapping
    public ResponseEntity<List<EventoDto>> listarEventos() {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        // Usamos el mapper para convertir cada Evento a un EventoDto
        List<EventoDto> dtos = eventos.stream()
                .map(eventoMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventoDto> crearEvento(@RequestBody Evento evento) {
        Evento eventoGuardado = eventoService.crearEvento(evento);
        // Devolvemos el DTO del evento recién creado
        return new ResponseEntity<>(eventoMapper.toDto(eventoGuardado), HttpStatus.CREATED);
    }

}
