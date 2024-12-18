package logros;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @brief Clase que representa un logro
 */
public class Achievement implements AchievementObserver {
    private final String name;
    private final String description;
    public boolean unlocked;
    private static final Logger LOGGER = LoggerFactory.getLogger(Achievement.class);

    /**
     * @brief Constructor de la clase Achievement
     * @param name
     * @param description
     */
    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
        this.unlocked = false;
    }

    /**
     * @brief Método observador de eventos
     * @return
     */
    @Override
    public void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException {}

    /**
     * @brief Método para desbloquear un logro
     * @return
     */
    public void unlock() {
        this.unlocked = true;
    }

    /**
     * @brief Método para mostrar una notificación
     * @throws InterruptedException
     * @throws InvocationTargetException
     */
    public void showNotification() throws InterruptedException, InvocationTargetException {
        try {
            SwingUtilities.invokeAndWait(() -> {
                JDialog dialog = new JOptionPane(
                        "¡Logro desbloqueado!: " + this.name + " - " + this.description,
                        JOptionPane.INFORMATION_MESSAGE
                ).createDialog(null, "Logro Desbloqueado");

                // Muestra el diálogo y lo cierra automáticamente después de 1 segundo
                new Timer(1000, e -> {
                    dialog.setVisible(false);
                    dialog.dispose();
                }).start();

                dialog.setVisible(true);
            });
        } catch (InterruptedException e) {
            LOGGER.error(() -> "Error al mostrar la notificación del logro: " + this.name);
            throw e;
        }
    }
}

