package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    Connection con = null;

    public Connection getConexion() {
        try {
            String conexionUrl = "jdbc:sqlserver://localhost:1433;database=ControlEscolar;user=sa;password=Shared1980;";
            con = DriverManager.getConnection(conexionUrl);
            //System.out.println("Conexion exitosa");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        return con;
    }
}
