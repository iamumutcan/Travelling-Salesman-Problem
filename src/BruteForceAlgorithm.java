public class BruteForceAlgorithm {

    public static void run(String filepath) {
        long startTime = System.currentTimeMillis();

        double[][] coordinates = CoordinateReader.readCoordinatesFromFile(filepath);
        int[] path = bruteForce(coordinates);
        System.out.print("Bulunabilecek en optimum sonuç: [" + printPath(path));  System.out.println("]");
        System.out.println("Yol uzunluğu:  " + calculatePathDistance(path, coordinates));
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("İşlem süresi: " + totalTime + " milisaniye");
    }

    public static int[] bruteForce(double[][] coordinates) {
        int n = coordinates.length;
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            path[i] = i;
        }
        double minDistance = Double.MAX_VALUE;
        int[] bestPath = path.clone();
        do {
            double distance = calculatePathDistance(path, coordinates);
            if (distance < minDistance) {
                minDistance = distance;
                bestPath = path.clone();
            }
        } while (nextPermutation(path));
        return bestPath;
    }

    public static boolean nextPermutation(int[] a) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                for (int j = a.length - 1; ; j--) {
                    if (a[j] > a[i]) {
                        int t = a[i];
                        a[i] = a[j];
                        a[j] = t;
                        for (int p = i + 1, q = a.length - 1; p < q; p++, q--) {
                            t = a[p];
                            a[p] = a[q];
                            a[q] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static double calculatePathDistance(int[] path, double[][] coordinates) {
        double distance = 0;
        for (int i = 1; i < path.length; i++) {
            int fromIndex = path[i - 1];
            int toIndex = path[i];
            distance += calculateDistance(coordinates[fromIndex], coordinates[toIndex]);
        }
        int fromIndex = path[path.length - 1];
        int toIndex = path[0];
        distance += calculateDistance(coordinates[fromIndex], coordinates[toIndex]);
        return distance;
    }

    public static double calculateDistance(double[] p1, double[] p2) {
        double dx = p1[0] - p2[0];
        double dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static String printPath(int[] path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.length; i++) {
            sb.append(path[i]); // 1-indexed
            if (i < path.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
