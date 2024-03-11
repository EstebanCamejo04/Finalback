package dh.com.example.finalBack.dao.implementacionDao;

import dh.com.example.finalBack.dao.BD;
import dh.com.example.finalBack.dao.IDao;
import dh.com.example.finalBack.model.Odontologo;
import dh.com.example.finalBack.model.Turno;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    private static final String INSERT_ODONTOLOGOS = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    private static final String SELECT_BY_ID = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM ODONTOLOGOS WHERE ID = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE ODONTOLOGOS SET NOMBRE=?, APELLIDO=?, MATRICULA=? WHERE ID=?";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Estamos guardando un odontologo");
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement psInsert = connection.prepareStatement(INSERT_ODONTOLOGOS, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getNombre());
            psInsert.setString(2, odontologo.getApellido());
            psInsert.setString(3, odontologo.getMatricula());

            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        LOGGER.info("Estamos buscando un Odontologo");
        Connection conexion = null;
        Odontologo odontologo = null;
        try {
            conexion = BD.getConnection();
            PreparedStatement psSearchByID =  conexion.prepareStatement(SELECT_BY_ID);
            psSearchByID.setInt(1, id);
            ResultSet rs  = psSearchByID.executeQuery();

            while (rs.next()){
                odontologo = new Odontologo();
                odontologo.setId(rs.getInt(1));
                odontologo.setNombre(rs.getString(2));
                odontologo.setApellido(rs.getString(3));
                odontologo.setMatricula(rs.getString(4));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conexion.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return odontologo;
    }

        @Override
        public Turno eliminar(Integer id) {
            LOGGER.info("Estamos eliminando un Odontologo");
            Connection conexion = null;
            try {
                conexion = BD.getConnection();
                PreparedStatement psDelete = conexion.prepareStatement(DELETE_BY_ID);
                psDelete.setInt(1, id);
                psDelete.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


    @Override
    public Turno actualizar(Odontologo odontologo) {
        LOGGER.info("Estamos actualizando un Odontologo");
        Connection conexion = null;
        try {
            conexion = BD.getConnection();
            PreparedStatement psUpdate = conexion.prepareStatement(SQL_UPDATE_BY_ID);
            psUpdate.setInt(1, odontologo.getId());
            psUpdate.setString(2, odontologo.getNombre());
            psUpdate.setString(3, odontologo.getApellido());
            psUpdate.setString(4, odontologo.getMatricula());

            psUpdate.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Estamos consultando todos los Odontologos.");
        Connection conexion = null;
        List<Odontologo>  listaOdontologos = new ArrayList<>();
        Odontologo odontologo =  null;
        try {
            conexion = BD.getConnection();
            PreparedStatement psUpdateById = conexion.prepareStatement(SELECT_ALL);
            ResultSet rs = psUpdateById.executeQuery();

            while (rs.next())  {
                odontologo = new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

                listaOdontologos.add(odontologo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conexion.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("estos son los odontologos" + listaOdontologos);
        return listaOdontologos;

    }

}

