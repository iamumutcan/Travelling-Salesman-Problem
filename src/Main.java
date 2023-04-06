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
        double[][] coordinates = readCoordinatesFromFile();
        ArrayList<Integer> shortestPath = nearestNeighbor(coordinates);
        double shortestDistance = calculateTourDistance(coordinates, shortestPath);
        System.out.println("Bulunabilecek en optimum sonuç: " + shortestPath.toString());
        System.out.println("Yol uzunluğu: " + shortestDistance);
    }
    private static double calculateTourDistance(double[][] coordinates, ArrayList<Integer> path) {
        double distance = 0;
        int n = path.size();
        for (int i = 0; i < n - 1; i++) {
            int current = path.get(i);
            int next = path.get(i + 1);
            distance += calculateDistance(coordinates[current], coordinates[next]);
        }
        distance += calculateDistance(coordinates[path.get(n - 1)], coordinates[path.get(0)]); // son kentten başlangıç kentine olan mesafe
        return distance;
    }
    private static double[][] readCoordinatesFromFile() { // dosyadanki kordinatları diziye aktarır
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

    private static ArrayList<Integer> nearestNeighbor(double[][] coordinates) {
        int n = coordinates.length;
        boolean[] visited = new boolean[n];
        ArrayList<Integer> path = new ArrayList<Integer>();
        int current = 0;
        visited[current] = true;
        path.add(current);
        while (path.size() < n) {
            double minDistance = Double.MAX_VALUE;
            int next = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    double distance = calculateDistance(coordinates[current], coordinates[i]);
                    if (distance < minDistance) {
                        minDistance = distance;
                        next = i;
                    }
                }
            }
            visited[next] = true;
            path.add(next);
            current = next;
        }
        return path;
    }

    private static double calculateDistance(double[] p1, double[] p2) {
        double xDiff = p1[0] - p2[0];
        double yDiff = p1[1] - p2[1];
        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }
}
