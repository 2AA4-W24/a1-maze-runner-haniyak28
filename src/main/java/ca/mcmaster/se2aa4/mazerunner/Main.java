package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        System.out.println("** Starting Maze Runner");

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
                    System.err.println("Error: Missing argument for input file option.");
                    return;
                }
            }
        }

        if (inputFile == null) {
            System.err.println("Error: Input file not specified. Use -i or --input option to specify the input file.");
            return;
        }

        try {
            System.out.println("**** Reading the maze from file " + inputFile);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
        } catch (Exception e) {
            System.err.println("/!\\ An error has occurred /!\\");
        }

        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }
}
