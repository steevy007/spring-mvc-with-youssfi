package com.steevelinformaticien.patientmvc;

import com.steevelinformaticien.patientmvc.entities.Patient;
import com.steevelinformaticien.patientmvc.repositories.PatientRepositories;
import com.steevelinformaticien.patientmvc.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    //@Bean
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

    //@Bean
    public CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder = passwordEncoder();

        return args -> {

            UserDetails user1=jdbcUserDetailsManager.loadUserByUsername("steeve");
            UserDetails user2=jdbcUserDetailsManager.loadUserByUsername("steeve1");
            UserDetails user3=jdbcUserDetailsManager.loadUserByUsername("admin");

            if(user1==null)jdbcUserDetailsManager.createUser(User.withUsername("steeve").password(passwordEncoder.encode("1234")).roles("USER").build());
            if(user2==null)jdbcUserDetailsManager.createUser(User.withUsername("steeve1").password(passwordEncoder.encode("1234")).roles("USER").build());
            if(user3==null)jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build());

        };
    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetail(AccountService service){
        return args->{
            service.addRole("USER");
            service.addRole("ADMIN");

            service.addNewUser("steeve","1234","sanon@gmail.com","1234");
            service.addNewUser("steeve1","1234","sanon1@gmail.com","1234");
            service.addNewUser("admin","1234","admin@gmail.com","1234");

            service.addRoleToUser("steeve","USER");
            service.addRoleToUser("steeve1","USER");
            service.addRoleToUser("admin","USER");
            service.addRoleToUser("admin","ADMIN");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
