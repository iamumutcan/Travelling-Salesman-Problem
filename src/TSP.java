import java.util.ArrayList;
import java.util.Collections;

public class TSP {
    private int numberOfNodes;
    private boolean[] visited;
    private double[][] adjacencyMatrix;
    private ArrayList<Integer> path = new ArrayList<Integer>();
    private double minCost = Double.MAX_VALUE;

    public TSP(double[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        numberOfNodes = adjacencyMatrix[0].length;
        visited = new boolean[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            path.add(i);
        }
    }

    public ArrayList<Integer> solve() {
        permute(0);
        return path;
    }

    private void permute(int startIndex) {
        if (startIndex == numberOfNodes - 1) {
            double cost = calculateCost();
            if (cost < minCost) {
                minCost = cost;
            }
        } else {
            for (int i = startIndex; i < numberOfNodes; i++) {
                Collections.swap(path, startIndex, i);
                permute(startIndex + 1);
                Collections.swap(path, startIndex, i);
            }
        }
    }

    private double calculateCost() {
        double cost = 0;
        for (int i = 0; i < numberOfNodes - 1; i++) {
            cost += adjacencyMatrix[path.get(i)][path.get(i+1)];
        }
        cost += adjacencyMatrix[path.get(numberOfNodes-1)][path.get(0)];
        return cost;
    }
}
