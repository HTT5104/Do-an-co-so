package project_do_an_co_so.BanDieuHanh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import project_do_an_co_so.Player;

public class Controller_BDH_Nhansu_BDH {

    public static boolean isNaturalNumber(String str) {
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void save(String filePath, ArrayList<Player> playerList1) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Ghi dữ liệu của từng cầu thủ vào file CSV
            writer.write("Name,Hometown,DoB,Number,Position,Weight,Height,Dominant foot,Pass");
            writer.newLine();
            for (Player player : playerList1) {
                System.out.println(player.toString());
                writer.newLine();
                writer.write(player.getName() + "," + player.getHometown() + "," + player.getBirthDate() + "," + player.getNumberShirt() + ","
                        + player.getPosition() + "," + player.getWeight() + "," + player.getHeight() + "," + player.getBodyMass() + "," + player.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void load2(String x, ArrayList<Player> playerList, ArrayList<Player> displayedPlayers, DefaultTableModel tableModel) {
        File file = new File("src/project_do_an_co_so/CSV/Data.csv");
        if (file.exists() && !file.isDirectory()) {
            // Nếu đường dẫn x là hợp lệ và không phải là một thư mục
            loadCSV(file, playerList, displayedPlayers, tableModel);
        } else {
            System.out.println("Error direction: " + x);
        }
    }
    
    public static void loadCSV(File file, ArrayList<Player> playerList, ArrayList<Player> displayedPlayers, DefaultTableModel tableModel) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Clear the previous list
            playerList.clear();
            displayedPlayers.clear();
            tableModel.setRowCount(0);

            // Read the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 5) {
                    continue; // Skip if there are not enough values
                }

                String name = values[0];
                String hometown = values[1];
                String birthDate = values[2];
                String numberShirt = values[3];
                String position = values[4];
                String weight = values[5];
                String height = values[6];
                String bodyMass = values[7];
                String password = values[8];

                Player player = new Player(name, hometown, birthDate, numberShirt, position, weight, height, bodyMass, password);
                playerList.add(player);
                displayedPlayers.add(player);
                tableModel.addRow(new Object[]{name});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}
