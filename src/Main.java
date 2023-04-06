import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //private static final String FILE_NAME = "C:/tsp/124xy.txt";
   // private static final String FILE_NAME = "C:/tsp/5000xy.txt";
   // private static final String FILE_NAME = "C:/tsp/80000xy.txt";
    private static final String FILE_NAME = "C:/tsp/5xy.txt";
    public static void main(String[] args) {

        SimulatedAnnealingAlgorithm simulatedAnnealingAlgorithm = new SimulatedAnnealingAlgorithm();
        simulatedAnnealingAlgorithm.run(FILE_NAME);
    }

}
