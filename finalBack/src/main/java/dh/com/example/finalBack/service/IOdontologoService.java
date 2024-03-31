package dh.com.example.finalBack.service;


import dh.com.example.finalBack.entity.Odontologo;
import dh.com.example.finalBack.exception.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Long id) throws ResourceNotFoundException;

    Odontologo actualizar(Odontologo odontologo);

    void eliminar(Long id);
}



