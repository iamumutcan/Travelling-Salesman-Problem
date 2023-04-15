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
        System.out.println(" ***       Neighbor      *** ");
        Neighbor neighbor = new Neighbor();
        neighbor.run(FILE_NAME);
        System.out.println(" *************************** ");
   /*     System.out.println(" *** Simulated Annealing *** ");
        SimulatedAnnealingAlgorithm sAA = new SimulatedAnnealingAlgorithm();
        sAA.run(FILE_NAME);
        System.out.println(" *************************** ");

        System.out.println(" ***         2-Opt       *** ");
        TwoOptAlgorithm twoOpt = new TwoOptAlgorithm();
        twoOpt.run(FILE_NAME);
        System.out.println(" *************************** ");



        System.out.println(" ***     Brute Force     *** ");
        BruteForceAlgorithm bruteForce = new BruteForceAlgorithm();
        bruteForce.run(FILE_NAME);
        System.out.println(" *************************** ");
*/
    }

}
