package project_do_an_co_so.BanDieuHanh;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import project_do_an_co_so.Ngoaile;
import project_do_an_co_so.Player;
import project_do_an_co_so.View_BDH_Nhansu_BDH;
import static project_do_an_co_so.View_BDH_Nhansu_BDH.chon2;
import static project_do_an_co_so.View_BDH_Nhansu_BDH.filterPlayers;
import static project_do_an_co_so.View_BDH_Nhansu_BDH.styleButton;
import static project_do_an_co_so.View_BDH_Nhansu_BDH.up;
import project_do_an_co_so.View_BHL_Nhan_su_BHL;

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
     public static void openFilterDialog(int y, Ngoaile x, JFrame frame) {
        JDialog filterDialog = new JDialog(frame, "Filter Players", true);
        filterDialog.setSize(500, 600); // Tăng kích thước hộp thoại
        filterDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tạo các trường nhập liệu cho khoảng giá trị cân nặng và chiều cao
        JTextField minWeightField = new JTextField(10);
        JTextField maxWeightField = new JTextField(10);
        JTextField minHeightField = new JTextField(10);
        JTextField maxHeightField = new JTextField(10);

        // Tạo các hộp chọn cho vị trí và quốc tịch
        // Thay JComboBox bằng JCheckBox
        JPanel positionPanel = new JPanel();
        positionPanel.setLayout(new BoxLayout(positionPanel, BoxLayout.Y_AXIS));
        String[] positions = { "GK", "CB", "LB", "RB", "LWB", "RWB", "CDM", "CM", "LM", "RM", "CAM", "CF", "LW", "RW", "ST" };
        for (String position : positions) {
            JCheckBox checkBox = new JCheckBox(position);
            positionPanel.add(checkBox);
        }

        // Thêm giá trị trống lên đầu và làm mặc định
        JComboBox<String> hometownComboBox = new JComboBox<>();
        hometownComboBox.addItem(""); // Thêm giá trị trống lên đầu
        for (String country : x.getCountries()) {
            hometownComboBox.addItem(country);
        }
        hometownComboBox.setEditable(true);
        hometownComboBox.setSelectedIndex(0); // Đặt giá trị trống làm mặc định
        JTextComponent editor = (JTextComponent) hometownComboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> x.autoComplete(hometownComboBox, editor.getText()));
            }
        });

        // Thêm các trường nhập liệu vào dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        filterDialog.add(new JLabel("Weight:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        filterDialog.add(minWeightField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        filterDialog.add(new JLabel("to"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        filterDialog.add(maxWeightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        filterDialog.add(new JLabel("Height:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        filterDialog.add(minHeightField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        filterDialog.add(new JLabel("to"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        filterDialog.add(maxHeightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        filterDialog.add(new JLabel("Positions:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        filterDialog.add(new JScrollPane(positionPanel), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        filterDialog.add(new JLabel("Nation:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        filterDialog.add(hometownComboBox, gbc);

        // Thêm nút lọc cầu thủ
        JButton filterButton = new JButton("Filter");
        View_BDH_Nhansu_BDH.styleButton(filterButton);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String minWeight = minWeightField.getText();
                String maxWeight = maxWeightField.getText();
                String minHeight = minHeightField.getText();
                String maxHeight = maxHeightField.getText();

                // Lấy các vị trí đã chọn từ JCheckBox
                StringBuilder selectedPositions = new StringBuilder();
                for (Component comp : positionPanel.getComponents()) {
                    if (comp instanceof JCheckBox && ((JCheckBox) comp).isSelected()) {
                        if (selectedPositions.length() > 0) {
                            selectedPositions.append(", ");
                        }
                        selectedPositions.append(((JCheckBox) comp).getText());
                    }
                }

                String hometown = hometownComboBox.getSelectedItem() != null
                        ? hometownComboBox.getSelectedItem().toString()
                        : "";

                // Đóng frame hiện tại
                frame.dispose();

                // Hiển thị frame mới với danh sách cầu thủ đã lọc
                filterPlayers(minWeight, maxWeight, minHeight, maxHeight, selectedPositions.toString(), hometown);

                filterDialog.dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        filterDialog.add(filterButton, gbc);

        // Hiển thị dialog
        filterDialog.setLocationRelativeTo(null);
        filterDialog.setVisible(true);
    }
     
     public static void showFilteredPlayers(ArrayList<Player> filteredList, ArrayList<Player> playerList, ArrayList<Player> displayedPlayers, DefaultTableModel tableModel) {
        JFrame filterFrame = new JFrame("Filtered Players");
        filterFrame.setSize(900, 600);
        filterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        filterFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Filtered Player List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(250, 20, 400, 30);
        panel.add(titleLabel);

        String[] columnNames = { "Name" };
        DefaultTableModel filteredTableModel = new DefaultTableModel(columnNames, 0);
        JTable filteredTable = new JTable(filteredTableModel);
        JScrollPane scrollPane = new JScrollPane(filteredTable);
        scrollPane.setBounds(100, 100, 600, 200);
        panel.add(scrollPane);

        for (Player player : filteredList) {
            filteredTableModel.addRow(new Object[] { player.getName() });
        }

        // Add "Chọn" button
        JButton selectButton = new JButton("Choose");
        styleButton(selectButton);
        selectButton.setBounds(350, 350, 120, 50);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFrame.dispose();
                chon2(filteredTable, filteredList); // Sử dụng filteredList để chọn cầu thủ
            }
        });
        panel.add(selectButton);

        // Add "Back" button
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.setBounds(200, 350, 120, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFrame.dispose();
                View_BDH_Nhansu_BDH.hien();
                up();
            }
        });
        panel.add(backButton);
        filterFrame.add(panel);

        filterFrame.setVisible(true);
    }
}
