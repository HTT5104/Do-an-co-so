package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        backgroundImage = new ImageIcon(fileName).getImage();
        setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để căn chỉnh các thành phần
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class ViewBanDieuHanh extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ViewBanDieuHanh() {
        setTitle("Soccer App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel homePanel = createHomePanel();

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(homePanel, "Home");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");

        setVisible(true);
    }

    private JPanel createLoginPanel() {
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/project_do_an_co_so/background.png");

        // Tạo bảng điều khiển cho biểu mẫu đăng nhập
        JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(false); // Đặt nền trong suốt
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tạo các thành phần cho biểu mẫu đăng nhập
        Font font = new Font("Arial", Font.BOLD, 14);

        JLabel roleLabel = new JLabel("Chọn vai trò");
        roleLabel.setFont(font);
        roleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(roleLabel, gbc);

        String[] roles = {"Ban điều hành", "Ban huấn luyện", "Y tế", "Cầu thủ"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(font);
        roleComboBox.setSelectedIndex(-1);
        gbc.gridy++;
        loginPanel.add(roleComboBox, gbc);

        JLabel userLabel = new JLabel("Ô Nhập tài khoản");
        userLabel.setFont(font);
        userLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        userField.setFont(font);
        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        JLabel passLabel = new JLabel("Ô Nhập mật khẩu");
        passLabel.setFont(font);
        passLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        loginPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        passField.setFont(font);
        gbc.gridx = 1;
        loginPanel.add(passField, gbc);

        JButton loginButton = new JButton("Nút Đăng nhập");
        loginButton.setFont(font);
        loginButton.setBackground(new Color(33, 150, 243));
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                if ("minhduy".equals(username) && "123".equals(password) && "Ban điều hành".equals(role)) {
                    cardLayout.show(mainPanel, "Home");
                } else {
                    JOptionPane.showMessageDialog(ViewBanDieuHanh.this, "Thông tin đăng nhập không chính xác", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Thêm bảng điều khiển vào backgroundPanel
        backgroundPanel.add(loginPanel, gbc);

        return backgroundPanel;
    }

    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel(null);
        homePanel.setBackground(Color.WHITE);

        Font font = new Font("Arial", Font.BOLD, 18);

        JLabel titleLabel = new JLabel("Trang chủ");
        titleLabel.setFont(font);
        titleLabel.setBounds(350, 30, 100, 30);
        homePanel.add(titleLabel);

        JButton logoutButton = new JButton("Đăng xuất");
        logoutButton.setFont(font);
        logoutButton.setBounds(30, 30, 150, 50);
        homePanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Login");
            }
        });

        JButton personnelButton = new JButton("Nhân sự");
        personnelButton.setFont(font);
        personnelButton.setBounds(200, 200, 150, 50);
        homePanel.add(personnelButton);

        JButton scheduleButton = new JButton("Lịch thi đấu");
        scheduleButton.setFont(font);
        scheduleButton.setBounds(400, 200, 150, 50);
        homePanel.add(scheduleButton);

        JButton rankingButton = new JButton("Bảng xếp hạng");
        rankingButton.setFont(font);
        rankingButton.setBounds(600, 200, 150, 50);
        homePanel.add(rankingButton);

        return homePanel;
    }

    public static void main(String[] args) {
        
    }
}
