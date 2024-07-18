package project_do_an_co_so;

import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class View_Toi_Uu extends JFrame {

    private static final int BUTTON_SIZE = 65;
    private static final int FIELD_WIDTH = 1080;
    private static final int FIELD_HEIGHT = 800;
    private BufferedImage backgroundImage;
    private int selectedCount = 0; // Counter for selected buttons
    private ArrayList<String> selectedPositions = new ArrayList<>(); // List to keep track of selected positions
    public File selectedFile;
    private ArrayList<Player>[] positionArrays = new ArrayList[15];

    public View_Toi_Uu() {
        try {
            backgroundImage = ImageIO.read(new File("Image/football_filed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Football Field GUI");
        setSize(FIELD_WIDTH, FIELD_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Use a custom JPanel to draw the background image
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);

        // Add BackgroundPanel to JFrame
        add(backgroundPanel, BorderLayout.CENTER);

        addButtons(backgroundPanel);

        // Create a JPanel to contain the bottom bar, OK button, and Back button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(70, 130, 180)); // Set background color

        JButton okButton = new JButton("OK");
        customizeButton(okButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(null, "Please upload a CSV file first.");
                } else if (selectedCount == 10) {
                    showTopPlayersTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select exactly 10 positions.");
                }
            }
        });

        JButton backButton = new JButton("Back");
        customizeButton(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View_BanHuanLuyen.hien();
                Window window = SwingUtilities.getWindowAncestor(bottomPanel);
                if (window != null) {
                    window.dispose();
                }
            }
        });

        JButton uploadButton = new JButton("Upload data");
        customizeButton(uploadButton);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("T:\\"); // Open the T drive
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory() || f.getName().toLowerCase().endsWith(".csv");
                    }

                    @Override
                    public String getDescription() {
                        return "CSV Files (*.csv)";
                    }
                });

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    if (!selectedFile.getName().toLowerCase().endsWith(".csv")) {
                        JOptionPane.showMessageDialog(null, "Please select a CSV file!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            readCSV(selectedFile);
                            JOptionPane.showMessageDialog(null, "CSV file successfully selected!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error reading CSV file. Please check the file.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        bottomPanel.add(uploadButton);
        bottomPanel.add(okButton);
        bottomPanel.add(backButton);

        // Add bottomPanel to JFrame
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(0, 0, 0));
        button.setFocusPainted(false);
        button.setMargin(new Insets(10, 20, 10, 20));
    }

    private void readCSV(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Initialize position arrays
            for (int i = 0; i < positionArrays.length; i++) {
                positionArrays[i] = new ArrayList<>();
            }

            // Read the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming comma-separated values
                if (values.length < 39) { // Ensure there are enough columns
                    continue;
                }

                String playerName = values[0]; // Player name

                // Parse position values (15 binary columns)
                boolean[] positions = new boolean[15];
                for (int i = 0; i < 15; i++) {
                    try {
                        positions[i] = Double.parseDouble(values[i + 1].trim()) > 0;
                    } catch (NumberFormatException e) {
                        positions[i] = false; // Treat invalid data as false
                    }
                }

                // Parse evaluation metrics
                double[] metrics = new double[values.length - 16];
                for (int i = 0; i < metrics.length; i++) {
                    try {
                        metrics[i] = Double.parseDouble(values[i + 16].trim());
                    } catch (NumberFormatException e) {
                        metrics[i] = 0; // Treat invalid data as 0
                    }
                }

                // Calculate the quality score using the provided formula
                double qi = (0.05 * metrics[0] + 10 * metrics[1] + 4 * metrics[2] + 2 * metrics[3] + 2 * metrics[4]
                        - 5 * metrics[5] + 6 * metrics[6] + 0.2 * metrics[7] + 0.2 * metrics[8] + 2 * metrics[9]
                        + 2 * metrics[10] + metrics[11] - metrics[12] - metrics[13] - 2 * metrics[14]
                        - 5 * metrics[15] - metrics[16] + 4 * metrics[17] + 5 * metrics[18] + 5 * metrics[19]
                        + 1.5 * metrics[20]) * metrics[21] * metrics[22];

                // Add player to the position arrays
                for (int i = 0; i < 15; i++) {
                    if (positions[i]) {
                        Player player = new Player(playerName, qi * Double.parseDouble(values[i + 1].trim()));
                        positionArrays[i].add(player);
                    }
                }
            }

            // Sort each position array by quality score
            for (ArrayList<Player> positionArray : positionArrays) {
                positionArray.sort(Comparator.comparingDouble(Player::getQuality).reversed());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int indexOfPosition(String pos, String[] positions) {
        for (int i = 0; i < positions.length; i++) {
            if (positions[i].equals(pos)) {
                return i;
            }
        }
        return -1;
    }

    private void showTopPlayersTable() {
        HashSet<String> selectedPlayers = new HashSet<>();
        String[] columnNames = {"Position", "Player Name", "Quality"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Map each button text to a position array index
        String[] positions = {
                "GK", "CB", "LB", "RB", "LWB",
                "RWB", "CDM", "CM", "LM", "RM",
                "CAM", "CF", "LW", "RW", "ST"
        };

        selectedPositions.sort(Comparator.comparingInt(pos -> indexOfPosition(pos, positions)));

        // Get top player for each selected position
        for (String pos : selectedPositions) {
            int index = indexOfPosition(pos, positions);

            if (index != -1) {
                boolean playerFound = false;
                for (Player player : positionArrays[index]) {
                    if (!selectedPlayers.contains(player.getName())) {
                        if (player.getQuality() > 0) {
                            selectedPlayers.add(player.getName());
                            tableModel.addRow(new Object[]{pos, player.getName(), player.getQuality()});
                        } else {
                            tableModel.addRow(new Object[]{pos, "Null", "N/A"});
                        }
                        playerFound = true;
                        break;
                    }
                }
                if (!playerFound) {
                    tableModel.addRow(new Object[]{pos, "Null", "N/A"});
                }
            } else {
                tableModel.addRow(new Object[]{pos, "Null", "N/A"});
            }
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JDialog dialog = new JDialog(this, "Selected Top Players", true);
        dialog.add(scrollPane);
        dialog.setSize(600, 250);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public void addButtons(JPanel panel) {
        String[] positions = {
                "LW", "ST", "ST", "ST", "RW",
                "CF", "CF", "CF",
                "CAM", "CAM", "CAM",
                "LM", "CM", "CM", "CM", "RM",
                "LWB", "LB", "CB", "CB", "CB", "RB", "RWB",
                "CDM", "CDM", "CDM"
        };

        int[][] coordinates = {
                { 800, 600 }, { 850, 330 }, { 850, 480 }, { 850, 180 }, { 800, 50 },
                { 700, 330 }, { 700, 480 }, { 700, 180 },
                { 600, 330 }, { 600, 480 }, { 600, 180 },
                { 450, 600 }, { 450, 330 }, { 450, 480 }, { 450, 180 }, { 450, 50 },
                { 250, 600 }, { 150, 600 }, { 150, 330 }, { 150, 480 }, { 150, 180 }, { 150, 50 }, { 250, 50 },
                { 300, 330 }, { 300, 480 }, { 300, 180 }
        };

        for (int i = 0; i < positions.length; i++) {
            JButton button = new JButton(positions[i]);
            button.setBounds(coordinates[i][0], coordinates[i][1], BUTTON_SIZE, BUTTON_SIZE);
            button.setBackground(Color.YELLOW);
            button.setFont(new Font("Arial", Font.BOLD, 13)); // Custom font
            button.setToolTipText("Select position: " + positions[i]); // Tooltip
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.getBackground() == Color.YELLOW) {
                        if (selectedCount < 10) {
                            button.setBackground(Color.GREEN);
                            selectedCount++;
                            selectedPositions.add(button.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "You can select up to 10 buttons only.");
                        }
                    } else {
                        button.setBackground(Color.YELLOW);
                        selectedCount--;
                        selectedPositions.remove(button.getText());
                    }
                }
            });
            panel.add(button);
        }
        selectedPositions.add("GK");
    }

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw the resized image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void hien() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View_Toi_Uu gui = new View_Toi_Uu();
                gui.setVisible(true);
            }
        });
    }

    public static class Player {
        private String name;
        private double quality;

        public Player(String name, double quality) {
            this.name = name;
            this.quality = quality;
        }

        public String getName() {
            return name;
        }

        public double getQuality() {
            return quality;
        }
    }

    /*public static class Main {
        public static void main(String[] args) {
            hien();
        }
    }*/
}
