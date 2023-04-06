public class NNAMAIN {

    public static void main(String[] args) {
        String filepath = "C:/tsp/10000xy.txt";
        double[][] coordinates = CoordinateReader.readCoordinatesFromFile(filepath);

        int[] path = nearestNeighborTSP(coordinates);
        double pathLength = getPathLength(path, coordinates);
        System.out.println("Path: " + java.util.Arrays.toString(path));
        System.out.println("Path Length: " + pathLength);
    }

    public static int[] nearestNeighborTSP(double[][] coordinates) {
        int n = coordinates.length;
        boolean[] visited = new boolean[n];
        int[] path = new int[n];
        path[0] = 0;
        visited[0] = true;
        for (int i = 1; i < n; i++) {
            int nearestNeighbor = -1;
            double minDistance = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    double distance = getDistance(coordinates[path[i-1]], coordinates[j]);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestNeighbor = j;
                    }
                }
            }
            path[i] = nearestNeighbor;
            visited[nearestNeighbor] = true;
        }
        return path;
    }

    public static double getDistance(double[] c1, double[] c2) {
        double xDist = c2[0] - c1[0];
        double yDist = c2[1] - c1[1];
        return Math.sqrt(xDist * xDist + yDist * yDist);
    }

    public static double getPathLength(int[] path, double[][] coordinates) {
        double pathLength = 0.0;
        for (int i = 0; i < path.length - 1; i++) {
            pathLength += getDistance(coordinates[path[i]], coordinates[path[i+1]]);
        }
        pathLength += getDistance(coordinates[path[path.length-1]], coordinates[path[0]]);
        return pathLength;
    }
}
