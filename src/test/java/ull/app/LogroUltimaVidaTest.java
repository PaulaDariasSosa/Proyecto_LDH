package test.java.ull.app;

import logros.LogroUltimaVida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class LogroUltimaVidaTest {
    private LogroUltimaVida logroUltimaVida;

    @BeforeEach
    void setUp() {
        logroUltimaVida = new LogroUltimaVida();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroUltimaVida.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievementWithOneLifeRemaining() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_ULTIMA_VIDA" con data 1 (una vida restante)
        logroUltimaVida.onEvent("LOGRO_ULTIMA_VIDA", 1);

        // Verificar que el logro se desbloquea al tener una vida restante
        assertTrue(logroUltimaVida.unlocked, "El logro debería desbloquearse cuando el jugador tiene solo una vida restante.");
    }

    @Test
    void testOnEventDoesNotUnlockWithMoreThanOneLifeRemaining() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_ULTIMA_VIDA" con data 2 (más de una vida restante)
        logroUltimaVida.onEvent("LOGRO_ULTIMA_VIDA", 2);

        // Verificar que el logro no se desbloquea con más de una vida restante
        assertFalse(logroUltimaVida.unlocked, "El logro no debería desbloquearse si el jugador tiene más de una vida restante.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() throws InterruptedException, InvocationTargetException {
        // Simular un evento diferente
        logroUltimaVida.onEvent("OTHER_EVENT", 1);

        // Verificar que el logro no se desbloquea con otro evento
        assertFalse(logroUltimaVida.unlocked, "El logro no debería desbloquearse con eventos diferentes.");
    }

    @Test
    void testShowNotificationAfterUnlock() throws InterruptedException, InvocationTargetException {
        // Desbloquear el logro cuando el jugador tiene una vida restante
        logroUltimaVida.onEvent("LOGRO_ULTIMA_VIDA", 1);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroUltimaVida::showNotification,
                "El método showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}

