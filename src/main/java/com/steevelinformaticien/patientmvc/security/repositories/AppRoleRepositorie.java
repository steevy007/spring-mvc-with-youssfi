package com.steevelinformaticien.patientmvc.security.repositories;

import com.steevelinformaticien.patientmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepositorie extends JpaRepository<AppRole , String> {
    public AppRole findByRole(String role);
}
