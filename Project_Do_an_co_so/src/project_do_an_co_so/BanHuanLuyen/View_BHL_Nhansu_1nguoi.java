package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import project_do_an_co_so.Player;
import project_do_an_co_so.View_BDH_Nhansu_BDH;

public class View_BHL_Nhansu_1nguoi {

    private static JFrame frame;
    private JLabel nameLabel, positionLabel, birthDateLabel, hometownLabel, numberShirtLabel, weightLabel, heightLabel,
            bodyMassLabel, photoLabel;
    private JLabel nameLabelTitle, positionLabelTitle, birthDateLabelTitle, hometownLabelTitle, numberShirtLabelTitle,
            weightLabelTitle, heightLabelTitle, bodyMassLabelTitle;
    private GridBagConstraints gbc;
    private Player currentPlayer;
    private JPanel panel, imagePanel;
    private JDialog editDialog;

    public void set(int selectedRow, Player player, JTable table, DefaultTableModel tableModel,
            ArrayList<Player> playerList) {
        this.currentPlayer = player;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Player");
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
                ImageIcon imageIcon = new ImageIcon(
                        "src/project_do_an_co_so/Image/Player_avatar/"
                                + Controller_Nhansu_1DoiTuong.formatNames(player.getName()) + ".png");
                Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(image));
            }
        });
    }

    private void openEditForm(int selectedRow, DefaultTableModel tableModel, ArrayList<Player> playerList) {
        editDialog = new JDialog(frame, "Information edit", true);
        editDialog.setSize(400, 300);
        editDialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Increase padding

        // Combobox để chọn thuộc tính cần chỉnh sửa
        String[] attributes = { "Name", "Position", "DoB", "Hometown", "Number", "Height", "Weight",
                "Dominant foot" };
        JComboBox<String> attributeComboBox = new JComboBox<>(attributes);
        JLabel attributeLabel = new JLabel("Choose a feature to edit:");
        attributeLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size to 18
        contentPanel.add(attributeLabel);
        contentPanel.add(attributeComboBox);

        // Trường nhập liệu mới
        JTextField newValueField = new JTextField(20);
        JLabel newValueLabel = new JLabel("New information:");
        newValueLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size to 18
        contentPanel.add(newValueLabel);
        contentPanel.add(newValueField);

        // Nút lưu thay đổi
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAttribute = (String) attributeComboBox.getSelectedItem();
                String newValue = newValueField.getText();

                if (newValue.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(editDialog, "Write new information");
                    return;
                }

                // Hộp thoại xác nhận
                int confirm = JOptionPane.showConfirmDialog(editDialog, "Do you want to edit this information?",
                        "Edit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Cập nhật thông tin của cầu thủ đã chọn
                    switch (selectedAttribute) {
                        case "Name":
                            currentPlayer.setName(newValue);
                            nameLabel.setText(newValue);
                            break;
                        case "Position":
                            currentPlayer.setPosition(newValue);
                            positionLabel.setText(newValue);
                            break;
                        case "DoB":
                            currentPlayer.setBirthDate(newValue);
                            birthDateLabel.setText(newValue);
                            break;
                        case "Hometown":
                            currentPlayer.setHometown(newValue);
                            hometownLabel.setText(newValue);
                            break;
                        case "Number":
                            currentPlayer.setNumberShirt(newValue);
                            numberShirtLabel.setText(newValue);
                            break;
                        case "Weight":
                            currentPlayer.setWeight(newValue);
                            weightLabel.setText(newValue);
                            break;
                        case "Height":
                            currentPlayer.setHeight(newValue);
                            heightLabel.setText(newValue);
                            break;
                        case "Dominant foot":
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
                        Controller_Nhansu_1DoiTuong.clearCSVFile("src/project_do_an_co_so/CSV/Data.csv");
                        View_BDH_Nhansu_BDH.save("src/project_do_an_co_so/CSV/Data.csv", playerList);

                        editDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Choose a player to edit information");
                    }
                }
            }
        });

        contentPanel.add(saveButton);
        editDialog.add(contentPanel, BorderLayout.CENTER);

        // Đặt form ra giữa màn hình
        editDialog.setLocationRelativeTo(frame);
        editDialog.setVisible(true);
    }

    public JPanel createPanel(int selectedRow, JTable table, DefaultTableModel tableModel,
            ArrayList<Player> playerList) {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // Màu nền xám nhạt
        gbc = new GridBagConstraints();

        // Tiêu đề
        JLabel titleLabel = new JLabel("Player's information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Tăng kích thước font
        gbc.gridx = 0; // Vị trí cột
        gbc.gridy = 0; // Vị trí hàng
        gbc.gridwidth = 3; // Chiếm 3 cột
        gbc.insets = new Insets(20, 20, 20, 20); // Tăng khoảng cách
        panel.add(titleLabel, gbc); // Thêm tiêu đề vào panel

        // Khung ảnh
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(220, 220, 220)); // Màu nền xám nhạt
        imagePanel.setPreferredSize(new Dimension(300, 300)); // Kích thước khung ảnh
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Viền đen cho khung ảnh

        // Tải ảnh từ file
        photoLabel = new JLabel();
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(photoLabel);
        gbc.gridx = 8; // Vị trí cột
        gbc.gridy = 1; // Vị trí hàng
        gbc.gridheight = 8; // Chiếm 8 hàng
        gbc.insets = new Insets(10, 10, 10, 10); // Giảm khoảng cách giữa các thành phần
        gbc.anchor = GridBagConstraints.CENTER; // Canh giữa
        panel.add(imagePanel, gbc); // Thêm khung ảnh vào panel

        // Các trường văn bản (có thể chỉnh sửa)
        gbc.gridheight = 1; // Chiếm 1 hàng
        gbc.anchor = GridBagConstraints.WEST; // Canh trái
        gbc.fill = GridBagConstraints.HORIZONTAL; // Kéo dãn theo chiều ngang

        // Thêm các JLabel
        nameLabelTitle = new JLabel("Name:");
        positionLabelTitle = new JLabel("Position:");
        birthDateLabelTitle = new JLabel("DoB:");
        hometownLabelTitle = new JLabel("Hometown:");
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

        // Tăng kích thước font
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

        // Thêm các nhãn và trường văn bản vào panel
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, nameLabelTitle, nameLabel, gbc, 1);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, positionLabelTitle, positionLabel, gbc, 2);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, birthDateLabelTitle, birthDateLabel, gbc, 3);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, hometownLabelTitle, hometownLabel, gbc, 4);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, numberShirtLabelTitle, numberShirtLabel, gbc, 5);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, weightLabelTitle, weightLabel, gbc, 6);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, heightLabelTitle, heightLabel, gbc, 7);
        Controller_Nhansu_1DoiTuong.addLabelAndTextField(panel, bodyMassLabelTitle, bodyMassLabel, gbc, 8);

        // Nút Quay lại
        JButton backButton = new JButton("Back");
        Controller_Nhansu_1DoiTuong.styleButton(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                View_BHL_Nhan_su_BHL.hien();
                View_BHL_Nhan_su_BHL.load2("src/project_do_an_co_so/CSV/Data.csv");
            }
        });
        gbc.gridx = 0; // Vị trí cột
        gbc.gridy = 9; // Vị trí hàng
        gbc.gridwidth = 1; // Chiếm 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Canh trái
        panel.add(backButton, gbc); // Thêm nút Quay lại vào panel

        // Nút Chỉnh sửa


        // Nút Cập nhật hình ảnh
        


        return panel; // Trả về panel đã được thiết lập
    }
}
