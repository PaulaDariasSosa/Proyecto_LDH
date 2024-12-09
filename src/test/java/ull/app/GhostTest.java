package test.java.ull.app;

import bagel.util.Point;
import es.ull.app.Ghost;
import es.ull.app.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GhostTest {

    private Ghost ghost;
    private ArrayList<Wall> walls;

    // Clase de prueba para Ghost
    class TestGhost extends Ghost {
        public TestGhost(Point topLeft, double speed) {
            super(topLeft, speed);
        }

        @Override
        public void changeDirection() {
            setDirection((getDirection() + 1) % 4); // Cambio simple de dirección
        }
    }

    @BeforeEach
    void setUp() {
        ghost = new TestGhost(new Point(100, 100), 2.0);
        walls = new ArrayList<>();
    }
}
