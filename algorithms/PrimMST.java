import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class PrimMST {
    
    private static final int NO_PARENT = -1;
    private PriorityQueue<Edge> minHeap;
    private boolean[] visited;
    private static final int TOTAL = 7;
    public int[] parent;
    
    public PrimMST() {
        this.parent = new int[TOTAL];
        this.visited = new boolean[TOTAL];   
        Arrays.fill(parent, NO_PARENT); 
        this.minHeap = new PriorityQueue<Edge>((a, b) -> Integer.compare(a.getWeight(), b.getWeight()));
    }
    
    public int[] mstCalc(int start) {
        primMSThelper(start, n);
        return this.parent;
    }
    
    
         
     
