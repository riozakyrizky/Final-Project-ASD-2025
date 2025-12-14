# Public Transportation Route System Using Graph Algorithm
Subject: ES234317 - Algorithm and Data Structure (ASD)

Lecturer: Renny Pradina Kusumawardani

Class: Q / IUP

Group: 2

Team Members:
1. 5026241047, Riozaky Muhammad Rizky
2. 5026241049, Najla Hanasyifa
3. 5026231056, Balindra Adisakti

# Project Background
Rapid urban growth increases the complexity of planning and using public transportation routes, especially when passengers need to find efficient trips involving transfers between bus corridors/lines. Manual route planning and information lookup can be slow and error-prone, which makes it harder for passengers to identify the fastest route and understand where transfers are required.

# Our Solution
The system models the Suroboyo Bus network as a weighted, undirected graph where bus stops (halte) are vertices, connections between stops are edges, and distances (km) are edge weights. With this structure, the program can compute optimized routes (shortest paths) and present step-by-step travel instructions, including transfer information between lines.

# Program Features
1. List and manage bus stops (83 halte) with corridor/line information, including designated transfer points.
2. Search for a bus stop by name to support user input in a menu-driven console interface.
3. Show available routes and sort route segments by distance for clearer viewing.
4. Compute the shortest route between two stops and display transfer steps when the bus line changes along the path.
5. Visualize/print the network structure (tree-like output) and provide adjacency-style views to help validate connectivity and support debugging.

# Algorithms and Implementations
1. Linear Search supports stop-name lookup because the stop list is not assumed to be alphabetically sorted and the dataset is relatively small. (Implemented in HalteManager.java)
2. Selection Sort supports the “sorted route list” feature by sorting route segments by distance while keeping multiple parallel arrays synchronized. (Implemented in Route.java)
3. Dijkstra’s Algorithm is the core of shortest-route calculation on a weighted graph and is used to compute shortest distances and parent pointers for path reconstruction. (Implemented in Graph.java)
4. A Stack-based path reconstruction technique supports converting “parent pointers” into an ordered path from start to destination. (Implemented in Graph.java)
5. Depth-first traversal via recursion supports the “tree structure” visualization of the network. (Implemented in Graph.java)
6. System flow and integration are coordinated through a menu-based controller that initializes data and constructs the network by repeatedly adding edges. (Implemented in FinalProject.java)

# Program Screenshots
<img width="1552" height="981" alt="image" src="https://github.com/user-attachments/assets/9c14b948-0bbc-4986-a87b-71dfd7838b17" />

<img width="479" height="666" alt="image" src="https://github.com/user-attachments/assets/a13284cf-3f7a-444a-bee7-2820c8ca9c2b" />

<img width="1153" height="672" alt="image" src="https://github.com/user-attachments/assets/3fd0c1fc-66ef-43c4-abdb-2cb6418ec289" />

# Other Groups' Repository Link
Group 1: github.com/yelllsit-glitch/Final-Project-ASD

Group 3: github.com/strupwa/RevASD

Group 4: github.com/Stephanie020207/Connect4AIGame

Group 5: github.com/Daffa-001/ASD-IUP-FinalProject
