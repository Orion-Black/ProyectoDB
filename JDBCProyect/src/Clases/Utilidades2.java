package Clases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utilidades2 extends JFrame {

    public Utilidades2() throws HeadlessException {
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

        try {
            ConexionBD cc = new ConexionBD();
            Connection cn = cc.getConexion();

            String query = "SELECT * FROM Alumnos";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            JTable tabla = new JTable();
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("No_Cuenta");
            modelo.addColumn("Nombre");
            modelo.addColumn("No_lista");
            modelo.addColumn("Grupo");
            modelo.addColumn("Tutores");
            modelo.addColumn("Clave_C");
            modelo.addColumn("Clave_A");

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
            JScrollPane scrollPane = new JScrollPane(tabla);
            getContentPane().add(scrollPane);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        Button consultar=new Button("Buscar");
        ActionListener buscar=new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        consultar.setBounds(100,400,100,200);
        panel.add(consultar);
    }
}
