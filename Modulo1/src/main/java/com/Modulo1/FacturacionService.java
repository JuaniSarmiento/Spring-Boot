package com.Modulo1;

import org.springframework.stereotype.Component;

@Component
public class FacturacionService {
    private final NotificadorService notificadorService;

    public FacturacionService(NotificadorService notificadorService) {
        this.notificadorService = notificadorService;
    }
    public void emitirFacturacion(String cliente, double monto) {
        System.out.println("Factura emitida para "+cliente+" por $"+monto);
        this.notificadorService.notificar(cliente);
    }
}
