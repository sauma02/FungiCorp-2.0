/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.utilidades;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class Utilidades {
    public static String guardarArchivo(MultipartFile file, String ruta){
        if(Utilidades.validaImagen(file.getContentType()) == false){
            return "no";
        }else{
            //Se crea una variable para obtener el tiempo y asignarle el nombre a todos los archivos almacenados
            //de esta manera ahorrando tiempo y renombrando cada imagen
            long time = System.currentTimeMillis();
            String nombre = time + Utilidades.getExtension(file.getContentType());
            try {
                File imageFile = new File(ruta+nombre);
                file.transferTo(imageFile);
                return nombre;
            } catch (IOException e) {
                return "Error al subir el archivo";
            }

        }
    }
    public static boolean validaImagen(String mime){
        boolean retorno = false;
        
        switch(mime){
            case "image/jpeg":
                retorno = true;
                break;
            case "image/jpg":
                retorno = true;
                break;
            case "image/png":
                retorno = true;
                break;
            default:
                retorno = false;
                break;
                
        }
        return retorno;
    }
    public static String getExtension(String mime){
        String retorno = "";
        switch(mime){
            case "image/jpeg":
                retorno = ".jpg";
                break;
            case "image/jpg":
                retorno = ".jpg";
                break;
            case "image/png":
                retorno = ".png";
                break;
           
                
        }
        return retorno;
    }
}
