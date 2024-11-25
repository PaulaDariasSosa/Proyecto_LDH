package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @brief FileUtils class
 */
public class FileUtils {
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
            e.printStackTrace();
        }
    }
}
