package com.partasign.recepciones.config;

import com.github.javafaker.Faker;
import com.partasign.recepciones.model.Recepcion;
import com.partasign.recepciones.repository.RecepcionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final RecepcionRepository repository;
    private final Faker faker = new Faker(new Locale("es"));

    public DataSeeder(RecepcionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        for (int i = 0; i < 8; i++) {
            Recepcion r = new Recepcion();
            r.setRepuestoId("RPT-" + faker.number().digits(5));
            r.setCantidadRecibida(ThreadLocalRandom.current().nextInt(1, 15));
            r.setRecibidoPor(faker.name().fullName());
            r.setFechaRecepcion(LocalDate.now().minusDays(faker.number().numberBetween(1, 10)));
            r.setObservacion(faker.lorem().sentence());
            repository.save(r);
        }
    }
}

