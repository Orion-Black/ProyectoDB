package Clases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utilidades extends JFrame {

    public Utilidades() throws HeadlessException {
        setTitle("Control Escolar");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponentes();
    }

    private void initComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
        JScrollPane scrollPane = null;
        logicaTablas logica = new logicaTablas();


        //scrollPane.add(tabla);

        try {
            ConexionBD cc = new ConexionBD();
            Connection cn = cc.getConexion();

            /*String queryGetColumnas = "select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = 'Alumnos'";
            ResultSet rsult = st.executeQuery(queryGetColumnas);
            List lista = new ArrayList();

            while (rsult.next()){
                lista.add(rsult.getString(1));
            }
            Iterator it = lista.iterator();
            String [] nombresColumnas= new String[lista.size()];
            while (it.hasNext()){
                System.out.println(it.next());
            }

            for (int i = 0; i < lista.size(); i++) {
                nombresColumnas[i] = (String) lista.get(i);
            }


            for (String s:nombresColumnas) {
                System.out.println(s);
            }*/
            //Fin de obtener columnas


            String query = "SELECT * FROM Alumnos";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            JTable tabla = new JTable();
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("No_lista");
            modelo.addColumn("Grupo");
            modelo.addColumn("Tutores");
            modelo.addColumn("Clave_C");
            modelo.addColumn("Clave_A");
            modelo.addColumn("No_Cuenta");
            tabla.setModel(modelo);

            String[] registros = new String[8];
            while (rs.next()) {
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                registros[4] = rs.getString(5);
                registros[5] = rs.getString(6);
                registros[6] = rs.getString(7);
                registros[7] = rs.getString(8);
                modelo.addRow(registros);
            }
            tabla.setModel(modelo);
            tabla.setBounds(10, 100, 700, 300);
            panel.add(tabla);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
