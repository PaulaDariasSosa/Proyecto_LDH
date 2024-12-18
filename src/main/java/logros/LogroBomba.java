package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo bomba
 */
public class LogroBomba extends Achievement {
    /**
     * @brief Constructor de la clase LogroBomba
     */
    public LogroBomba() {
        super("Explota, explótame, expló'", "Usa una bomba");
    }

    /**
     * @brief Método observador de eventos para el logro de bomba
     * @return
     */
    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_BOMBA")) {
            int bombsEaten = (int) data;
            if (bombsEaten >= 1) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
