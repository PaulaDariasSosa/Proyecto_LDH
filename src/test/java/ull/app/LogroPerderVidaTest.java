package test.java.ull.app;

import logros.LogroPerderVida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogroPerderVidaTest {
    private LogroPerderVida logroPerderVida;

    @BeforeEach
    void setUp() {
        logroPerderVida = new LogroPerderVida();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroPerderVida.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievementWhenPlayerLosesLife() {
        // Simular el evento "LOGRO_PERDER_VIDA" con data `true` (el jugador pierde una vida)
        logroPerderVida.onEvent("LOGRO_PERDER_VIDA", true);

        // Verificar que el logro se desbloquea
        assertTrue(logroPerderVida.unlocked, "El logro debería desbloquearse cuando el jugador pierde una vida.");
    }

    @Test
    void testOnEventDoesNotUnlockWhenPlayerDoesNotLoseLife() {
        // Simular el evento "LOGRO_PERDER_VIDA" con data `false` (el jugador no pierde vida)
        logroPerderVida.onEvent("LOGRO_PERDER_VIDA", false);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroPerderVida.unlocked, "El logro no debería desbloquearse si el jugador no pierde una vida.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() {
        // Simular un evento diferente al esperado
        logroPerderVida.onEvent("OTHER_EVENT", true);

        // Verificar que el logro no se desbloquea con otros eventos
        assertFalse(logroPerderVida.unlocked, "El logro no debería desbloquearse con un evento diferente.");
    }

    @Test
    void testShowNotificationAfterUnlock() {
        // Desbloquear el logro al perder una vida
        logroPerderVida.onEvent("LOGRO_PERDER_VIDA", true);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroPerderVida::showNotification,
                "El método showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}

