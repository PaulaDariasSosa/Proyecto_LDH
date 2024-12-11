package logros;

import javax.swing.*;
import java.util.Objects;

public class LogroVictoriaFinal extends Achievement {
    public LogroVictoriaFinal() {
        super("Victoria Final", "Ganar la partida final");
    }

    @Override
    public void onEvent(String event, Object data) {
        if (!this.unlocked && Objects.equals(event, "LOGRO_VICTORIA_FINAL")) {
            boolean level = (boolean) data;
            if (level) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
