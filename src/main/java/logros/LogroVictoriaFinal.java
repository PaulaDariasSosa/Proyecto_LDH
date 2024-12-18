package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo victoria final
 */
public class LogroVictoriaFinal extends Achievement {
    /**
     * @brief Constructor de la clase LogroVictoriaFinal
     */
    public LogroVictoriaFinal() {
        super("Victoria Final", "Ganar la partida final");
    }

    /**
     * @brief Método observador de eventos para el logro de victoria final
     * @return
     */
    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_VICTORIA_FINAL")) {
            boolean level = (boolean) data;
            if (level) {
                this.unlock();
                this.showNotification();
            }

        }
    }
}
