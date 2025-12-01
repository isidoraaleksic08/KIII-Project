package com.example.lab1emt.model.enumerations;
import org.springframework.security.core.GrantedAuthority;
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_Librarian;

    @Override
    public String getAuthority() {
        return name();
    }
}
