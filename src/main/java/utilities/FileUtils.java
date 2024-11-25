package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief FileUtils class
 */
public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());
    /**
     * @brief Guardar la puntuación en un archivo
     * @param playerName
     * @param highScore
     */
    public static void saveScore(String playerName, int highScore) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("puntuaciones.txt", true))) {
            bw.write("Nombre: " + playerName);
            bw.newLine();
            bw.write("Highscore: " + highScore);
            bw.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while writing to the file.", e);
        }
    }
}
