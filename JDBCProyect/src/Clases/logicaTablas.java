package Clases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class logicaTablas {
    public DefaultTableModel mostrarAlumnos() {
        String[] nombresColumnas = {"ID", "Nombre", "No_Lista", "Grupo", "Tutores", "Clave_C", "Clave_A","No_Cuenta"};
        String[] registros = new String[8];

        DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);

        try {
            ConexionBD cc = new ConexionBD();
            Connection cn = cc.getConexion();
            Statement st = cn.createStatement();
            String queryAlumnos = "SELECT * FROM Alumnos";
            ResultSet rs = st.executeQuery(queryAlumnos);

            while (rs.next()){
                registros[0] = rs.getString("ID");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("No_lista");
                registros[3] = rs.getString("Grupo");
                registros[4] = rs.getString("Tutores");
                registros[5] = rs.getString("Clave_C");
                registros[6] = rs.getString("Clave_A");
                registros[6] = rs.getString("No_Cuenta");
                modelo.addRow(registros);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return modelo;
    }
}
