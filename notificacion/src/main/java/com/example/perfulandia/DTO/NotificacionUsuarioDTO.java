package com.example.perfulandia.DTO;

import java.time.LocalDate;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionUsuarioDTO {

    private Long id;
    private String run;
    private String mensaje;
    private LocalDate fechaEnvio;
    private String estado;

    private String nombre;
    private String apellido;
    private String direccion;
    private Long contacto;
    private String email;



}
