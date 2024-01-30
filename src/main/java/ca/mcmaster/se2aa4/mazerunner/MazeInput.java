package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class MazeInput {
    public static char[][] MazeRead(String inputFile) {
        try {
            List<List<Character>> mazeArray = getLists(inputFile);

            // Convert List of Lists to a 2D char array
            char[][] maze = new char[mazeArray.size()][];
            if (!mazeArray.isEmpty()) {
                for (int i = 0; i < mazeArray.size(); i++) {
                    List<Character> row = mazeArray.get(i);
                    maze[i] = new char[row.size()];
                    for (int j = 0; j < row.size(); j++) {
                        maze[i][j] = row.get(j);
                    }
                }
                return maze;
            }
            return null;
        } catch (IOException e) {
            System.out.println("/!\\ An error has occurred /!\\");
            return null;
        }
    }

    private static List<List<Character>> getLists(String inputFile) throws IOException {
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
