package com.partasign.auth.config;

import com.partasign.auth.model.Usuario;
import com.partasign.auth.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository repository;

    public DataSeeder(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        repository.save(new Usuario(null, "admin", "admin123", "ADMIN", true));
        repository.save(new Usuario(null, "tecnico1", "tecnico123", "TECNICO", true));
        repository.save(new Usuario(null, "supervisor1", "super123", "SUPERVISOR", true));
    }
}

