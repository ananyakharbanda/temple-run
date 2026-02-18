package algorithms;

import java.util.List;
import java.util.ArrayList;

public class DFSRoute {

    private List<List<Integer>> mstTree;
    private boolean[] visited;
    private List<Integer> route;

    public DFSRoute(List<List<Integer>> mstTree) {
        this.mstTree = mstTree;
    }

    public List<Integer> getRoute(int start) {

        visited = new boolean[mstTree.size()];
        route = new ArrayList<>();

        dfs(start);

        return route;
    }

    private void dfs(int node) {

        visited[node] = true;
        route.add(node);

        for (int neighbor : mstTree.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}
