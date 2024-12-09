package test.java.ull.app;

import es.ull.app.Dot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DotTest {

    @Test
    void testPointsValue() {
        // Verificar que el valor de los puntos es el esperado
        assertEquals(10, Dot.POINTS);
    }

}
