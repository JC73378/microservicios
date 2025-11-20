package com.partasign.cl.partasign_ot_ms.Service;

import com.partasign.cl.partasign_ot_ms.Model.OrdenTrabajoProgramada;
import com.partasign.cl.partasign_ot_ms.Repository.OtProgramadaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OtProgramadaService {

    @Autowired
    private OtProgramadaRepository repository;

    public List<OrdenTrabajoProgramada> findAll() {
        return repository.findAll();
    }

    public Optional<OrdenTrabajoProgramada> findById(Integer id) {
        return repository.findById(id);
    }

    public OrdenTrabajoProgramada save(OrdenTrabajoProgramada ot) {
        return repository.save(ot);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
