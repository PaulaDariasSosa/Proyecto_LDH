package test.java.ull.app;
import logros.LogroPrimeraVictoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class LogroPrimeraVictoriaTest {
    private LogroPrimeraVictoria logroPrimeraVictoria;

    @BeforeEach
    void setUp() {
        logroPrimeraVictoria = new LogroPrimeraVictoria();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroPrimeraVictoria.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievementAtLevel1() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_PRIMERA_VICTORIA" con nivel 1
        logroPrimeraVictoria.onEvent("LOGRO_PRIMERA_VICTORIA", 1);

        // Verificar que el logro se desbloquea
        assertTrue(logroPrimeraVictoria.unlocked, "El logro debería desbloquearse al alcanzar el nivel 1.");
    }

    @Test
    void testOnEventUnlocksAchievementAtHigherLevel() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_PRIMERA_VICTORIA" con nivel 5
        logroPrimeraVictoria.onEvent("LOGRO_PRIMERA_VICTORIA", 5);

        // Verificar que el logro también se desbloquea en niveles superiores
        assertTrue(logroPrimeraVictoria.unlocked, "El logro debería desbloquearse al alcanzar niveles superiores.");
    }

    @Test
    void testOnEventDoesNotUnlockForLevelZero() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_PRIMERA_VICTORIA" con nivel 0
        logroPrimeraVictoria.onEvent("LOGRO_PRIMERA_VICTORIA", 0);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroPrimeraVictoria.unlocked, "El logro no debería desbloquearse si el nivel es menor a 1.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() throws InterruptedException, InvocationTargetException {
        // Simular un evento diferente
        logroPrimeraVictoria.onEvent("OTHER_EVENT", 1);

        // Verificar que el logro no se desbloquea con otro evento
        assertFalse(logroPrimeraVictoria.unlocked, "El logro no debería desbloquearse con eventos diferentes.");
    }

    @Test
    void testShowNotificationAfterUnlock() throws InterruptedException, InvocationTargetException {
        // Desbloquear el logro al alcanzar el nivel 1
        logroPrimeraVictoria.onEvent("LOGRO_PRIMERA_VICTORIA", 1);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroPrimeraVictoria::showNotification,
                "El método showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}

