package project_do_an_co_so.BanHuyenLuyen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import project_do_an_co_so.Player;

public class View_Nhansu_1DoiTuong {
    private static JFrame frame;
    private JLabel nameLabel;
    private JLabel positionLabel;
    private JLabel birthDateLabel;
    private JLabel hometownLabel;
    private JLabel photoLabel;

    public void set(Player player) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hien();
                nameLabel.setText(player.getName());
                positionLabel.setText(player.getPosition());
                birthDateLabel.setText(player.getBirthDate());
                hometownLabel.setText(player.getHometown());
                ImageIcon imageIcon = new ImageIcon("src/project_do_an_co_so/Image/van_quyet.jpg");
                Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(image));
            }
        });
    }

    public void hien() {
        frame = new JFrame("Nhân sự - 1 đối tượng (có thể sửa vàng)");
        frame.setSize(1000, 700); // Thay đổi kích thước của JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = createPanel();
        frame.add(panel);
        frame.setVisible(true);
    }

    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        // Title label
        JLabel titleLabel = new JLabel("Thông tin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Increase font size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 20, 20, 20); // Increase spacing
        panel.add(titleLabel, gbc);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        backButton.setPreferredSize(new Dimension(150, 50)); // Increase button size
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chức năng quay lại (đóng cửa sổ hiện tại)
                frame.dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(backButton, gbc);

        // Image placeholder
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(255, 182, 193));
        imagePanel.setPreferredSize(new Dimension(350, 350)); // Increase image size

        // Load image from file
        photoLabel = new JLabel();
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon imageIcon = new ImageIcon("src/project_do_an_co_so/Image/van_quyet.jpg"); // Đường dẫn đến ảnh đã tải
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
        JLabel positionLabelTitle = new JLabel("Chức vụ / Số áo:");
        JLabel birthDateLabelTitle = new JLabel("Ngày sinh:");
        JLabel hometownLabelTitle = new JLabel("Quê quán:");

        nameLabel = new JLabel();
        positionLabel = new JLabel();
        birthDateLabel = new JLabel();
        hometownLabel = new JLabel();

        // Increase font sizes
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        nameLabelTitle.setFont(labelFont);
        positionLabelTitle.setFont(labelFont);
        birthDateLabelTitle.setFont(labelFont);
        hometownLabelTitle.setFont(labelFont);

        nameLabel.setFont(labelFont);
        positionLabel.setFont(labelFont);
        birthDateLabel.setFont(labelFont);
        hometownLabel.setFont(labelFont);

        addLabelAndTextField(panel, nameLabelTitle, nameLabel, gbc, 2);
        addLabelAndTextField(panel, positionLabelTitle, positionLabel, gbc, 3);
        addLabelAndTextField(panel, birthDateLabelTitle, birthDateLabel, gbc, 4);
        addLabelAndTextField(panel, hometownLabelTitle, hometownLabel, gbc, 5);

        // Buttons (non-editable)
        String[] buttonLabels = { "Chiều cao", "Cân nặng", "Chân thuận" };
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
            button.setBackground(new Color(173, 216, 230));
            button.setEnabled(false);
            button.setPreferredSize(new Dimension(200, 60)); // Increase button size
            gbc.gridx = i; // Cột tương ứng với chỉ số i
            gbc.gridy = 6; // Hàng thứ 6 (hàng cố định)
            gbc.gridwidth = 1;
            gbc.insets = new Insets(20, 20, 20, 20); // Increase spacing
            panel.add(button, gbc); // Thêm thành phần vào panel với các thiết lập trên
        }

        return panel;
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
}