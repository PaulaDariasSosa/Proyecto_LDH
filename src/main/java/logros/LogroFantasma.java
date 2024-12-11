package logros;

import javax.swing.*;

public class LogroFantasma extends Achievement {
    public LogroFantasma() {
        super("Fantasma", "Comer un fantasma");
    }


    @Override
    public void onEvent(String event, Object data) {
        if (!this.unlocked && event == "LOGRO_FANTASMA") {
            int ghostsEaten = (int) data;
            if (ghostsEaten >= 0) {
                this.unlock();
                this.showNotification();
            }

        }
    }



}
