import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerSelection {
    public static void main(String[] args) {
        String filePath = "/mnt/data/MC_(vs_WH).csv"; // Update the path as necessary
        List<Player> players = readPlayersFromCSV(filePath);

        // Sort players based on quality in descending order
        players.sort(Comparator.comparingDouble(player -> -player.quality));

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

    private static List<Player> readPlayersFromCSV(String filePath) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                String position = values[1];
                double MP = Double.parseDouble(values[2]);
                double G = Double.parseDouble(values[3]);
                double SOnT = Double.parseDouble(values[4]);
                double SOffT = Double.parseDouble(values[5]);
                double BS = Double.parseDouble(values[6]);
                double OG = Double.parseDouble(values[7]);
                double A = Double.parseDouble(values[8]);
                double P = Double.parseDouble(values[9]);
                double C = Double.parseDouble(values[10]);
                double Tk = Double.parseDouble(values[11]);
                double INT = Double.parseDouble(values[12]);
                double FW = Double.parseDouble(values[13]);
                double FC = Double.parseDouble(values[14]);
                double O = Double.parseDouble(values[15]);
                double YC = Double.parseDouble(values[16]);
                double RC = Double.parseDouble(values[17]);
                double GC = Double.parseDouble(values[18]);
                double PW = Double.parseDouble(values[19]);
                double S = Double.parseDouble(values[20]);
                double PS = Double.parseDouble(values[21]);
                double train = Double.parseDouble(values[22]);

                players.add(new Player(name, position, MP, G, SOnT, SOffT, BS, OG, A, P, C, Tk, INT, FW, FC, O, YC, RC,

                        GC, PW, S, PS, train));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    private static List<Player> selectTeam(List<Player> players) {
        // Positions required in a team
        String[] positions = { "GK", "DEF", "MID", "FWD" };

        List<Player> team = new ArrayList<>();
        for (String position : positions) {
            for (Player player : players) {
                if (player.position.contains(position)) {
                    team.add(player);
                    break; // Select the first available player for this position
                }
            }
        }

        return team;
    }
}
