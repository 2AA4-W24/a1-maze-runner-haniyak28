package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeExplorer extends pathGenerator{
    //Input sequence verification
    private static final Logger logger = LogManager.getLogger();
    //initializing entry point and exit point variables
    //private static int entryRow, entryCol, exitRow, exitCol;
    public static void VerifyPath(char[][] maze, String inputPath) {
        //use variables entryRow and entryCol and exitRow and exitCol in this class too
        EntryAndExit(maze); //could I make this into a separate class?
        char[] path = inputPath.toCharArray();
        char[] directionChoice = {'E','W'};
        int[] entryRowPoint = {entryRow, exitRow};
        int[] entryColPoint = {entryCol, exitCol};
        logger.info(path);
        boolean verification = false;
        for (int choice = 0; choice<directionChoice.length;choice++) {
            currentDirection = directionChoice[choice];
            // Find entry and exit points
            currentRow = entryRowPoint[choice];
            currentCol = entryColPoint[choice];

            //go through the maze using given input sequence and see if it is right
            for (int i = 0; i < path.length; i++) {
                if (path[i] == 'R') {
                    turnRight();
                } else if (path[i] == 'F') {
                    moveForward();
                } else if (path[i] == 'L') {
                    turnLeft();
                }
                logger.info("current row and col " + currentRow + " " + currentCol);
                if (maze[currentRow][currentCol] == '#') {
                    break;
                }
            }
            logger.info("row and col now: " + currentRow + " " + currentCol);
            if (currentRow == exitRow && currentCol == exitCol) {
                verification = true;
                //logger.info("Your path sequence is correct!");
            }
        }
        //if true, print that their sequence is right otherwise print that it was wrong
        if (verification) {
            logger.info("Your path sequence is correct!");
        } else {
            logger.info("Your path sequence is not correct");
            logger.info("**** Computing a correct path");
            // Using the pathGenerator class to generate the path
            pathGenerator.generatePath(maze);
            //logger.info(generatedPath);
        }
    }
}
