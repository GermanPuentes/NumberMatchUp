package JuegoMemoriafx;

import java.util.ArrayList; // Importa la clase ArrayList para listas dinámicas
import java.util.Collections; // Importa la clase Collections para operaciones con colecciones
import java.util.List; // Importa la interfaz List para listas

// Clase que representa el tablero del juego
public class Tablero {
    private int filas; // Número de filas del tablero
    private int columnas; // Número de columnas del tablero
    private List<Celda> celdas = new ArrayList<>(); // Lista de celdas del tablero

    // Constructor que inicializa el tablero con las dimensiones dadas
    public Tablero(int filas, int columnas) {
        this.filas = filas; // Establece el número de filas
        this.columnas = columnas; // Establece el número de columnas
    }

    // Método para generar los valores aleatorios para las celdas del tablero
    public void generarValoresTablero() {
        List<String> valores = new ArrayList<>(); // Lista de valores para las celdas
        for (int i = 1; i <= (filas * columnas) / 2; i++) {
            valores.add(String.valueOf(i)); // Añade un número
            valores.add(Celda.numeroEnIngles(i)); // Añade el nombre del número en inglés
        }
        Collections.shuffle(valores); // Baraja los valores generados

        int index = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas.add(new Celda(i, j, valores.get(index++))); // Crea nuevas celdas con los valores barajados
            }
        }
    }

    // Método para obtener una celda específica del tablero
    public Celda getCelda(int fila, int columna) {
        for (Celda celda : celdas) { // Recorre todas las celdas
            if (celda.getFila() == fila && celda.getColumna() == columna) { // Verifica si la celda está en la posición especificada
                return celda; // Devuelve la celda encontrada
            }
        }
        return null; // Retorna null si la celda no se encuentra
    }

    // Método para imprimir el tablero en la consola
    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) { // Recorre las filas
            for (int j = 0; j < columnas; j++) { // Recorre las columnas
                System.out.print("[" + i + "," + j + "] "); // Imprime la posición de cada celda
            }
            System.out.println(); // Imprime una nueva línea al final de cada fila
        }
    }
}