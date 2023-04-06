import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Verysmall {

    private static final String FILE_NAME = "C:/tsp/5xy.txt"; // dosya yolu
    public static void main(String[] args) {
        double[][] coordinates = readCoordinatesFromFile(); // Dosyadan koordinatları oku
        double[][] adjacencyMatrix = calculateAdjacencyMatrix(coordinates); // Komşuluk matrisini hesapla
        TSP tsp = new TSP(adjacencyMatrix); // TSP sınıfı oluştur
        ArrayList<Integer> shortestPath = tsp.solve(); // TSP problemi çöz
        System.out.println("En kısa rota: " + shortestPath.toString()); // Sonucu ekrana yazdır

    }

    private static double[][] readCoordinatesFromFile() {
        ArrayList<double[]> coordinatesList = new ArrayList<double[]>();
        try {
            File file = new File(FILE_NAME);
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

    private static double[][] calculateAdjacencyMatrix(double[][] coordinates) {
        int n = coordinates.length;
        double[][] adjacencyMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = calculateDistance(coordinates[i], coordinates[j]);
            }
        }
        return adjacencyMatrix;
    }

    private static double calculateDistance(double[] p1, double[] p2) {
        double xDiff = p1[0] - p2[0];
        double yDiff = p1[1] - p2[1];
        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }
}
