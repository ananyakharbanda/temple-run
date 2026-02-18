package algorithms;

import core.Edge;
import core.Graph;
import java.util.List;
import java.util.Arrays;
import java.util.PriorityQueue;

public class PrimMST {

    private Graph g;
    private static final int NO_PARENT = -1;

    private PriorityQueue<Edge> minHeap;
    private boolean[] visited;
    private int n;

    private int[] parent;
    private double totalWeight;

    public PrimMST(Graph g) {
        this.g = g;
        this.n = g.size();

        this.parent = new int[n];
        this.visited = new boolean[n];

        this.minHeap = new PriorityQueue<>((a, b) -> Double.compare(a.getWeight(), b.getWeight()));
    }

    public int[] mstCalc(int start) {
        Arrays.fill(parent, NO_PARENT);
        Arrays.fill(visited, false);
        minHeap.clear();
        totalWeight = 0;

        mstCalcHelper(start);

        return parent;
    }

    private void mstCalcHelper(int start) {
        visited[start] = true;

        for (Edge e : g.getNeighbours(start)) {
            minHeap.add(e);
        }

        int count = 0;

        while (count < n - 1 && !minHeap.isEmpty()) {
            Edge min = minHeap.poll();
            int destIndex = min.getDest();

            if (visited[destIndex]) {
                continue;
            }

            count++;

            visited[destIndex] = true;
            parent[destIndex] = min.getSrc();

            totalWeight += min.getWeight();

            for (Edge e : g.getNeighbours(destIndex)) {
                if (!visited[e.getDest()]) {
                    minHeap.add(e);
                }
            }
        }
    }

    public int[] getParent() {
        return parent;
    }

    public double getTotalWeight() {
        return totalWeight;
    }
}
