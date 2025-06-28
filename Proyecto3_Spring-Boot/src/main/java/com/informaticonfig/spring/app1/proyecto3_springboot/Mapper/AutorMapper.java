package com.informaticonfig.spring.app1.proyecto3_springboot.Mapper;

import com.informaticonfig.spring.app1.proyecto3_springboot.Dto.AutorDto;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AutorMapper {
    private final LibroMapper libroMapper;

    @Autowired
    public AutorMapper(LibroMapper libroMapper) {
        this.libroMapper = libroMapper;
    }
    public AutorDto toDto(Autor autor) {
        AutorDto dto = new AutorDto();
        dto.setId(autor.getId());
        dto.setNombre(autor.getNombre());
        dto.setBiografia(autor.getBiografia());

        dto.setLibros(autor.getLibros().stream().map(libroMapper::toLibroDto).collect(Collectors.toList()));
        return dto;
    }
}
