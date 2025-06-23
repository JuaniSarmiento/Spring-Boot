package com.informaticonfig.spring.app1.modulo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacturacionController {
    private final FacturacionService facturacionService;

    public FacturacionController(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }
    @GetMapping("/facturar")
    public String probarFacturacion() {
        facturacionService.emitirFactura("Luna", 25000);
        return "¡Factura emitida y notificada desde Spring Boot! Revisa la consola de tu IDE.";
    }
}

