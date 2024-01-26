package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main { //this class will take in the maze file

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Default input file
        String inputFile = null;

        // Parse command line options
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i") || args[i].equals("--input")) {
                // Check if there is another argument after the option
                if (i + 1 < args.length) {
                    inputFile = args[i + 1];
                    break;
                } else {
                    logger.error("Error: Missing argument for input file option.");
                    return;
                }
            }
        }

        if (inputFile == null) {
            logger.error("Error: Input file not specified. Use -i or --input option to specify the input file.");
            return;
        }

        try {
            logger.info("**** Reading the maze from file " + inputFile);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.info("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.info("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
        }

        logger.info("**** Computing path");

        //take file input and turn maze into 2d array: char[][] maze
        //char[][] maze;

        //if there's a second argument of path sequence
        //MazeExplorer(char[][] maze, string inputSequence);
        //if (MazeExplorer is true, print that their sequence is right otherwise print that it was wrong)
        //System.out.println(pathGenerator(maze)); //will print the correct generated path using right hand algorithm
    }

    public static String pathGenerator(char[][] maze) {
        int entryRow = -1;
        int entryCol = -1;
        int exitRow = -1;
        int exitCol = -1;

        List<Character> gen_path_sequence = new ArrayList<>();
        String path = "";

        // Abstraction1: Find the entry point
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][0] == ' ') {
                entryRow = row;
                entryCol = 0;
                break;
            }
        }
        if (entryRow == -1 || entryCol == -1) {
            logger.error("Error: Entry point not found in the maze.");
            return path; //empty string
        }

        //Abstraction2: Find the exit point assuming it's on the right most wall
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][maze[row].length - 1] == ' ') {
                exitRow = row;
                exitCol = maze[row].length - 1;
                break;
            }
        }
        if (exitRow == -1 || exitCol == -1) {
            logger.error("Error: Exit point not found in the maze.");
            return path;
        }

        // Abstraction3: Initialize the current position and direction
        int currentRow = entryRow;
        int currentCol = entryCol;

        // Abstraction4: Algorithm to find the path and make the moves
        while (currentRow != exitRow && currentCol != exitCol) {
            //run the right hand algorithm
        }

        //turn list into string to return and print in main
        return path;
    }

    // Abstraction6: input sequence verification
    public static boolean MazeExplorer(char[][] maze, String inputSequence) {
        //use variables entryRow and entryCol and exitRow and exitCol in this class too
        boolean sequence_verification = false;

        //go through the maze using given input sequence and see if it is right

        return sequence_verification; //true if it is right and false if it is not right
    }
}
