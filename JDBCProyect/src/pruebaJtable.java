import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class marcotabla extends JFrame{
    public marcotabla() throws HeadlessException {
        setTitle("Prueba Tabla");
        setBounds(300,300,400,150);
        JTable tabla = new JTable(datosFilas,nombresColumnas);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        JButton imprimir = new JButton("Presione para imprimir");
        imprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tabla.print();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        JPanel laminaBoton = new JPanel();
        laminaBoton.add(imprimir);
        add(laminaBoton, BorderLayout.SOUTH);
    }

    private String[] nombresColumnas = {"ID", "Nombre"};
    private Object[][] datosFilas ={
            {1,"Jesus"},{1, "Martin"},{3, "Martin"},{4,"Lupita"},
            {5,"Saul"},{6,"Fredy"},{7,"Magali"}
    };

    public static class pruebaJtable {
        public static void main(String[] args) {
            /*JFrame marco = new marcotabla();
            marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            marco.setVisible(true);
             */
        }

    }
}
