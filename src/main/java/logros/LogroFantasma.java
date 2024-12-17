package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo fantasma
 */
public class LogroFantasma extends Achievement {
    /**
     * @brief Constructor de la clase LogroFantasma
     */
    public LogroFantasma() {
        super("Fantasma", "Comer un fantasma");
    }

    /**
     * @brief Método observador de eventos para el logro de fantasma
     * @return
     */
    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_FANTASMA")) {
            int ghostsEaten = (int) data;
            if (ghostsEaten >= 0) {
                this.unlock();
                this.showNotification();
            }

        }
    }



}
