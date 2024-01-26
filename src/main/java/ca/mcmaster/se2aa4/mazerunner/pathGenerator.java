package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class pathGenerator {
    private static final Logger logger = LogManager.getLogger();

    public static String generatePath(char[][] maze) {
        //initializing entry point and exit point variables
        int entryRow = -1;
        int entryCol = -1;
        int exitRow = -1;
        int exitCol = -1;

        List<Character> gen_path_sequence = new ArrayList<>(); //to record the directions moved
        StringBuilder path = new StringBuilder(); //for final result output

        //Find the entry point assuming its on the left most wall
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][0] == ' ') {
                entryRow = row;
                entryCol = 0;
                break;
            }
        }
        if (entryRow == -1 || entryCol == -1) {
            logger.error("Error: Entry point not found in the maze.");
            return null; //empty
        }

        //Find the exit point assuming it's on the right most wall
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][maze[row].length - 1] == ' ') {
                exitRow = row;
                exitCol = maze[row].length - 1;
                break;
            }
        }
        if (exitRow == -1 || exitCol == -1) {
            logger.error("Error: Exit point not found in the maze.");
            return null;
        }

        //Initialize the current position and direction
        int currentRow = entryRow;
        int currentCol = entryCol;

        //Algorithm to find the path and make the moves
        while (currentRow != exitRow && currentCol != exitCol) {
            //run the right hand algorithm
        }

        //turn list into string to return and print in main
        for (Character i : gen_path_sequence) {
            path.append(i);
        }
        return path.toString();
    }
}
