package project_do_an_co_so.BanDieuHanh;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import project_do_an_co_so.Controller_Nhansu_1DoiTuong;
import project_do_an_co_so.Player;
import project_do_an_co_so.View_BDH_Nhansu_BDH;

public class View_Chon_Loc_1Cauthu {
    private static JFrame frame;
    private JLabel nameLabel, positionLabel, birthDateLabel, hometownLabel, numberShirtLabel, weightLabel, heightLabel,
            bodyMassLabel, photoLabel;
    private JLabel nameLabelTitle, positionLabelTitle, birthDateLabelTitle, hometownLabelTitle, numberShirtLabelTitle,
            weightLabelTitle, heightLabelTitle, bodyMassLabelTitle;
    private GridBagConstraints gbc;
    private Player currentPlayer;
    private JPanel panel, imagePanel;

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
                View_BDH_Nhansu_BDH.backk();
            }
        });
        gbc.gridx = 0; // Vị trí cột
        gbc.gridy = 9; // Vị trí hàng
        gbc.gridwidth = 1; // Chiếm 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Canh trái
        panel.add(backButton, gbc); // Thêm nút Quay lại vào panel

        gbc.gridx = 1; // Vị trí cột
        gbc.gridy = 9; // Vị trí hàng
        gbc.gridwidth = 1; // Chiếm 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Canh trái
        
        gbc.gridx = 2; // Vị trí cột
        gbc.gridy = 9; // Vị trí hàng
        gbc.gridwidth = 1; // Chiếm 1 cột
        gbc.anchor = GridBagConstraints.WEST; // Canh trái


        return panel; // Trả về panel đã được thiết lập
    }
}
