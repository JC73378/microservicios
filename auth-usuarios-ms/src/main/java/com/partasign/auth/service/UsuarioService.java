package com.partasign.auth.service;

import com.partasign.auth.model.Usuario;
import com.partasign.auth.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return repository.findById(id);
    }

    public Optional<Usuario> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

