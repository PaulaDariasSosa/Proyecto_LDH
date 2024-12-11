package test.java.ull.app;

import logros.Achievement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AchievementTest {
    private Achievement achievement;

    @BeforeEach
    void setUp() {
        achievement = new Achievement("Primer Logro", "Desbloquea tu primer logro.");
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado al inicio
        assertFalse(achievement.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testUnlock() {
        // Desbloquear el logro
        achievement.unlock();
        // Verificar que el logro ahora está desbloqueado
        assertTrue(achievement.unlocked, "El logro debería estar desbloqueado después de llamar a unlock().");
    }

    @Test
    void testShowNotification() {
        // Este test verifica que no se lanza una excepción al mostrar la notificación
        // Aunque la ventana no puede verificarse directamente aquí, se asegura de que el método funcione sin errores.
        achievement.unlock(); // Desbloquear antes de mostrar la notificación
        assertDoesNotThrow(achievement::showNotification, "showNotification() no debería lanzar excepciones.");
    }

    @Test
    void testOnEvent() {
        // Test básico para verificar que onEvent no altera el estado por defecto
        achievement.onEvent("EventoPrueba", null);
        assertFalse(achievement.unlocked, "onEvent no debería modificar el estado del logro.");
    }
}

