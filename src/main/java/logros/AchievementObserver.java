package logros;

import java.lang.reflect.InvocationTargetException;

public interface AchievementObserver {
    void onEvent(String event, Object data) throws InterruptedException, InvocationTargetException;
}
