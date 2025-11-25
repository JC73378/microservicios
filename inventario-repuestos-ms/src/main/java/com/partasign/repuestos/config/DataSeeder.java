package com.partasign.repuestos.config;

import com.github.javafaker.Faker;
import com.partasign.repuestos.model.Repuesto;
import com.partasign.repuestos.repository.RepuestoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final RepuestoRepository repository;
    private final Faker faker = new Faker(new Locale("es"));

    public DataSeeder(RepuestoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        for (int i = 0; i < 12; i++) {
            Repuesto r = new Repuesto();
            r.setCodigo365("RPT-" + faker.number().digits(5));
            r.setCodigoBarras(faker.number().digits(13));
            r.setNombre(faker.commerce().productName());
            r.setStockActual(ThreadLocalRandom.current().nextInt(0, 30));
            r.setUbicacion("Rack-" + faker.number().numberBetween(1, 8) + "-" + faker.letterify("??"));
            r.setCategoria(faker.commerce().department());
            repository.save(r);
        }
    }
}

