package test.java.ull.app;

import bagel.Input;
import bagel.Keys;
import es.ull.app.ShadowPac;
import es.ull.app.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShadowPacTest {
    @Test
    public void testConstructor_Default() {
        ShadowPac game = new ShadowPac();

        assertNotNull(game);
    }

    @Test
    public void testConstructor_WithUser() {
        User user = new User("TestUser");
        ShadowPac game = new ShadowPac(user);

        assertNotNull(game);
        assertEquals(user.getName(), "TestUser");
    }

    @Test
    public void testResetGame() {
        User user = new User("TestUser");
        ShadowPac game = new ShadowPac(user);
        game.resetGame();
        assertEquals(game.getPlayerScore(), 0);
    }

    @Test
    public void testGetPlayerScore() {
        User user = new User("TestUser");
        ShadowPac game = new ShadowPac(user);
        assertEquals(game.getPlayerScore(), 0);
    }


}
