package app;

import core.Graph;
import core.Temple;
import algorithms.PrimMST;
import algorithms.DFSRoute;
import algorithms.BruteForceTSP;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Temple> temples = new ArrayList<>();

        temples.add(new Temple(0, "Central Sikh Temple", 1.317220, 103.865100));
        temples.add(new Temple(1, "Gurdwara Sahib Yishun", 1.430250, 103.818530));
        temples.add(new Temple(2, "Gurdwara Sahib Silat Road", 1.277720, 103.839450));
        temples.add(new Temple(3, "Gurdwara Sahib Katong Singapore", 1.300270, 103.900460));
        temples.add(new Temple(4, "Shri Guru Singh Sabha", 1.306660, 103.852930));
        temples.add(new Temple(5, "Khalsa Dharmak Sabha Gurudwara", 1.311280, 103.878050));
        temples.add(new Temple(6, "Pardesi Khalsa Dharmak Diwan Gurudwara", 1.333650, 103.937580));

        int n = temples.size();
        double[][] dist = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = haversine(
                            temples.get(i).getLat(),
                            temples.get(i).getLon(),
                            temples.get(j).getLat(),
                            temples.get(j).getLon()
                    );
                }
            }
        }

        Graph graph = new Graph(dist);

        PrimMST prim = new PrimMST(graph);
        int[] parent = prim.mstCalc(0);
        double mstEdgeCost = prim.getTotalWeight();

        // Build MST tree (undirected)
        List<List<Integer>> mstTree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mstTree.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] != -1) {
                mstTree.get(i).add(parent[i]);
                mstTree.get(parent[i]).add(i);
            }
        }

        DFSRoute dfsRoute = new DFSRoute(mstTree);
        List<Integer> dfsPath = dfsRoute.getRoute(0);
        double dfsCost = calculateRouteCost(dfsPath, graph);

        BruteForceTSP tsp = new BruteForceTSP(graph);
        List<Integer> optimalPath = tsp.solve(0);
        double optimalCost = tsp.getBestCost();

        System.out.println("START: " + temples.get(0).getName());

        System.out.println("\nMST Edge Cost: "
                + String.format("%.2f", mstEdgeCost) + " km");

        System.out.println("\nMST DFS Route:");
        printRoute(dfsPath, temples);
        System.out.println("Route Cost: "
                + String.format("%.2f", dfsCost) + " km");

        System.out.println("\nOptimal TSP Route:");
        printRoute(optimalPath, temples);
        System.out.println("Optimal Cost: "
                + String.format("%.2f", optimalCost) + " km");

        double percentDiff =
                ((dfsCost - optimalCost) / optimalCost) * 100.0;

        System.out.println("\nDifference (DFS vs Optimal): "
                + String.format("%.2f", percentDiff) + "%");
    }

    private static double calculateRouteCost(List<Integer> route, Graph g) {

        double cost = 0;

        for (int i = 0; i < route.size() - 1; i++) {
            cost += g.getDistance(route.get(i), route.get(i + 1));
        }

        return cost;
    }

    private static void printRoute(List<Integer> route, List<Temple> temples) {
        for (int i = 0; i < route.size(); i++) {
            System.out.print(temples.get(route.get(i)).getName());
            if (i != route.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println();
    }

    private static double haversine(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371; // Earth radius in km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
