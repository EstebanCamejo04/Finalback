package dh.com.example.finalBack.controller;

import dh.com.example.finalBack.entity.Odontologo;
import dh.com.example.finalBack.entity.Paciente;
import dh.com.example.finalBack.service.IPacienteService;
import dh.com.example.finalBack.service.ImplementacionService.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger LOGGER = Logger.getLogger(PacienteController.class);

    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    //BUSCA A UN PACIENTE POR SU ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        LOGGER.info("Estamos buscando a un paciente con el id:" + " " + id);

        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    //GUARDA A UN PACIENTE
    @PostMapping("/guardar")
    public Paciente guardar(@RequestBody Paciente paciente) {
        LOGGER.info("Estamos guardando un paciente");

        return pacienteService.guardar(paciente);
    }


    //LISTA A TODOS LOS PACIENTES
    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarTodos() {
        LOGGER.info("Estamos listando todos los pacientes");
        return ResponseEntity.ok(pacienteService.listarTodos());
    }


    //ACTUALIZA LOS DATOS DE UN PACIENTE
    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) {
        LOGGER.info("Estamos actualizando los datos de un paciente");
        return ResponseEntity.ok(pacienteService.actualizar(paciente));
    }

//    @PutMapping("/actualizar")
//    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {
//        LOGGER.info("Estamos actualizando los datos de un paciente");
//        ResponseEntity<String> response;
//        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
//        if (pacienteBuscado != null) {
//            pacienteService.actualizar(paciente);
//            response = ResponseEntity.ok("Se actualizó el paciente con id " + paciente.getId());
//        } else {
//            response = ResponseEntity.ok().body("No se puede actualizar el paciente");
//        }
//        return response;
//
//    }


    //ELIMINA A UN PACIENTE
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarPacientePorId(@RequestParam Long id) {
        LOGGER.info("Estamos eliminando a un paciente");
        ResponseEntity<String> response;
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            pacienteService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el paciente con ID " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }


}
