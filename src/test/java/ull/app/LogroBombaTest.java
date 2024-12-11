package test.java.ull.app;

import logros.LogroBomba;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogroBombaTest {
    private LogroBomba logroBomba;

    @BeforeEach
    void setUp() {
        logroBomba = new LogroBomba();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroBomba.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievement() {
        // Simular el evento "LOGRO_BOMBA" con al menos una bomba utilizada
        logroBomba.onEvent("LOGRO_BOMBA", 1);

        // Verificar que el logro se desbloquea
        assertTrue(logroBomba.unlocked, "El logro debería desbloquearse cuando se usa una bomba.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() {
        // Simular un evento diferente
        logroBomba.onEvent("OTHER_EVENT", 1);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroBomba.unlocked, "El logro no debería desbloquearse con un evento diferente.");
    }

    @Test
    void testOnEventDoesNotUnlockIfBombsEatenIsZero() {
        // Simular el evento "LOGRO_BOMBA" con 0 bombas utilizadas
        logroBomba.onEvent("LOGRO_BOMBA", 0);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroBomba.unlocked, "El logro no debería desbloquearse si no se usan bombas.");
    }

    @Test
    void testShowNotificationAfterUnlock() {
        // Simular el evento para desbloquear el logro
        logroBomba.onEvent("LOGRO_BOMBA", 1);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroBomba::showNotification,
                "showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}

