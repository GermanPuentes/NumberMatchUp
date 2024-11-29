package JuegoMemoriafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int FILAS = 3;
    private static final int COLUMNAS = 4;
    private ControladorMemoria controladorMemoria;
    private Button[][] botones;
    private Label turnoLabel;
    private Label puntosJugadorUnoLabel;
    private Label puntosJugadorDosLabel;

    @Override
    public void start(Stage primaryStage) {
        controladorMemoria = new ControladorMemoria(FILAS, COLUMNAS);
        controladorMemoria.inicializar();

        GridPane gridPane = new GridPane();
        botones = new Button[FILAS][COLUMNAS];
        turnoLabel = new Label("Turno del Jugador " + controladorMemoria.getJugadorActual());
        puntosJugadorUnoLabel = new Label("Puntos Jugador 1: " + controladorMemoria.getPuntosJugadorUno());
        puntosJugadorDosLabel = new Label("Puntos Jugador 2: " + controladorMemoria.getPuntosJugadorDos());

        gridPane.add(turnoLabel, 0, FILAS, COLUMNAS, 1);
        gridPane.add(puntosJugadorUnoLabel, 0, FILAS + 1, COLUMNAS, 1);
        gridPane.add(puntosJugadorDosLabel, 0, FILAS + 2, COLUMNAS, 1);

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                final int f = fila;
                final int c = columna;
                botones[fila][columna] = new Button();
                botones[fila][columna].setPrefSize(100, 100);
                botones[fila][columna].setOnAction(e -> manejarClickBoton(f, c));
                gridPane.add(botones[fila][columna], columna, fila);
            }
        }

        Scene scene = new Scene(gridPane, 400, 500);
        primaryStage.setTitle("Juego de Memoria");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void manejarClickBoton(int fila, int columna) {
        controladorMemoria.manejarClickCelda(fila, columna);
        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                Celda celda = controladorMemoria.getTablero().getCelda(fila, columna);
                if (celda.isRevelada()) {
                    botones[fila][columna].setText(celda.getValor());
                } else {
                    botones[fila][columna].setText("");
                }
            }
        }

        turnoLabel.setText("Turno del Jugador " + controladorMemoria.getJugadorActual());
        puntosJugadorUnoLabel.setText("Puntos Jugador 1: " + controladorMemoria.getPuntosJugadorUno());
        puntosJugadorDosLabel.setText("Puntos Jugador 2: " + controladorMemoria.getPuntosJugadorDos());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
