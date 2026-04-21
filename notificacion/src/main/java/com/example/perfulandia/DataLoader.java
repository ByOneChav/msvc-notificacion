package com.example.perfulandia;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.perfulandia.model.Notificacion;
import com.example.perfulandia.service.NotificacionService;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    private final Faker faker = new Faker(new Locale("es", "cl"));
    private final Random random = new Random();

    @Autowired
    private  NotificacionService notificacionService;

    @Override
    public void run(String... args) throws Exception{
        for(int i=0; i<15; i++){
            Notificacion notificacionFicticia = new Notificacion();
            notificacionFicticia .setId(generarIdNotificacion());
            notificacionFicticia .setRun(generarRut());
            notificacionFicticia .setMensaje(generarNotificacionEnvio());
            notificacionFicticia .setFechaEnvio(LocalDate.now().minusDays(random.nextInt(60)));
            notificacionFicticia .setEstado(generarEstadoEnvio());

            notificacionService.guardar(notificacionFicticia);
            System.out.println("Notificación Registrada: "+notificacionFicticia.getMensaje());
        }


    }

    private static long idNotifacionActual = 50; // valor inicial
    private Long generarIdNotificacion() {
        idNotifacionActual += 7;
        return idNotifacionActual;
    }

    private String generarRut(){
        int cuerpo = 10000000 + random.nextInt(8999999);
        String dv = calcularDv(cuerpo);
        return cuerpo + "-"+dv;
    }

    private String  calcularDv(int cuerpo){
        int m = 0, s = 1;
        while (cuerpo != 0) {
            s = (s + cuerpo % 10 * (9 - m++ % 6)) % 11;
            cuerpo /= 10;
        }

        if (s == 0) return "K";
        if (s == 1) return "0";
        return String.valueOf(11 - s);
    }

    public  String generarNotificacionEnvio() {
        String[] mensajes = {
            "📦 Tu pedido ha sido despachado.",
            "🚚 Tu pedido está en camino.",
            "✅ Tu pedido ha sido entregado con éxito.",
            "⏳ Tu pedido está siendo preparado para el envío.",
            "❗ Hubo un retraso en el despacho de tu pedido.",
            "📍 Tu pedido está en el centro de distribución.",
            "📬 Tu paquete será entregado hoy.",
            "🔄 Tu pedido ha cambiado de estado: en tránsito."
        };
        return faker.options().option(mensajes);
    }

    public  String generarEstadoEnvio() {
        String[] estados = {
            "Pendiente",
            "En preparación",
            "En camino",
            "En bodega",
            "Despachado",
            "Entregado",
            "Rechazado",
            "Devuelto",
            "Cancelado"
        };
        return faker.options().option(estados);
    }




    // private String generarCategoria() {
    //     String[] metodos = {"Perfumes",
    //                         "Cuidado Personal",
    //                         "Belleza",
    //                         "Cosmética",
    //                         "Aromaterapia",
    //                         "Accesorios",
    //                         "Fragancias Unisex"};
    //     return metodos[new Random().nextInt(metodos.length)];
    // }

    // private String generarDireccionEnvio() {
    //     String[] calles = {"Av. Providencia", "Calle Los Pinos", "Av. Libertador", "Pasaje Las Flores", "Camino Real"};
    //     int numero = new Random().nextInt(1000) + 1;
    //     String ciudad = new String[] {"Santiago", "Valparaíso", "Concepción", "La Serena", "Antofagasta"}[new Random().nextInt(5)];
    //     return calles[new Random().nextInt(calles.length)] + " #" + numero + ", " + ciudad;
    // }



}
