package com.partasign.cl.partsasign_ms.Service;

import com.partasign.cl.partsasign_ms.Model.Repuesto;
import com.partasign.cl.partsasign_ms.Repository.RepuestosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RepuestosService {

    @Autowired
    private RepuestosRepository repuestosRepository;

    public List<Repuesto> findAll() {
        return repuestosRepository.findAll();
    }

    public Optional<Repuesto> findById(Integer id) {
        return repuestosRepository.findById(id);
    }

    public Repuesto save(Repuesto repuesto) {
        return repuestosRepository.save(repuesto);
    }

    public void deleteById(Integer id) {
        repuestosRepository.deleteById(id);
    }
}
