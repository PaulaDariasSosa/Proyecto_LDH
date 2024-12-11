package logros;

import java.util.Objects;

public class LogroPerderVida extends Achievement {
    public LogroPerderVida() {
        super("Siempre hay un primera vez", "Perder una vida");
    }

    @Override
    public void onEvent(String event, Object data) {
        if (!this.unlocked && Objects.equals(event, "LOGRO_PERDER_VIDA")) {
            boolean perdioVida = (boolean) data;
            if (perdioVida) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
