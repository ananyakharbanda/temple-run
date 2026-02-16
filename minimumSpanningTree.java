import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/* ---------- Edge ---------- */
class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

/* ---------- Weighted Graph ---------- */
class WeightedGraph {

    private List<List<Edge>> g;
    private boolean[] visited;
    private int[] parent;

    private PriorityQueue<Edge> minHeap;
    private static final int NO_PARENT = -1;

    // stores final MST edges (optional but useful)
    private List<Edge> mstEdges;

    public WeightedGraph(List<List<Edge>> g) {
        this.g = g;
    }

    public int[] primMST(int start, int n) {

        visited = new boolean[n];
        parent = new int[n];
        Arrays.fill(parent, NO_PARENT);

        mstEdges = new ArrayList<>();
        minHeap = new PriorityQueue<>();

        // convert 1-indexed → 0-indexed
        int startIdx = start - 1;

        // start from first node
        visited[startIdx] = true;
        minHeap.addAll(g.get(startIdx));

        int edgesUsed = 0;

        while (!minHeap.isEmpty() && edgesUsed < n - 1) {

            Edge min = minHeap.poll();

            int destIdx = min.dest - 1;

            if (visited[destIdx]) continue;

            // include edge in MST
            visited[destIdx] = true;
            parent[destIdx] = min.src;
            mstEdges.add(min);

            edgesUsed++;

            // add new frontier edges
            for (Edge e : g.get(destIdx)) {
                if (!visited[e.dest - 1]) {
                    minHeap.add(e);
                }
            }
        }

        return parent;
    }

    public void printMST() {
        System.out.println("MST Edges:");
        for (Edge e : mstEdges) {
            System.out.println(e.src + " -> " + e.dest +
                               " (w=" + e.weight + ")");
        }
    }

    public int[] getParent() {
        return parent;
    }
}

/* ---------- Main ---------- */
class WeightMain {

    public static void main(String[] args) {

        // --- Graph edges ---
        Edge e1_2 = new Edge(1,2,3);
        Edge e1_4 = new Edge(1,4,4);

        Edge e2_1 = new Edge(2,1,3);
        Edge e2_3 = new Edge(2,3,3);
        Edge e2_4 = new Edge(2,4,4);

        Edge e3_2 = new Edge(3,2,3);
        Edge e3_4 = new Edge(3,4,2);
        Edge e3_5 = new Edge(3,5,4);

        Edge e4_1 = new Edge(4,1,4);
        Edge e4_2 = new Edge(4,2,4);
        Edge e4_3 = new Edge(4,3,2);
        Edge e4_5 = new Edge(4,5,5);
        Edge e4_6 = new Edge(4,6,3);

        Edge e5_3 = new Edge(5,3,4);
        Edge e5_4 = new Edge(5,4,5);
        Edge e5_6 = new Edge(5,6,4);

        Edge e6_4 = new Edge(6,4,3);
        Edge e6_5 = new Edge(6,5,4);

        // adjacency list
        List<List<Edge>> graph = new ArrayList<>();
        graph.add(new ArrayList<>(List.of(e1_2, e1_4)));
        graph.add(new ArrayList<>(List.of(e2_1, e2_3, e2_4)));
        graph.add(new ArrayList<>(List.of(e3_2, e3_4, e3_5)));
        graph.add(new ArrayList<>(List.of(e4_1, e4_2, e4_3, e4_5, e4_6)));
        graph.add(new ArrayList<>(List.of(e5_3, e5_4, e5_6)));
        graph.add(new ArrayList<>(List.of(e6_4, e6_5)));

        WeightedGraph g = new WeightedGraph(graph);

        g.primMST(1, 6);

        g.printMST();

        System.out.println("\nParent array:");
        int[] parent = g.getParent();
        for (int i = 0; i < parent.length; i++) {
            System.out.println((i + 1) + " <- " + parent[i]);
        }
    }
}

