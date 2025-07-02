package com.informaticonfig.spring.app1.proyecto4.Mapper;

import com.informaticonfig.spring.app1.proyecto4.Dto.ReservaDto;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Reserva;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    public ReservaDto toDto(Reserva reserva) {
        if (reserva == null) return null;

        ReservaDto dto = new ReservaDto();
        dto.setId(reserva.getId());
        dto.setFechaDeReserva(reserva.getFecha()); // Uso .getFecha() porque así lo llamaste en tu entidad

        if (reserva.getUsuario() != null) {
            dto.setNombreUsuario(reserva.getUsuario().getNombre());
        }
        if (reserva.getEvento() != null) {
            dto.setNombreEvento(reserva.getEvento().getNombre());
        }
        return dto;
    }
}