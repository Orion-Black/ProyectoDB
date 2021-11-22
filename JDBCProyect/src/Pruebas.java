public class Pruebas {
    public static void main(String[] args) {
        String[][] data = {
                {"1", "Mates", "7:00", "16", "Juan"},
                {"2", "Espanol", "9:00", "15", "Ricardo"}
        };

        int columnas = 15;
        int filas = 5;

        System.out.println('-' + data[0][0]);
        System.out.println('-' + data[0][1]);
        System.out.println('-' + data[0][2]);
        System.out.println('-' + data[0][3]);
        System.out.println('-' + data[0][4]);
        System.out.println('-' + data[1][0]);
        System.out.println('-' + data[1][1]);
        System.out.println('-' + data[1][2]);
        System.out.println('-' + data[1][3]);
        System.out.println('-' + data[1][4]);


        for (int i = 0; i < 2; i++) {//N FILAS
            for (int j = 0; j < 5; j++) { //N COLUMNAS
                System.out.println(data[i][j]);
            }
        }

    }
}
