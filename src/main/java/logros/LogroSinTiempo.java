package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo sin tiempo
 */
public class LogroSinTiempo extends Achievement {
    /**
     * @brief Constructor de la clase LogroSinTiempo
     */
    public LogroSinTiempo() {
        super("Se acabó lo que se daba", "Se ha terminado el tiempo");
    }

    /**
     * @brief Método observador de eventos para el logro de sin tiempo
     * @return
     */
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
