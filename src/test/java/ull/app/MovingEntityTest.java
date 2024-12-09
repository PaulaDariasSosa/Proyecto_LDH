package test.java.ull.app;

import es.ull.app.MovingEntity;
import es.ull.app.Wall;
import bagel.util.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class MovingEntityTest {

    private MovingEntity movingEntity;
    private ArrayList<Wall> walls;

    @BeforeEach
    void setUp() {
        // Configuración antes de cada test
        walls = new ArrayList<>();

        // Creamos un objeto MovingEntity para probar
        movingEntity = new MovingEntity(new Point(10, 10), 1.0, 2.0) {
            @Override
            public boolean canMove(ArrayList<Wall> walls) {
                // Implementación simple para pasar la prueba
                return true;
            }
        };
    }

    @Test
    void testGoLeft() {
        movingEntity.goLeft(walls, false);
        assertEquals(new Point(9, 10), movingEntity.getPosition());
    }

    @Test
    void testGoRight() {
        movingEntity.goRight(walls, false);
        assertEquals(new Point(11, 10), movingEntity.getPosition());
    }

    @Test
    void testGoUp() {
        movingEntity.goUp(walls, false);
        assertEquals(new Point(10, 9), movingEntity.getPosition());
    }

    @Test
    void testGoDown() {
        movingEntity.goDown(walls, false);
        assertEquals(new Point(10, 11), movingEntity.getPosition());
    }

    @Test
    void testGoLeftFrenzyMode() {
        movingEntity.goLeft(walls, true);
        assertEquals(new Point(8, 10), movingEntity.getPosition()); // Frenzy speed is 2.0
    }

    @Test
    void testGoRightFrenzyMode() {
        movingEntity.goRight(walls, true);
        assertEquals(new Point(12, 10), movingEntity.getPosition());
    }

    @Test
    void testStartRespawn() {
        movingEntity.startRespawn();
        assertFalse(movingEntity.isActive());
        assertTrue(movingEntity.isRespawning());
    }

    @Test
    void testRespawn() {
        movingEntity.startRespawn();

        // Simulamos el paso del tiempo llamando varias veces a respawn()
        for (int i = 0; i < 100; i++) {
            movingEntity.respawn(); // Reducir el contador cada vez
        }

        // Verificamos que la entidad se haya activado y no esté en estado de respawn
        assertTrue(movingEntity.isActive());
        assertFalse(movingEntity.isRespawning());
        assertEquals(new Point(10, 10), movingEntity.getPosition());
    }


    @Test
    void testResetPosition() {
        movingEntity.goRight(walls, false); // Moving the entity first
        movingEntity.resetPosition();
        assertEquals(new Point(10, 10), movingEntity.getPosition()); // Position should reset
    }
}
