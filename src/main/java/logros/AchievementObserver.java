package logros;

import java.lang.reflect.InvocationTargetException;

/**
 * @brief Interfaz que representa un observador de logros
 */
public interface AchievementObserver {
    void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException;
}
