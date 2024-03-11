package dh.com.example.finalBack.service;
import dh.com.example.finalBack.model.Domicilio;

import java.util.List;

public interface IDomicilioService {

        Domicilio guardar (Domicilio domicilio);

        List<Domicilio> listarTodos();

        Domicilio buscarPorId(Integer id);

}
