package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        String inputFile = null;
        String inputPath = null;

        // read command line options
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i")) {
                // Check if there is another argument after the option
                if (i + 1 < args.length) {
                    inputFile = args[i + 1];
                }
            }
            if (args[i].equals("-p")) {
                // Check if there is another argument after the option
                if (i + 1 < args.length) {
                    inputPath = args[i + 1];
                }
            }
        }
        if (inputFile == null) {
            logger.error("Error: No input file given. Use -i to specify the input file.");
            return;
        }
        logger.info("**** Reading the maze from file " + inputFile);
        //takes file input and turns maze into a 2d array list
        char[][] maze = Maze.MazeRead(inputFile);
        //logger.info("**** maze char " + Arrays.deepToString(maze));

        //if there's a second argument of path sequence
        if (inputPath != null) {
            logger.info("**** verifying the path " + inputPath);
            MazeExplorer.VerifyPath(maze, inputPath);
        } else {
            logger.info("**** Computing a path");
            // Using the pathGenerator class to generate the path
            pathGen path = new pathGenerator(maze);
            path.generatePath(maze);
            //logger.info(generatedPath);
        }
    }
}
