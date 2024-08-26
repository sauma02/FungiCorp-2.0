/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nombreProducto;
    private String descripcion;
    private List<Imagen> imagenes;

    public Producto(String id, String nombreProducto, String descripcion, List<Imagen> imagenes) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
    }

    public Producto() {
    }
    
    
    
}
