package com.informaticonfig.spring.app1.proyecto3_springboot;

import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Libro;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Prestamo;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Usuario;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.LibroRepository;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.PrestamoRepository;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.UsuarioRepository;
import com.informaticonfig.spring.app1.proyecto3_springboot.Service.PrestamoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrestamoServiceTest {

    @Mock // 1. Creamos un doble de riesgo para LibroRepository
    private LibroRepository libroRepository;

    @Mock // 2. Y otro para UsuarioRepository
    private UsuarioRepository usuarioRepository;

    @Mock // 3. Y otro para PrestamoRepository
    private PrestamoRepository prestamoRepository;

    @InjectMocks // 4. Inyectamos estos dobles en nuestro servicio
    private PrestamoService prestamoService;

    @BeforeEach // 5. Esto se ejecuta antes de cada test
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa la magia de Mockito
    }

    @Test // 6. ¡Acá empieza la prueba!
    void testPrestarLibro_cuandoLibroEstaDisponible_deberiaRealizarPrestamo() {
        // --- PREPARACIÓN (Arrange) ---
        // 7. Creamos los datos falsos que vamos a usar
        Libro libroDisponible = new Libro();
        libroDisponible.setId(1L);
        libroDisponible.setTitulo("El Aleph");
        libroDisponible.setDisponible(true);

        Usuario usuario = new Usuario();
        usuario.setId(10L);
        usuario.setNombre("Juani");

        // 8. Le enseñamos a los dobles qué hacer
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libroDisponible));
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(usuario));
        when(prestamoRepository.save(any(Prestamo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // --- ACCIÓN (Act) ---
        // 9. Ejecutamos el método que queremos probar
        Prestamo resultado = prestamoService.prestarLibro(1L, 10L);

        // --- VERIFICACIÓN (Assert) ---
        // 10. Comprobamos que los resultados son los esperados
        assertNotNull(resultado);
        assertFalse(libroDisponible.isDisponible()); // El libro ahora debe estar no disponible
        assertEquals(libroDisponible, resultado.getLibro());
        assertEquals(usuario, resultado.getUsuario());

        // 11. Verificamos que los dobles fueron llamados como esperábamos
        verify(libroRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).findById(10L);
        verify(libroRepository, times(1)).save(libroDisponible);
        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    void testPrestarLibro_cuandoLibroNoEstaDisponible_deberiaLanzarExcepcion() {
        // --- PREPARACIÓN (Arrange) ---
        Libro libroNoDisponible = new Libro();
        libroNoDisponible.setId(2L);
        libroNoDisponible.setTitulo("Ficciones");
        libroNoDisponible.setDisponible(false);

        when(libroRepository.findById(2L)).thenReturn(Optional.of(libroNoDisponible));
        when(usuarioRepository.findById(10L)).thenReturn(Optional.of(new Usuario()));


        // --- ACCIÓN Y VERIFICACIÓN (Act & Assert) ---
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            prestamoService.prestarLibro(2L, 10L);
        });

        assertEquals("El libro Ficciones no esta disponible", exception.getMessage());

        // Verificamos que el método save NUNCA fue llamado
        verify(libroRepository, never()).save(any(Libro.class));
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }
}