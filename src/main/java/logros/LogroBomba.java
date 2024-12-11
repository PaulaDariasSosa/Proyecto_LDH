package logros;

import java.util.Objects;

public class LogroBomba extends Achievement {
    public LogroBomba() {
        super("Explota, explótame, expló'", "Usa una bomba");
    }

    @Override
    public void onEvent(String event, Object data) {
        if (!this.unlocked && Objects.equals(event, "LOGRO_BOMBA")) {
            int bombsEaten = (int) data;
            if (bombsEaten >= 1) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
