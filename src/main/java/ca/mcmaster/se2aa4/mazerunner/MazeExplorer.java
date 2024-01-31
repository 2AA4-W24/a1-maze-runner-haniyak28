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
        char[] directionChoice = {'E', 'W'};
        int[] entryRowPoint = {entryRow, exitRow};
        int[] entryColPoint = {entryCol, exitCol};
        int[] exitRowPoint = {exitRow, entryRow};
        int[] exitColPoint = {exitCol, entryCol};
        boolean correct = false;

        for (int choice = 0; choice < directionChoice.length; choice++) {
            correct = verification(maze, directionChoice[choice], entryRowPoint[choice], entryColPoint[choice], exitRowPoint[choice], exitColPoint[choice], inputPath);
        }
        if (correct) {
            logger.info("Correct Path");
        } else {
            logger.info("Incorrect Path");
        }
    }

    private static boolean verification(char[][] maze, char direction, int entryRowP, int entryColP, int exitRowP, int exitColP, String inputPath){
        boolean verificationCanonical = false;
        boolean verificationFactorized = false;
        char[] path = inputPath.toCharArray();
        currentDirection = direction;
        // Find entry and exit points
        currentRow = entryRowP;
        currentCol = entryColP;
        if (inputPath.charAt(0) == 'F' || inputPath.charAt(0) == 'L' || inputPath.charAt(0) == 'R') {
            verificationCanonical = verifyCanonicalPath(maze, path, exitRowP, exitColP);
        } else if (Character.isDigit(inputPath.charAt(0))) {
            verificationFactorized = verifyFactorizedPath(maze, inputPath, exitRowP, exitColP);
        } else {
            logger.error("Wrong path format given");
        }

        if (verificationCanonical || verificationFactorized) {
           return true; 
        }
        return false;
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
