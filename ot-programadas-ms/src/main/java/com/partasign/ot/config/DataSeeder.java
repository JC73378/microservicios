package com.partasign.ot.config;

import com.github.javafaker.Faker;
import com.partasign.ot.model.OtProgramada;
import com.partasign.ot.repository.OtProgramadaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final OtProgramadaRepository repository;
    private final Faker faker = new Faker(new Locale("es"));

    public DataSeeder(OtProgramadaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        for (int i = 0; i < 8; i++) {
            OtProgramada ot = new OtProgramada();
            ot.setNombreTecnico(faker.name().firstName());
            ot.setApellidoTecnico(faker.name().lastName());
            ot.setRutTecnico(faker.number().digits(8) + "-" + faker.number().digits(1));
            ot.setNumeroEquipo(String.valueOf(faker.number().numberBetween(1000, 9999)));
            ot.setFechaFirma(LocalDate.now().minusDays(faker.number().numberBetween(1, 15)));
            ot.setRepuestoId("RPT-" + faker.number().digits(5));
            repository.save(ot);
        }
    }
}

