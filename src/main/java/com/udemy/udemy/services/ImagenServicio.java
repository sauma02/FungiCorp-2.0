/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.services;

import com.udemy.udemy.entities.Imagen;
import com.udemy.udemy.repositories.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class ImagenServicio {
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    
    public Imagen guardarImagen(Imagen imagen){
        imagenRepositorio.save(imagen);
        return imagen;
    }
    
}
