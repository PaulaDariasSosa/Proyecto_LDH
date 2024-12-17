package logros;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @brief Clase que representa un logro de tipo ultima vida
 */
public class LogroUltimaVida extends Achievement {
    /**
     * @brief Constructor de la clase LogroUltimaVida
     */
    public LogroUltimaVida() {
        super("Ultima Vida", "Ganar la partida con una vida restante");
    }

    /**
     * @brief Método observador de eventos para el logro de ultima vida
     * @return
     */
    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {
        if (!this.unlocked && Objects.equals(event, "LOGRO_ULTIMA_VIDA")) {
            int lives = (int) data;
            if (lives == 1) {
                this.unlock();
                this.showNotification();
            }

        }
    }


}
