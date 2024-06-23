package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public static JPanel createLoginPanel() {
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/project_do_an_co_so/Image/background.png");

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
        roleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(roleLabel, gbc);

        String[] roles = { "Ban điều hành", "Ban huấn luyện", "Y tế", "Cầu thủ" };
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(font);
        roleComboBox.setSelectedIndex(-1);
        gbc.gridy++;
        loginPanel.add(roleComboBox, gbc);

        JLabel userLabel = new JLabel("Nhập tài khoản");
        userLabel.setFont(font);
        userLabel.setForeground(Color.BLACK);
        gbc.gridy++;
        gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        userField.setFont(font);
        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        JLabel passLabel = new JLabel("Nhập mật khẩu");
        passLabel.setFont(font);
        passLabel.setForeground(Color.BLACK);
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

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            if ("minhduy".equals(username) && "123".equals(password) && "Ban điều hành".equals(role)) {
                // Login successful
                              
                View_BanDieuHanh.hien();
                Window window = SwingUtilities.getWindowAncestor(loginPanel);
                if (window != null) {
                    window.dispose();
                }
            }else if("minhduy".equals(username) && "123".equals(password) && "Ban huấn luyện".equals(role)){
                View_BanHuanLuyen.hien();
                Window window = SwingUtilities.getWindowAncestor(loginPanel);
                if (window != null) {
                    window.dispose();
                }
            } 
            else {
                JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không chính xác", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Thêm bảng điều khiển vào backgroundPanel
        backgroundPanel.add(loginPanel, gbc);                     
        return backgroundPanel;
    }
    public static void Hien() {
        try {
            // Set a modern look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            JPanel loginPanel = createLoginPanel();
            frame.add(loginPanel);
            frame.setVisible(true);
        });
    }
}

class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

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
