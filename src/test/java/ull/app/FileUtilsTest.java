package test.java.ull.app;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUtilsTest {

    private static final String TEST_FILE_NAME = "puntuaciones.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Elimina el archivo si existe antes de cada prueba
        File testFile = new File(TEST_FILE_NAME);
        if (testFile.exists()) {
            testFile.delete();
        }
        testFile.createNewFile();
    }

    @AfterEach
    void tearDown() {
        // Limpia el archivo después de cada prueba
        File testFile = new File(TEST_FILE_NAME);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    void testSaveScore() throws IOException {
        String playerName = "Player1";
        int highScore = 100;

        // Llama al método saveScore
        FileUtils.saveScore(playerName, highScore);

        // Verifica que los datos se hayan guardado correctamente
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_NAME))) {
            String line1 = br.readLine();
            String line2 = br.readLine();

            assertEquals("Nombre: Player1", line1);
            assertEquals("Highscore: 100", line2);
        }
    }

    @Test
    void testSaveScoreAppend() throws IOException {
        // Prueba para verificar que el archivo permite múltiples entradas
        String playerName1 = "Player1";
        int highScore1 = 100;
        String playerName2 = "Player2";
        int highScore2 = 200;

        // Llama al método saveScore dos veces
        FileUtils.saveScore(playerName1, highScore1);
        FileUtils.saveScore(playerName2, highScore2);

        // Verifica que ambas entradas se hayan guardado
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_NAME))) {
            assertEquals("Nombre: Player1", br.readLine());
            assertEquals("Highscore: 100", br.readLine());
            assertEquals("Nombre: Player2", br.readLine());
            assertEquals("Highscore: 200", br.readLine());
        }
    }

}


