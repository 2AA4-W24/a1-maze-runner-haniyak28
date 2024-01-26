package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class pathGenerator {
    private static final Logger logger = LogManager.getLogger();
    //initializing entry point and exit point variables
    public static int entryRow, entryCol, exitRow, exitCol;
    public static String generatePath(char[][] maze) {
        String path = ""; //for final result output

        // Find entry and exit points
        EntryAndExit(maze);
        if (entryRow == -1 || entryCol == -1 || exitRow == -1 || exitCol == -1) {
            logger.error("Error: Entry or exit point not found in the maze.");
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
        // Initial direction, facing East because the entry point is on the West border
        char currentDirection = 'E';

        //Algorithm to find the path and make the moves
        while (currentRow != exitRow && currentCol != exitCol) {
            //run the right hand algorithm
            char rightCell = RightCell(currentDirection, currentRow, currentCol, maze);
            char frontCell = FrontCell(currentDirection, currentRow, currentCol, maze);
            if (rightCell == ' ' && frontCell == ' ') {
                // If there's an empty space on the right and in front, turn right
                turnRight(currentDirection);
                gen_path.add('R');
            } else if (rightCell == ' ') {
                // If there's an empty space to the right but not forward, turn right
                turnRight(currentDirection);
                gen_path.add('R');
            } else if (frontCell == ' ') {
                // If there's an empty space in front, move forward
                moveForward(currentDirection, currentRow, currentCol);
                gen_path.add('F');
            } else {
                // If there's a wall on the right and in front, turn left
                turnLeft(currentDirection);
                gen_path.add('L');
            }
        }
        return gen_path;
    }

    private static char RightCell(char currentDirection, int currentRow, int currentCol, char[][] maze) {
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
    private static char FrontCell(char currentDirection, int currentRow, int currentCol, char[][] maze) {
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

    public static void turnRight(char currentDirection) {
        char[] directions = {'N', 'E', 'S', 'W'};
        // Helper method to turn right
        // Update currentDirection based on the current direction
        for (int i = 0; i< directions.length; i++){
            if (directions[i] == currentDirection) {
                if (i == directions.length -1){
                    currentDirection = directions[0];
                } else {
                    currentDirection = directions[i+1];
                }
            }
        }
    }

    public static void turnLeft(char currentDirection) {
        char[] directions = {'N', 'W', 'S', 'E'};
        // Helper method to turn left
        // Update currentDirection based on the current direction
        for (int i = 0; i< directions.length; i++){
            if (directions[i] == currentDirection) {
                if (i == directions.length -1){
                    currentDirection = directions[0];
                } else {
                    currentDirection = directions[i+1];
                }
            }
        }
    }

    public static void moveForward(char currentDirection, int currentRow, int currentCol) {
        // Helper method to move forward
        // Update currentRow and currentCol based on the current direction
        if (currentDirection == 'N') currentRow--;
        else if (currentDirection == 'E') currentCol++;
        else if (currentDirection == 'S') currentRow++;
        else currentCol--;
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
