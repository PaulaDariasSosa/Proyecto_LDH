package test.java.ull.app;

import logros.LogroFantasma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogroFantasmaTest {
    private LogroFantasma logroFantasma;

    @BeforeEach
    void setUp() {
        logroFantasma = new LogroFantasma();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroFantasma.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievement() {
        // Simular el evento "LOGRO_FANTASMA" con al menos un fantasma comido
        logroFantasma.onEvent("LOGRO_FANTASMA", 1);

        // Verificar que el logro se desbloquea
        assertTrue(logroFantasma.unlocked, "El logro debería desbloquearse cuando se come un fantasma.");
    }

    @Test
    void testOnEventUnlocksAchievementWithZeroGhosts() {
        // Simular el evento "LOGRO_FANTASMA" con 0 fantasmas comidos
        logroFantasma.onEvent("LOGRO_FANTASMA", 0);

        // Verificar que el logro también se desbloquea
        assertTrue(logroFantasma.unlocked, "El logro debería desbloquearse incluso si se comen 0 fantasmas.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() {
        // Simular un evento diferente
        logroFantasma.onEvent("OTHER_EVENT", 1);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroFantasma.unlocked, "El logro no debería desbloquearse con un evento diferente.");
    }

    @Test
    void testShowNotificationAfterUnlock() {
        // Desbloquear el logro
        logroFantasma.onEvent("LOGRO_FANTASMA", 1);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroFantasma::showNotification,
                "showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}
