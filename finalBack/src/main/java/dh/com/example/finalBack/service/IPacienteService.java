package dh.com.example.finalBack.service;


import dh.com.example.finalBack.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente guardar(Paciente paciente);

    List<Paciente> listarTodos();

    Paciente buscarPorId(Long id);

    Paciente actualizar(Paciente paciente);

    void eliminar(Long id);
}
