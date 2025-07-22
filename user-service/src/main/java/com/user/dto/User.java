package com.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String name;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String role; // USER or ADMIN
    
    public User(String userId, String name, String email, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

}
