import bagel.Font;
import bagel.Image;
import bagel.util.Point;

public class Timer {
    private final static Image CLOCk_IMAGE = new Image("res/timer.png");
    private final static Point CLOCK_POINT = new Point(750, 24);
    private final static int FONT_SIZE = 20;
    private final static Font TIMER_FONT = new Font("res/FSO8BITR.ttf", FONT_SIZE);
    private final static Point TIMER_POINT = new Point(770, 30);

    private final static int TOTAL_TIME = 50;
    private final static int FRAME_RATE = 60;
    private int time;
    private int frameInSecond;
    public Timer() {
        time = TOTAL_TIME;
        frameInSecond = FRAME_RATE;
    }
    public void update() {
        CLOCk_IMAGE.draw(CLOCK_POINT.x, CLOCK_POINT.y);
        TIMER_FONT.drawString(String.valueOf(time), TIMER_POINT.x, TIMER_POINT.y);

        frameInSecond--;
        if (frameInSecond == 0) {
            time--;
            frameInSecond = FRAME_RATE;
        }
    }

    public boolean timesUp() {
        return time == 0;
    }
}
