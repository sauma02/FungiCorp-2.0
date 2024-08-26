/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public String nombreRol;

    public Rol() {
    }

    public Rol(String id, String nombreRol) {
        this.id = id;
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", nombreRol=" + nombreRol + '}';
    }
    
    
}
