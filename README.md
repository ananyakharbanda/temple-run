```text
temple-run

a small graph theory experiment.

goal:
visit all 7 major sikh gurdwaras in singapore starting from central sikh temple,
using classical graph algorithms instead of a maps api.

model:
- each temple → vertex (gps coordinates)
- distance → haversine formula
- complete weighted graph

compare:

1) mst + dfs (approximation)
   prim → O(E log V)
   dfs  → O(V)

2) brute-force tsp (exact)
   O(n!)  where n = 7 (6! = 720 permutations)

core idea in code:

PrimMST prim = new PrimMST(graph);
int[] parent = prim.mstCalc(0);

DFSRoute dfs = new DFSRoute(mstTree);
List<Integer> approxRoute = dfs.getRoute(0);

BruteForceTSP tsp = new BruteForceTSP(graph);
List<Integer> optimalRoute = tsp.solve(0);

what it shows:
- mst minimizes connection cost, not traversal cost
- tsp minimizes traversal cost
- approximation can be close to optimal
- factorial growth becomes real very quickly

structure:
core/        → graph, temple, edge
algorithms/  → prim, dfs, tsp
app/         → main

run:
javac core/*.java algorithms/*.java app/*.java
java app.Main

github:
https://github.com/ananyakharbanda/temple-run
```
