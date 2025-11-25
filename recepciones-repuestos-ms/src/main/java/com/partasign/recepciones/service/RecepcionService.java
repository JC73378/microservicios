package com.partasign.recepciones.service;

import com.partasign.recepciones.model.Recepcion;
import com.partasign.recepciones.repository.RecepcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecepcionService {

    private final RecepcionRepository repository;

    public RecepcionService(RecepcionRepository repository) {
        this.repository = repository;
    }

    public List<Recepcion> findAll() {
        return repository.findAll();
    }

    public Optional<Recepcion> findById(Integer id) {
        return repository.findById(id);
    }

    public Recepcion save(Recepcion recepcion) {
        return repository.save(recepcion);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

