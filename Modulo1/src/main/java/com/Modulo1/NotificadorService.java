package com.Modulo1;
import org.springframework.stereotype.Component;
@Component
public class NotificadorService {
    public void notificar(String cliente) {
        System.out.println("Notificando al cliente " + cliente+ " por gmail");
    }
}
