package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class LogroUltimaVida extends Achievement {
    public LogroUltimaVida() {
        super("Ultima Vida", "Ganar la partida con una vida restante");
    }

    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_ULTIMA_VIDA")) {
            int lives = (int) data;
            if (lives == 1) {
                this.unlock();
                this.showNotification();
            }

        }
    }


}
