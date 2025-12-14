package FinalProjectASD;

public class Route {

    // Selection Sort - sorts routes by weight (distance)
    public static void selectionSort(int[] A, int[] B, int[] W, String[] L, String[] haltes) {
        int n = W.length;

        System.out.println("\n[Selection Sort] Sorting routes by distance...");
        System.out.println("Total Routes: " + n);

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (W[j] < W[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap weights
            int tempW = W[i];
            W[i] = W[minIndex];
            W[minIndex] = tempW;

            // Swap corresponding A values
            int tempA = A[i];
            A[i] = A[minIndex];
            A[minIndex] = tempA;

            // Swap corresponding B values
            int tempB = B[i];
            B[i] = B[minIndex];
            B[minIndex] = tempB;

            // Swap corresponding line values
            String tempL = L[i];
            L[i] = L[minIndex];
            L[minIndex] = tempL;
        }

        System.out.println("✓ Routes sorted successfully!\n");
    }

    // Display sorted routes with line information
    public static void displaySortedRoutes(int[] A, int[] B, int[] W, String[] L, String[] haltes) {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║      SORTED ROUTES (by distance)                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");

        for (int i = 0; i < W.length; i++) {
            String lineSymbol = L[i].equals("RED") ? "[🔴]" : "[🔵]";
            System.out.printf("%2d. %-20s <-> %-20s : %2d km %s\n",
                    (i + 1), haltes[A[i]], haltes[B[i]], W[i], lineSymbol);
        }
        System.out.println("\n═════════════════════════════════════════════════════\n");
    }
}