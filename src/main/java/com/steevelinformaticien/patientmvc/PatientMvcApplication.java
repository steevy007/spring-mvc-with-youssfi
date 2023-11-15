package com.steevelinformaticien.patientmvc;

import com.steevelinformaticien.patientmvc.entities.Patient;
import com.steevelinformaticien.patientmvc.repositories.PatientRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepositories patientRepositories) {
        return args -> {
           /* patientRepositories.save(new Patient(null, "Sanon Steeve", new Date(), false, 10));

            patientRepositories.save(new Patient(null, "Laurent Esmer", new Date(), true, 20));

            patientRepositories.save(new Patient(null, "Sanon Jean Pesnel", new Date(), false, 30));


            patientRepositories.save(new Patient(null, "Merisca Lenicia", new Date(), false, 40));

            patientRepositories.save(new Patient(null, "Lyam Mael Pierre", new Date(), false, 50));
            patientRepositories.save(new Patient(null, "Gassant Erns", new Date(), true, 60));


            patientRepositories.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
            });*/

        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
