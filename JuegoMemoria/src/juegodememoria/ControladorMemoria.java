package juegodememoria;

import java.util.ArrayList; // Importa la clase ArrayList para listas dinámicas
import java.util.Collections; // Importa la clase Collections para operaciones con colecciones
import java.util.List; // Importa la interfaz List para listas
import java.util.Scanner; // Importa la clase Scanner para entrada de datos

// Clase principal que controla el juego de memoria
public class ControladorMemoria {
    private int jugadorActual = 1; // Variable para el jugador que tiene el turno
    private int puntosJugadorUno = 0; // Puntos del Jugador 1
    private int puntosJugadorDos = 0; // Puntos del Jugador 2
    private Tablero tablero; // Instancia del Tablero que contiene el estado del juego
    private List<Celda> celdasReveladas = new ArrayList<>(); // Lista de celdas reveladas

    // Constructor que inicializa el tablero con las dimensiones dadas
    public ControladorMemoria(int filas, int columnas) {
        this.tablero = new Tablero(filas, columnas); // Crear un nuevo tablero con las dimensiones especificadas
    }

    // Método para inicializar el juego
    public void inicializar() {
        tablero.generarValoresTablero(); // Genera valores aleatorios para las celdas del tablero
        tablero.imprimirTablero(); // Imprime el tablero en la consola
        actualizarTurno(); // Muestra de quién es el turno actual
    }

    // Método para manejar el click de una celda
    public void manejarClickCelda(int fila1, int columna1, int fila2, int columna2) {
        Celda celda1 = tablero.getCelda(fila1, columna1); // Obtiene la celda en la primera posición
        Celda celda2 = tablero.getCelda(fila2, columna2); // Obtiene la celda en la segunda posición

        // Verificar si las celdas ya han sido reveladas
        if (celdasReveladas.contains(celda1) || celdasReveladas.contains(celda2)) return;

        celda1.revelar(); // Revela el contenido de la primera celda
        celda2.revelar(); // Revela el contenido de la segunda celda
        celdasReveladas.add(celda1); // Añade la primera celda a la lista de celdas reveladas
        celdasReveladas.add(celda2); // Añade la segunda celda a la lista de celdas reveladas

        // Imprime los valores de las celdas reveladas
        System.out.println("Celda 1: " + celda1.getValor());
        System.out.println("Celda 2: " + celda2.getValor());

        // Verifica si se han revelado dos celdas
        if (celdasReveladas.size() == 2) {
            verificarPareja(); // Verifica si las dos celdas reveladas forman una pareja
        }
    }

    // Método para verificar si las celdas reveladas forman una pareja
    private void verificarPareja() {
        Celda celda1 = celdasReveladas.get(0); // Obtiene la primera celda revelada
        Celda celda2 = celdasReveladas.get(1); // Obtiene la segunda celda revelada

        // Verifica si los valores de las celdas coinciden
        if (esParejaCoincidente(celda1.getValor(), celda2.getValor())) {
            añadirPuntoAlJugadorActual(); // Añade un punto al jugador actual si hay coincidencia
            System.out.println("¡Pareja encontrada!");
        } else {
            System.out.println("No es una pareja.");
            celda1.ocultar(); // Oculta la primera celda si no hay coincidencia
            celda2.ocultar(); // Oculta la segunda celda si no hay coincidencia
            cambiarJugador(); // Cambia el turno al otro jugador
            actualizarTurno(); // Actualiza la información del turno
        }

        celdasReveladas.clear(); // Vacía la lista de celdas reveladas para la siguiente ronda
    }

    // Método para verificar si dos valores forman una pareja coincidente
    private boolean esParejaCoincidente(String valor1, String valor2) {
        return valor1.equals(valor2) || 
               (esNumerico(valor1) && valor1.equals(Celda.numeroEnIngles(Integer.parseInt(valor2)))) || 
               (esNumerico(valor2) && valor2.equals(Celda.numeroEnIngles(Integer.parseInt(valor1))));
    }

    // Método para verificar si una cadena es numérica
    private boolean esNumerico(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para añadir un punto al jugador actual
    private void añadirPuntoAlJugadorActual() {
        if (jugadorActual == 1) {
            puntosJugadorUno++; // Incrementa el puntaje del Jugador 1
            System.out.println("Puntos Jugador 1: " + puntosJugadorUno);
        } else {
            puntosJugadorDos++; // Incrementa el puntaje del Jugador 2
            System.out.println("Puntos Jugador 2: " + puntosJugadorDos);
        }
    }

    // Método para cambiar el turno al otro jugador
    private void cambiarJugador() {
        jugadorActual = (jugadorActual == 1) ? 2 : 1; // Cambia el turno al otro jugador
    }

    // Método para actualizar y mostrar el turno actual
    private void actualizarTurno() {
        System.out.println("Turno del Jugador " + jugadorActual);
    }
}