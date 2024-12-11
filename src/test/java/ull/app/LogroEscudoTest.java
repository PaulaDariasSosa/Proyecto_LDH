package test.java.ull.app;

import logros.LogroEscudo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogroEscudoTest {
    private LogroEscudo logroEscudo;

    @BeforeEach
    void setUp() {
        logroEscudo = new LogroEscudo();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroEscudo.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievement() {
        // Simular el evento "LOGRO_BOMBA" con al menos una bomba utilizada
        logroEscudo.onEvent("LOGRO_ESCUDO", true);

        // Verificar que el logro se desbloquea
        assertTrue(logroEscudo.unlocked, "El logro debería desbloquearse cuando se usa un escudo.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() {
        // Simular un evento diferente
        logroEscudo.onEvent("OTHER_EVENT", true);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroEscudo.unlocked, "El logro no debería desbloquearse con un evento diferente.");
    }

    @Test
    void testOnEventDoesNotUnlockIfShieldsEatenIsZero() {
        logroEscudo.onEvent("LOGRO_ESCUDO", false);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroEscudo.unlocked, "El logro no debería desbloquearse si no se usan escudos.");
    }

    @Test
    void testShowNotificationAfterUnlock() {
        // Simular el evento para desbloquear el logro
        logroEscudo.onEvent("LOGRO_ESCUDO", true);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroEscudo::showNotification,
                "showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}
