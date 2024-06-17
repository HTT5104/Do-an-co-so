public class Main {
    public static void main(String[] args) {
        String filePath = "MC_(vs_WH).csv"; // Update the path as necessary
        Player Player = new Player(filePath, filePath, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Player> players = readPlayersFromCSV(filePath);
        // Sort players based on quality in descending order
        // players.sort(Comparator.comparingDouble(player -> -player.quality));

        // Print sorted players
        System.out.println("Sorted Players by Quality:");
        for (Player player : players) {
            System.out.println(player);
        }

        // Select players for each position
        List<Player> selectedTeam = selectTeam(players);

        // Print the selected team
        System.out.println("\nSelected Team:");
        for (Player player : selectedTeam) {
            System.out.println(player);
        }
    }
}
