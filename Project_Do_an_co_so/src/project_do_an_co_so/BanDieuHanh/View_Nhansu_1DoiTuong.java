package project_do_an_co_so;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import project_do_an_co_so.BanDieuHanh.Controller_BDH_Nhansu_BDH;

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
    private final Ngoaile x = new Ngoaile();

    public void set(int selectedRow, Player player, JTable table, DefaultTableModel tableModel,
            ArrayList<Player> playerList) {
        this.currentPlayer = player;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Player's information");
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

    public void openEditForm(int selectedRow, DefaultTableModel tableModel, ArrayList<Player> playerList) {
        editDialog = new JDialog(frame, "Information edit", true);
        editDialog.setSize(400, 400);
        editDialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Tăng padding

        // Nút lưu thay đổi
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18)); // Tăng kích cỡ font chữ

        // Checkboxes cho từng thuộc tính
        JCheckBox nameCheckBox = new JCheckBox("Name");
        JCheckBox positionCheckBox = new JCheckBox("Position");
        JCheckBox dobCheckBox = new JCheckBox("DoB");
        JCheckBox hometownCheckBox = new JCheckBox("Nation");
        JCheckBox numberCheckBox = new JCheckBox("Number");
        JCheckBox weightCheckBox = new JCheckBox("Weight");
        JCheckBox heightCheckBox = new JCheckBox("Height");
        JCheckBox dominantFootCheckBox = new JCheckBox("Dominant foot");

        // Khung nhập liệu cho từng thuộc tính
        JTextField nameField = new JTextField(20);
        JButton positionButton = new JButton("Select Positions"); // Nút mở dialog chọn vị trí
        JPanel dobPanel = x.createDobPanel(); // Tạo panel chứa 3 JComboBox
        JComboBox<String> hometownComboBox = new JComboBox<>(x.getCountries()); // Tạo JComboBox với danh sách các nước
        hometownComboBox.setEditable(true);
        JTextComponent editor = (JTextComponent) hometownComboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> x.autoComplete(hometownComboBox, editor.getText()));
            }
        });
        JTextField numberField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JTextField heightField = new JTextField(20);
        JComboBox<String> dominantFootComboBox = new JComboBox<>(new String[]{"Right", "Left", "Both"});

        // Panel chứa checkboxes và khung nhập liệu
        JPanel attributePanel = new JPanel();
        attributePanel.setLayout(new GridLayout(0, 2));
        attributePanel.add(nameCheckBox);
        attributePanel.add(nameField);
        attributePanel.add(positionCheckBox);
        attributePanel.add(positionButton); // Thêm nút mở dialog chọn vị trí
        attributePanel.add(dobCheckBox);
        attributePanel.add(dobPanel); // Thêm panel chứa 3 JComboBox
        attributePanel.add(hometownCheckBox);
        attributePanel.add(hometownComboBox); // Thêm JComboBox cho Hometown
        attributePanel.add(numberCheckBox);
        attributePanel.add(numberField);
        attributePanel.add(weightCheckBox);
        attributePanel.add(weightField);
        attributePanel.add(heightCheckBox);
        attributePanel.add(heightField);
        attributePanel.add(dominantFootCheckBox);
        attributePanel.add(dominantFootComboBox);

        // Thêm action listeners cho checkboxes để hiển thị/ẩn khung nhập liệu
        nameCheckBox.addActionListener(e -> nameField.setVisible(nameCheckBox.isSelected()));
        positionCheckBox.addActionListener(e -> positionButton.setVisible(positionCheckBox.isSelected())); // Sử dụng positionButton
        dobCheckBox.addActionListener(e -> dobPanel.setVisible(dobCheckBox.isSelected())); // Sử dụng dobPanel
        hometownCheckBox.addActionListener(e -> hometownComboBox.setVisible(hometownCheckBox.isSelected()));
        numberCheckBox.addActionListener(e -> numberField.setVisible(numberCheckBox.isSelected()));
        weightCheckBox.addActionListener(e -> weightField.setVisible(weightCheckBox.isSelected()));
        heightCheckBox.addActionListener(e -> heightField.setVisible(heightCheckBox.isSelected()));
        dominantFootCheckBox.addActionListener(e -> dominantFootComboBox.setVisible(dominantFootCheckBox.isSelected()));

        // Ban đầu ẩn tất cả các khung nhập liệu
        nameField.setVisible(false);
        positionButton.setVisible(false); // Ẩn positionButton
        dobPanel.setVisible(false); // Ẩn dobPanel
        hometownComboBox.setVisible(false); // Ẩn hometownComboBox
        numberField.setVisible(false);
        weightField.setVisible(false);
        heightField.setVisible(false);
        dominantFootComboBox.setVisible(false);

        // Action listener cho nút positionButton để mở dialog chọn vị trí
        positionButton.addActionListener(e -> {
            String selectedPositions = x.openPositionSelectionDialog(positionButton, editDialog);
            if (selectedPositions != null && !selectedPositions.isEmpty()) {
                currentPlayer.setPosition(selectedPositions);
                positionButton.setText(selectedPositions); // Cập nhật tên nút
            }
        });

        // Action listener cho nút lưu
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameCheckBox.isSelected() && !nameField.getText().trim().isEmpty()) {
                    currentPlayer.setName(x.normalizeName(nameField.getText().trim()));
                    nameLabel.setText(currentPlayer.getName());
                }
                if (dobCheckBox.isSelected()) {
                    String dob = x.getDobFromComboBoxes(dobPanel); // Lấy giá trị từ 3 JComboBox
                    currentPlayer.setBirthDate(dob);
                    birthDateLabel.setText(dob);
                }
                if (positionCheckBox.isSelected() && !positionButton.getText().equals("Select Positions")) {
                    String vitri = positionButton.getText(); // Lấy giá trị từ 3 JComboBox
                    currentPlayer.setPosition(vitri);
                    positionLabel.setText(vitri);
                }
                if (hometownCheckBox.isSelected() && hometownComboBox.getSelectedItem() != null) {
                    currentPlayer.setHometown(hometownComboBox.getSelectedItem().toString());
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
                if (dominantFootCheckBox.isSelected() && dominantFootComboBox.getSelectedItem() != null) {
                    currentPlayer.setBodyMass(dominantFootComboBox.getSelectedItem().toString());
                    bodyMassLabel.setText(currentPlayer.getBodyMass());
                }

                if (selectedRow >= 0 && selectedRow < playerList.size()) {
                    tableModel.setValueAt(currentPlayer.getName(), selectedRow, 0);
                    playerList.set(selectedRow, currentPlayer);

                    Controller_Nhansu_1DoiTuong.clearCSVFile("src/project_do_an_co_so/CSV/Data.csv");
                    Controller_BDH_Nhansu_BDH.save("src/project_do_an_co_so/CSV/Data.csv", playerList);

                    editDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Choose a player to edit information");
                }
            }
        });

        contentPanel.add(attributePanel);
        contentPanel.add(saveButton);
        editDialog.add(contentPanel, BorderLayout.CENTER);

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
                View_BDH_Nhansu_BDH.up();
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
