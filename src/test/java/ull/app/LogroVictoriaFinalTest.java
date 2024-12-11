package test.java.ull.app;

import logros.LogroVictoriaFinal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogroVictoriaFinalTest {
    private LogroVictoriaFinal logroVictoriaFinal;

    @BeforeEach
    void setUp() {
        logroVictoriaFinal = new LogroVictoriaFinal();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroVictoriaFinal.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievementWhenGameIsWon() {
        // Simular el evento "LOGRO_VICTORIA_FINAL" con data `true` (se gana la partida final)
        logroVictoriaFinal.onEvent("LOGRO_VICTORIA_FINAL", true);

        // Verificar que el logro se desbloquea
        assertTrue(logroVictoriaFinal.unlocked, "El logro debería desbloquearse cuando el jugador gana la partida final.");
    }

    @Test
    void testOnEventDoesNotUnlockWhenGameIsNotWon() {
        // Simular el evento "LOGRO_VICTORIA_FINAL" con data `false` (no se gana la partida final)
        logroVictoriaFinal.onEvent("LOGRO_VICTORIA_FINAL", false);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroVictoriaFinal.unlocked, "El logro no debería desbloquearse si el jugador no gana la partida final.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() {
        // Simular un evento diferente
        logroVictoriaFinal.onEvent("OTHER_EVENT", true);

        // Verificar que el logro no se desbloquea con otro evento
        assertFalse(logroVictoriaFinal.unlocked, "El logro no debería desbloquearse con eventos no esperados.");
    }

    @Test
    void testShowNotificationAfterUnlock() {
        // Desbloquear el logro cuando el jugador gana la partida final
        logroVictoriaFinal.onEvent("LOGRO_VICTORIA_FINAL", true);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroVictoriaFinal::showNotification,
                "El método showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}

