package dh.com.example.finalBack.dao;

import dh.com.example.finalBack.model.Turno;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);
    T buscarPorId(Integer id);
    Turno eliminar(Integer id);
    Turno actualizar(T t);
    List<T> listarTodos();

}
