package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo primera victoria
 */
public class LogroPrimeraVictoria extends Achievement {
    /**
     * @brief Constructor de la clase LogroPrimeraVictoria
     */
    public LogroPrimeraVictoria() {
        super("Primera Victoria", "Ganar la primera partida");
    }

    /**
     * @brief Método observador de eventos para el logro de primera victoria
     * @return
     */
    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_PRIMERA_VICTORIA")) {
            int level = (int) data;
            if (level >= 1) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
