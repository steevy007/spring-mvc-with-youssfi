package com.steevelinformaticien.patientmvc.security.services;

import com.steevelinformaticien.patientmvc.security.entities.AppRole;
import com.steevelinformaticien.patientmvc.security.entities.AppUser;
import com.steevelinformaticien.patientmvc.security.repositories.AppUserRepositorie;

public interface AccountService {
    public AppUser addNewUser(String username,String password , String email ,String confirmPassword);
    public AppRole addRole(String role);

    public void addRoleToUser(String username,String role);

    public void removeRoleToUser(String username,String role);

    public AppUser loadUserByUsername(String username);
}
