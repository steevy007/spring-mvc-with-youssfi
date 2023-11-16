package com.steevelinformaticien.patientmvc.security.services;

import com.steevelinformaticien.patientmvc.security.entities.AppRole;
import com.steevelinformaticien.patientmvc.security.entities.AppUser;
import com.steevelinformaticien.patientmvc.security.repositories.AppRoleRepositorie;
import com.steevelinformaticien.patientmvc.security.repositories.AppUserRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AppUserRepositorie appUserRepositorie;
    private AppRoleRepositorie appRoleRepositorie;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email , String confirmPassword) {
        AppUser appUser=appUserRepositorie.findByUsername(username);
        if(appUser!=null) throw new RuntimeException("User already exist");
        if(!password.equalsIgnoreCase(confirmPassword))throw new RuntimeException("Password incorrect");
        appUser=AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        return appUserRepositorie.save(appUser);
    }

    @Override
    public AppRole addRole(String role) {
        AppRole appRole=appRoleRepositorie.findById(role).orElse(null);
        if(appRole!=null)throw new RuntimeException("Role already exist");
        appRole=AppRole.builder().role(role).build();

        return appRoleRepositorie.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppRole appRole=appRoleRepositorie.findByRole(role);
        AppUser appUser=appUserRepositorie.findByUsername(username);
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleToUser(String username, String role) {
        AppRole appRole=appRoleRepositorie.findByRole(role);
        AppUser appUser=appUserRepositorie.findByUsername(username);
        appUser.getRoles().remove(appRole);
    }
    @Override
    public AppUser loadUserByUsername(String username){
        return appUserRepositorie.findByUsername(username);
    }
}
