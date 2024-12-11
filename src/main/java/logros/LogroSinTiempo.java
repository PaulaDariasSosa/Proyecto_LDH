package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class LogroSinTiempo extends Achievement {
    public LogroSinTiempo() {
        super("Se acabó lo que se daba", "Se ha terminado el tiempo");
    }

    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_SIN_TIEMPO")) {
            boolean sinTiempo = (boolean) data;
            if (sinTiempo) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
