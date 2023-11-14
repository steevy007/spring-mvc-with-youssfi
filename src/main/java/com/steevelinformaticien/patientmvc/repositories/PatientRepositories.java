package com.steevelinformaticien.patientmvc.repositories;

import com.steevelinformaticien.patientmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositories extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContains (String keywords , Pageable pageable);
}
