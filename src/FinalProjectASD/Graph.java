package FinalProjectASD;

public class Graph {
    private int[][] matrix;
    private int size;
    private String[] edgeLines; // Track which line each edge belongs to

    public Graph(int size) {
        this.size = size;
        matrix = new int[size][size];
        edgeLines = new String[size * size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void addEdge(int from, int to, int weight, String line) {
        matrix[from][to] = weight;
        matrix[to][from] = weight; // Undirected graph
        edgeLines[from * size + to] = line;
        edgeLines[to * size + from] = line;
    }

    public String getEdgeLine(int from, int to) {
        return edgeLines[from * size + to];
    }

    public void printAllEdges(String[] names, String[] lines) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║         ALL SUROBOYO BUS ROUTES                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        System.out.println("RED LINE Routes:");
        System.out.println("───────────────────────────────────────────────────");
        int count = 1;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] != 0 && getEdgeLine(i, j).equals("RED")) {
                    System.out.printf("%2d. %-20s <-> %-20s : %2d km [🔴]\n",
                            count++, names[i], names[j], matrix[i][j]);
                }
            }
        }

        System.out.println("\nBLUE LINE Routes:");
        System.out.println("───────────────────────────────────────────────────");
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] != 0 && getEdgeLine(i, j).equals("BLUE")) {
                    System.out.printf("%2d. %-20s <-> %-20s : %2d km [🔵]\n",
                            count++, names[i], names[j], matrix[i][j]);
                }
            }
        }
        System.out.println("\n════════════════════════════════════════════════════\n");
    }

    public int countEdges() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public int[][] getEdgeArrays() {
        int edgeCount = countEdges();
        int[][] edges = new int[edgeCount][3];
        int index = 0;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] != 0) {
                    edges[index][0] = i;
                    edges[index][1] = j;
                    edges[index][2] = matrix[i][j];
                    index++;
                }
            }
        }
        return edges;
    }

    // Dijkstra's algorithm with line tracking
    public int[] dijkstra(int start, int[] parents, String[] haltes) {
        System.out.println("\n[Dijkstra's Algorithm] Finding shortest path...");
        System.out.println("From: " + haltes[start]);

        int[] distances = new int[size];
        boolean[] visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            parents[i] = -1;
        }
        distances[start] = 0;

        for (int count = 0; count < size - 1; count++) {
            int u = -1;
            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                if (!visited[i] && distances[i] < minDistance) {
                    minDistance = distances[i];
                    u = i;
                }
            }

            if (u == -1) break;

            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (matrix[u][v] != 0 && !visited[v]) {
                    int newDist = distances[u] + matrix[u][v];
                    if (newDist < distances[v]) {
                        distances[v] = newDist;
                        parents[v] = u;
                    }
                }
            }
        }

        System.out.println("✓ Calculation complete!\n");
        return distances;
    }

    public int[] buildPath(int start, int end, int[] parents) {
        int[] stack = new int[size];
        int top = 0;

        int current = end;
        while (current != -1) {
            stack[top++] = current;
            current = parents[current];
        }

        int[] path = new int[top];
        for (int i = top - 1; i >= 0; i--) {
            path[top - 1 - i] = stack[i];
        }
        return path;
    }

    public boolean hasPath(int start, int end, int[] distances) {
        return distances[end] != Integer.MAX_VALUE;
    }

    // Display path with line transfer information
    public void displayPathWithTransfers(int[] path, String[] haltes, String[] lines) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║            ROUTE WITH TRANSFER INFO              ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        String currentLine = getEdgeLine(path[0], path[1]);

        System.out.println("1. Start at: " + haltes[path[0]]);
        System.out.println("   Board: " + (currentLine.equals("RED") ? "🔴 RED LINE BUS" : "🔵 BLUE LINE BUS"));
        System.out.println();

        int stepNumber = 2;
        for (int i = 1; i < path.length; i++) {
            String nextLine;
            if (i < path.length - 1) {
                nextLine = getEdgeLine(path[i], path[i + 1]);
            } else {
                nextLine = currentLine; // Last stop, no next segment
            }

            // Check if transfer is needed
            if (!nextLine.equals(currentLine) && i < path.length - 1) {
                System.out.println(stepNumber + ". 🚏 TRANSFER AT: " + haltes[path[i]]);
                System.out.println("   ⚠️  Get off: " + (currentLine.equals("RED") ? "🔴 RED LINE" : "🔵 BLUE LINE"));
                System.out.println("   ✅ Board: " + (nextLine.equals("RED") ? "🔴 RED LINE BUS" : "🔵 BLUE LINE BUS"));
                System.out.println();
                currentLine = nextLine;
                stepNumber++;
            }

            // Regular stop
            if (i == path.length - 1) {
                System.out.println(stepNumber + ". 🏁 Arrive at: " + haltes[path[i]]);
            } else {
                System.out.println(stepNumber + ". Pass through: " + haltes[path[i]]);
                stepNumber++;
            }
        }

        System.out.println("\n════════════════════════════════════════════════════\n");
    }

    public void printTreeStructure(String[] haltes) {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║     TREE STRUCTURE - SUROBOYO BUS NETWORK         ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");

        boolean[] visited = new boolean[size];
        printTreeRecursive(0, haltes, visited, "", true);

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                System.out.println("\n[Disconnected Component]");
                printTreeRecursive(i, haltes, visited, "", true);
            }
        }
        System.out.println();
    }

    private void printTreeRecursive(int node, String[] haltes, boolean[] visited,
                                    String prefix, boolean isLast) {
        if (visited[node]) return;

        visited[node] = true;
        System.out.println(prefix + (isLast ? "└── " : "├── ") + haltes[node]);

        int[] adjacent = new int[size];
        int adjCount = 0;

        for (int i = 0; i < size; i++) {
            if (matrix[node][i] != 0 && !visited[i]) {
                adjacent[adjCount++] = i;
            }
        }

        for (int i = 0; i < adjCount; i++) {
            boolean isLastChild = (i == adjCount - 1);
            String newPrefix = prefix + (isLast ? "    " : "│   ");
            System.out.print(newPrefix + (isLastChild ? "└── " : "├── "));
            System.out.println(haltes[adjacent[i]] + " (" + matrix[node][adjacent[i]] + " km)");

            printTreeRecursive(adjacent[i], haltes, visited,
                    newPrefix + (isLastChild ? "    " : "│   "), true);
        }
    }

    public void printAdjacencyList(String[] haltes) {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║    ADJACENCY LIST - NETWORK REPRESENTATION        ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");

        for (int i = 0; i < size; i++) {
            System.out.print(haltes[i] + " → ");
            boolean first = true;

            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != 0) {
                    if (!first) System.out.print(", ");
                    System.out.print(haltes[j] + " (" + matrix[i][j] + " km)");
                    first = false;
                }
            }

            if (first) {
                System.out.print("(no connections)");
            }
            System.out.println();
        }
        System.out.println();
    }
}