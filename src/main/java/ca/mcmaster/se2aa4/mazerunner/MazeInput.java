package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class MazeInput {
    public static List<List<Character>> getLists(String inputFile) throws IOException {
        List<List<Character>> mazeArray = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            List<Character> row = new ArrayList<>();
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    row.add('#');
                } else if (line.charAt(idx) == ' ') {
                    row.add(' ');
                }
            }
            mazeArray.add(row);
        }
        return mazeArray;
    }
}
