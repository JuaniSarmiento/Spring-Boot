package com.informaticonfig.spring.app1.proyecto3_springboot.Service;

import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Libro;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Prestamo;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Usuario;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.LibroRepository;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.PrestamoRepository;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;
    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository, UsuarioRepository usuarioRepository, LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
    }

    @Transactional
    public Prestamo prestarLibro(Long idlibro, Long idUsuario) {
        Libro libro = libroRepository.findById(idlibro).orElseThrow(()-> new RuntimeException("Libro no encontrado"));
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        if (!libro.isDisponible()){
            throw new RuntimeException("El libro "+ libro.getTitulo() + " no esta disponible");
        }

        Prestamo nuevoPrestamo= new Prestamo();
        nuevoPrestamo.setLibro(libro);
        nuevoPrestamo.setUsuario(usuario);
        nuevoPrestamo.setFechaDePrestamo(new Date());

        libro.setDisponible(false);
        libroRepository.save(libro);

        return prestamoRepository.save(nuevoPrestamo);
                
    }
    @Transactional
    public Prestamo devolverLibro(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + idPrestamo));

        prestamo.setFechaDeDevolucion(new Date());

        Libro libroDevuelto= prestamo.getLibro();
        libroDevuelto.setDisponible(true);
        libroRepository.save(libroDevuelto);

        return prestamoRepository.save(prestamo);


    }



}
