import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Neighbor {
       private static final String FILE_NAME = "C:/tsp/5xy.txt";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        double[][] coordinates = CoordinateReader.readCoordinatesFromFile(FILE_NAME);
        ArrayList<Integer> shortestPath = nearestNeighbor(coordinates);
        double shortestDistance = calculateTourDistance(coordinates, shortestPath);
        System.out.println("Bulunabilecek en optimum sonuç: " + shortestPath.toString());
        System.out.println("Yol uzunluğu: " + shortestDistance);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("İşlem süresi: " + totalTime + " milisaniye");


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
    private static double calculateDistance(double[] p1, double[] p2) {
        double xDiff = p1[0] - p2[0];
        double yDiff = p1[1] - p2[1];
        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }


}
