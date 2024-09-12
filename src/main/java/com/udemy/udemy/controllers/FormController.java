/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.controllers;

import com.udemy.udemy.entities.Usuario;
import com.udemy.udemy.services.UsuarioServicio;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author USUARIO
 */
@Controller
public class FormController {
        @Autowired
    private UsuarioServicio usuarioServicio;
        
        
    @GetMapping("/registrar")
    public String validaciones_form(Usuario usuario, Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrarUsuario";
    }
    
    @PostMapping("/registrar")

    public String validaciones_post(@Valid Usuario usuario, BindingResult result, Model model) throws Exception {
        try {

            if (result.hasErrors()) {
                //Se crea un hasmap para darle valor a los errores y poder buscarlos de una manera mas efica<
                Map<String, String> errors = new HashMap<>();
                //Al resultado se le llama la funcion getFieldErrors y hasta un forEach con una expresion lambda para asignarle a cada error
                //su respectivo valor en string e ingresarlo dentro del mapa
                result.getFieldErrors()
                        .forEach(err -> {
                            errors.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
                        });
                model.addAttribute("errors", errors);
                model.addAttribute("usuario", usuario);
                return "registrarUsuario";
            }
            usuarioServicio.crearUsuario(usuario);
            return "valids";
        } catch (Exception e) {
            e.getCause();

            if (e.getCause() != null) {
                System.err.println("Error: " + e.getCause().getMessage());
            }
            if (result.hasErrors()) {
                //Se crea un hasmap para darle valor a los errores y poder buscarlos de una manera mas efica<
                Map<String, String> errors = new HashMap<>();
                //Al resultado se le llama la funcion getFieldErrors y hasta un forEach con una expresion lambda para asignarle a cada error
                //su respectivo valor en string e ingresarlo dentro del mapa
                result.getFieldErrors()
                        .forEach(err -> {
                            errors.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
                        });
                model.addAttribute("errors", errors);
                model.addAttribute("usuario", usuario);
                return "registrarUsuario";
            }
            
            return "registrarUsuario";

        }
    }
}
