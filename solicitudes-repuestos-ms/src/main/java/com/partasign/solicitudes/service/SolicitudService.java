package com.partasign.solicitudes.service;

import com.partasign.solicitudes.model.Solicitud;
import com.partasign.solicitudes.repository.SolicitudRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SolicitudService {

    private final SolicitudRepository repository;

    public SolicitudService(SolicitudRepository repository) {
        this.repository = repository;
    }

    public List<Solicitud> findAll() {
        return repository.findAll();
    }

    public Optional<Solicitud> findById(Integer id) {
        return repository.findById(id);
    }

    public Solicitud save(Solicitud solicitud) {
        return repository.save(solicitud);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

