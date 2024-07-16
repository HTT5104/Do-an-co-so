package project_do_an_co_so;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class View_Cau_Thu_1minh {

    private static JFrame frame;
    private JLabel nameLabel, positionLabel, birthDateLabel, hometownLabel, numberShirtLabel, weightLabel, heightLabel, bodyMassLabel, photoLabel;
    private JLabel nameLabelTitle, positionLabelTitle, birthDateLabelTitle, hometownLabelTitle, numberShirtLabelTitle, weightLabelTitle, heightLabelTitle, bodyMassLabelTitle;
    private GridBagConstraints gbc;
    private Player currentPlayer;
    private JPanel panel, imagePanel;

    public View_Cau_Thu_1minh() {
        panel = createPanel();
    }

    public void set(Player player) {
        this.currentPlayer = player;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Homepage");
                frame.setSize(1200, 800); // Updated frame size
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.add(panel);
                frame.setVisible(true);

                nameLabel.setText(player.getName());
                positionLabel.setText(player.getPosition());
                birthDateLabel.setText(player.getBirthDate());
                hometownLabel.setText(player.getHometown());
                numberShirtLabel.setText(player.getNumberShirt());
                weightLabel.setText(player.getWeight());
                heightLabel.setText(player.getHeight());
                bodyMassLabel.setText(player.getBodyMass());
                ImageIcon imageIcon = new ImageIcon("src/project_do_an_co_so/Image/Player_avatar/" + Controller_Nhansu_1DoiTuong.formatNames(player.getName()) + ".png");
                Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(image));
            }
        });
    }

    private JPanel createPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // Light gray background
        gbc = new GridBagConstraints();

        // Image frame
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(220, 220, 220)); // Light gray background
        imagePanel.setPreferredSize(new Dimension(300, 300)); // Image frame size
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Black border for image frame

        // Load image from file
        photoLabel = new JLabel();
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(photoLabel);
        gbc.gridx = 3; // Column position
        gbc.gridy = 1; // Row position
        gbc.gridheight = 8; // Span 8 rows
        gbc.insets = new Insets(10, 10, 10, 10); // Reduced padding between components
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        panel.add(imagePanel, gbc); // Add image frame to panel

        // Text fields (editable)
        gbc.gridheight = 1; // Span 1 row
        gbc.anchor = GridBagConstraints.WEST; // Left alignment
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch horizontally

        // Add JLabels
        nameLabelTitle = new JLabel("Name:");
        positionLabelTitle = new JLabel("Position:");
        birthDateLabelTitle = new JLabel("DoB:");
        hometownLabelTitle = new JLabel("Nation:");
        numberShirtLabelTitle = new JLabel("Number:");
        weightLabelTitle = new JLabel("Weight:");
        heightLabelTitle = new JLabel("Height:");
        bodyMassLabelTitle = new JLabel("Dominant foot:");

        nameLabel = new JLabel();
        positionLabel = new JLabel();
        birthDateLabel = new JLabel();
        hometownLabel = new JLabel();
        numberShirtLabel = new JLabel();
        weightLabel = new JLabel();
        heightLabel = new JLabel();
        bodyMassLabel = new JLabel();

        // Increased font size
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        nameLabelTitle.setFont(labelFont);
        positionLabelTitle.setFont(labelFont);
        birthDateLabelTitle.setFont(labelFont);
        hometownLabelTitle.setFont(labelFont);
        numberShirtLabelTitle.setFont(labelFont);
        weightLabelTitle.setFont(labelFont);
        heightLabelTitle.setFont(labelFont);
        bodyMassLabelTitle.setFont(labelFont);

        nameLabel.setFont(labelFont);
        positionLabel.setFont(labelFont);
        birthDateLabel.setFont(labelFont);
        hometownLabel.setFont(labelFont);
        numberShirtLabel.setFont(labelFont);
        weightLabel.setFont(labelFont);
        heightLabel.setFont(labelFont);
        bodyMassLabel.setFont(labelFont);

        // Add labels and text fields to panel
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, nameLabelTitle, nameLabel, gbc, 1);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, positionLabelTitle, positionLabel, gbc, 2);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, birthDateLabelTitle, birthDateLabel, gbc, 3);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, hometownLabelTitle, hometownLabel, gbc, 4);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, numberShirtLabelTitle, numberShirtLabel, gbc, 5);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, weightLabelTitle, weightLabel, gbc, 6);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, heightLabelTitle, heightLabel, gbc, 7);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, bodyMassLabelTitle, bodyMassLabel, gbc, 8);

        // Schedule button
        JButton scheduleButton = new JButton("Schedule");
        Controller_Nhansu_1DoiTuong.styleButton(scheduleButton);
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code for schedule button event
                View_CT_LichThiDau.main(currentPlayer);
                frame.dispose();
            }
        });
        gbc.gridx = 2; // Column position
        gbc.gridy = 9; // Row position
        gbc.gridwidth = 1; // Span 1 column
        gbc.anchor = GridBagConstraints.WEST; // Left alignment
        panel.add(scheduleButton, gbc); // Add schedule button to panel

        // Change password button
        JButton uploadImageButton = new JButton("Change Password");
        Controller_Nhansu_1DoiTuong.styleButton(uploadImageButton);
        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller_Cau_Thu_1minh.viewController_Cau_Thu_1minh(currentPlayer);
            }
        });
        gbc.gridx = 3; // Column position
        gbc.gridy = 9; // Row position
        gbc.gridwidth = 1; // Span 1 column
        gbc.anchor = GridBagConstraints.WEST; // Left alignment
        panel.add(uploadImageButton, gbc); // Add change password button to panel

        // Logout button
        JButton logoutButton = new JButton("Logout");
        Controller_Nhansu_1DoiTuong.styleButton(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.OK_OPTION) {
                    Login.Hien();
                    frame.dispose();
                }
            }
        });
        gbc.gridx = 1; // Column position
        gbc.gridy = 9; // Row position
        gbc.gridwidth = 1; // Span 1 column
        gbc.anchor = GridBagConstraints.WEST; // Left alignment
        panel.add(logoutButton, gbc); // Add logout button to panel

        return panel; // Return configured panel
    }

    // Read data from CSV file
    public static ArrayList<Player> readCSV(ArrayList<Player> data, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2 && !values[0].isEmpty() && !values[values.length - 1].isEmpty()) {
                    String name = values[0];
                    String hometown = values[1];
                    String birthDate = values[2];
                    String numberShirt = values[3];
                    String position = values[4];
                    String weight = values[5];
                    String height = values[6];
                    String bodyMass = values[7];
                    String md5Password = values[8];

                    Player pr = new Player(name, hometown, birthDate, numberShirt, position, weight, height, bodyMass, md5Password);
                    data.add(pr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Write data to CSV file and only change the password of specific players
    public static void writeCSV(String filePath, ArrayList<Player> players) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Player player : players) {
                String[] fields = {
                    player.getName(),
                    player.getPosition(),
                    player.getBirthDate(),
                    player.getHometown(),
                    player.getNumberShirt(),
                    player.getWeight(),
                    player.getHeight(),
                    player.getBodyMass(),
                    player.getPassword()
                };
                bw.write(String.join(",", fields));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
