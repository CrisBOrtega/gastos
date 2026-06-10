package com.example.prototype.datafetchers;

import com.example.prototype.entities.Usuario;
import com.example.prototype.services.UsuarioService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

@DgsComponent
public class UsuarioDataFetcher {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioDataFetcher(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @DgsQuery
    public Iterable<Usuario> usuarios() {
        return usuarioService.findAll();
    }

    @DgsQuery
    public Usuario usuario(@InputArgument String id) {
        return usuarioService.findById(Long.parseLong(id)).orElse(null);
    }

    @DgsMutation
    public Usuario createUsuario(@InputArgument Map<String, String> input) {
        Usuario usuario = new Usuario();
        usuario.setUsername(input.get("username"));
        usuario.setEmail(input.get("email"));
        usuario.setPassword(input.get("password"));
        return usuarioService.save(usuario);
    }

    @DgsMutation
    public Usuario updateUsuario(@InputArgument String id, @InputArgument Map<String, String> input) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(Long.parseLong(id));
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setUsername(input.get("username"));
            usuario.setEmail(input.get("email"));
            usuario.setPassword(input.get("password"));
            return usuarioService.save(usuario);
        }
        return null;
    }

    @DgsMutation
    public boolean deleteUsuario(@InputArgument String id) {
        Long parsedId = Long.parseLong(id);
        if (usuarioService.findById(parsedId).isPresent()) {
            usuarioService.deleteById(parsedId);
            return true;
        }
        return false;
    }
}
