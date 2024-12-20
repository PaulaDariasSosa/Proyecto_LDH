package test.java.ull.app;
import logros.LogroPerder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class LogroPerderTest {
    private LogroPerder logroPerder;

    @BeforeEach
    void setUp() {
        logroPerder = new LogroPerder();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroPerder.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievementWhenPlayerLosesLife() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_PERDER" con data `true` (el jugador pierde una vida)
        logroPerder.onEvent("LOGRO_PERDER", true);

        // Verificar que el logro se desbloquea
        assertTrue(logroPerder.unlocked, "El logro debería desbloquearse cuando el jugador pierde una vida.");
    }

    @Test
    void testOnEventDoesNotUnlockWhenPlayerDoesNotLoseLife() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_PERDER" con data `false` (el jugador no pierde vida)
        logroPerder.onEvent("LOGRO_PERDER", false);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroPerder.unlocked, "El logro no debería desbloquearse si el jugador no pierde una vida.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() throws InterruptedException, InvocationTargetException {
        // Simular un evento diferente
        logroPerder.onEvent("OTHER_EVENT", true);

        // Verificar que el logro no se desbloquea
        assertFalse(logroPerder.unlocked, "El logro no debería desbloquearse con un evento diferente.");
    }

    @Test
    void testShowNotificationAfterUnlock() throws InterruptedException, InvocationTargetException {
        // Desbloquear el logro al perder una vida
        logroPerder.onEvent("LOGRO_PERDER", true);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroPerder::showNotification,
                "showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}

