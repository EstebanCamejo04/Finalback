package dh.com.example.finalBack.controller;

import dh.com.example.finalBack.entity.Odontologo;
import dh.com.example.finalBack.service.IOdontologoService;
import dh.com.example.finalBack.service.ImplementacionService.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger LOGGER = Logger.getLogger(OdontologoController.class);
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //BUSCA UN ODONTOLOGO POR SU ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        LOGGER.info("Estamos buscando un odontologo con el id:" + " " + id);
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    //GUARDA UN ODONTOLOGO
    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        LOGGER.info("Estamos guardando un odontologo");

        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    //LISTA TODOS LOS ODONTOLOGOS
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        LOGGER.info("Estamos listando los odontologos");
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    //ACTUALIZA DATOS DE UN ODONTOLOGO
    @PutMapping
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) {
        LOGGER.info("Estamos actualizando un odontologo");
        return ResponseEntity.ok(odontologoService.actualizar(odontologo));

    }


    //        @PutMapping
//    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
//        LOGGER.info("Estamos actualizando un odontologo");
//        ResponseEntity<String> response;
//        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
//        if (odontologoBuscado != null) {
//            odontologoService.actualizar(odontologo);
//            response = ResponseEntity.ok("Se actualizó el odontologo con id " + odontologo.getId());
//        } else {
//            response = ResponseEntity.ok().body("No se puede actualizar el odontologo");
//        }
//        return response;
//    }
    //ELIMINA A UN ODONTOLOGO
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        LOGGER.info("Estamos eliminando un odontologo");
        ResponseEntity<String> response;
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if (odontologo != null) {
            odontologoService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el odontólogo con ID: " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }


}

