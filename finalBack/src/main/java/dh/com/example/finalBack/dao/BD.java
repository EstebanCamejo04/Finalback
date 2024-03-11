package dh.com.example.finalBack.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {
    private static final Logger LOGGER = Logger.getLogger(BD.class);
    private static final String SQL_DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS " +
            "ODONTOLOGOS; CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100)  NOT NULL," +
            " MATRICULA VARCHAR(100) NOT NULL)";
    private static final String SQL_DROP_CREATE_DOMICILIOS = "DROP TABLE IF EXISTS " +
            "DOMICILIOS; CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " CALLE VARCHAR(100) NOT NULL," +
            " NUMERO INT NOT NULL," +
            " LOCALIDAD VARCHAR(100) NOT NULL," +
            " PROVINCIA VARCHAR(100) NOT NULL" +
            ")";
    private static final String SQL_DROP_CREATE_PACIENTES = "DROP TABLE IF EXISTS " +
            "PACIENTES; CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100)  NOT NULL," +
            " DNI VARCHAR(100) NOT NULL ," +
            " FECHA_INGRESO DATE NOT NULL," +
            " DOMICILIO_ID INT NOT NULL)";


    public static Connection getConnection() throws Exception {
        Connection conexion = null;
        try {
            Class.forName("org.h2.Driver");
            conexion = DriverManager.getConnection("jdbc:h2:C:/Users/camej/Desktop/finalBack/BDFinalBack", "sa", null);
        } catch (Exception e) {
            throw new Exception("Error de conexion con la base de datos");
        }
        return conexion;
    }

    public static void crearTablas() {
        Connection connection = null;

        try {

            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_DROP_CREATE_ODONTOLOGOS);
            LOGGER.info("se creo la tabla odontologos");
            statement.execute(SQL_DROP_CREATE_PACIENTES);
            LOGGER.info("se creo la tabla pacientes");
            statement.execute(SQL_DROP_CREATE_DOMICILIOS);
                LOGGER.info("se creo la tabla domicilios");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
