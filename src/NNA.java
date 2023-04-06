public class NNA {

    private int numberOfNodes;
    private boolean[] visited;
    private double[][] distances;
    private double minDistance;

    public NNA(double[][] distances) {
        this.distances = distances;
        this.numberOfNodes = distances.length;
        this.visited = new boolean[numberOfNodes];
    }

    public void nna() {
        int currentNode = 0;
        visited[currentNode] = true;

        for (int i = 0; i < numberOfNodes-1; i++) {
            int nextNode = findClosestNode(currentNode);
            visited[nextNode] = true;
            minDistance += distances[currentNode][nextNode];
            currentNode = nextNode;
        }

        minDistance += distances[currentNode][0];
    }

    private int findClosestNode(int currentNode) {
        int closestNode = -1;
        double closestDistance = Double.MAX_VALUE;

        for (int i = 0; i < numberOfNodes; i++) {
            if (!visited[i] && distances[currentNode][i] < closestDistance) {
                closestDistance = distances[currentNode][i];
                closestNode = i;
            }
        }

        return closestNode;
    }

    public double getMinDistance() {
        return minDistance;
    }

}
