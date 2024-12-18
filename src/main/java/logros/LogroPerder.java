package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo perder
 */
public class LogroPerder extends Achievement {
    /**
     * @brief Constructor de la clase LogroPerder
     */
    public LogroPerder() {
        super("En la vida no siempre se gana", "Perder la partida");
    }

    /**
     * @brief Método observador de eventos para el logro de perder
     * @return
     */
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
