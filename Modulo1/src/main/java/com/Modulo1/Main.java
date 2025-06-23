package com.Modulo1;
import com.Modulo1.Configuracion;
import com.Modulo1.FacturacionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando aplicación...");

        // 1. Levantamos el Contenedor de Spring usando nuestra clase de configuración.
        // En este momento, Spring escanea, crea e inyecta todos los beans.
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);

        System.out.println("Contenedor de Spring inicializado.");

        // 2. Le pedimos al contenedor que nos dé el bean de FacturacionService.
        // ¡No estamos haciendo new! Estamos pidiendo el objeto ya listo y conectado.
        com.Modulo1.FacturacionService facturacionService = context.getBean(com.Modulo1.FacturacionService.class);

        // 3. Usamos el servicio.
        facturacionService.emitirFacturacion("Luna",10000);

        System.out.println("Aplicación finalizada.");
    }
}