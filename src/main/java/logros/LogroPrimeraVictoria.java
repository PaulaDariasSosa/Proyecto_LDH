package logros;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class LogroPrimeraVictoria extends Achievement {
    public LogroPrimeraVictoria() {
        super("Primera Victoria", "Ganar la primera partida");
    }

    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_PRIMERA_VICTORIA")) {
            int level = (int) data;
            if (level >= 1) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
