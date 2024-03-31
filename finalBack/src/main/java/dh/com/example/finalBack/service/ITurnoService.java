package dh.com.example.finalBack.service;


import dh.com.example.finalBack.entity.Turno;

import java.util.List;

public interface ITurnoService {
    Turno guardar(Turno turno);
    List<Turno> listarTodos();

    Turno buscarPorId(Long id);
    void eliminar(Long id);



    void actualizar(Turno turno);
}

