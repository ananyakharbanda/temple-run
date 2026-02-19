package app;

import core.Graph;
import algorithms.PrimMST;
import algorithms.DFSRoute;
import algorithms.BruteForceTSP;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        // Example test graph (4 nodes)
        double[][] dist = {
            {0, 1, 4, 100},
            {1, 0, 2, 5},
            {4, 2, 0, 3},
            {100, 5, 3, 0}
        };

        Graph graph = new Graph(dist);

        PrimMST prim = new PrimMST(graph);
        int[] parent = prim.mstCalc(0);

        System.out.println("Parent array:");
        for (int i = 0; i < parent.length; i++) {
            System.out.println(i + " <- " + parent[i]);
        }

        double mstEdgeCost = prim.getTotalWeight();
        System.out.println("\nMST Edge Cost: " + mstEdgeCost);

        List<List<Integer>> mstTree = new ArrayList<>();

        for (int i = 0; i < parent.length; i++) {
            mstTree.add(new ArrayList<>());
        }

        for (int i = 0; i < parent.length; i++) {
            int p = parent[i];

            if (p != -1) {
                mstTree.get(i).add(p);
                mstTree.get(p).add(i);
            }
        }

        System.out.println("\nMST Tree:");
        for (int i = 0; i < mstTree.size(); i++) {
            System.out.println(i + " -> " + mstTree.get(i));
        }

        DFSRoute dfsRoute = new DFSRoute(mstTree);
        List<Integer> dfsRouteList = dfsRoute.getRoute(0);

        double dfsCost = calculateRouteCost(dfsRouteList, graph);

        System.out.println("\nMST DFS Route:");
        System.out.println(dfsRouteList);
        System.out.println("DFS Route Cost: " + dfsCost);

        BruteForceTSP tsp = new BruteForceTSP(graph);
        List<Integer> optimalRoute = tsp.solve(0);
        double optimalCost = tsp.getBestCost();

        System.out.println("\nOptimal TSP Route:");
        System.out.println(optimalRoute);
        System.out.println("Optimal TSP Cost: " + optimalCost);

        double percentDiff = ((dfsCost - optimalCost) / optimalCost) * 100.0;

        System.out.println("\nDifference (DFS vs Optimal): " + String.format("%.2f", percentDiff) + "%");
    }

    // Shared cost calculator for BOTH DFS + TSP
    private static double calculateRouteCost(
            List<Integer> route,
            Graph g) {

        double cost = 0;

        for (int i = 0; i < route.size() - 1; i++) {
            cost += g.getDistance(route.get(i), route.get(i + 1));
        }

        return cost;
    }
}
