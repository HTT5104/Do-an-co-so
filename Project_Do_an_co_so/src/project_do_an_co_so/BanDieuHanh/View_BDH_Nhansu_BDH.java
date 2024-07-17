package project_do_an_co_so;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.text.JTextComponent;
import project_do_an_co_so.BanDieuHanh.Controller_BDH_Nhansu_BDH;
import project_do_an_co_so.BanDieuHanh.View_Chon_Loc_1Cauthu;
import project_do_an_co_so.Controller_Nhansu_1DoiTuong;

public class View_BDH_Nhansu_BDH {

    private static JFrame frame;
    private static DefaultTableModel tableModel;
    private static final ArrayList<Player> playerList = new ArrayList<>();
    private static final ArrayList<Player> displayedPlayers = new ArrayList<>();
    private static String url;
    private static final Ngoaile x = new Ngoaile();
    private static JTextField searchField;

    public static void chon(JTable table, ArrayList<Player> playerList) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Player selectedPlayer = displayedPlayers.get(selectedRow);
            View_Nhansu_1DoiTuong view = new View_Nhansu_1DoiTuong();
            view.set(playerList.indexOf(selectedPlayer), selectedPlayer, table, tableModel, playerList);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Choose a player");
        }
    }

    public static void chon2(JTable table, ArrayList<Player> playerList) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Player selectedPlayer = displayedPlayers.get(selectedRow); // Sử dụng displayedPlayers để lấy cầu thủ được
            // chọn
            View_Chon_Loc_1Cauthu view = new View_Chon_Loc_1Cauthu();
            view.set(playerList.indexOf(selectedPlayer), selectedPlayer, table, tableModel, playerList);
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
        addPlayerDialog.add(new JLabel("Nation:"), gbc);
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
        styleButton(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                String name = x.normalizeName(nameField.getText());
                String hometown = hometownComboBox.getSelectedItem() != null
                        ? hometownComboBox.getSelectedItem().toString()
                        : "";
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
                if (!Controller_BDH_Nhansu_BDH.isNaturalNumber(numberShirt)
                        || !Controller_BDH_Nhansu_BDH.isNaturalNumber(weight)
                        || !Controller_BDH_Nhansu_BDH.isNaturalNumber(height)) {
                    JOptionPane.showMessageDialog(frame, "Number, weight and height must be natural numbers");
                    return;
                }

                // Thêm dữ liệu vào bảng
                Player newPlayer = new Player(name, hometown, birthDate, numberShirt, position, weight, height,
                        bodyMass, password);
                playerList.add(newPlayer);
                displayedPlayers.add(newPlayer);
                tableModel.addRow(new Object[]{name});

                // Lưu dữ liệu vào file CSV
                savePlayerToCSV(name, hometown, birthDate, numberShirt, position, weight, height, bodyMass, password);

                // Đóng dialog
                addPlayerDialog.dispose();
                frame.dispose();
                View_BDH_Nhansu_BDH.hien();
                Controller_BDH_Nhansu_BDH.load2("src/project_do_an_co_so/CSV/Data.csv", playerList, displayedPlayers,
                        tableModel);
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
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/project_do_an_co_so/Image/BG1.png");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        // Create table
        String[] columnNames = {"Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 100, 600, 200);
        panel.add(scrollPane);

        // Add search field
        searchField = new JTextField(20);
        searchField.setBounds(150, 60, 200, 30);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });
        panel.add(searchField);

        // Add "Thêm" button
        JButton addButton = new JButton("Add");
        Controller_Nhansu_1DoiTuong.styleButton(addButton);
        styleButton(addButton);
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
        styleButton(deleteButton);
        deleteButton.setBounds(200, 350, 120, 50);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Do you want to delete this player?", "Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Player selectedPlayer = displayedPlayers.get(selectedRow);
                        playerList.remove(selectedPlayer);
                        displayedPlayers.remove(selectedPlayer);
                        tableModel.removeRow(selectedRow);

                        // Gọi hàm save2 để lưu danh sách cầu thủ sau khi xóa
                        Controller_Nhansu_1DoiTuong.clearCSVFile("src/project_do_an_co_so/CSV/Data.csv");
                        Controller_BDH_Nhansu_BDH.save("src/project_do_an_co_so/CSV/Data.csv", playerList);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Choose a player to delete");
                }
            }
        });
        panel.add(deleteButton);

        // Add "Chọn" button
        JButton selectButton = new JButton("Choose");
        
        styleButton(selectButton);
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
        styleButton(backButton);
        backButton.setBounds(650, 450, 120, 50);
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
        styleButton(loadCsvButton);
        loadCsvButton.setBounds(650, 350, 120, 50);
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up();
            }
        });
        panel.add(loadCsvButton);

        // Add "Filter" button
        JButton filterButton = new JButton("Filter");
        styleButton(filterButton);
        filterButton.setBounds(500, 350, 120, 50);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller_BDH_Nhansu_BDH.openFilterDialog(1, x, frame);
            }
        });
        panel.add(filterButton);

        return panel;
    }

    public static void up() {
        Controller_BDH_Nhansu_BDH.load2("src/project_do_an_co_so/CSV/Data.csv", playerList, displayedPlayers,
                tableModel);
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

    public static void backk() {
        filterPlayers(a, b, c, d, k, g);
    }

    public static void filterPlayers(String minWeight, String maxWeight, String minHeight, String maxHeight,
            String position, String hometown) {
        a = minWeight;
        b = maxWeight;
        c = minHeight;
        d = maxHeight;
        k = position;
        g = hometown;

        displayedPlayers.clear(); // Clear the displayedPlayers list

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

            if (!position.isEmpty()) {
                String[] selectedPositions = position.split(",\\s*");
                boolean positionMatches = false;
                for (String pos : selectedPositions) {
                    if (player.getPosition().contains(pos)) {
                        positionMatches = true;
                        break;
                    }
                }
                if (!positionMatches) {
                    matches = false;
                }
            }

            if (!hometown.isEmpty() && !player.getHometown().equalsIgnoreCase(hometown)) {
                matches = false;
            }

            if (matches) {
                displayedPlayers.add(player); // Add to displayedPlayers if all conditions are met
            }
        }

        Controller_BDH_Nhansu_BDH.showFilteredPlayers(displayedPlayers, playerList, displayedPlayers, tableModel); // Display the filtered players
    }

    public static void filterTable() {
        String query = searchField.getText().toLowerCase();
        displayedPlayers.clear();
        tableModel.setRowCount(0);

        for (Player player : playerList) {
            if (player.getName().toLowerCase().contains(query)) {
                displayedPlayers.add(player);
                tableModel.addRow(new Object[]{player.getName()});
            }
        }
    }

    public static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size to 18
        button.setPreferredSize(new Dimension(200, 60)); // Increase button size
        //button.setBackground(new Color(34, 139, 34)); // Forest green background color
        button.setBackground(new Color(255, 0, 0));
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false); // Remove focus paint
        //button.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2)); // Add white border
        //button.setOpaque(true);
    }

}
