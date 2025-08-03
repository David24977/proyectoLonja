package com.lonja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Con esta clase sincronizamos el frontend y el backend, si están en diferentes puerto,
// le decimos al navegador que no bloquee mensajes AJAX/axios  de un puerto diferente
// y a Spring Boot que permita conexiones con el puerto que deseemo en este caso: http://localhost:5173
// que es el que utiliza react
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //.allowedOrigins("http://localhost:5173",  "http://192.168.0.123:5173")
                        .allowedOrigins("*")//Solo para pruebas, permite entrar a todo el mundo
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
