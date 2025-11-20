package com.partasign.cl.partasign_ot_ms;

import com.github.javafaker.Faker;
import com.partasign.cl.partasign_ot_ms.Model.OrdenTrabajoProgramada;
import com.partasign.cl.partasign_ot_ms.Repository.OtProgramadaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Component
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
            OrdenTrabajoProgramada ot = new OrdenTrabajoProgramada();
            ot.setNombreTecnico(faker.name().firstName());
            ot.setApellidoTecnico(faker.name().lastName());
            ot.setRutTecnico(faker.number().digits(8) + "-" + faker.number().digits(1));
            ot.setNumeroEquipo(String.valueOf(faker.number().numberBetween(1000, 9999)));
            ot.setFechaFirma(LocalDate.now().minusDays(faker.number().numberBetween(1, 20)));
            ot.setRepuestoId("REP-" + faker.number().digits(5));
            repository.save(ot);
        }
    }
}
