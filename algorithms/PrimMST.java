package algorithms;

import core.Edge;
import core.Graph;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class PrimMST {

    private Graph g;
    private static final int NO_PARENT = -1;

    private PriorityQueue<Edge> minHeap;
    private boolean[] visited;
    private int n;

    public int[] parent;

    public PrimMST(Graph g) {
        this.g = g;
        this.n = g.size();
        this.parent = new int[n];
        this.visited = new boolean[n];

        Arrays.fill(parent, NO_PARENT);

        this.minHeap = new PriorityQueue<>((a, b) -> Double.compare(a.getWeight(), b.getWeight()));
    }

    public int[] mstCalc(int start) {
        mstCalcHelper(start);
        return this.parent;
    }

    private void mstCalcHelper(int start) {
        List<Edge> vertexAdjacencyList = this.g.getNeighbours(start);
        visited[start] = true;
        
        for (Edge e : vertexAdjacencyList) {
            this.minHeap.add(e);
        }
        
        int count = 0;
        while(count < n - 1 && !minHeap.isEmpty()) {
            Edge min = minHeap.poll();
            int destIndex = min.getDest();
            
            if (visited[destIndex]) {
                continue;
            }
            
            count++;
            
            visited[destIndex] = true;
            parent[destIndex] = min.getSrc();
            
            List<Edge> vertexAdjacencyList2 = this.g.getNeighbours(min.getDest());
                
            for (Edge e : vertexAdjacencyList2) {
                if (!visited[e.getDest()]) {
                    this.minHeap.add(e);
                }
            }
        }
    }
}

