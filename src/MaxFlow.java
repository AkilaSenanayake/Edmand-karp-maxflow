import java.util.*;

//Stores the graph as an adjacency list and runs the Edmonds-Karp algorithm.

public class MaxFlow {
    int n;
    int source;
    int sink;
    List<Edge>[] graph;

    public MaxFlow(int n, int source, int sink) {  //Initialize the graph and set source/sink.
        this.n = n;
        this.source = source;
        this.sink = sink;
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, int capacity) {  //Adds both the real edge and its residual edge.
        Edge e1 = new Edge(from, to, capacity);
        Edge e2 = new Edge(to, from, 0);
        e1.residual = e2;
        e2.residual = e1;
        graph[from].add(e1);
        graph[to].add(e2);
    }

    int edmondsKarpWithPaths() { //Main Algorithm
        int maxFlow = 0;         //Repeat until no more paths from source to sink: Use BFS to find the shortest augmenting path.
                                 //Find the bottleneck capacity (smallest residual capacity in the path) Add flow to all edges in that path.Print the path and flow added.
        int pathNum = 1;
        while (true) {
            Edge[] parent = new Edge[n];
            Queue<Integer> q = new LinkedList<>();
            q.add(source);   //BFS setup: Find shortest path from source to sink.

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (Edge e : graph[cur]) {
                    if (parent[e.to] == null && e.to != source && e.residualCapacity() > 0) {
                        parent[e.to] = e;
                        q.add(e.to);
                    }
                }
            }                             //Stop if no more path found.

            if (parent[sink] == null) break;

            List<Integer> path = new ArrayList<>();
            int bottleneck = Integer.MAX_VALUE;
            for (Edge e = parent[sink]; e != null; e = parent[e.from]) {
                bottleneck = Math.min(bottleneck, e.residualCapacity());
                path.add(0, e.to);
            }
            path.add(0, source);

            System.out.print("Augmenting Path " + pathNum++ + ": ");  //Shows the exact path used for each step.
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) System.out.print(" -> ");
            }
            System.out.println(" | Flow added: " + bottleneck);

            for (Edge e = parent[sink]; e != null; e = parent[e.from]) {
                e.addFlow(bottleneck);
            }

            maxFlow += bottleneck;
        }
        return maxFlow;
    }
}


//"MaxFlow.java implements the Edmonds-Karp algorithm using BFS. It finds paths from the source to sink and sends as much flow as possible along them,
// adjusting both forward and backward edges.
// It repeats this until no more paths are available."
