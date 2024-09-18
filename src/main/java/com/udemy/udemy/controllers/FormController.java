/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.controllers;

import com.udemy.udemy.entities.Imagen;
import com.udemy.udemy.entities.Usuario;
import com.udemy.udemy.repositories.ImagenRepositorio;
import com.udemy.udemy.services.ImagenServicio;
import com.udemy.udemy.services.UsuarioServicio;
import com.udemy.udemy.utilidades.Utilidades;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author USUARIO
 */
@Controller
public class FormController {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Value("${valores.ruta}")
    private String ruta_upload;

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

    @GetMapping("/imagenesForm")
    public String imagenesForm(Model model) {

        model.addAttribute("imagen", new Imagen());
        return "subirArchivos";

    }

    @PostMapping("/imagenesForm")
    public String imagenes_post(@Valid Imagen imagen, BindingResult result, Model model,
            @RequestParam("archivoImagen") MultipartFile file, RedirectAttributes flash) {
        try {
            if (result.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                result.getFieldErrors().forEach(err -> {
                    errors.put(err.getField(),
                            "El campo".concat(err.getField()).concat("").concat(err.getDefaultMessage()));
                });
                model.addAttribute("errors", errors);
                model.addAttribute("imagen", imagen);
                return "subirArchivos";
            }
            if (file.isEmpty()) {
                flash.addAttribute("clase", "danger");
                flash.addAttribute("mensaje", "No se ha cargado ningun archivo jpg, png, jpeg");
                return "redirect:/imagenesForm";
            } else {
                String nombreImagen = Utilidades.guardarArchivo(file, this.ruta_upload + "images/");
                
                if (nombreImagen == "no") {
                    flash.addAttribute("clase", "danger");
                    flash.addAttribute("mensaje", "El formato no es valido, porfavor solo suba archivos JPG, PNG o JPEG");
                    return "redirect:/imagenesForm";
                }
                if (nombreImagen != null) {
                    imagen.setFileName(nombreImagen);
                    imagen.setFileType(file.getContentType());
                    imagen.setProducto(null);
                    imagen.setRuta(ruta_upload);
                    imagenServicio.guardarImagen(imagen);

                }

            }
            flash.addAttribute("clase", "success");
            flash.addAttribute("mensaje", "Exito al cargar archivo");
            return "/archivos_respuesta";

        } catch (Exception e) {
            e.printStackTrace();
            flash.addAttribute("clase", "danger");
            flash.addAttribute("mensaje", e.getMessage());
            return "redirect:/imagenesForm";
        }

    }
}
