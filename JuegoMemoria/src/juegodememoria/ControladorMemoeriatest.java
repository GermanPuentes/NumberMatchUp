import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ControladorMemoriaTest {
    @Test
    public void testInicializar() {
        ControladorMemoria juego = new ControladorMemoria(3, 4);
        juego.inicializar();
        assertNotNull(juego.getTablero());
    }
}