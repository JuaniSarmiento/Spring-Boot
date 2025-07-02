package com.informaticonfig.spring.app1.proyecto4.Controlador;

import com.informaticonfig.spring.app1.proyecto4.Dto.UsuarioDto;
import com.informaticonfig.spring.app1.proyecto4.Mapper.UsuarioMapper;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Usuario;
import com.informaticonfig.spring.app1.proyecto4.Repository.UsuarioRepository;
import com.informaticonfig.spring.app1.proyecto4.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioControlador {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioControlador(UsuarioRepository usuarioRepository, UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }



    @PostMapping
    public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody Usuario usuario) {
        // 1. Le pasamos la entidad al servicio para que la cree (y hashee la contraseña)
        Usuario usuarioCreado = usuarioService.crearUsuario(usuario);

        // 2. Convertimos la entidad ya creada (con ID y todo) de nuevo a un DTO para la respuesta
        UsuarioDto dtoDeRespuesta = usuarioMapper.toDto(usuarioCreado);

        // 3. Devolvemos una respuesta prolija con el código de estado correcto
        return new ResponseEntity<>(dtoDeRespuesta, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        List<UsuarioDto> dtos = usuarios.stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
