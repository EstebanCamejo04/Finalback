package dh.com.example.finalBack.controller;

import dh.com.example.finalBack.entity.Odontologo;
import dh.com.example.finalBack.entity.Paciente;
import dh.com.example.finalBack.entity.Turno;
import dh.com.example.finalBack.service.IOdontologoService;
import dh.com.example.finalBack.service.IPacienteService;
import dh.com.example.finalBack.service.ITurnoService;
import dh.com.example.finalBack.service.ImplementacionService.OdontologoService;
import dh.com.example.finalBack.service.ImplementacionService.PacienteService;
import dh.com.example.finalBack.service.ImplementacionService.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);

    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }


    //GUARDA UN TURNO
    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardar(@RequestBody Map<String, Object> requestData) {
        LOGGER.info("Estamos guardando un turno");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ResponseEntity<Turno> response;

        Long odontologoId = Long.valueOf(requestData.get("odontologo").toString());
        Long pacienteId = Long.valueOf(requestData.get("paciente").toString());
        LocalDate fechaTurno = LocalDate.parse(requestData.get("fechaTurno").toString(), formatter);

        Odontologo odontologo = odontologoService.buscarPorId(odontologoId);
        Paciente paciente = pacienteService.buscarPorId(pacienteId);

        if (odontologo != null && paciente != null) {
            Turno turno = new Turno();
            turno.setOdontologo(odontologo);
            turno.setPaciente(paciente);
            turno.setFechaTurno(fechaTurno);
            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    //LISTA LOS TURNOS
    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> listarTodos() {
        LOGGER.info("Estamos listando los turnos");
        return ResponseEntity.ok(turnoService.listarTodos());
    }



    //ESTOS TODAVIA NO SE IMPLEMENTAN EN LA VISTA

    //ACTUALIZA LOS DATOS DE UN TURNO
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) {
        LOGGER.info("Estamos Actualizando datos de un turno");
        Turno turnoExistente = turnoService.buscarPorId(turno.getId());
        if (turnoExistente != null) {
            turnoService.actualizar(turno);
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //ELIMINA UN TURNO
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        LOGGER.info("Estamos eliminando un turno");
        ResponseEntity<String> response;

        Turno turno = turnoService.buscarPorId(id);
        if (turno != null) {
            turnoService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el turno con ID: " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }



}