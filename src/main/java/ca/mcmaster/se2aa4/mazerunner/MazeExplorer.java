package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeExplorer extends pathGenerator{
    //Input sequence verification
    private static final Logger logger = LogManager.getLogger();
    //initializing entry point and exit point variables
    public static void VerifyPath(char[][] maze, String inputPath) {
        //use variables entryRow and entryCol and exitRow and exitCol in this class too
        EntryAndExit(maze); //could I make this into a separate class?
        char[] path = inputPath.toCharArray();
        boolean verificationCanonical = false;
        boolean verificationFactorized = false;
        char[] directionChoice = {'E', 'W'};
        int[] entryRowPoint = {entryRow, exitRow};
        int[] entryColPoint = {entryCol, exitCol};
        int[] exitRowPoint = {exitRow, entryRow};
        int[] exitColPoint = {exitCol, entryCol};

        //logger.info(path);
        //boolean verification = false;
        for (int choice = 0; choice < directionChoice.length; choice++) {
            currentDirection = directionChoice[choice];
            // Find entry and exit points
            currentRow = entryRowPoint[choice];
            currentCol = entryColPoint[choice];
            if (inputPath.charAt(0) == 'F' || inputPath.charAt(0) == 'L' || inputPath.charAt(0) == 'R') {
                verificationCanonical = verifyCanonicalPath(maze, path, exitRowPoint[choice], exitColPoint[choice]);
            } else if (Character.isDigit(inputPath.charAt(0))) {
                verificationFactorized = verifyFactorizedPath(maze, inputPath, exitRowPoint[choice], exitColPoint[choice]);
            } else {
                logger.error("Wrong path format given");
                return;
            }

            if (verificationCanonical || verificationFactorized) {
                logger.info("Correct Path");
                return;
            }
        }
        logger.info("Incorrect Path");
        //logger.info("**** Computing a correct path");
        // Using the pathGenerator class to generate the path
        //pathGenerator.generatePath(maze);
    }

    private static boolean verifyCanonicalPath(char[][] maze, char[] path, int exitRow, int exitCol) {
        //go through the maze using given input sequence and see if it is right
        for (int i = 0; i < path.length; i++) {
            if (path[i] == 'R') {
                turnRight();
            } else if (path[i] == 'F') {
                moveForward();
            } else if (path[i] == 'L') {
                turnLeft();
            }
            //logger.info("current row and col " + currentRow + " " + currentCol);
            if (maze[currentRow][currentCol] == '#') {
                return false;
            }
        }
        //logger.info("row and col now: " + currentRow + " " + currentCol);
        return currentRow == exitRow && currentCol == exitCol;
    }

    private static boolean verifyFactorizedPath(char[][] maze, String inputPath, int exitRow, int exitCol) {
        int i = 0;
        while (i < inputPath.length()) {
            // Read the count of consecutive moves
            StringBuilder countBuilder = new StringBuilder();
            while (i < inputPath.length() && Character.isDigit(inputPath.charAt(i))) {
                countBuilder.append(inputPath.charAt(i));
                i++;
            }

            // Convert the count to an integer
            int count = Integer.parseInt(countBuilder.toString());

            // Read the direction character
            if (i < inputPath.length()) {
                char pathMove = inputPath.charAt(i);

                for (int j = 0; j < count; j++) {
                    if (pathMove == 'R') {
                        turnRight();
                    } else if (pathMove == 'F') {
                        moveForward();
                    } else if (pathMove == 'L') {
                        turnLeft();
                    }
                    if (maze[currentRow][currentCol] == '#') {
                        return false;
                    }
                }
                i++; // Move to the next character after the direction
            }
        }
        return currentRow == exitRow && currentCol == exitCol;
    }
}
