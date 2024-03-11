package dh.com.example.finalBack.dao.implementacionDao;

import dh.com.example.finalBack.dao.IDao;
import dh.com.example.finalBack.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDaoList implements IDao<Turno> {

    private List<Turno> turnoList = new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        //id = 1 -> nosotros
        turnoList.add(turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoADevolver = null;

        for (Turno t: turnoList) {
            if (t.getId().equals(id)) {
                turnoADevolver = t;
                return turnoADevolver;
            }
        }
        return turnoADevolver;
    }

    @Override
    public Turno eliminar(Integer id) {
        Turno turnoAEliminar = buscarPorId(id);
        if (turnoAEliminar != null) {
            turnoList.remove(turnoAEliminar);
        }
        return turnoAEliminar;
    }

    @Override
    public Turno actualizar(Turno turno) {
        for (int i = 0; i < turnoList.size(); i++) {
            Turno TurnoAActualizar = turnoList.get(i);
            if (TurnoAActualizar.getId().equals(turno.getId())) {
                turnoList.set(i, turno);
                break;
            }
        }
        return turno;
    }

    @Override
    public List<Turno> listarTodos() {
        return new ArrayList<>(turnoList);
    }
}
