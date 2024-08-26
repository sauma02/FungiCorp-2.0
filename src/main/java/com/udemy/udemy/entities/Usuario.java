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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nombre;
    private String usuario;
    private String password;
    private String correo;
    private String numero;

    public Usuario(String id, String nombre, String usuario, String password, String correo, String numero) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.numero = numero;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", password=" + password + ", correo=" + correo + ", numero=" + numero + '}';
    }
    
    
    
    
}
