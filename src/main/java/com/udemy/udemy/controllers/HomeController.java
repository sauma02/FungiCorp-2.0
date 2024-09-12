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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Admin
 */
@Controller
public class HomeController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/ajax")
    public String ajax(Model model) {

        return "pruebaAjax";
    }

    @GetMapping("/peticion")
    public String peticion(Model model, @RequestParam("valor") String valor) {
        model.addAttribute("valor", valor);
        return "peticion";
    }

    @GetMapping("/fancybox")
    public String fancybox(Model model) {
        return "fancybox";
    }

    
   
    @GetMapping("/selecDinamico")
    public String selectDinamico(Model model,Usuario usuario){
        model.addAttribute("usuario", new Usuario());
        return "selecDinamico";
    }
}
