package com.informaticonfig.spring.app1.proyecto4.Mapper;

import com.informaticonfig.spring.app1.proyecto4.Dto.UsuarioDto;
import com.informaticonfig.spring.app1.proyecto4.Modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());

        return dto;
    }
    public Usuario toEntity(UsuarioDto dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        // Ojo, no pasamos el ID porque lo genera la base de datos
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());

        // IMPORTANTE: La contraseña no la mapeamos acá,
        // porque el DTO de entrada no debería tenerla.
        // La vamos a manejar en el controlador o servicio.

        return usuario;
    }
}