package com.informaticonfig.spring.app1.proyecto3_springboot.Controlador;

import com.informaticonfig.spring.app1.proyecto3_springboot.Dto.LibroDto;
import com.informaticonfig.spring.app1.proyecto3_springboot.Mapper.LibroMapper;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Libro;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/libros")
public class LibroControlador {
    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;
    @Autowired
    public LibroControlador(LibroRepository libroRepository, LibroMapper libroMapper) {
        this.libroRepository = libroRepository;
        this.libroMapper = libroMapper;
    }
    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @GetMapping
    public List<LibroDto> listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        return libros.stream()
                .map(libroMapper::toLibroDto)
                .collect(Collectors.toList());
    }
}
