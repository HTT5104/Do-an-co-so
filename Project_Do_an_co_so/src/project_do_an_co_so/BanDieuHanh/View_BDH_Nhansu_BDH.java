package project_do_an_co_so;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
import javax.swing.text.JTextComponent;
import project_do_an_co_so.BanDieuHanh.View_Chon_Loc_1Cauthu;
import project_do_an_co_so.Controller_Nhansu_1DoiTuong;

public class View_BDH_Nhansu_BDH {

    private static JFrame frame;
    private static DefaultTableModel tableModel;
    private static final ArrayList<Player> playerList = new ArrayList<>();
    private static String url;
    private static final Ngoaile x = new Ngoaile();

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

    public static void load2(String x) {
        File file = new File("src/project_do_an_co_so/CSV/Data.csv");
        if (file.exists() && !file.isDirectory()) {
            // Nếu đường dẫn x là hợp lệ và không phải là một thư mục
            loadCSV(file);
        } else {
            System.out.println("Error direction: " + x);
        }
    }

    public static void chon(JTable table, ArrayList<Player> playerList) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Player selectedPlayer = playerList.get(selectedRow);
            View_Nhansu_1DoiTuong view = new View_Nhansu_1DoiTuong();
            view.set(selectedRow, selectedPlayer, table, tableModel, playerList);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Choose a player");
        }
    }
    
    public static void chon2(JTable table, ArrayList<Player> playerList) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Player selectedPlayer = playerList.get(selectedRow);
            View_Chon_Loc_1Cauthu view = new View_Chon_Loc_1Cauthu();
            view.set(selectedRow, selectedPlayer, table, tableModel, playerList);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Choose a player");
        }
    }

    public static void openAddPlayerForm() {
        // Tạo một form mới để nhập thuộc tính cầu thủ
        JDialog addPlayerDialog = new JDialog(frame, "Add a new player", true);
        addPlayerDialog.setSize(400, 600);
        addPlayerDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tạo các trường nhập liệu cho thuộc tính cầu thủ
        JTextField nameField = new JTextField(20);
        JComboBox<String> hometownComboBox = new JComboBox<>(x.getCountries());
        hometownComboBox.setEditable(true);
        JTextComponent editor = (JTextComponent) hometownComboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> x.autoComplete(hometownComboBox, editor.getText()));
            }
        });

        JPanel dobPanel = x.createDobPanel(); // Tạo panel chứa 3 JComboBox cho ngày tháng năm

        JTextField numberShirtField = new JTextField(20);
        JButton positionButton = new JButton("Select Positions"); // Nút mở dialog chọn vị trí
        JTextField weightField = new JTextField(20);
        JTextField heightField = new JTextField(20);

        // Tạo JComboBox cho phần nhập chân thuận
        JComboBox<String> dominantFootComboBox = new JComboBox<>(new String[]{"Left", "Right", "Both"});

        // Thêm các trường nhập liệu vào dialog
        gbc.gridx = 0;
        gbc.gridy = 0;
        addPlayerDialog.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addPlayerDialog.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addPlayerDialog.add(new JLabel("Hometown:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        addPlayerDialog.add(hometownComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addPlayerDialog.add(new JLabel("DoB:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        addPlayerDialog.add(dobPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addPlayerDialog.add(new JLabel("Number:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        addPlayerDialog.add(numberShirtField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        addPlayerDialog.add(new JLabel("Positions:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        addPlayerDialog.add(positionButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        addPlayerDialog.add(new JLabel("Weight:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        addPlayerDialog.add(weightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        addPlayerDialog.add(new JLabel("Height:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        addPlayerDialog.add(heightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        addPlayerDialog.add(new JLabel("Dominant foot:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        addPlayerDialog.add(dominantFootComboBox, gbc);

        // Action listener cho nút positionButton để mở dialog chọn vị trí
        positionButton.addActionListener(e -> {
            String selectedPositions = x.openPositionSelectionDialog(positionButton, addPlayerDialog);
            if (selectedPositions != null && !selectedPositions.isEmpty()) {
                positionButton.setText(selectedPositions); // Cập nhật tên nút
            }
        });

        // Thêm nút lưu cầu thủ
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                String name = x.normalizeName(nameField.getText());
                String hometown = hometownComboBox.getSelectedItem() != null ? hometownComboBox.getSelectedItem().toString() : "";
                String birthDate = x.getDobFromComboBoxes(dobPanel);
                String numberShirt = numberShirtField.getText();
                String position = positionButton.getText();
                String weight = weightField.getText();
                String height = heightField.getText();
                String bodyMass = dominantFootComboBox.getSelectedItem().toString();
                String password = "dd4b21e9ef71e1291183a46b913ae6f2";

                // Kiểm tra xem tên có được nhập hay không
                if (name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Write the player's name");
                    return;
                }

                // Kiểm tra xem vị trí có được nhập hay không
                if (position.equals("Select Positions")) {
                    JOptionPane.showMessageDialog(frame, "Select at least one position");
                    return;
                }

                // Kiểm tra xem số áo, chiều cao và cân nặng có phải là số tự nhiên hay không
                if (!isNaturalNumber(numberShirt) || !isNaturalNumber(weight) || !isNaturalNumber(height)) {
                    JOptionPane.showMessageDialog(frame, "Number, weight and height must be natural numbers");
                    return;
                }

                // Thêm dữ liệu vào bảng
                tableModel.addRow(new Object[]{name});

                // Lưu dữ liệu vào file CSV
                savePlayerToCSV(name, hometown, birthDate, numberShirt, position, weight, height, bodyMass, password);

                // Đóng dialog
                addPlayerDialog.dispose();
                frame.dispose();
                View_BDH_Nhansu_BDH.hien();
                load2("src/project_do_an_co_so/CSV/Data.csv");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        addPlayerDialog.add(saveButton, gbc);

        // Hiển thị dialog
        addPlayerDialog.setLocationRelativeTo(null);
        addPlayerDialog.setVisible(true);
    }

    private static boolean isNaturalNumber(String str) {
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Hàm lưu dữ liệu vào file CSV, được khai báo bên ngoài phương thức createPanel
    public static void savePlayerToCSV(String name, String hometown, String birthDate, String numberShirt,
            String position, String weight, String height, String bodyMass, String password) {
        File fileToSave = new File("src/project_do_an_co_so/CSV/Data.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave, true))) {
            writer.newLine();
            writer.write(name + "," + hometown + "," + birthDate + "," + numberShirt + "," + position + "," + weight
                    + "," + height + "," + bodyMass + "," + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hien() {
        frame = new JFrame("Player");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = createPanel();
        frame.add(panel);
        frame.setVisible(true);
    }

    public static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Player list");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(250, 20, 400, 30);
        panel.add(titleLabel);

        // Create table
        String[] columnNames = {"Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 100, 600, 200);
        panel.add(scrollPane);

        // Add "Thêm" button
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBackground(new Color(255, 182, 193));
        addButton.setBounds(50, 350, 120, 50);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddPlayerForm();
            }
        });
        panel.add(addButton);

        // Add "Xóa" button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteButton.setBackground(new Color(255, 182, 193));
        deleteButton.setBounds(200, 350, 120, 50);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Do you want to delete this player?", "Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        playerList.remove(selectedRow);
                        tableModel.removeRow(selectedRow);

                        // Gọi hàm save2 để lưu danh sách cầu thủ sau khi xóa
                        Controller_Nhansu_1DoiTuong.clearCSVFile("src/project_do_an_co_so/CSV/Data.csv");
                        save("src/project_do_an_co_so/CSV/Data.csv", playerList);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Choose a player to delete");
                }
            }
        });

        panel.add(deleteButton);

        // Add "Chọn" button
        JButton selectButton = new JButton("Choose");
        selectButton.setFont(new Font("Arial", Font.BOLD, 18));
        selectButton.setBackground(new Color(255, 182, 193));
        selectButton.setBounds(350, 350, 120, 50);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chon(table, playerList);
            }
        });
        panel.add(selectButton);

        // Add "Back" button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 182, 193));
        backButton.setBounds(500, 350, 120, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View_BanDieuHanh.hien();
                frame.dispose();
            }
        });
        panel.add(backButton);

        // Nút up csv
        JButton loadCsvButton = new JButton("Update");
        loadCsvButton.setFont(new Font("Arial", Font.BOLD, 18));
        loadCsvButton.setBackground(new Color(255, 182, 193));
        loadCsvButton.setBounds(650, 350, 150, 50);
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load2("src/project_do_an_co_so/CSV");
            }
        });
        panel.add(loadCsvButton);

        // Add "Filter" button
        JButton filterButton = new JButton("Filter");
        filterButton.setFont(new Font("Arial", Font.BOLD, 18));
        filterButton.setBackground(new Color(255, 182, 193));
        filterButton.setBounds(650, 450, 150, 50);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFilterDialog();
            }
        });
        panel.add(filterButton);

        return panel;
    }

    private static void loadCSV(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Clear the previous list
            playerList.clear();
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
                tableModel.addRow(new Object[]{name});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void set() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hien();
            }
        });
    }
    
    private static String a, b, c, d, k, g;
    
    public static void openFilterDialog() {
        JDialog filterDialog = new JDialog(frame, "Filter Players", true);
        filterDialog.setSize(500, 600);  // Tăng kích thước hộp thoại
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
        String[] positions = {"GK", "LB", "CB", "RB", "LWB", "RWB", "CDM", "CM", "CAM", "LM", "RM", "LW", "RW", "CF", "ST"};
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
        hometownComboBox.setSelectedIndex(0);  // Đặt giá trị trống làm mặc định
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
        filterDialog.add(new JLabel("Hometown:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        filterDialog.add(hometownComboBox, gbc);

        // Thêm nút lọc cầu thủ
        JButton filterButton = new JButton("Filter");
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

                String hometown = hometownComboBox.getSelectedItem() != null ? hometownComboBox.getSelectedItem().toString() : "";

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
    
    public static void backk(){
        filterPlayers(a, b, c, d, k, g);
    }
    
    public static void filterPlayers(String minWeight, String maxWeight, String minHeight, String maxHeight, String position, String hometown) {
        
        a = minWeight;
        b = maxWeight;
        c = minHeight;
        d = maxHeight;
        k = position;
        g = hometown;
        
        ArrayList<Player> filteredList = new ArrayList<>();

        for (Player player : playerList) {
            boolean matches = true;

            if (!minWeight.isEmpty() && !maxWeight.isEmpty()) {
                int weight = Integer.parseInt(player.getWeight());
                int minW = Integer.parseInt(minWeight);
                int maxW = Integer.parseInt(maxWeight);
                if (weight < minW || weight > maxW) {
                    matches = false;
                }
            }

            if (!minHeight.isEmpty() && !maxHeight.isEmpty()) {
                int height = Integer.parseInt(player.getHeight());
                int minH = Integer.parseInt(minHeight);
                int maxH = Integer.parseInt(maxHeight);
                if (height < minH || height > maxH) {
                    matches = false;
                }
            }

            if (!position.isEmpty() && !player.getPosition().contains(position)) {
                matches = false;
            }

            if (!hometown.isEmpty() && !player.getHometown().equalsIgnoreCase(hometown)) {
                matches = false;
            }

            if (matches) {
                filteredList.add(player);
            }
        }

        showFilteredPlayers(filteredList);
    }

    public static void showFilteredPlayers(ArrayList<Player> filteredList) {
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
        
        
        filterFrame.setVisible(true);

        String[] columnNames = {"Name"};
        DefaultTableModel filteredTableModel = new DefaultTableModel(columnNames, 0);
        JTable filteredTable = new JTable(filteredTableModel);
        JScrollPane scrollPane = new JScrollPane(filteredTable);
        scrollPane.setBounds(100, 100, 600, 200);
        panel.add(scrollPane);

        for (Player player : filteredList) {
            filteredTableModel.addRow(new Object[]{player.getName()});
        }

        // Add "Chọn" button
        JButton selectButton = new JButton("Choose");
        selectButton.setFont(new Font("Arial", Font.BOLD, 18));
        selectButton.setBackground(new Color(255, 182, 193));
        selectButton.setBounds(350, 350, 120, 50);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFrame.dispose(); 
                chon2(filteredTable, filteredList);
            }
        });
        panel.add(selectButton);

        // Add "Back" button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 182, 193));
        backButton.setBounds(200, 350, 120, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFrame.dispose();   
                View_BDH_Nhansu_BDH.hien();
                View_BDH_Nhansu_BDH.load2("src/project_do_an_co_so/CSV/Data.csv");                
            }
        });
        panel.add(backButton);
        filterFrame.add(panel);
    }
}
