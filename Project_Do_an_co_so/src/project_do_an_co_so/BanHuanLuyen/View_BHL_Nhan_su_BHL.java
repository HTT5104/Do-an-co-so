package project_do_an_co_so;

//import project_do_an_co_so.BanDieuHanh.View_Nhansu_1DoiTuong;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import project_do_an_co_so.BanHuanLuyen.BHL_Chonloc;
import project_do_an_co_so.BanHuanLuyen.Controller_BHL_Nhansu_BHL;

public class View_BHL_Nhan_su_BHL {

    private static JFrame frame;
    private static DefaultTableModel tableModel;
    private static final ArrayList<Player> playerList = new ArrayList<>();
    private static final ArrayList<Player> displayedPlayers = new ArrayList<>();
    private static final Ngoaile x = new Ngoaile();
    private static JTextField searchField;
    
    public static void up() {
        Controller_BHL_Nhansu_BHL.load2("src/project_do_an_co_so/CSV/Data.csv", playerList, displayedPlayers,
                tableModel);
    }

    public static void chon(JTable table, ArrayList<Player> playerList) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Player selectedPlayer = displayedPlayers.get(selectedRow);
            View_BHL_Nhansu_1nguoi view = new View_BHL_Nhansu_1nguoi();
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
            BHL_Chonloc view = new BHL_Chonloc();
            view.set(playerList.indexOf(selectedPlayer), selectedPlayer, table, tableModel, playerList);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Choose a player");
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
        panel.setBackground(Color.WHITE);

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

        // Add "Chọn" button
        JButton selectButton = new JButton("Choose");
        selectButton.setFont(new Font("Arial", Font.BOLD, 18));
        selectButton.setBackground(new Color(255, 182, 193));
        selectButton.setBounds(300, 350, 120, 50);
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
        backButton.setBounds(150, 350, 120, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View_BanHuanLuyen.hien();
                frame.dispose();
            }
        });
        panel.add(backButton);

        // Nút up csv
        JButton loadCsvButton = new JButton("Update");
        loadCsvButton.setFont(new Font("Arial", Font.BOLD, 18));
        loadCsvButton.setBackground(new Color(255, 182, 193));
        loadCsvButton.setBounds(450, 350, 120, 50);
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up();
            }
        });
        panel.add(loadCsvButton);

        // Add "Filter" button
        JButton filterButton = new JButton("Filter");
        filterButton.setFont(new Font("Arial", Font.BOLD, 18));
        filterButton.setBackground(new Color(255, 182, 193));
        filterButton.setBounds(600, 350, 120, 50);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller_BHL_Nhansu_BHL.openFilterDialog(x, frame);
            }
        });
        panel.add(filterButton);

        return panel;
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

        Controller_BHL_Nhansu_BHL.showFilteredPlayers(displayedPlayers, playerList, displayedPlayers, tableModel); // Display the filtered players
    }

    public static void loadCSV(File file) {
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

                Player player = new Player(name, hometown, birthDate, numberShirt, position, weight, height, bodyMass);
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
