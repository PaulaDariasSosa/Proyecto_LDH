package logros;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class LogroFantasma extends Achievement {
    public LogroFantasma() {
        super("Fantasma", "Comer un fantasma");
    }


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
