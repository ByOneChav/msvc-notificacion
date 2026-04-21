package com.example.perfulandia.testNotificacionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.perfulandia.repository.NotificacionRepository;
import com.example.perfulandia.service.NotificacionService;
import com.example.perfulandia.model.Notificacion;



@ExtendWith(MockitoExtension.class)
public class TestNotificacionService {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){
        List<Notificacion> listaNotificacion = new ArrayList<>();


        Notificacion n1 = new Notificacion();

        n1.setId(1L);
        n1.setRun("25820817-K");
        n1.setMensaje("Tu pedido está en camino.");
        n1.setFechaEnvio(LocalDate.parse("2026-09-12"));
        n1.setEstado("En preparación");

        listaNotificacion.add(n1);

        when(notificacionRepository.findAll()).thenReturn(listaNotificacion);
        List<Notificacion> resultadoBucada = notificacionService.buscarTodo();
        assertEquals(1, resultadoBucada.size());
        verify(notificacionRepository, times(1)).findAll();

    }

    @Test
    public void testBuscarUnaNotificacion(){

        Notificacion n1 = new Notificacion();

        n1.setId(1L);
        n1.setRun("25820817-K");
        n1.setMensaje("Tu pedido está en camino.");
        n1.setFechaEnvio(LocalDate.parse("2026-09-12"));
        n1.setEstado("En preparación");

        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(n1));
        Notificacion notificacionBuscada = notificacionService.buscar(1L);
        assertEquals(1, notificacionBuscada.getId());
        verify(notificacionRepository, times(1)).findById(1L);

    }

    @Test
    public void testGuardarNotificacion(){

        Notificacion n1 = new Notificacion();

        n1.setId(1L);
        n1.setRun("25820817-K");
        n1.setMensaje("Tu pedido está en camino.");
        n1.setFechaEnvio(LocalDate.parse("2026-09-12"));
        n1.setEstado("En preparación");

        when(notificacionRepository.save(n1)).thenReturn(n1);
        Notificacion notificacionGuardada = notificacionService.guardar(n1);
        assertEquals(1, notificacionGuardada.getId());
        verify(notificacionRepository, times(1)).save(n1);
    }

    @Test
    public void testEliminarNotificacion(){
        Long idNotificacion = 1L; 
        doNothing().when(notificacionRepository).deleteById(idNotificacion);

        notificacionService.eliminar(idNotificacion);
        verify(notificacionRepository, times(1)).deleteById(idNotificacion);
    }

}
