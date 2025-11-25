package com.partasign.repuestos.service;

import com.partasign.repuestos.model.Repuesto;
import com.partasign.repuestos.repository.RepuestoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RepuestoService {

    private final RepuestoRepository repository;

    public RepuestoService(RepuestoRepository repository) {
        this.repository = repository;
    }

    public List<Repuesto> findAll() {
        return repository.findAll();
    }

    public Optional<Repuesto> findById(Integer id) {
        return repository.findById(id);
    }

    public Repuesto save(Repuesto repuesto) {
        return repository.save(repuesto);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

