package dh.com.example.finalBack.dao.implementacionDao;

import dh.com.example.finalBack.dao.BD;
import dh.com.example.finalBack.dao.IDao;
import dh.com.example.finalBack.model.Domicilio;
import dh.com.example.finalBack.model.Odontologo;
import dh.com.example.finalBack.model.Paciente;
import dh.com.example.finalBack.model.Turno;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente>{

        private static final Logger LOGGER = Logger.getLogger(dh.com.example.finalBack.dao.implementacionDao.PacienteDaoH2.class);

        private static final String INSERT_PACIENTES = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DOMICILIO, DNI, FECHA_ALTA_PRIVAT) VALUES (?,?,?,?,?)";
        private static final String SELECT_ALL = "SELECT * FROM PACIENTES";
        private static final String SELECT_BY_ID = "SELECT * FROM PACIENTES WHERE ID = ?";
        private static final String DELETE_BY_ID = "DELETE FROM PACIENTES WHERE ID = ?";
        private static final String SQL_UPDATE_BY_ID = "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, DOMICILIO=?, DNI=?, FECHA_ALTA_PRIVAT=? WHERE ID=?";
        @Override
        public Paciente guardar(Paciente paciente) {
            LOGGER.info("Estamos guardando un paciente");
            Connection connection = null;

            try {
                DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
                domicilioDaoH2.guardar(paciente.getDomicilio());

                connection = BD.getConnection();

                PreparedStatement psInsert = connection.prepareStatement(INSERT_PACIENTES, Statement.RETURN_GENERATED_KEYS);
                psInsert.setString(1, paciente.getNombre());
                psInsert.setString(2, paciente.getApellido());
                psInsert.setInt(3, paciente.getDomicilio().getId());
                psInsert.setString(4, paciente.getDni());
                psInsert.setDate(5, Date.valueOf(paciente.getFechaAltaPrivat()));


                psInsert.execute();

                ResultSet rs = psInsert.getGeneratedKeys();
                while (rs.next()) {
                    paciente.setId(rs.getInt(1));
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
            return paciente;
        }

        @Override
        public Paciente buscarPorId(Integer id) {
            LOGGER.info("Estamos buscando un Paciente");
            Connection conexion = null;
            Paciente paciente = null;
            try {
                conexion = BD.getConnection();

                PreparedStatement psSearchByID =  conexion.prepareStatement(SELECT_BY_ID);
                psSearchByID.setInt(1, id);
                ResultSet rs  = psSearchByID.executeQuery();

                DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
                while (rs.next()){
                    Domicilio domicilio = domicilioDaoH2.buscarPorId(rs.getInt(4));

                    paciente = new Paciente();
                    paciente.setId(rs.getInt(1));
                    paciente.setNombre(rs.getString(2));
                    paciente.setApellido(rs.getString(3));
                    paciente.setDomicilio(domicilio);
                    paciente.setDni(rs.getString(5));
                    paciente.setFechaAltaPrivat(rs.getDate(6).toLocalDate());
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
            return paciente;
        }

        @Override
        public Turno eliminar(Integer id) {
            LOGGER.info("Estamos eliminando un Paciente");
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
        public Turno actualizar(Paciente paciente) {
            LOGGER.info("Estamos actualizando un Paciente");
            Connection conexion = null;
            try {
                DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
                domicilioDaoH2.guardar(paciente.getDomicilio());

                conexion = BD.getConnection();

                PreparedStatement psUpdate = conexion.prepareStatement(SQL_UPDATE_BY_ID);
                psUpdate.setInt(1, paciente.getId());
                psUpdate.setString(2, paciente.getNombre());
                psUpdate.setString(3, paciente.getApellido());
                psUpdate.setInt(4, paciente.getDomicilio().getId());
                psUpdate.setString(5, paciente.getDni());
                psUpdate.setDate(6, Date.valueOf(paciente.getFechaAltaPrivat()));
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
        public List<Paciente> listarTodos() {
            LOGGER.info("Estamos consultando todos los Pacientes.");
            Connection conexion = null;
            Paciente paciente =  null;
            List<Paciente>  listaPacientes = new ArrayList<>();

            try {
                conexion = BD.getConnection();
                PreparedStatement psUpdateById = conexion.prepareStatement(SELECT_ALL);
                ResultSet rs = psUpdateById.executeQuery();

                DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
                LOGGER.info("VIENDO QUE PEDOO");
                //TENGO QUE VER PORQUE NO ENTRA AL WHILEE
                while (rs.next())  {
                    Domicilio domicilio = domicilioDaoH2.buscarPorId(rs.getInt(4));

                    paciente = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3), domicilio,
                            rs.getString(5), rs.getDate(6).toLocalDate());
                    LOGGER.info("VIENDO POR QUE MIERDA NO FUNCA");

                    listaPacientes.add(paciente);
                }
                LOGGER.info("VIENDO QUE ONDA22");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    conexion.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            LOGGER.info("Esta es la lista de pacientes" + listaPacientes);
            return listaPacientes;
        }

    }

