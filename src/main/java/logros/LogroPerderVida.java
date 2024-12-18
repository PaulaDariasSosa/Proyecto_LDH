package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo perder vida
 */
public class LogroPerderVida extends Achievement {
    /**
     * @brief Constructor de la clase LogroPerderVida
     */
    public LogroPerderVida() {
        super("Siempre hay un primera vez", "Perder una vida");
    }

    /**
     * @brief Método observador de eventos para el logro de perder vida
     * @return
     */
    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_PERDER_VIDA")) {
            boolean perdioVida = (boolean) data;
            if (perdioVida) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
