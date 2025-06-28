package com.informaticonfig.spring.app1.proyecto3_springboot.Mapper;

import com.informaticonfig.spring.app1.proyecto3_springboot.Dto.PrestamoDto;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Libro;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Prestamo;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PrestamoMapper {
    public PrestamoDto toPrestamoDto(Prestamo prestamo) {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setId(prestamo.getId());
        prestamoDto.setFechaDePrestamo(prestamo.getFechaDeDevolucion());
        prestamoDto.setFechaDeDevolucion(prestamo.getFechaDeDevolucion());
        Usuario usuario = prestamo.getUsuario();
        if (usuario != null) {
            prestamoDto.setUsuarioId(usuario.getId());
            prestamoDto.setUsuarioNombre(usuario.getNombre() + " " + usuario.getApellido());
        }

        Libro libro = prestamo.getLibro();
        if (libro != null) {
            prestamoDto.setLibroId(libro.getId());
            prestamoDto.setLibroTitulo(libro.getTitulo());
        }

        return prestamoDto;
    }
}
