package logros;

import java.util.Objects;

public class LogroEscudo extends Achievement {
    public LogroEscudo() {
        super("Salvado por los pelos", "Usar un escudo");
    }

    @Override
    public void onEvent(String event, Object data) {
        if (!this.unlocked && Objects.equals(event, "LOGRO_ESCUDO")) {
            boolean shieldsUsed = (boolean) data;
            if (shieldsUsed) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
