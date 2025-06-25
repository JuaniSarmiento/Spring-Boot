package com.informaticonfig.spring.app1.proyecto2springboot.Controlador;

import com.informaticonfig.spring.app1.proyecto2springboot.Modelo.Reseña;
import com.informaticonfig.spring.app1.proyecto2springboot.Modelo.Usuario;
import com.informaticonfig.spring.app1.proyecto2springboot.Repositorio.ReseñaRepository;
import com.informaticonfig.spring.app1.proyecto2springboot.Repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    private final UsuarioRepository usuarioRepository;
    private final ReseñaRepository reseñaRepository;

    @Autowired
    public UsuarioControlador(UsuarioRepository usuarioRepository, ReseñaRepository reseñaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.reseñaRepository = reseñaRepository;
    }
    @PostMapping
    public String crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario).toString();
    }
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    @GetMapping("/{idUsuario}/reseñas")
    public List<Reseña> getReseñasDeUsuario(@PathVariable Long idUsuario) {
        // 1. Buscamos el usuario por su ID usando el usuarioRepository.
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        // 2. Si lo encontramos, simplemente devolvemos su lista de reseñas.
        return usuario.getReseñas();
    }
}
