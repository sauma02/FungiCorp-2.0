/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Admin
 */
@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Value("${valores.ruta}")
    private String ruta;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/uploads/**").addResourceLocations("file: "+this.ruta);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        try {
            return https
                    .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll()
                    )
                    .authorizeHttpRequests(req -> req
                    .requestMatchers("/**", "/static/**", "/js/**", "/css/**", "/images/**", "/login/**", "/templates/**").permitAll()
                    .anyRequest().authenticated()
                    )
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            if(e.getCause() != null){
                System.err.println("Error encontrado: "+ e.getMessage());
            }
            
            throw new Exception("Error encontrado: "+e.getCause().toString());
            
        }
    }
    

}
