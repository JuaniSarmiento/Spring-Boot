package com.informaticonfig.spring.app1.proyecto3_springboot.Controlador;

import com.informaticonfig.spring.app1.proyecto3_springboot.Dto.PrestamoDto;
import com.informaticonfig.spring.app1.proyecto3_springboot.Mapper.PrestamoMapper;
import com.informaticonfig.spring.app1.proyecto3_springboot.Modelo.Prestamo;
import com.informaticonfig.spring.app1.proyecto3_springboot.Repositorio.PrestamoRepository;
import com.informaticonfig.spring.app1.proyecto3_springboot.Service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoControlador {
    private final PrestamoService prestamoService;
    private final PrestamoMapper prestamoMapper; //


    @Autowired
    public PrestamoControlador(PrestamoService prestamoService, PrestamoMapper prestamoMapper) {
        this.prestamoService = prestamoService;
        this.prestamoMapper = prestamoMapper;
    }


    @PostMapping("/prestar/libro/{idLibro}/usuario/{idUsuario}")
    public PrestamoDto prestar(@PathVariable Long idLibro, @PathVariable Long idUsuario) {
        Prestamo prestamoRealizado = prestamoService.prestarLibro(idLibro, idUsuario);

        return prestamoMapper.toPrestamoDto(prestamoRealizado);
    }

    @PostMapping("/{idPrestamo}/devolver")
    public PrestamoDto devolver(@PathVariable Long idPrestamo) {
        Prestamo prestamoDevuelto = prestamoService.devolverLibro(idPrestamo);
        return prestamoMapper.toPrestamoDto(prestamoDevuelto);


    }
}