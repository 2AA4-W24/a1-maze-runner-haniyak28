package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class pathGenerator {
    private static final Logger logger = LogManager.getLogger();
    //initializing entry point and exit point variables
    static int entryRow, entryCol, exitRow, exitCol;
    public static String generatePath(char[][] maze) {
        String path = ""; //for final result output

        // Find entry and exit points
        EntryAndExit(maze);
        if (entryRow == -1 || entryCol == -1) {
            logger.error("Error: Entry point not found in the maze.");
            return null;
        }
        if (exitRow == -1 || exitCol == -1) {
            logger.error("Error: Exit point not found in the maze.");
            return null;
        }

        //run the algorithm function
        List<Character> gen_path = RightHandRule(maze);

        //turn list into string to return and print in main
        for (Character i : gen_path) {
            path += i;
        }
        return path;
    }

    private static List<Character> RightHandRule(char[][] maze) {
        //Initialize the current position and direction
        int currentRow = entryRow;
        int currentCol = entryCol;
        List<Character> gen_path = new ArrayList<>(); //to record the directions moved
        //Algorithm to find the path and make the moves
        while (currentRow != exitRow && currentCol != exitCol) {
            //run the right hand algorithm
        }
        return gen_path;
    }

    private static void EntryAndExit(char[][] maze) {
        entryRow = entryCol = exitRow = exitCol = -1;
        // Find the entry point assuming it's on the leftmost wall
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][0] == ' ') {
                entryRow = row;
                entryCol = 0;
                break;
            }
        }
        // Find the exit point assuming it's on the rightmost wall
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][maze[row].length - 1] == ' ') {
                exitRow = row;
                exitCol = maze[row].length - 1;
                break;
            }
        }
    }
}
