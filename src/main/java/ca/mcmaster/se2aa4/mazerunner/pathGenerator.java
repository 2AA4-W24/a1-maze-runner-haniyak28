package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class pathGenerator implements pathGen{
    private static final Logger logger = LogManager.getLogger();
    //initializing entry point and exit point variables
    public static int entryRow, entryCol, exitRow, exitCol;
    public static int currentRow, currentCol;
    public static char currentDirection;
    public pathGenerator(char[][] maze) {
        String[] generatedPath = generatePath(maze);

        logger.info("Canonical Path: " + generatedPath[0]);
        logger.info("Factorized Path: " + generatedPath[1]);
    }
    @Override
    public String[] generatePath(char[][] maze) {
        String[] fullPath;
        String path = ""; //for final result output

        // Find entry and exit points
        EntryAndExit(maze);

        if (entryRow == -1 || entryCol == -1 || exitRow == -1 || exitCol == -1) {
            logger.error("Error: Entry or exit point not found in the maze.");
            return null;
        }

        //run the algorithm function
        List<Character> gen_path = RightHandRule(maze);
        //logger.info("right hand rule ran");

        // Factorize the generated path
        List<String> factorizedPath = factorizePath(gen_path);

        //turn list into string to return and print in main

        for (Character i : gen_path) {
            path += i;
        }

        fullPath = new String[]{path, String.join("", factorizedPath)};

        return fullPath;
    }

    private static List<String> factorizePath(List<Character> path) {
        List<String> factorizedPath = new ArrayList<>();
        int counter = 1;

        for (int i = 1; i < path.size(); i++) {
            if (path.get(i) == path.get(i - 1)) {
                counter++;
            } else {
                factorizedPath.add(counter + String.valueOf(path.get(i - 1)));
                counter = 1;
            }
        }
        // Add the last move
        factorizedPath.add(counter + String.valueOf(path.get(path.size() - 1)));
        return factorizedPath;
    }

    private static List<Character> RightHandRule(char[][] maze) {
        //Initialize the current position and direction
        currentRow = entryRow;
        currentCol = entryCol;
        List<Character> gen_path = new ArrayList<>(); //to record the directions moved
        // Initial direction, facing East because the entry point is on the West border
        currentDirection = 'E';
        //Algorithm to find the path and make the moves
        while (!(currentRow == exitRow && currentCol == exitCol)) {
            //run the right hand algorithm
            char rightCell = RightCell(maze);
            char frontCell = FrontCell(maze);

            //logger.info("current col and row " + currentCol + " " + currentRow);
            //logger.info("right cell, front cell " + rightCell + " " + frontCell);
            if (rightCell == ' ') {
                // If there's an empty space to the right but not forward, turn right
                turnRight();
                gen_path.add('R');
                moveForward();
                gen_path.add('F');
            } else if (frontCell == ' ') {
                // If there's an empty space in front, move forward
                moveForward();
                gen_path.add('F');
            } else {
                // If there's a wall on the right and in front, turn left
                turnLeft();
                gen_path.add('L');
            }
            /*if (currentCol<0 || currentCol>maze[0].length - 1) {
            }*/
        }
        //logger.info("row and col now: " + currentRow + " " + currentCol);
        return gen_path;
    }

    private static char RightCell(char[][] maze) {
        //gives us what the charachter is to the right of the person based on their current direction
        char RightChar = 'X';
        if (currentDirection == 'N') {
            RightChar = maze[currentRow][currentCol + 1];
        } else if (currentDirection == 'E') {
            RightChar = maze[currentRow + 1][currentCol];
        } else if (currentDirection == 'S') {
            RightChar = maze[currentRow][currentCol - 1];
        } else if (currentDirection == 'W') {
            RightChar = maze[currentRow - 1][currentCol];
        }
        return RightChar;
    }
    private static char FrontCell(char[][] maze) {
        //gives us what the charachter is to the front of the person based on their current direction
        char FrontChar = 'X';
        if (currentDirection == 'N') {
            FrontChar = maze[currentRow - 1][currentCol];
        } else if (currentDirection == 'E') {
            FrontChar = maze[currentRow][currentCol +1];
        } else if (currentDirection == 'S') {
            FrontChar = maze[currentRow+1][currentCol];
        } else if (currentDirection == 'W') {
            FrontChar = maze[currentRow][currentCol -1];
        }
        return FrontChar;
    }

    public static void turnRight() {
        char[] directions = {'N', 'E', 'S', 'W'};
        // Helper method to turn right
        // Update currentDirection based on the current direction
        switch (currentDirection) {
            case 'N' -> currentDirection = directions[1];
            case 'E' -> currentDirection = directions[2];
            case 'S' -> currentDirection = directions[3];
            case 'W' -> currentDirection = directions[0];
        }
        //logger.info("turned right");
        //logger.info(currentDirection);
    }

    public static void turnLeft() {
        char[] directions = {'N', 'W', 'S', 'E'};
        // Helper method to turn left
        // Update currentDirection based on the current direction
        switch (currentDirection) {
            case 'N' -> currentDirection = directions[1];
            case 'E' -> currentDirection = directions[0];
            case 'S' -> currentDirection = directions[3];
            case 'W' -> currentDirection = directions[2];
        }
        //logger.info("left turn");
        //logger.info(currentDirection);
    }

    public static void moveForward() {
        // Helper method to move forward
        // Update currentRow and currentCol based on the current direction
        if (currentDirection == 'N') currentRow--;
        else if (currentDirection == 'E') currentCol++;
        else if (currentDirection == 'S') currentRow++;
        else currentCol--;
        //logger.info("moved forward");
        //logger.info(currentDirection);
    }

    public static void EntryAndExit(char[][] maze) {
        entryRow = entryCol = exitRow = exitCol = -1;
        // Find the entry point assuming it's on the leftmost wall
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][0] == ' ') {
                entryRow = row;
                entryCol = 0;
                break;
            }
        }
        //logger.info(entryCol);
        //logger.info(entryRow);
        // Find the exit point assuming it's on the rightmost wall
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][maze[row].length - 1] == ' ') {
                exitRow = row;
                exitCol = maze[row].length - 1;
                break;
            }
        }
        //logger.info(exitCol);
        //logger.info(exitRow);
    }
}
