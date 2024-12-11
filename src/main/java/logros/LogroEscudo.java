package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class LogroEscudo extends Achievement {
    public LogroEscudo() {
        super("Salvado por los pelos", "Usar un escudo");
    }

    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_ESCUDO")) {
            boolean shieldsUsed = (boolean) data;
            if (shieldsUsed) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
