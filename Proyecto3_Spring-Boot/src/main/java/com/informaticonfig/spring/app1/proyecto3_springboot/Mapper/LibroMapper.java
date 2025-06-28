package com.informaticonfig.spring.app1.proyecto3_springboot.Mapper;

import com.informaticonfig.spring.app1.proyecto3_springboot.Dto.LibroDto;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {
    public LibroDto toLibroDto(Libro libro) {
        LibroDto dto = new LibroDto();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setIsbn(libro.getIsbn());
        dto.setDisponible(libro.isDisponible());
        if (libro.getAutor() != null) {
            dto.setNombreAutor(libro.getAutor().getNombre());
        }
        return dto;
    }
}
