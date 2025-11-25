package com.partasign.ot.service;

import com.partasign.ot.model.OtProgramada;
import com.partasign.ot.repository.OtProgramadaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OtProgramadaService {

    private final OtProgramadaRepository repository;

    public OtProgramadaService(OtProgramadaRepository repository) {
        this.repository = repository;
    }

    public List<OtProgramada> findAll() {
        return repository.findAll();
    }

    public Optional<OtProgramada> findById(Integer id) {
        return repository.findById(id);
    }

    public OtProgramada save(OtProgramada ot) {
        return repository.save(ot);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

