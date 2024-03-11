package dh.com.example.finalBack.service;

import dh.com.example.finalBack.model.Turno;

import java.util.List;

public interface ITurnoService {
    Turno guardar(Turno turno);
    List<Turno> listarTodos();

    Turno buscarPorId(Integer id);

    void eliminar(Integer id);

    void actualizar(Turno turno);
}
