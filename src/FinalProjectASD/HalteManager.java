package FinalProjectASD;

public class HalteManager {
    private String[] haltes;
    private String[] busLines; // RED or BLUE line

    public HalteManager() {
        // EXPANDED DATASET: 83 Stops based on real Suroboyo Bus / Trans Semanggi zones
        // Mapped to "RED" (North-South Axis) and "BLUE" (East-West/MERR Axis) for algorithm compatibility

        haltes = new String[] {
                // --- ZONE 1: SOUTH (PURABAYA - WONOKROMO) [Indices 0-13] ---
                "Terminal Purabaya",    // 0
                "Dukuh Menanggal",      // 1
                "Siwalankerto",         // 2
                "Taman Pelangi",        // 3
                "RS Bhayangkara",       // 4
                "UBHARA",               // 5
                "PUSVETMA",             // 6
                "Ketintang",            // 7
                "Royal Plaza",          // 8
                "Joyoboyo (TIJ)",       // 9  [HUB - Transfer Point]
                "Wonokromo",            // 10
                "Jagir",                // 11
                "Ngagel",               // 12
                "Marvel City",          // 13

                // --- ZONE 2: CENTRAL (DARMO - TUNJUNGAN) [Indices 14-28] ---
                "Museum BI",            // 14
                "RS Darmo",             // 15 [Transfer Point]
                "Pandegiling",          // 16
                "Basra",                // 17
                "Kaliasin",             // 18
                "Embong Malang",        // 19
                "Blauran",              // 20
                "Pirngadi",             // 21
                "Siola",                // 22
                "Tunjungan Plaza",      // 23
                "Gubernur Suryo",       // 24
                "Panglima Sudirman",    // 25 [Transfer Point]
                "Sono Kembang",         // 26
                "Urip Sumoharjo",       // 27
                "Santa Maria",          // 28

                // --- ZONE 3: NORTH (PASAR TURI - PERAK) [Indices 29-39] ---
                "Pasar Turi",           // 29
                "Masjid Kemayoran",     // 30
                "Indrapura",            // 31
                "Rajawali",             // 32
                "Jembatan Merah",       // 33
                "Veteran",              // 34
                "Tugu Pahlawan",        // 35
                "Alun-Alun Contong",    // 36
                "Perak Timur",          // 37
                "Perak Barat",          // 38
                "Barunawati",           // 39

                // --- ZONE 4: WEST (UNESA - MAYJEN SUNGKONO) [Indices 40-51] ---
                "UNESA Lidah Wetan",    // 40
                "Graha Family",         // 41
                "Yono Suwoyo",          // 42
                "PTC",                  // 43
                "Dukuh Pakis",          // 44
                "HR Muhammad",          // 45
                "Mayjen Sungkono",      // 46
                "Adityawarman",         // 47
                "Kutai",                // 48
                "Bengawan",             // 49
                "KPU Indragiri",        // 50
                "Gelora Pancasila",     // 51

                // --- ZONE 5: EAST (GUBENG - ITS) [Indices 52-65] ---
                "Gubeng Airlangga",     // 52
                "UNAIR Kampus B",       // 53
                "Karang Menjangan",     // 54
                "Samsat Manyar",        // 55
                "Kertajaya",            // 56
                "KONI",                 // 57
                "Galaxy Mall",          // 58
                "MERR ITS",             // 59 [Transfer Point]
                "ITS Kampus",           // 60
                "PENS",                 // 61
                "Keputih",              // 62
                "Arief Rahman Hakim",   // 63
                "Klampis",              // 64
                "Asrama Haji",          // 65

                // --- ZONE 6: MERR EXTENSION (SOUTH & NORTH) [Indices 66-82] ---
                "Semolowaru",           // 66
                "Nginden",              // 67
                "Prapen",               // 68
                "Panjang Jiwo",         // 69
                "Rungkut Madya",        // 70
                "Gunung Anyar",         // 71
                "UPN Veteran",          // 72
                "Pandugo",              // 73
                "Penjaringan Sari",     // 74
                "Kedung Baruk",         // 75
                "Kalijudan",            // 76
                "Mulyorejo",            // 77
                "Kenjeran Park",        // 78
                "Bulak",                // 79
                "Kedung Cowek",         // 80
                "Suramadu",             // 81
                "Sidotopo"              // 82
        };

        // Assign Lines (Red=NorthSouth, Blue=EastWest/MERR)
        busLines = new String[83];
        for(int i=0; i<83; i++) {
            if (i <= 39) busLines[i] = "RED";       // Zones 1, 2, 3 are RED LINE
            else busLines[i] = "BLUE";              // Zones 4, 5, 6 are BLUE LINE
        }

        // Mark specific Transfer Points (Nodes that connect the two main lines)
        busLines[9]  = "RED+BLUE"; // Joyoboyo
        busLines[15] = "RED+BLUE"; // RS Darmo
        busLines[25] = "RED+BLUE"; // Panglima Sudirman
        busLines[59] = "RED+BLUE"; // MERR ITS
    }

    public String[] getHaltes() {
        return haltes;
    }

    public String[] getBusLines() {
        return busLines;
    }

    public void printAll() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║        SUROBOYO BUS STOPS - FULL NETWORK          ║");
        System.out.println("╠═══════════════════════════════════════════════════╣");
        System.out.println("║  RED LINE: North-South (Purabaya - Perak)         ║");
        System.out.println("║  BLUE LINE: East-West (Unesa - Kenjeran/MERR)     ║");
        System.out.println("║  RED+BLUE: Transfer Points                        ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        for (int i = 0; i < haltes.length; i++) {
            String lineColor = getLineColorSymbol(busLines[i]);
            System.out.printf("%2d. %-25s %s\n", i, haltes[i], lineColor);
        }
        System.out.println("\n════════════════════════════════════════════════════\n");
    }

    private String getLineColorSymbol(String line) {
        if (line.equals("RED")) {
            return "[🔴 RED LINE]";
        } else if (line.equals("BLUE")) {
            return "[🔵 BLUE LINE]";
        } else {
            return "[🔴🔵 TRANSFER POINT]";
        }
    }

    // Linear search to find halte index by name
    public int searchByName(String name) {
        System.out.println("\n[Linear Search] Searching for stop: " + name);
        String n = name.toLowerCase().trim();

        for (int i = 0; i < haltes.length; i++) {
            if (haltes[i].toLowerCase().equals(n)) {
                System.out.println("✓ Stop '" + haltes[i] + "' found at index " + i);
                System.out.println("  Line: " + busLines[i]);
                return i;
            }
        }

        System.out.println("✗ Stop '" + name + "' not found.");
        return -1;
    }

    // Search by index (validation)
    public boolean isValidIndex(int index) {
        return index >= 0 && index < haltes.length;
    }

    public String getHalteName(int index) {
        if (isValidIndex(index)) {
            return haltes[index];
        }
        return "Invalid Index";
    }

    public String getBusLine(int index) {
        if (isValidIndex(index)) {
            return busLines[index];
        }
        return "UNKNOWN";
    }
}