package com.informaticonfig.spring.app1.proyecto4.Mapper;


import com.informaticonfig.spring.app1.proyecto4.Dto.EventoDto;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Evento;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    public EventoDto toDto(Evento evento) {
        if (evento == null) {
            return null;
        }

        EventoDto dto = new EventoDto();
        dto.setId(evento.getId());
        dto.setNombre(evento.getNombre());
        dto.setLugar(evento.getLugar());
        dto.setFecha(evento.getFecha());

        return dto;
    }
}