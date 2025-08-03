package com.lonja.cajas_clientes.controller;

import com.lonja.cajas_clientes.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//Lo uso para pruebas
@RestController
@RequestMapping("/email") // <-- Esto indica el prefijo de URL
public class EmailTestController {
    private final EmailService emailService;

    public EmailTestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test")
    public String enviarEmailDePrueba(@RequestParam String destinatario) {
        emailService.enviarEmail(
                destinatario,
                "¡Prueba de email desde LonjaApp!",
                "Esto es un email de prueba enviado desde tu app Spring Boot."
        );
        return "Email de prueba enviado a " + destinatario;
    }
}
