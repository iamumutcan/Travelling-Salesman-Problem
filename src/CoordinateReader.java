import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CoordinateReader {

    public static double[][] readCoordinatesFromFile(String filepath) {
        ArrayList<double[]> coordinatesList = new ArrayList<double[]>();
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                double[] coordinates = new double[2];
                coordinates[0] = Double.parseDouble(line[0]);
                coordinates[1] = Double.parseDouble(line[1]);
                coordinatesList.add(coordinates);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double[][] coordinates = new double[coordinatesList.size()][2];
        for (int i = 0; i < coordinatesList.size(); i++) {
            coordinates[i][0] = coordinatesList.get(i)[0];
            coordinates[i][1] = coordinatesList.get(i)[1];
        }
        return coordinates;
    }
    public static void printCoordinates(double[][] coordinates) {
        for (int i = 0; i < coordinates.length; i++) {
            System.out.println("Coordinate " + (i+1) + ": [" + coordinates[i][0] + ", " + coordinates[i][1] + "]");
        }
    }
}
