package com.partasign.cl.partsasign_ms;

import com.github.javafaker.Faker;
import com.partasign.cl.partsasign_ms.Model.Repuesto;
import com.partasign.cl.partsasign_ms.Repository.RepuestosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RepuestosRepository repuestosRepository;
    private final Faker faker = new Faker(new Locale("es"));

    public DataSeeder(RepuestosRepository repuestosRepository) {
        this.repuestosRepository = repuestosRepository;
    }

    @Override
    public void run(String... args) {
        if (repuestosRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 12; i++) {
            Repuesto repuesto = new Repuesto();
            repuesto.setCodigo365("P-" + faker.number().digits(6));
            repuesto.setNombre(faker.commerce().productName());
            repuesto.setCodigoBarras(faker.number().digits(13));
            repuesto.setStockActual(ThreadLocalRandom.current().nextInt(1, 50));
            repuesto.setUbicacion("Rack-" + faker.number().numberBetween(1, 8) + "-" + faker.letterify("??"));
            repuesto.setCategoria(faker.commerce().department());

            repuestosRepository.save(repuesto);
        }
    }
}
