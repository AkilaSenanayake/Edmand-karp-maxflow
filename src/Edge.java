//Represents each edge in the graph, including capacity, flow, and a reference to the residual edge.

public class Edge {
    int from, to;
    int capacity, flow;  //maximum possible flow,  current flow
    Edge residual;  //the reverse edge (used in Edmonds-Karp)

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public int residualCapacity() {
        return capacity - flow;  //Shows how much more flow can still go through the edge. (tells you the remaining space to push more flow).
    }

    public void addFlow(int amount) {
        flow += amount;
        residual.flow -= amount;  //Adds flow to the forward edge and subtracts from the residual (reverse) edge.
        //adds flow to the forward edge.
        //It subtracts the same amount from the residual (reverse) edge, so that flow can be adjusted if needed later.
    }
}


//"Edge.java stores information about an edge in the flow network.
// Each edge also has a reverse edge called the residual edge, which is used to adjust flow back if needed."