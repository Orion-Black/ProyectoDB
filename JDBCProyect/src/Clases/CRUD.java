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
    public static String Instancia;
    public static String Puerto;
    public static String User;
    public static String Pass;
    public static String BaseName;

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
    private JRadioButton estadoDeConexionRadioButton;


    void inializarComponente() {

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(128, 128, 128));
        getContentPane().add(panel);

        //Encabezado (ACCESO)
        JLabel etiqueta1 = new JLabel("Acceso");
        etiqueta1.setBounds(143, 5, 100, 20);
        etiqueta1.setForeground(Color.WHITE);
        Font letra = new Font("Times New Roman", Font.PLAIN, 25);
        etiqueta1.setFont(letra);
        panel.add(etiqueta1);

        //etiqueta Img
        ImageIcon TIENDA = new ImageIcon("src/Recursos/login.png");
        JLabel etiquetaIMG = new JLabel(TIENDA);
        etiquetaIMG.setBounds(88, 10, 190, 190);
        etiquetaIMG.setIcon(new ImageIcon(TIENDA.getImage().getScaledInstance(140, 140, 5)));
        panel.add(etiquetaIMG);

        //Etiqueta Datos
        //Campo Instancia
        JLabel etiquetadatos1 = new JLabel("Instancia:");
        etiquetadatos1.setBounds(55, 177, 500, 30);
        etiquetadatos1.setForeground(Color.BLACK);
        Font letradatos1 = new Font("Times New Roman", Font.PLAIN, 15);
        etiquetadatos1.setFont(letradatos1);
        panel.add(etiquetadatos1);

        JTextField textodatos1 = new JTextField();
        textodatos1.setBounds(115, 185, 140, 20);
        panel.add(textodatos1);
        //Campo Puerto
        JLabel etiquetadatos2 = new JLabel("Puerto:");
        etiquetadatos2.setBounds(55, 208, 500, 30);
        etiquetadatos2.setForeground(Color.BLACK);
        Font letradatos2 = new Font("Times New Roman", Font.PLAIN, 15);
        etiquetadatos2.setFont(letradatos2);
        panel.add(etiquetadatos2);
        JTextField textodatos2 = new JTextField();
        textodatos2.setBounds(110, 215, 60, 20);
        panel.add(textodatos2);
        //Campo Usuario
        JLabel etiquetadatos3 = new JLabel("Usuario:");
        etiquetadatos3.setBounds(55, 238, 500, 30);
        etiquetadatos3.setForeground(Color.BLACK);
        Font letradatos3 = new Font("Times New Roman", Font.PLAIN, 15);
        etiquetadatos3.setFont(letradatos3);
        panel.add(etiquetadatos3);
        JTextField textodatos3 = new JTextField();
        textodatos3.setBounds(115, 245, 100, 20);
        panel.add(textodatos3);
        //Campo Contrasena
        JLabel etiquetadatos4 = new JLabel("Contraseña:");
        etiquetadatos4.setBounds(55, 268, 500, 30);
        etiquetadatos4.setForeground(Color.BLACK);
        Font letradatos4 = new Font("Times New Roman", Font.PLAIN, 15);
        etiquetadatos4.setFont(letradatos4);
        panel.add(etiquetadatos4);
        //Campo Database
        JTextField textodatos4 = new JTextField();
        textodatos4.setBounds(133, 275, 100, 20);
        panel.add(textodatos4);
        //Campo Base de datos
        JLabel etiquetadatos5 = new JLabel("Base de Datos:");
        etiquetadatos5.setBounds(55, 298, 500, 30);
        etiquetadatos5.setForeground(Color.BLACK);
        Font letradatos5 = new Font("Times New Roman", Font.PLAIN, 15);
        etiquetadatos5.setFont(letradatos5);
        panel.add(etiquetadatos5);

        JTextField textodatos5 = new JTextField();
        textodatos5.setBounds(150, 305, 100, 20);
        panel.add(textodatos5);


        //Etiqueta Boton
        ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Llamas a las variables donde almacenaras los datos solo llamas a los valores
                //hasta que tocas el boton
                //Les almacenamos el valor contenido en los Campos de texto
                Instancia = textodatos1.getText();
                Puerto = textodatos2.getText();
                User = textodatos3.getText();
                Pass = textodatos4.getText();
                BaseName = textodatos5.getText();

                //Los mostramos en consola como medida de control (puedes borrarlos despues)
                System.out.println("Instancia: " + Instancia);
                System.out.println("Puerto: " + Puerto);
                System.out.println("Usuario: " + User);
                System.out.println("Password: " + Pass);
                System.out.println("Database: " + BaseName);

                //Creamos la conexion pero solo despues de guardar los datos
                ConexionBD cc = new ConexionBD();
                Connection cn = cc.getConexion();
            }

        };

        JButton boton = new JButton("Entrar");
        boton.setBounds(125, 340, 100, 25);
        panel.add(boton);
        boton.addActionListener(accion);

    }

    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("No_lista");
        modelo.addColumn("Grupo");
        modelo.addColumn("Tutores");
        modelo.addColumn("Clave_C");
        modelo.addColumn("Clave_A");
        modelo.addColumn("No_Cuenta");
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

    void limpiarcampos() {
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
