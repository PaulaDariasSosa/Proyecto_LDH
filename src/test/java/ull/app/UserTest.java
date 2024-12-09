package test.java.ull.app;

import static org.junit.jupiter.api.Assertions.*;

import es.ull.app.User;
import org.junit.jupiter.api.Test;

        public class UserTest {

            @Test
            public void testConstructorPorDefecto() {
                User user = new User();
                assertEquals("", user.getName(), "El nombre debería estar vacío.");
            }

            @Test
            public void testConstructorConParametros() {
                User user = new User("Juan");
                assertEquals("Juan", user.getName(), "El nombre debería ser 'Juan'.");
            }

            @Test
            public void testSetName() {
                User user = new User();
                user.setName("Carlos");
                assertEquals("Carlos", user.getName(), "El nombre debería ser 'Carlos'.");
            }

            @Test
            public void testAskForName() {
                User user = new User();
                // Suponiendo que el método askForName se modifica para aceptar entradas automáticas en un test
                user.askForName();
                assertNotNull(user.getName(), "El nombre no debería ser nulo después de ingresar uno.");
            }
        }
