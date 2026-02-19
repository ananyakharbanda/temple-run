package app;

import core.Graph;
import algorithms.PrimMST;
import algorithms.DFSRoute;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
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
            System.out.println(i + " == " + parent[i]);
        }

        System.out.println("Total MST weight: " + prim.getTotalWeight());

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

        System.out.println("MST Tree:");
        for (int i = 0; i < mstTree.size(); i++) {
            System.out.println(i + " -> " + mstTree.get(i));
        }

        DFSRoute dfsRoute = new DFSRoute(mstTree);

        List<Integer> route = dfsRoute.getRoute(0);

        System.out.println("\nDFS Route:");
        System.out.println(route);
    }
}
