/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Entity
@Getter
@Setter
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name="Imagen_id")
    private String id;
    private String fileName;
    private String fileType;
    private String ruta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Producto_id")
    private Producto producto;

    public Imagen() {
    }

    public Imagen(String id, String fileName, String fileType, String ruta, Producto producto) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.ruta = ruta;
        this.producto = producto;
    }
    
    
}
