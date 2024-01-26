package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
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
        char[] path = inputPath.toCharArray();
        boolean verification = true;
        char currentDirection = 'E';
        int currentRow = entryRow;
        int currentCol = entryCol;

        //go through the maze using given input sequence and see if it is right
        for (int i = 0; i<path.length; i++) {
            if (path[i] == 'R'){
                turnRight(currentDirection);
            } else if (path[i] == 'F'){
                moveForward(currentDirection, currentRow, currentCol);
            } else if (path[i] == 'L'){
                turnLeft(currentDirection);
            }

            if (maze[currentRow][currentCol] == '#') {
                verification = false;
                break;
            }
        }
        if (currentRow != exitRow && currentCol != exitCol) {
            verification = false;
        }
        //if true, print that their sequence is right otherwise print that it was wrong
        if (verification) {
            logger.info("Your path sequence is correct!");
        }
        else {
            logger.info("Your path sequence is not correct");
            logger.info("**** Computing a path");
            // Using the pathGenerator class to generate the path
            String generatedPath = pathGenerator.generatePath(maze);
            logger.info(generatedPath);
        }
    }
}
