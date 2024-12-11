package test.java.ull.app;

import logros.LogroSinTiempo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class LogroSinTiempoTest {
    private LogroSinTiempo logroSinTiempo;

    @BeforeEach
    void setUp() {
        logroSinTiempo = new LogroSinTiempo();
    }

    @Test
    void testInitialState() {
        // Verificar que el logro no está desbloqueado inicialmente
        assertFalse(logroSinTiempo.unlocked, "El logro debería estar bloqueado inicialmente.");
    }

    @Test
    void testOnEventUnlocksAchievementWhenTimeRunsOut() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_SIN_TIEMPO" con data `true` (se queda sin tiempo)
        logroSinTiempo.onEvent("LOGRO_SIN_TIEMPO", true);

        // Verificar que el logro se desbloquea
        assertTrue(logroSinTiempo.unlocked, "El logro debería desbloquearse cuando se acaba el tiempo.");
    }

    @Test
    void testOnEventDoesNotUnlockWhenTimeIsNotExpired() throws InterruptedException, InvocationTargetException {
        // Simular el evento "LOGRO_SIN_TIEMPO" con data `false` (el tiempo no se ha acabado)
        logroSinTiempo.onEvent("LOGRO_SIN_TIEMPO", false);

        // Verificar que el logro sigue bloqueado
        assertFalse(logroSinTiempo.unlocked, "El logro no debería desbloquearse si el tiempo no se ha acabado.");
    }

    @Test
    void testOnEventDoesNotUnlockForOtherEvents() throws InterruptedException, InvocationTargetException {
        // Simular un evento diferente al esperado
        logroSinTiempo.onEvent("OTHER_EVENT", true);

        // Verificar que el logro no se desbloquea con otro evento
        assertFalse(logroSinTiempo.unlocked, "El logro no debería desbloquearse con un evento diferente.");
    }

    @Test
    void testShowNotificationAfterUnlock() throws InterruptedException, InvocationTargetException {
        // Desbloquear el logro cuando el tiempo se acaba
        logroSinTiempo.onEvent("LOGRO_SIN_TIEMPO", true);

        // Verificar que no se lanzan excepciones al mostrar la notificación
        assertDoesNotThrow(logroSinTiempo::showNotification,
                "El método showNotification() no debería lanzar excepciones después de desbloquear el logro.");
    }
}
