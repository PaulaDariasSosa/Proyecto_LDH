package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class LogroPerder extends Achievement {
    public LogroPerder() {
        super("En la vida no siempre se gana", "Perder la partida");
    }

    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_PERDER")) {
            boolean perdioVida = (boolean) data;
            if (perdioVida) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
