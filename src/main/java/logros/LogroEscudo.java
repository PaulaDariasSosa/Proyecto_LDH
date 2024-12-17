package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo escudo
 */
public class LogroEscudo extends Achievement {
    /**
     * @brief Constructor de la clase LogroEscudo
     */
    public LogroEscudo() {
        super("Salvado por los pelos", "Usar un escudo");
    }

    /**
     * @brief Método observador de eventos para el logro de escudo
     * @return
     */
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
