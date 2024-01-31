package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.List;

public class Maze {
    private static final Logger logger = LogManager.getLogger();

    public static char[][] MazeRead(String inputFile) {
        try {
            List<List<Character>> mazeList = MazeInput.getLists(inputFile);
            return convertListTo2DArray(mazeList);
        } catch (IOException e) {
            logger.error("/!\\ An error has occurred /!\\");
            return null;
        }
    }

    private static char[][] convertListTo2DArray(List<List<Character>> mazeList) {
        char[][] mazeArray = new char[mazeList.size()][];
        if (!mazeList.isEmpty()) {
            for (int i = 0; i < mazeList.size(); i++) {
                List<Character> row = mazeList.get(i);
                mazeArray[i] = new char[row.size()];
                for (int j = 0; j < row.size(); j++) {
                    mazeArray[i][j] = row.get(j);
                }
            }
            return mazeArray;
        }
        return null;
    }
}
