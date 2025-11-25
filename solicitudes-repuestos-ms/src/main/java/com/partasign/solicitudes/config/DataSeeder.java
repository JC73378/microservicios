package com.partasign.solicitudes.config;

import com.github.javafaker.Faker;
import com.partasign.solicitudes.model.Solicitud;
import com.partasign.solicitudes.repository.SolicitudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final SolicitudRepository repository;
    private final Faker faker = new Faker(new Locale("es"));

    public DataSeeder(SolicitudRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        for (int i = 0; i < 10; i++) {
            Solicitud s = new Solicitud();
            s.setCodigo("COD-" + faker.number().digits(5));
            s.setTipo(faker.options().option("Eléctrico", "Mecánico", "Hidráulico", "Neumático", "Otros"));
            s.setCantidad(ThreadLocalRandom.current().nextInt(1, 10));
            s.setDescripcion(faker.lorem().sentence());
            s.setNombreTecnico(faker.name().fullName());
            s.setFecha(LocalDate.now().minusDays(faker.number().numberBetween(0, 30)));
            repository.save(s);
        }
    }
}

