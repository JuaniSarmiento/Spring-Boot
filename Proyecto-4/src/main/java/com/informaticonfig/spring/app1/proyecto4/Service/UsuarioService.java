package com.informaticonfig.spring.app1.proyecto4.Service;

import com.informaticonfig.spring.app1.proyecto4.Modelo.Usuario;
import com.informaticonfig.spring.app1.proyecto4.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // ¡Importante!
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // El "triturador" de contraseñas

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Devuelve una lista de todos los usuarios.
     */
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Crea un nuevo usuario y encripta su contraseña.
     */
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        // ¡ACÁ ESTÁ LA MAGIA!
        // 1. Agarramos la contraseña que el usuario escribió (ej: "123456")
        String contraseñaPlana = usuario.getContraseña();

        // 2. La pasamos por el "triturador" para encriptarla
        String contraseñaHasheada = passwordEncoder.encode(contraseñaPlana);

        // 3. Guardamos la contraseña ya encriptada en el objeto usuario
        usuario.setContraseña(contraseñaHasheada);

        // 4. Guardamos el usuario en la base de datos con la contraseña segura
        return usuarioRepository.save(usuario);
    }
}