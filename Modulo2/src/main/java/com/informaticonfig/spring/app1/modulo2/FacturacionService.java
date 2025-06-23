package com.informaticonfig.spring.app1.modulo2;

import org.springframework.stereotype.Component;

@Component
public class FacturacionService {
    private final NotificadorService notificadorService;

    public FacturacionService(NotificadorService notificadorService) {
        this.notificadorService = notificadorService;
    }

    public void emitirFactura(String cliente,double monto){
        System.out.println("Factura emitida para " + cliente + " por $" + monto);
        this.notificadorService.notificar(cliente);
    }

}
