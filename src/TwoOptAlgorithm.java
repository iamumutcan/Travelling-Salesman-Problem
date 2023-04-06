import java.util.*;

public class TwoOptAlgorithm {

    public static void main(String[] args) {
        double[][] coordinates = CoordinateReader.readCoordinatesFromFile("C:/tsp/5xy.txt");
        int[] path = twoOpt(coordinates);
        System.out.println("Bulunabilecek en optimum sonuç: " + Arrays.toString(path));
        double distance = pathDistance(coordinates, path);
        System.out.println("Yol uzunluğu: " + distance);
    }
    public static double pathDistance(double[][] coordinates, int[] path) {
        double distance = 0;
        for (int i = 0; i < path.length - 1; i++) {
            distance += distance(coordinates[path[i]], coordinates[path[i+1]]);
        }
        distance += distance(coordinates[path[path.length-1]], coordinates[path[0]]);
        return distance;
    }


    public static int[] twoOpt(double[][] coordinates) {
        // Initialize path as a simple tour
        int n = coordinates.length;
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            path[i] = i;
        }

        // Repeat until no improvement is made
        boolean improvement = true;
        while (improvement) {
            improvement = false;

            // Loop through all edges
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 2; j < n; j++) {
                    // Check if swapping these edges will improve the solution
                    if (distance(coordinates[path[i]], coordinates[path[i+1]]) +
                            distance(coordinates[path[j]], coordinates[path[(j+1)%n]]) >
                            distance(coordinates[path[i]], coordinates[path[j]]) +
                                    distance(coordinates[path[i+1]], coordinates[path[(j+1)%n]])) {
                        // If an improvement is found, swap the edges
                        path = reverse(path, i+1, j);
                        improvement = true;
                    }
                }
            }
        }

        return path;
    }

    public static double distance(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static int[] reverse(int[] path, int i, int j) {
        int n = path.length;
        int[] newPath = new int[n];
        for (int k = 0; k < i; k++) {
            newPath[k] = path[k];
        }
        for (int k = i; k <= j; k++) {
            newPath[k] = path[j-k+i];
        }
        for (int k = j+1; k < n; k++) {
            newPath[k] = path[k];
        }
        return newPath;
    }
}
