package com.informaticonfig.spring.app1.proyecto2springboot.Controlador;

import com.informaticonfig.spring.app1.proyecto2springboot.Modelo.Pelicula;
import com.informaticonfig.spring.app1.proyecto2springboot.Modelo.Reseña;
import com.informaticonfig.spring.app1.proyecto2springboot.Repositorio.PeliculaRepository;
import com.informaticonfig.spring.app1.proyecto2springboot.Repositorio.ReseñaRepository;
import com.informaticonfig.spring.app1.proyecto2springboot.Service.ReseñaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/peliculas")
public class PeliculaControlador {
    private final PeliculaRepository peliculaRepository;
    private final ReseñaRepository reseñaRepository;
    private final ReseñaService reseñaService;

    @Autowired
    public PeliculaControlador(PeliculaRepository peliculaRepository, ReseñaRepository reseñaRepository, ReseñaService reseñaService) {
        this.peliculaRepository = peliculaRepository;
        this.reseñaRepository = reseñaRepository;
        this.reseñaService = reseñaService;
    }

    @PostMapping
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }
    @GetMapping
    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }

    @PostMapping("/{idPelicula}/reseñas")
    public Reseña crearReseña(@PathVariable Long idPelicula, @RequestBody Reseña reseña){
        return reseñaService.crearReseñaParaPelicula(idPelicula, reseña);
    }
    @GetMapping("/{idPelicula}/reseñas")
    public List<Reseña> getReseñasDePelicula(@PathVariable Long idPelicula){
        Pelicula pelicula = peliculaRepository.findById(idPelicula).orElseThrow();
        return pelicula.getReseñas();
    }
}
