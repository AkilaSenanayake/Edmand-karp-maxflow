import java.io.*;  // Import classes for reading files and handling input/output.
import java.util.Scanner;  // Import Scanner class for user input.

//Takes a filename input from the user. Locates and loads a graph from that file. Runs the Edmonds-Karp algorithm to find maximum flow.
//Prints paths and final result.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "";
        boolean validFile = false;  // Flag to check if a valid file was found.

        //Ask user to enter file name. Keep asking until a valid file is found.
        while (!validFile) {
            System.out.print("Enter the name of the input file: ");
            filename = scanner.nextLine().trim();  // Get user input and remove extra spaces.

            File file;
            // Check if the filename already includes the "benchmarks" folder
            if (filename.contains("benchmarks")) {   //Try to locate the file. If it already includes "benchmarks", use it directly. Otherwise, check in benchmarks/ or src/benchmarks/.
                file = new File(filename);
            } else {
                file = new File("benchmarks" + File.separator + filename);  // Try to find the file inside the "benchmarks" folder
                if (file.exists()) {
                    filename = file.getPath();  // If it exists, update the filename path
                } else {
                    // Try to look in "src/benchmarks" folder as a backup
                    file = new File("src" + File.separator + "benchmarks" + File.separator + filename);
                    if (file.exists()) {
                        filename = file.getPath();  // Update the path again if found
                    }
                }
            }

            // If the file exists and is a real file (not a folder)
            if (file.exists() && file.isFile()) {
                validFile = true;  // Exit the loop
                System.out.println("File found: " + file.getAbsolutePath());
            } else {
                // If not found, show error and loop again
                System.out.println("Error: File '" + filename + "' not found.");
                System.out.println("Make sure the file is in the 'benchmarks' folder or provide the full path.");
            }
        }

        try {
            int[] sourceSink = new int[2];  // This array will store source and sink node numbers.

            // Load the graph from the input file, and set up the MaxFlow object.
            MaxFlow mf = GraphFromFile.loadGraph(filename, sourceSink);  //Call another class to load the graph from file.
            int source = sourceSink[0]; // Get the source node.
            int sink = sourceSink[1];  // Get the sink node.

            System.out.println("Successfully loaded network from file: " + filename);
            System.out.println("Network Information:");
            System.out.println("- Number of nodes: " + mf.n);
            System.out.println("- Source node: " + source);
            System.out.println("- Sink node: " + sink);

            long start = System.currentTimeMillis(); //Start timer before the algorithm runs
            int maxFlow = mf.edmondsKarpWithPaths();  //Call the main algorithm to find the maximum flow. It also prints each path found.
            long end = System.currentTimeMillis();  //	Stop timer after algorithm completes

            System.out.println("\nMaximum Flow: " + maxFlow);
            System.out.println("Total time taken: " + (end - start) + " ms");  //Time taken to compute maximum flow

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format in the input file. Please check the file format.");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


//"The Main.java file is the entry point. It loads a graph file, identifies the source and sink nodes, and then applies
// the Edmonds-Karp algorithm to find the maximum flow from source to sink. It prints each augmenting path and the final result."