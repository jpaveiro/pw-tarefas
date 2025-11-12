package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository,
                          PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Usuario registrar(Usuario u) {
        if (repository.existsByUsername(u.getUsername()))
            throw new RuntimeException("Username já registrado.");

        var senhaCifrada = encoder.encode(u.getPassword());
        u.setPassword(senhaCifrada);

        return repository.save(u);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado com username: " + username));
    }
}
