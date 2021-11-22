package Clases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUD extends JFrame {
    private JTextField id;
    private JTextField ncuenta;
    private JTextField nombre;
    private JTextField nlista;
    private JTextField grupo;
    private JTextField tutor;
    private JTextField curso;
    private JTextField asignatura;
    private JTable tbce;
    private JPanel Main;
    private JButton Buscar;
    private JButton Guardar;
    private JButton Actualizar;
    private JButton Eliminar;
    private JButton Limpiar;

    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("No_Cuenta");
        modelo.addColumn("Nombre");
        modelo.addColumn("No_lista");
        modelo.addColumn("Grupo");
        modelo.addColumn("Tutores");
        modelo.addColumn("Clave_C");
        modelo.addColumn("Clave_A");
        tbce.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM Alumnos";
        } else {
            sql = "SELECT * FROM Alumnos WHERE ID='" + valor + "'";
        }
        String[] registros = new String[8];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
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
            tbce.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void limpiarcampos(){
        id.setText(null);
        ncuenta.setText(null);
        nombre.setText(null);
        nlista.setText(null);
        grupo.setText(null);
        tutor.setText(null);
        curso.setText(null);
        asignatura.setText(null);
    }

    public CRUD() {
        mostrardatos("");
        Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarcampos();
            }
        });
        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    PreparedStatement pst = cn.prepareStatement("INSERT INTO Alumnos"
                            + "(No_cuenta,Nombre,No_lista,Grupo,Tutores,Clave_C,Clave_A)VALUES(?,?,?,?,?,?,?)");
                    pst.setString(1, ncuenta.getText());
                    pst.setString(2, nombre.getText());
                    pst.setString(3, nlista.getText());
                    pst.setString(4, grupo.getText());
                    pst.setString(5, tutor.getText());
                    pst.setString(6, curso.getText());
                    pst.setString(7, asignatura.getText());
                    pst.executeUpdate();
                    mostrardatos("");
                    limpiarcampos();
                    JOptionPane.showMessageDialog(null, "DATOS GUARDADOS CORRECTAMENTE");
                } catch (HeadlessException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrardatos(id.getText());
            }
        });
        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement pst = cn.prepareStatement
                            ("UPDATE Alumnos SET No_cuenta='" + ncuenta.getText() + "'," + "Nombre='" + nombre.getText() + "',"
                                    + "No_lista='" + nlista.getText() + "'," + "Grupo='" + grupo.getText() + "',"
                                    + "Tutores='" + tutor.getText() + "'," + "Clave_C='" + curso.getText() + "',"
                                    + "Clave_A='" + asignatura.getText() + "'"
                                    + "WHERE ID='" + id.getText() + "'");
                    pst.executeUpdate();
                    mostrardatos("");
                    limpiarcampos();
                    JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CORRECTAMENTE");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement pst = cn.prepareStatement
                            ("DELETE FROM Alumnos WHERE ID='" + id.getText() + "'");
                    pst.executeUpdate();
                    mostrardatos("");
                    limpiarcampos();
                    JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO CORRECTAMENTE");
                } catch (SQLException ex) {
                }
            }
        });
    }

    public static void main(String[] args) {
        CRUD crud = new CRUD();
        crud.setContentPane(new CRUD().Main);
        crud.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crud.setVisible(true);
        crud.pack();
    }

    ConexionBD cc = new ConexionBD();
    Connection cn = cc.getConexion();
}
