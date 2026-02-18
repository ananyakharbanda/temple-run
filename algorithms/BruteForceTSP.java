package algorithms;

import core.Graph;
import java.util.*;

public class BruteForceTSP {

    private Graph g;
    private double bestCost;
    private List<Integer> bestRoute;

    public BruteForceTSP(Graph g) {
        this.g = g;
        bestCost = Double.POSITIVE_INFINITY;
        bestRoute = new ArrayList<>();
    }

    public List<Integer> solve(int start) {

        int n = g.size();

        boolean[] used = new boolean[n];
        List<Integer> current = new ArrayList<>();

        used[start] = true;
        current.add(start);

        dfs(current, used, 0.0);

        return bestRoute;
    }

    private void dfs(List<Integer> current,
                     boolean[] used,
                     double costSoFar) {

        int n = g.size();

        if (current.size() == n) {
            if (costSoFar < bestCost) {
                bestCost = costSoFar;
                bestRoute = new ArrayList<>(current);
            }
            return;
        }

        int last = current.get(current.size() - 1);

        for (int next = 0; next < n; next++) {
            if (!used[next]) {

                double newCost =
                    costSoFar + g.getDistance(last, next);

                if (newCost >= bestCost) continue;

                used[next] = true;
                current.add(next);

                dfs(current, used, newCost);

                current.remove(current.size() - 1);
                used[next] = false;
            }
        }
    }

    public double getBestCost() {
        return bestCost;
    }
}
