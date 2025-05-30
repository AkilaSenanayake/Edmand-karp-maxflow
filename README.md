# Edmonds-Karp Max Flow (Java Implementation)

This project implements the **Edmonds-Karp algorithm** in Java to compute the **maximum flow** in a directed flow network. The algorithm uses **Breadth-First Search (BFS)** to find the shortest augmenting paths and calculates the total maximum flow from a **source node** to a **sink node**.

## 📌 Features

- Reads network graph input from a `.txt` file
- Automatically detects source and sink nodes
- Uses adjacency lists for efficient graph representation
- Prints each augmenting path and its flow contribution
- Calculates total maximum flow and execution time

## 🧠 Algorithm Used

This project uses the **Edmonds-Karp** algorithm, which is an implementation of the **Ford-Fulkerson** method that uses **BFS** to find augmenting paths. It ensures polynomial-time performance for finding the maximum flow in a flow network.

## 📂 Project Structure

├── Main.java # Main class for file input and program execution
├── Edge.java # Defines the Edge structure with reverse/residual edges
├── GraphFromFile.java # Loads the graph from input file and builds the graph structure
├── MaxFlow.java # Core Edmonds-Karp algorithm and flow computation
└── benchmarks/ # Folder to store input files (graphs)

## 📝 Sample Input Format (in benchmarks folder)

The input file should follow this structure:

6
0 1 10
0 2 10
1 3 4
1 2 2
2 4 9
3 5 10
4 3 6
4 5 10

- The first line contains the number of nodes.
- Each following line represents a directed edge with format:  
  `from_node to_node capacity`

## ✅ How to Run

1. **Compile all Java files:**

javac Main.java

🖥️ Example Output

Enter the name of the input file: sample.txt
File found: /path/to/benchmarks/sample.txt
Successfully loaded network from file: sample.txt
Network Information:
- Number of nodes: 6
- Source node: 0
- Sink node: 5

Augmenting Path 1: 0 -> 1 -> 3 -> 5 | Flow added: 4
Augmenting Path 2: 0 -> 2 -> 4 -> 5 | Flow added: 9
...

Maximum Flow: 19
Total time taken: 2 ms

👨‍💻 Author
Your Name Here

GitHub Profile

📄 License
This project is licensed under the MIT License - feel free to use and modify it.

Let me know if you'd like me to customize it further with your name or GitHub link!
