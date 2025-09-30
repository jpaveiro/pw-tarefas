package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario registrar(Usuario u) {
        if (repository.existsByUsername(u.getUsername()))
            throw new RuntimeException("Username já registrado.");

        return repository.save(u);
    }
}
