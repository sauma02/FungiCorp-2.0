/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Setter
@Getter
public class PasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String token;
    private String email;
    @OneToOne
    private Usuario usuario;
    private LocalDateTime expirationDate;

    public PasswordToken() {
        this.token = UUID.randomUUID().toString();
        this.email = email;
        this.expirationDate = calculateExpirationDate();
     
    }

    public PasswordToken(String id, String token, String email, Usuario usuario, LocalDateTime expirationDate) {
        this.token = token;
        this.email = email;
        this.usuario = usuario;
        this.expirationDate = expirationDate;
    }

    public PasswordToken(Usuario usuario) {
        this.usuario = usuario;
        this.email = email;
        this.token = UUID.randomUUID().toString();
        this.expirationDate = calculateExpirationDate();
    }
    private LocalDateTime calculateExpirationDate(){
        return LocalDateTime.now().plusDays(1); 
    }
    public boolean isExpired(LocalDateTime expirationDate){
        return expirationDate.isBefore(LocalDateTime.now());
    }
    
    
    
    
}
