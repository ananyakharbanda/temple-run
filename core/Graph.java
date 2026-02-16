package core;

import java.util.List;
import java.util.ArrayList;

public class Graph {
    
    private int n;
    private double[][] dist;
    
    private List<List<Edge>> adj;
        
    public Graph(double[][] dist) {
        this.n = dist.length;
        this.dist = dist;
        buildAdjacencyList();
    }
    
    private void buildAdjacencyList() {
        adj = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            for(int j = 0; j < n; j++) {
                if (i != j) {
                    adj.get(i).add(new Edge(i, j, dist[i][j]));
                }
            }
        }
    }
    
    public int size() {
        return n;
    }
    
    public double getDistance(int i, int j) {
        return dist[i][j];
    }
    
    public List<Edge> getNeighbours(int node) { 
        return adj.get(node);
    }
    
    public double[][] getDistanceMatrix() {
        return dist;
    }
}
