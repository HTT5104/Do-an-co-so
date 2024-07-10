package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class View_BHL_LichThiDau {

    private static final int MAX_MATCHES = 5;
    private static Queue<Match> matchQueue = new LinkedList<>();

    public static void main() {
        // Create the main frame
        JFrame frame = new JFrame("Schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create the main panel with GridLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(MAX_MATCHES + 1, 1, 10, 10)); // +1 for buttons row

        // Create a panel for each match data
        for (int i = 0; i < MAX_MATCHES; i++) {
            JPanel rowPanel = createMatchPanel();
            mainPanel.add(rowPanel);
        }

        // Load data from CSV and populate the panels
        loadDataFromCSV(mainPanel, matchQueue);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View_BanHuanLuyen.hien();
                frame.dispose();
            }
        });
        buttonPanel.add(backButton);
        
        // Add the main panel and the button panel to the frame
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Method to create a panel for a match with specified fields
    private static JPanel createMatchPanel() {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new GridLayout(1, 6, 10, 10));

        rowPanel.add(createLabel("Time"));
        rowPanel.add(createLabel("Date"));
        rowPanel.add(createLabel("League"));
        rowPanel.add(createLabel("Opponent"));
        rowPanel.add(createLabel("Stadium"));
        rowPanel.add(createLabel("Home or Away"));

        return rowPanel;
    }
    


    // Method to create a JLabel with specified text and styling
    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(173, 216, 230)); // Light blue background
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    public static void clearCSVFile(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.print(""); // Overwrite the file with an empty string
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveDataToCSV(String filePath, Queue<Match> matchQueue) {
        clearCSVFile(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            for (Match match : matchQueue) {
                String csvLine = String.join(",",
                        match.getTime(),
                        match.getDate(),
                        match.getTournament(),
                        match.getTeam(),
                        match.getStadium(),
                        match.getHomeAway());
                bw.write(csvLine);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load data from CSV and populate the panels
    private static void loadDataFromCSV(JPanel mainPanel, Queue<Match> matchQueue) {
        matchQueue.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("src/project_do_an_co_so/CSV/lich_thi_dau.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Match match = new Match(data[0], data[1], data[2], data[3], data[4], data[5]);
                matchQueue.add(match);
            }
            updateMainPanel(mainPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update main panel with current match queue
    private static void updateMainPanel(JPanel mainPanel) {
        Component[] components = mainPanel.getComponents();
        int rowIndex = 0;
        Object[] matchArray = matchQueue.toArray();
        int startIndex = Math.max(0, matchArray.length - MAX_MATCHES);
        for (int i = startIndex; i < matchArray.length; i++) {
            Match match = (Match) matchArray[i];
            JPanel rowPanel = (JPanel) components[rowIndex];
            ((JLabel) rowPanel.getComponent(0)).setText(match.getTime());
            ((JLabel) rowPanel.getComponent(1)).setText(match.getDate());
            ((JLabel) rowPanel.getComponent(2)).setText(match.getTournament());
            ((JLabel) rowPanel.getComponent(3)).setText(match.getTeam());
            ((JLabel) rowPanel.getComponent(4)).setText(match.getStadium());
            ((JLabel) rowPanel.getComponent(5)).setText(match.getHomeAway());
            rowIndex++;
        }
        // Clear remaining panels if any
        for (int i = rowIndex; i < MAX_MATCHES; i++) {
            JPanel rowPanel = (JPanel) components[i];
            ((JLabel) rowPanel.getComponent(0)).setText("");
            ((JLabel) rowPanel.getComponent(1)).setText("");
            ((JLabel) rowPanel.getComponent(2)).setText("");
            ((JLabel) rowPanel.getComponent(3)).setText("");
            ((JLabel) rowPanel.getComponent(4)).setText("");
            ((JLabel) rowPanel.getComponent(5)).setText("");
        }
    }

    // Match class to hold match details
}
