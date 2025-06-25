package com.informaticonfig.spring.app1.proyecto2springboot.Service;


import com.informaticonfig.spring.app1.proyecto2springboot.Modelo.Pelicula;
import com.informaticonfig.spring.app1.proyecto2springboot.Modelo.Reseña;
import com.informaticonfig.spring.app1.proyecto2springboot.Repositorio.PeliculaRepository;
import com.informaticonfig.spring.app1.proyecto2springboot.Repositorio.ReseñaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReseñaService {

    private final PeliculaRepository peliculaRepository;
    private final ReseñaRepository reseñaRepository;

    @Autowired
    public ReseñaService(PeliculaRepository peliculaRepository, ReseñaRepository reseñaRepository) {
        this.peliculaRepository = peliculaRepository;
        this.reseñaRepository = reseñaRepository;
    }

    public Reseña crearReseñaParaPelicula(Long idPelicula, Reseña reseña) {
        // 1. Buscamos la película a la que pertenece la reseña.
        Pelicula pelicula = peliculaRepository.findById(idPelicula)
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));

        // 2. Le asignamos la película a la reseña. Esto establece la relación.
        reseña.setPelicula(pelicula);
        // (Acá también iría la lógica para asociar el usuario logueado)

        // 3. ¡Guardamos la RESEÑA! Esto la inserta en la base de datos y le genera un ID.
        return reseñaRepository.save(reseña);
    }
}