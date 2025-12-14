package FinalProjectASD;

import java.util.*;

public class FinalProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HalteManager halteManager = new HalteManager();
        String[] haltes = halteManager.getHaltes();
        String[] lines = halteManager.getBusLines();

        // Initialize FinalProjectASD.Graph with new size (83 stops)
        Graph graph = new Graph(haltes.length);

        // ==================================================================
        // BUILDING THE NETWORK (EDGES)
        // ==================================================================

        // --- 1. RED LINE: SOUTH SEGMENT (Purabaya -> Wonokromo -> Joyoboyo) ---
        graph.addEdge(0, 1, 2, "RED");   // Purabaya - Dukuh Menanggal
        graph.addEdge(1, 2, 2, "RED");   // Dukuh Menanggal - Siwalankerto
        graph.addEdge(2, 3, 3, "RED");   // Siwalankerto - Taman Pelangi
        graph.addEdge(3, 4, 2, "RED");   // Taman Pelangi - RS Bhayangkara
        graph.addEdge(4, 5, 1, "RED");   // RS Bhayangkara - UBHARA
        graph.addEdge(5, 6, 2, "RED");   // UBHARA - PUSVETMA
        graph.addEdge(6, 7, 2, "RED");   // PUSVETMA - Ketintang
        graph.addEdge(7, 8, 2, "RED");   // Ketintang - Royal Plaza
        graph.addEdge(8, 9, 2, "RED");   // Royal Plaza - Joyoboyo (HUB)
        graph.addEdge(9, 10, 1, "RED");  // Joyoboyo - Wonokromo
        graph.addEdge(10, 11, 2, "RED"); // Wonokromo - Jagir
        graph.addEdge(11, 12, 3, "RED"); // Jagir - Ngagel
        graph.addEdge(12, 13, 2, "RED"); // Ngagel - Marvel City

        // --- 2. RED LINE: CENTRAL SEGMENT (Joyoboyo -> Tunjungan) ---
        graph.addEdge(9, 14, 3, "RED");  // Joyoboyo - Museum BI
        graph.addEdge(14, 15, 2, "RED"); // Museum BI - RS Darmo
        graph.addEdge(15, 16, 2, "RED"); // RS Darmo - Pandegiling
        graph.addEdge(16, 17, 2, "RED"); // Pandegiling - Basra
        graph.addEdge(17, 18, 1, "RED"); // Basra - Kaliasin
        graph.addEdge(18, 19, 2, "RED"); // Kaliasin - Embong Malang
        graph.addEdge(19, 20, 2, "RED"); // Embong Malang - Blauran
        graph.addEdge(20, 21, 2, "RED"); // Blauran - Pirngadi
        graph.addEdge(21, 29, 3, "RED"); // Pirngadi - Pasar Turi

        // Loop back Southbound (One-way logic simulated as undirected for simplicity)
        graph.addEdge(20, 22, 2, "RED"); // Blauran - Siola
        graph.addEdge(22, 23, 1, "RED"); // Siola - Tunjungan Plaza
        graph.addEdge(23, 24, 1, "RED"); // Tunjungan Plaza - Gubernur Suryo
        graph.addEdge(24, 25, 2, "RED"); // Gubernur Suryo - Panglima Sudirman
        graph.addEdge(25, 26, 1, "RED"); // Panglima Sudirman - Sono Kembang
        graph.addEdge(26, 27, 2, "RED"); // Sono Kembang - Urip Sumoharjo
        graph.addEdge(27, 28, 2, "RED"); // Urip Sumoharjo - Santa Maria
        graph.addEdge(28, 15, 2, "RED"); // Santa Maria - RS Darmo (Connects back)

        // --- 3. RED LINE: NORTH SEGMENT (Pasar Turi -> Perak) ---
        graph.addEdge(29, 30, 2, "RED"); // Pasar Turi - Masjid Kemayoran
        graph.addEdge(30, 31, 2, "RED"); // Masjid Kemayoran - Indrapura
        graph.addEdge(31, 32, 3, "RED"); // Indrapura - Rajawali
        graph.addEdge(32, 33, 1, "RED"); // Rajawali - Jembatan Merah
        graph.addEdge(33, 34, 1, "RED"); // Jembatan Merah - Veteran
        graph.addEdge(34, 35, 2, "RED"); // Veteran - Tugu Pahlawan
        graph.addEdge(35, 36, 2, "RED"); // Tugu Pahlawan - Alun-Alun Contong
        graph.addEdge(36, 22, 2, "RED"); // Alun-Alun Contong - Siola (Connects loop)

        // North Extension to Perak
        graph.addEdge(32, 37, 4, "RED"); // Rajawali - Perak Timur
        graph.addEdge(37, 38, 2, "RED"); // Perak Timur - Perak Barat
        graph.addEdge(38, 39, 2, "RED"); // Perak Barat - Barunawati
        graph.addEdge(39, 32, 3, "RED"); // Barunawati - Rajawali (Loop)

        // --- 4. BLUE LINE: WEST SEGMENT (UNESA -> Mayjen) ---
        graph.addEdge(40, 41, 3, "BLUE"); // UNESA - Graha Family
        graph.addEdge(41, 42, 2, "BLUE"); // Graha Family - Yono Suwoyo
        graph.addEdge(42, 43, 2, "BLUE"); // Yono Suwoyo - PTC
        graph.addEdge(43, 44, 3, "BLUE"); // PTC - Dukuh Pakis
        graph.addEdge(44, 45, 2, "BLUE"); // Dukuh Pakis - HR Muhammad
        graph.addEdge(45, 46, 3, "BLUE"); // HR Muhammad - Mayjen Sungkono
        graph.addEdge(46, 47, 2, "BLUE"); // Mayjen Sungkono - Adityawarman
        graph.addEdge(47, 48, 2, "BLUE"); // Adityawarman - Kutai
        graph.addEdge(48, 49, 2, "BLUE"); // Kutai - Bengawan
        graph.addEdge(49, 15, 3, "BLUE"); // Bengawan - RS Darmo (**TRANSFER POINT**)

        // Alternative path via Gelora Pancasila to Joyoboyo
        graph.addEdge(46, 50, 2, "BLUE"); // Mayjen - KPU Indragiri
        graph.addEdge(50, 51, 2, "BLUE"); // KPU - Gelora Pancasila
        graph.addEdge(51, 9, 3, "BLUE");  // Gelora Pancasila - Joyoboyo (**TRANSFER POINT**)

        // --- 5. BLUE LINE: EAST SEGMENT (Gubeng -> ITS) ---
        graph.addEdge(25, 52, 4, "BLUE"); // Panglima Sudirman - Gubeng Airlangga (**TRANSFER POINT**)
        graph.addEdge(52, 53, 2, "BLUE"); // Gubeng - UNAIR B
        graph.addEdge(53, 54, 2, "BLUE"); // UNAIR B - Karang Menjangan
        graph.addEdge(54, 55, 3, "BLUE"); // Karang Menjangan - Samsat Manyar
        graph.addEdge(55, 56, 2, "BLUE"); // Samsat Manyar - Kertajaya
        graph.addEdge(56, 57, 2, "BLUE"); // Kertajaya - KONI
        graph.addEdge(57, 58, 2, "BLUE"); // KONI - Galaxy Mall
        graph.addEdge(58, 59, 2, "BLUE"); // Galaxy Mall - MERR ITS
        graph.addEdge(59, 60, 2, "BLUE"); // MERR ITS - ITS Kampus
        graph.addEdge(60, 61, 2, "BLUE"); // ITS Kampus - PENS
        graph.addEdge(61, 62, 2, "BLUE"); // PENS - Keputih
        graph.addEdge(62, 63, 2, "BLUE"); // Keputih - Arief Rahman Hakim
        graph.addEdge(63, 64, 3, "BLUE"); // Arief Rahman Hakim - Klampis
        graph.addEdge(64, 55, 4, "BLUE"); // Klampis - Samsat Manyar (Loop)

        // --- 6. BLUE LINE: MERR NORTH & SOUTH EXTENSION ---
        // North from Galaxy Mall
        graph.addEdge(59, 77, 3, "BLUE"); // MERR ITS - Mulyorejo
        graph.addEdge(77, 76, 3, "BLUE"); // Mulyorejo - Kalijudan
        graph.addEdge(76, 78, 4, "BLUE"); // Kalijudan - Kenjeran Park
        graph.addEdge(78, 79, 2, "BLUE"); // Kenjeran Park - Bulak
        graph.addEdge(79, 80, 3, "BLUE"); // Bulak - Kedung Cowek
        graph.addEdge(80, 81, 5, "BLUE"); // Kedung Cowek - Suramadu
        graph.addEdge(81, 82, 4, "BLUE"); // Suramadu - Sidotopo

        // South from MERR ITS
        graph.addEdge(59, 66, 3, "BLUE"); // MERR ITS - Semolowaru
        graph.addEdge(66, 67, 2, "BLUE"); // Semolowaru - Nginden
        graph.addEdge(67, 68, 3, "BLUE"); // Nginden - Prapen
        graph.addEdge(68, 69, 2, "BLUE"); // Prapen - Panjang Jiwo
        graph.addEdge(69, 70, 3, "BLUE"); // Panjang Jiwo - Rungkut Madya
        graph.addEdge(70, 71, 3, "BLUE"); // Rungkut Madya - Gunung Anyar
        graph.addEdge(71, 72, 2, "BLUE"); // Gunung Anyar - UPN Veteran
        graph.addEdge(72, 73, 2, "BLUE"); // UPN - Pandugo
        graph.addEdge(73, 74, 2, "BLUE"); // Pandugo - Penjaringan Sari
        graph.addEdge(74, 75, 2, "BLUE"); // Penjaringan Sari - Kedung Baruk
        graph.addEdge(75, 66, 3, "BLUE"); // Kedung Baruk - Semolowaru (Loop)

        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║      SUROBOYO BUS ROUTE FINDER SYSTEM              ║");
        System.out.println("║                                                    ║");
        System.out.println("║   Algorithms: Linear Search, Selection Sort,       ║");
        System.out.println("║               and Dijkstra's Algorithm             ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        while (true) {
            System.out.println("\n┌────────────────────────────────────────────────┐");
            System.out.println("│              MAIN MENU                         │");
            System.out.println("├────────────────────────────────────────────────┤");
            System.out.println("│ 1. View All Bus Stops (with Lines)             │");
            System.out.println("│ 2. View All Bus Routes (by Line)               │");
            System.out.println("│ 3. View Tree Structure                         │");
            System.out.println("│ 4. View Adjacency List                         │");
            System.out.println("│ 5. Find Shortest Path with Transfer Info       │");
            System.out.println("│ 6. Exit                                        │");
            System.out.println("└────────────────────────────────────────────────┘");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                halteManager.printAll();

            } else if (choice == 2) {
                graph.printAllEdges(haltes, lines);

            } else if (choice == 3) {
                graph.printTreeStructure(haltes);

            } else if (choice == 4) {
                graph.printAdjacencyList(haltes);

            } else if (choice == 5) {
                System.out.println("\n" + "═".repeat(60));
                System.out.println("     SHORTEST PATH FINDER - WITH TRANSFER INFORMATION");
                System.out.println("═".repeat(60));

                halteManager.printAll();

                // STEP 1: LINEAR SEARCH - Input Start Location
                System.out.println("\n--- STEP 1: INPUT START LOCATION ---");
                System.out.print("Enter starting stop name: ");
                String startName = sc.nextLine();
                int startIndex = halteManager.searchByName(startName);

                if (startIndex == -1) {
                    System.out.println("⚠ Starting stop not found. Please try again.\n");
                    continue;
                }

                // STEP 2: LINEAR SEARCH - Input End Location
                System.out.println("\n--- STEP 2: INPUT DESTINATION ---");
                System.out.print("Enter destination stop name: ");
                String endName = sc.nextLine();
                int endIndex = halteManager.searchByName(endName);

                if (endIndex == -1) {
                    System.out.println("⚠ Destination stop not found. Please try again.\n");
                    continue;
                }

                if (startIndex == endIndex) {
                    System.out.println("⚠ Start and destination are the same!\n");
                    continue;
                }

                // STEP 3: SELECTION SORT - Sort all routes by distance
                System.out.println("\n--- STEP 3: SELECTION SORT ---");
                int[][] edges = graph.getEdgeArrays();
                int n = edges.length;
                int[] A = new int[n];
                int[] B = new int[n];
                int[] W = new int[n];
                String[] L = new String[n];

                for (int i = 0; i < n; i++) {
                    A[i] = edges[i][0];
                    B[i] = edges[i][1];
                    W[i] = edges[i][2];
                    L[i] = graph.getEdgeLine(A[i], B[i]);
                }

                Route.selectionSort(A, B, W, L, haltes);
                Route.displaySortedRoutes(A, B, W, L, haltes);

                // STEP 4: DIJKSTRA - Find shortest path
                System.out.println("--- STEP 4: DIJKSTRA'S ALGORITHM ---");
                int[] parents = new int[haltes.length];
                int[] distances = graph.dijkstra(startIndex, parents, haltes);

                if (!graph.hasPath(startIndex, endIndex, distances)) {
                    System.out.println("✗ No path exists from " + haltes[startIndex] +
                            " to " + haltes[endIndex] + "\n");
                    continue;
                }

                // STEP 5: OUTPUT - Display result with transfer information
                System.out.println("═".repeat(60));
                System.out.println("                  SEARCH RESULTS");
                System.out.println("═".repeat(60));
                System.out.println("From      : " + haltes[startIndex] + " [" + lines[startIndex] + "]");
                System.out.println("To        : " + haltes[endIndex] + " [" + lines[endIndex] + "]");
                System.out.println("Distance  : " + distances[endIndex] + " km");

                int[] path = graph.buildPath(startIndex, endIndex, parents);
                System.out.print("\nSimple Path: ");
                for (int i = 0; i < path.length; i++) {
                    System.out.print(haltes[path[i]]);
                    if (i < path.length - 1) {
                        System.out.print(" → ");
                    }
                }
                System.out.println("\n" + "═".repeat(60));

                // Display detailed path with transfer information
                graph.displayPathWithTransfers(path, haltes, lines);

            } else if (choice == 6) {
                System.out.println("\n╔════════════════════════════════════════════════════╗");
                System.out.println("║       Thank you for using                          ║");
                System.out.println("║       Suroboyo Bus FinalProjectASD.Route Finder System!            ║");
                System.out.println("╚════════════════════════════════════════════════════╝\n");
                break;

            } else {
                System.out.println("⚠ Invalid choice. Please choose 1-6.\n");
            }
        }
        sc.close();
    }
}