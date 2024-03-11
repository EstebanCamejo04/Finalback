package dh.com.example.finalBack.service;
import dh.com.example.finalBack.model.Paciente;

import java.util.List;

public interface IPacienteService {
        Paciente guardar (Paciente paciente);

        List<Paciente> listarTodos();

        Paciente buscarPorId(Integer id);

    }
