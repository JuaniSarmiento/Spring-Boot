package com.informaticonfig.spring.app1.proyecto4.Controlador;

import com.informaticonfig.spring.app1.proyecto4.Dto.CrearReservaRequest;
import com.informaticonfig.spring.app1.proyecto4.Dto.ReservaDto;
import com.informaticonfig.spring.app1.proyecto4.Mapper.ReservaMapper;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Reserva;
import com.informaticonfig.spring.app1.proyecto4.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservaControlador {
    private final ReservaService reservaService;
    private final ReservaMapper reservaMapper;

    @Autowired
    public ReservaControlador(ReservaService reservaService, ReservaMapper reservaMapper) {
        this.reservaService = reservaService;
        this.reservaMapper = reservaMapper;
    }

    @PostMapping
    public ResponseEntity<ReservaDto> crearReserva(@RequestBody CrearReservaRequest request) {
        Reserva reservaGuardada = reservaService.crearReserva(request.getUsuarioId(), request.getEventoId());

        // Convertimos la entidad a un DTO de RESPUESTA y lo devolvemos
        return new ResponseEntity<>(reservaMapper.toDto(reservaGuardada), HttpStatus.CREATED);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReservaDto>> obtenerReservasPorUsuario(@PathVariable Long usuarioId) {
        List<Reserva> reservas = reservaService.obtenerReservasPorUsuario(usuarioId);
        List<ReservaDto> dtos = reservas.stream()
                .map(reservaMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}

