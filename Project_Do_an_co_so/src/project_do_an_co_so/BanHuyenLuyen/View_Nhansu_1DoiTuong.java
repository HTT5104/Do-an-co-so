package project_do_an_co_so.BanHuyenLuyen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import project_do_an_co_so.Player;
import project_do_an_co_so.View_BDH_Nhansu_BDH;

public class View_Nhansu_1DoiTuong {

    private static JFrame frame;
    private JLabel nameLabel;
    private JLabel positionLabel;
    private JLabel birthDateLabel;
    private JLabel hometownLabel;
    private JLabel numberShirtLabel;
    private JLabel weightLabel;
    private JLabel heightLabel;
    private JLabel bodyMassLabel;
    private JLabel photoLabel;
    private Player currentPlayer;
    
    public static String formatNames(String names) {
        return Arrays.stream(names.split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.joining("-"));
    }
    
    public void set(int selectedRow, Player player, JTable table, DefaultTableModel tableModel,
            ArrayList<Player> playerList) {
        this.currentPlayer = player;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Nhân sự");
                frame.setSize(1200, 800); // Updated frame size
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                JPanel panel = createPanel(selectedRow, table, tableModel, playerList);
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
                ImageIcon imageIcon = new ImageIcon("src/project_do_an_co_so/Image/Player_avatar/" + formatNames(player.getName()) + ".png");
                Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(image));
            }
        });
    }

    public JPanel createPanel(int selectedRow, JTable table, DefaultTableModel tableModel,
            ArrayList<Player> playerList) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // Light gray background
        GridBagConstraints gbc = new GridBagConstraints();

        // Title label
        JLabel titleLabel = new JLabel("Thông tin cầu thủ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 20, 20, 20); // Increase spacing
        panel.add(titleLabel, gbc);

        // Back button
        JButton backButton = new JButton("Quay lại");
        styleButton(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                View_BDH_Nhansu_BDH.hien();
                View_BDH_Nhansu_BDH.load2("src/project_do_an_co_so/CSV/Data.csv");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(backButton, gbc);

        // Edit button
        JButton editButton = new JButton("Chỉnh sửa");
        styleButton(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditForm(selectedRow, table, tableModel, playerList);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(editButton, gbc);

        // Image placeholder
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(220, 220, 220)); // Light gray background
        imagePanel.setPreferredSize(new Dimension(350, 350)); // Increase image size
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add a black border to the image

        // Load image from file
        photoLabel = new JLabel();
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon imageIcon = new ImageIcon("src/project_do_an_co_so/Image/van_quyet.jpg"); // Default image path
        Image image = imageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(image));

        imagePanel.add(photoLabel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(imagePanel, gbc);

        // Text fields (editable)
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding JLabels
        JLabel nameLabelTitle = new JLabel("Họ tên:");
        JLabel positionLabelTitle = new JLabel("Vị trí:");
        JLabel birthDateLabelTitle = new JLabel("Ngày sinh:");
        JLabel hometownLabelTitle = new JLabel("Quê quán:");
        JLabel numberShirtLabelTitle = new JLabel("Số áo:");
        JLabel weightLabelTitle = new JLabel("Cân nặng:");
        JLabel heightLabelTitle = new JLabel("Chiều cao:");
        JLabel bodyMassLabelTitle = new JLabel("Chân thuận:");

        nameLabel = new JLabel();
        positionLabel = new JLabel();
        birthDateLabel = new JLabel();
        hometownLabel = new JLabel();
        numberShirtLabel = new JLabel();
        weightLabel = new JLabel();
        heightLabel = new JLabel();
        bodyMassLabel = new JLabel();

        // Increase font sizes
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
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

        addLabelAndTextField(panel, nameLabelTitle, nameLabel, gbc, 2);
        addLabelAndTextField(panel, positionLabelTitle, positionLabel, gbc, 3);
        addLabelAndTextField(panel, birthDateLabelTitle, birthDateLabel, gbc, 4);
        addLabelAndTextField(panel, hometownLabelTitle, hometownLabel, gbc, 5);
        addLabelAndTextField(panel, numberShirtLabelTitle, numberShirtLabel, gbc, 6);
        addLabelAndTextField(panel, weightLabelTitle, weightLabel, gbc, 7);
        addLabelAndTextField(panel, heightLabelTitle, heightLabel, gbc, 8);
        addLabelAndTextField(panel, bodyMassLabelTitle, bodyMassLabel, gbc, 9);

        return panel;
    }

    private void openEditForm(int selectedRow, JTable table, DefaultTableModel tableModel,
            ArrayList<Player> playerList) {
        JDialog editDialog = new JDialog(frame, "Chỉnh sửa thông tin", true);
        editDialog.setSize(400, 300);
        editDialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Increase padding

        // Combobox để chọn thuộc tính cần chỉnh sửa
        String[] attributes = { "Họ tên", "Vị trí", "Ngày sinh", "Quê quán", "Số áo", "Cân nặng", "Chiều cao", "Chân thuận" };
        JComboBox<String> attributeComboBox = new JComboBox<>(attributes);
        JLabel attributeLabel = new JLabel("Chọn thuộc tính cần chỉnh sửa:");
        attributeLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size to 18
        contentPanel.add(attributeLabel);
        contentPanel.add(attributeComboBox);

        // Trường nhập liệu mới
        JTextField newValueField = new JTextField(20);
        JLabel newValueLabel = new JLabel("Giá trị mới:");
        newValueLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size to 18
        contentPanel.add(newValueLabel);
        contentPanel.add(newValueField);

                // Nút lưu thay đổi
        JButton saveButton = new JButton("Lưu");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAttribute = (String) attributeComboBox.getSelectedItem();
                String newValue = newValueField.getText();

                if (newValue.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(editDialog, "Vui lòng nhập giá trị mới.");
                    return;
                }

                // Cập nhật thông tin của cầu thủ đã chọn
                switch (selectedAttribute) {
                    case "Họ tên":
                        currentPlayer.setName(newValue);
                        nameLabel.setText(newValue);
                        break;
                    case "Vị trí":
                        currentPlayer.setPosition(newValue);
                        positionLabel.setText(newValue);
                        break;
                    case "Ngày sinh":
                        currentPlayer.setBirthDate(newValue);
                        birthDateLabel.setText(newValue);
                        break;
                    case "Quê quán":
                        currentPlayer.setHometown(newValue);
                        hometownLabel.setText(newValue);
                        break;
                    case "Số áo":
                        currentPlayer.setNumberShirt(newValue);
                        numberShirtLabel.setText(newValue);
                        break;
                    case "Cân nặng":
                        currentPlayer.setWeight(newValue);
                        weightLabel.setText(newValue);
                        break;
                    case "Chiều cao":
                        currentPlayer.setHeight(newValue);
                        heightLabel.setText(newValue);
                        break;
                    case "Chân thuận":
                        currentPlayer.setBodyMass(newValue);
                        bodyMassLabel.setText(newValue);
                        break;
                }

                if (selectedRow >= 0 && selectedRow < playerList.size()) {
                    // Cập nhật lại bảng với thông tin mới
                    tableModel.setValueAt(currentPlayer.getName(), selectedRow, 0);

                    // Cập nhật lại danh sách cầu thủ trong playerList
                    playerList.set(selectedRow, currentPlayer);

                    // Save updated player information to CSV
                    clearCSVFile("src/project_do_an_co_so/CSV/Data.csv");
                    View_BDH_Nhansu_BDH.save3("src/project_do_an_co_so/CSV/Data.csv", playerList);

                    editDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Chọn một đối tượng hợp lệ để cập nhật.");
                }
            }
        });

        contentPanel.add(saveButton);
        editDialog.add(contentPanel, BorderLayout.CENTER);

        // Đặt form ra giữa màn hình
        editDialog.setLocationRelativeTo(frame);
        editDialog.setVisible(true);
    }

    private void clearCSVFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(""); // Ghi đè lên file CSV với chuỗi rỗng để xóa sạch nội dung
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addLabelAndTextField(JPanel panel, JLabel labelTitle, JLabel label, GridBagConstraints gbc,
            int gridy) {
        gbc.gridx = 1;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(labelTitle, gbc);

        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.YELLOW);
        labelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelPanel.setPreferredSize(new Dimension(300, 50)); // Increase label panel size
        labelPanel.setLayout(new BorderLayout());
        labelPanel.add(label, BorderLayout.CENTER);
        gbc.gridx = 2;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(labelPanel, gbc);
    }

    private void savePlayerToCSV(Player player, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(player.getName() + ","
                    + player.getPosition() + ","
                    + player.getBirthDate() + ","
                    + player.getHometown() + ","
                    + player.getNumberShirt() + ","
                    + player.getWeight() + ","
                    + player.getHeight() + ","
                    + player.getBodyMass() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size to 24
        button.setPreferredSize(new Dimension(150, 50)); // Increase button size
        button.setBackground(new Color(70, 130, 180)); // Steel blue background color
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false); // Remove focus paint
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
    }
}

