package JuegoMemoriafx;

import java.util.ArrayList;
import java.util.List;

public class ControladorMemoria {
    private int jugadorActual = 1; 
    private int puntosJugadorUno = 0;
    private int puntosJugadorDos = 0;
    private Tablero tablero;
    private List<Celda> celdasReveladas = new ArrayList<>();

    public ControladorMemoria(int filas, int columnas) {
        this.tablero = new Tablero(filas, columnas);
    }

    public void inicializar() {
        tablero.generarValoresTablero();
    }

    public void manejarClickCelda(int fila, int columna) {
        Celda celda = tablero.getCelda(fila, columna);

        if (celda.isRevelada() || celdasReveladas.contains(celda)) return;

        celda.revelar();
        celdasReveladas.add(celda);

        if (celdasReveladas.size() == 2) {
            verificarPareja();
        }
    }

    private void verificarPareja() {
        Celda celda1 = celdasReveladas.get(0);
        Celda celda2 = celdasReveladas.get(1);

        if (esParejaCoincidente(celda1.getValor(), celda2.getValor())) {
            añadirPuntoAlJugadorActual();
        } else {
            celda1.ocultar();
            celda2.ocultar();
            cambiarJugador();
        }

        celdasReveladas.clear();
    }

    private boolean esParejaCoincidente(String valor1, String valor2) {
        return valor1.equals(valor2) || 
               (esNumerico(valor1) && valor1.equals(Celda.numeroEnIngles(Integer.parseInt(valor2)))) || 
               (esNumerico(valor2) && valor2.equals(Celda.numeroEnIngles(Integer.parseInt(valor1))));
    }

    private boolean esNumerico(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void añadirPuntoAlJugadorActual() {
        if (jugadorActual == 1) {
            puntosJugadorUno++;
        } else {
            puntosJugadorDos++;
        }
    }

    private void cambiarJugador() {
        jugadorActual = (jugadorActual == 1) ? 2 : 1;
    }

    public int getJugadorActual() {
        return jugadorActual;
    }

    public int getPuntosJugadorUno() {
        return puntosJugadorUno;
    }

    public int getPuntosJugadorDos() {
        return puntosJugadorDos;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
