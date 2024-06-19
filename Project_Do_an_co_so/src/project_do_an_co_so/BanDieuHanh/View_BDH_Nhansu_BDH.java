package project_do_an_co_so;

import project_do_an_co_so.BanHuyenLuyen.View_Nhansu_1DoiTuong;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class View_BDH_Nhansu_BDH {
    private static JFrame frame;
    private static DefaultTableModel tableModel;
    private static final ArrayList<Player> playerList = new ArrayList<>();

    public static void hien() {
        frame = new JFrame("Nhân sự - 1 nhóm đối tượng");
        frame.setSize(800, 600);
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

        JLabel titleLabel = new JLabel("Nhân sự - 1 nhóm đối tượng");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(250, 20, 400, 30);
        panel.add(titleLabel);

        // Create table
        String[] columnNames = { "Tên" };
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 100, 600, 200);
        panel.add(scrollPane);

        // Add "Thêm" button
        JButton addButton = new JButton("Nút Thêm");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBackground(new Color(255, 182, 193));
        addButton.setBounds(50, 350, 120, 50);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Nhập tên đối tượng:");
                if (name != null && !name.trim().isEmpty()) {
                    tableModel.addRow(new Object[] { name });
                }
            }
        });
        panel.add(addButton);

        // Add "Xóa" button
        JButton deleteButton = new JButton("Nút Xóa");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteButton.setBackground(new Color(255, 182, 193));
        deleteButton.setBounds(200, 350, 120, 50);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Chọn một đối tượng để xóa.");
                }
            }
        });
        panel.add(deleteButton);

        // Add "Chọn" button
        JButton selectButton = new JButton("Nút Chọn");
        selectButton.setFont(new Font("Arial", Font.BOLD, 18));
        selectButton.setBackground(new Color(255, 182, 193));
        selectButton.setBounds(350, 350, 120, 50);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Player selectedPlayer = playerList.get(selectedRow);
                    View_Nhansu_1DoiTuong view = new View_Nhansu_1DoiTuong();
                    view.set(selectedPlayer);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Chọn một đối tượng.");
                }
            }
        });
        panel.add(selectButton);

        // Add "Back" button
        JButton backButton = new JButton("Nút Back");
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

        // Add "Chọn file CSV" button
        JButton loadCsvButton = new JButton("Up file CSV");
        loadCsvButton.setFont(new Font("Arial", Font.BOLD, 18));
        loadCsvButton.setBackground(new Color(255, 182, 193));
        loadCsvButton.setBounds(650, 350, 150, 50);
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("src/project_do_an_co_so/CSV");
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    loadCSV(selectedFile);
                }
            }
        });
        panel.add(loadCsvButton);

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
                String position = values[1];
                String birthDate = values[2];
                String hometown = values[3];
                String photoPath = values[4];

                Player player = new Player(name, position, birthDate, hometown, photoPath);
                playerList.add(player);
                tableModel.addRow(new Object[] { name });
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
}