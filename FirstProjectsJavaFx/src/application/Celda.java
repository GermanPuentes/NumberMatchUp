// Clase que representa una celda en el tablero
public class Celda {
    private int fila; // Fila de la celda
    private int columna; // Columna de la celda
    private String valor; // Valor de la celda
    private boolean revelada; // Estado de la celda (revelada u oculta)

    // Constructor que inicializa la celda con su posición y valor
    public Celda(int fila, int columna, String valor) {
        this.fila = fila; // Establece la fila de la celda
        this.columna = columna; // Establece la columna de la celda
        this.valor = valor; // Establece el valor de la celda
        this.revelada = false; // Inicialmente, la celda está oculta
    }

    // Método para obtener la fila de la celda
    public int getFila() {
        return fila; // Devuelve la fila de la celda
    }

    // Método para obtener la columna de la celda
    public int getColumna() {
        return columna; // Devuelve la columna de la celda
    }

    // Método para obtener el valor de la celda
    public String getValor() {
        return valor; // Devuelve el valor de la celda
    }

    // Método para revelar el valor de la celda
    public void revelar() {
        revelada = true; // Cambia el estado de la celda a revelada
    }

    // Método para ocultar el valor de la celda
    public void ocultar() {
        revelada = false; // Cambia el estado de la celda a oculta
    }

    // Método para verificar si la celda está revelada
    public boolean estaRevelada() {
        return revelada; // Devuelve el estado de la celda
    }

    // Método estático para convertir un número a su representación en inglés
    public static String numeroEnIngles(int numero) {
        switch (numero) {
            case 1: return "one"; // Devuelve "one" si el número es 1
            case 2: return "two"; // Devuelve "two" si el número es 2
            case 3: return "three"; // Devuelve "three" si el número es 3
            case 4: return "four"; // Devuelve "four" si el número es 4
            case 5: return "five"; // Devuelve "five" si el número es 5
            case 6: return "six"; // Devuelve "six" si el número es 6
            default: return ""; // Devuelve una cadena vacía si el número no está en el rango
        }
    }
}
