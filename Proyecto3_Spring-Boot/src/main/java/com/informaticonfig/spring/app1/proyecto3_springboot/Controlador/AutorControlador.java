package com.informaticonfig.spring.app1.proyecto3_springboot.Controlador;

import com.informaticonfig.spring.app1.proyecto3_springboot.Dto.AutorDto;
import com.informaticonfig.spring.app1.proyecto3_springboot.Mapper.AutorMapper;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Autor;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
public class AutorControlador {
    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;
    @Autowired
    public AutorControlador(AutorRepository autorRepository, AutorMapper autorMapper) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
    }
    @PostMapping
    public Autor crearAutores(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @GetMapping
    public List<AutorDto> listaAutores() {
        List<Autor> autores = autorRepository.findAll();

        return autores.stream().map(autorMapper::toDto).collect(Collectors.toList());
    }
    }

