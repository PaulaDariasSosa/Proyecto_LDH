package logros;

import es.ull.app.ShadowPac;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import javax.swing.*;

public class Achievement implements AchievementObserver {
    private final String name;
    private final String description;
    public boolean unlocked;
    private static final Logger LOGGER = LoggerFactory.getLogger(Achievement.class);


    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
        this.unlocked = false;
    }

    @Override
    public void onEvent(String event, Object data) {}

    public void unlock() {
        this.unlocked = true;

    }

    public void showNotification() {
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
        } catch (Exception e) {
            LOGGER.error(() -> "Error al mostrar la notificación del logro: " + this.name);
        }
    }


}

