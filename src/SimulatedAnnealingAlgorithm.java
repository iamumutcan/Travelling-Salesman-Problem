import java.util.Random;

public class SimulatedAnnealingAlgorithm {
    public static void run(String filepath) {
        long startTime = System.currentTimeMillis();
        double[][] coordinates = CoordinateReader.readCoordinatesFromFile(filepath);
        int[] path = generateInitialPath(coordinates.length);
        double temperature = 100000;
        double coolingRate = 0.003;
        double absoluteTemperature = 1e-8;
        double bestDistance = calculateDistance(coordinates, path);
        int[] bestPath = path;

        while (temperature > absoluteTemperature) {
            int[] newPath = generateNeighborPath(path);
            double newDistance = calculateDistance(coordinates, newPath);
            double deltaDistance = newDistance - bestDistance;
            if (deltaDistance < 0 || Math.exp(-deltaDistance / temperature) > Math.random()) {
                path = newPath;
                bestDistance = newDistance;
                if (bestDistance < calculateDistance(coordinates, bestPath)) {
                    bestPath = path;
                }
            }
            temperature *= 1 - coolingRate;
        }

        System.out.print("Bulunabilecek en optimum sonuç: [");
        for (int i = 0; i < bestPath.length; i++) {
            System.out.print(bestPath[i] );
            if (i < bestPath.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();
        System.out.println("Yol uzunluğu: " + bestDistance);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("İşlem süresi: " + totalTime + " milisaniye");
    }

    private static int[] generateInitialPath(int length) {
        int[] path = new int[length];
        for (int i = 0; i < length; i++) {
            path[i] = i;
        }
        shuffle(path);
        return path;
    }

    private static void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static int[] generateNeighborPath(int[] currentPath) {
        Random rand = new Random();
        int[] newPath = currentPath.clone();
        int i = rand.nextInt(newPath.length);
        int j = rand.nextInt(newPath.length);
        int temp = newPath[i];
        newPath[i] = newPath[j];
        newPath[j] = temp;
        return newPath;
    }

    private static double calculateDistance(double[][] coordinates, int[] path) {
        double distance = 0;
        for (int i = 0; i < path.length - 1; i++) {
            int city1 = path[i];
            int city2 = path[i+1];
            distance += calculateDistanceBetweenCities(coordinates[city1], coordinates[city2]);
        }
        distance += calculateDistanceBetweenCities(coordinates[path[path.length-1]], coordinates[path[0]]);
        return distance;
    }

    private static double calculateDistanceBetweenCities(double[] city1, double[] city2) {
        double xDiff = city1[0] - city2[0];
        double yDiff = city1[1] - city2[1];
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
