package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import project_do_an_co_so.Player;
import project_do_an_co_so.View_BDH_Nhansu_BDH;

public class View_Nhansu_1DoiTuong {

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
    
    public static String normalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String[] words = name.trim().split("\\s+"); // Tách chuỗi dựa trên một hoặc nhiều dấu cách
        StringBuilder normalized = new StringBuilder();

        for (String word : words) {
            String normalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            normalized.append(normalizedWord).append(" ");
        }

        return normalized.toString().trim(); // Loại bỏ dấu cách thừa ở cuối chuỗi trước khi trả về
    }
    
    public void openEditForm(int selectedRow, DefaultTableModel tableModel, ArrayList<Player> playerList) {
        editDialog = new JDialog(frame, "Information edit", true);
        editDialog.setSize(400, 400);
        editDialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Increase padding

        // Nút lưu thay đổi
        // Nút lưu thay đổi
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size

        // Checkboxes for each attribute
        JCheckBox nameCheckBox = new JCheckBox("Name");
        JCheckBox positionCheckBox = new JCheckBox("Position");
        JCheckBox dobCheckBox = new JCheckBox("DoB");
        JCheckBox hometownCheckBox = new JCheckBox("Hometown");
        JCheckBox numberCheckBox = new JCheckBox("Number");
        JCheckBox weightCheckBox = new JCheckBox("Weight");
        JCheckBox heightCheckBox = new JCheckBox("Height");
        JCheckBox dominantFootCheckBox = new JCheckBox("Dominant foot");

        // Input fields for each attribute
        JTextField nameField = new JTextField(20);
        JTextField positionField = new JTextField(20);
        JTextField dobField = new JTextField(20);
        JTextField hometownField = new JTextField(20);
        JTextField numberField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JTextField heightField = new JTextField(20);
        JTextField dominantFootField = new JTextField(20);

        // Panel to hold checkboxes and input fields
        JPanel attributePanel = new JPanel();
        attributePanel.setLayout(new GridLayout(0, 2));
        attributePanel.add(nameCheckBox);
        attributePanel.add(nameField);
        attributePanel.add(positionCheckBox);
        attributePanel.add(positionField);
        attributePanel.add(dobCheckBox);
        attributePanel.add(dobField);
        attributePanel.add(hometownCheckBox);
        attributePanel.add(hometownField);
        attributePanel.add(numberCheckBox);
        attributePanel.add(numberField);
        attributePanel.add(weightCheckBox);
        attributePanel.add(weightField);
        attributePanel.add(heightCheckBox);
        attributePanel.add(heightField);
        attributePanel.add(dominantFootCheckBox);
        attributePanel.add(dominantFootField);

        // Add action listeners to checkboxes to show/hide input fields
        nameCheckBox.addActionListener(e -> nameField.setVisible(nameCheckBox.isSelected()));
        positionCheckBox.addActionListener(e -> positionField.setVisible(positionCheckBox.isSelected()));
        dobCheckBox.addActionListener(e -> dobField.setVisible(dobCheckBox.isSelected()));
        hometownCheckBox.addActionListener(e -> hometownField.setVisible(hometownCheckBox.isSelected()));
        numberCheckBox.addActionListener(e -> numberField.setVisible(numberCheckBox.isSelected()));
        weightCheckBox.addActionListener(e -> weightField.setVisible(weightCheckBox.isSelected()));
        heightCheckBox.addActionListener(e -> heightField.setVisible(heightCheckBox.isSelected()));
        dominantFootCheckBox.addActionListener(e -> dominantFootField.setVisible(dominantFootCheckBox.isSelected()));

        // Initially hide all input fields
        nameField.setVisible(false);
        positionField.setVisible(false);
        dobField.setVisible(false);
        hometownField.setVisible(false);
        numberField.setVisible(false);
        weightField.setVisible(false);
        heightField.setVisible(false);
        dominantFootField.setVisible(false);

        // Save button action listener
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update currentPlayer based on selected checkboxes
                if (nameCheckBox.isSelected() && !nameField.getText().trim().isEmpty()) {
                    currentPlayer.setName(normalizeName(nameField.getText().trim()));
                    nameLabel.setText(currentPlayer.getName());
                }
                if (positionCheckBox.isSelected() && !positionField.getText().trim().isEmpty()) {
                    currentPlayer.setPosition(positionField.getText().trim());
                    positionLabel.setText(currentPlayer.getPosition());
                }
                if (dobCheckBox.isSelected() && !dobField.getText().trim().isEmpty()) {
                    currentPlayer.setBirthDate(dobField.getText().trim());
                    birthDateLabel.setText(currentPlayer.getBirthDate());
                }
                if (hometownCheckBox.isSelected() && !hometownField.getText().trim().isEmpty()) {
                    currentPlayer.setHometown(hometownField.getText().trim());
                    hometownLabel.setText(currentPlayer.getHometown());
                }
                if (numberCheckBox.isSelected() && !numberField.getText().trim().isEmpty()) {
                    currentPlayer.setNumberShirt(numberField.getText().trim());
                    numberShirtLabel.setText(currentPlayer.getNumberShirt());
                }
                if (weightCheckBox.isSelected() && !weightField.getText().trim().isEmpty()) {
                    currentPlayer.setWeight(weightField.getText().trim());
                    weightLabel.setText(currentPlayer.getWeight());
                }
                if (heightCheckBox.isSelected() && !heightField.getText().trim().isEmpty()) {
                    currentPlayer.setHeight(heightField.getText().trim());
                    heightLabel.setText(currentPlayer.getHeight());
                }
                if (dominantFootCheckBox.isSelected() && !dominantFootField.getText().trim().isEmpty()) {
                    currentPlayer.setBodyMass(dominantFootField.getText().trim());
                    bodyMassLabel.setText(currentPlayer.getBodyMass());
                }

                if (selectedRow >= 0 && selectedRow < playerList.size()) {
                    // Update the table and playerList with the new information
                    tableModel.setValueAt(currentPlayer.getName(), selectedRow, 0);
                    playerList.set(selectedRow, currentPlayer);

                    // Save updated player information to CSV
                    Controller_Nhansu_1DoiTuong.clearCSVFile("src/project_do_an_co_so/CSV/Data.csv");
                    View_BDH_Nhansu_BDH.save("src/project_do_an_co_so/CSV/Data.csv", playerList);

                    editDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Choose a player to edit information");
                }
            }
        });

        contentPanel.add(attributePanel);
        contentPanel.add(saveButton);
        editDialog.add(contentPanel, BorderLayout.CENTER);

        // Set dialog position and visibility
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
                View_BDH_Nhansu_BDH.hien();
                View_BDH_Nhansu_BDH.load2("src/project_do_an_co_so/CSV/Data.csv");
            }
        });
        gbc.gridx = 0; // Vị trí cột
        gbc.gridy = 9; // Vị trí hàng
        gbc.gridwidth = 1; // Chiếm 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Canh trái
        panel.add(backButton, gbc); // Thêm nút Quay lại vào panel

        // Nút Chỉnh sửa
        JButton editButton = new JButton("Edit");
        Controller_Nhansu_1DoiTuong.styleButton(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditForm(selectedRow, tableModel, playerList);
            }
        });
        gbc.gridx = 1; // Vị trí cột
        gbc.gridy = 9; // Vị trí hàng
        gbc.gridwidth = 1; // Chiếm 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Canh trái
        panel.add(editButton, gbc); // Thêm nút Chỉnh sửa vào panel

        // Nút Cập nhật hình ảnh
        JButton uploadImageButton = new JButton("Avatar");
        Controller_Nhansu_1DoiTuong.styleButton(uploadImageButton);
        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller_Nhansu_1DoiTuong.upAnh(currentPlayer);
                frame.dispose();
                View_BDH_Nhansu_BDH.chon(table, playerList);
            }
        });
        gbc.gridx = 2; // Vị trí cột
        gbc.gridy = 9; // Vị trí hàng
        gbc.gridwidth = 1; // Chiếm 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Canh trái
        panel.add(uploadImageButton, gbc); // Thêm nút Cập nhật hình ảnh vào panel

        return panel; // Trả về panel đã được thiết lập
    }
}
