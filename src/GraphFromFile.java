import java.io.*;
import java.util.*; // Import utility classes like List, Set, etc.

//reading data and converting it into a graph.
//Reads the input file, creates the graph, and returns a MaxFlow object.

public class GraphFromFile {
    public static MaxFlow loadGraph(String filename, int[] sourceSinkHolder) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        int n = Integer.parseInt(br.readLine().trim());         //Open the file

        List<int[]> edgeList = new ArrayList<>();
        Set<Integer> fromNodes = new HashSet<>();
        Set<Integer> toNodes = new HashSet<>();     //Use lists/sets to collect edge info.

        String line;
        int source = -1;
        boolean firstEdgeLine = true;

        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 3) continue;                    //Skip lines that don't have 3 numbers.

            int from = Integer.parseInt(parts[0]);  //start node
            int to = Integer.parseInt(parts[1]);  //end node
            int capacity = Integer.parseInt(parts[2]);  //max flow for the edge

            if (firstEdgeLine) {
                source = from;
                firstEdgeLine = false;
            }

            edgeList.add(new int[]{from, to, capacity});
            fromNodes.add(from);
            toNodes.add(to);
        }
        br.close();

        Set<Integer> candidateSinks = new HashSet<>(toNodes);
        candidateSinks.removeAll(fromNodes);        //Try to automatically detect the sink node. A sink node has no outgoing edges.
        int sink = candidateSinks.isEmpty() ? n - 1 : Collections.max(candidateSinks); // fallback to n-1

        sourceSinkHolder[0] = source;
        sourceSinkHolder[1] = sink;

        MaxFlow mf = new MaxFlow(n, source, sink);  //Create a MaxFlow object. Add all edges to the flow graph.
        for (int[] edge : edgeList) {
            mf.addEdge(edge[0], edge[1], edge[2]);
        }

        return mf;
    }
}


//"GraphFromFile.java reads a graph from a file and returns a MaxFlow object.
// It reads each line as an edge with capacity and identifies the source and sink nodes automatically based on edge direction."