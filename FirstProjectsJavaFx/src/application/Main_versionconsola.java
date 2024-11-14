// Clase principal para ejecutar el juego en version consola
public class Main {
    public static void main(String[] args) {
        ControladorMemoria juego = new ControladorMemoria(3, 4); // Crea una nueva instancia del juego con un tablero de 3x4
        juego.inicializar(); // Inicializa el juego
    }
}
