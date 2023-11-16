package com.steevelinformaticien.patientmvc.security.repositories;

import com.steevelinformaticien.patientmvc.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepositorie extends JpaRepository<AppUser , String> {

    public AppUser findByUsername(String username);
}
