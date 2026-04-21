package com.example.perfulandia.service;

import com.example.perfulandia.DTO.UsuarioDTO;
import com.example.perfulandia.model.Notificacion;
import com.example.perfulandia.repository.NotificacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class NotificacionService {

    private  WebClient webClient;

    public NotificacionService(WebClient webClient) {
        this.webClient = webClient;
    }


    public UsuarioDTO BuscarUsuario(String run){
        UsuarioDTO usuario = webClient.get()
                                        .uri("/{run}", run)
                                        .retrieve()
                                        .bodyToMono(UsuarioDTO.class)
                                        .block();
        return usuario;
    }

    // @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository){
        this.notificacionRepository = notificacionRepository;
    }

    public List<Notificacion> buscarTodo(){
        return notificacionRepository.findAll();
    }

    public Notificacion buscar(long id){
        return notificacionRepository.findById(id).get();
    }

    public Notificacion guardar(Notificacion notificacion){
        return notificacionRepository.save(notificacion);
    }

    public void eliminar(long id){
        notificacionRepository.deleteById(id);
    }

}




