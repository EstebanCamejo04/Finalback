package dh.com.example.finalBack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> recursoNoEncontrado(ResourceNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(OdontologoMatriculaException.class)
    public ResponseEntity<String> matriculaRegistrada(OdontologoMatriculaException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(PacienteDniException.class)
    public ResponseEntity<String> dniRegistrado(PacienteDniException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
