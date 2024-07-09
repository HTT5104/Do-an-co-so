package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import project_do_an_co_so.CauThu.View_Cau_Thu_1minh;

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

        JLabel roleLabel = new JLabel("Chọn vai trò ");
        roleLabel.setFont(font);
        roleLabel.setForeground(Color.BLACK);
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
            String formattedUsername = username;
            String md5Password = getMD5(password);
            Map<Map<String, String>, Player> playerData = loadPlayerData("src/project_do_an_co_so/CSV/Data.csv");

            Map<String, String> mp = new HashMap<>();
            mp.put(formattedUsername, md5Password);

            System.out.println("Attempting login with username: " + username + " and password: " + password);
            System.out.println("MD5 Password: " + md5Password);
            System.out.println("Role: " + role);

            // In ra mp để kiểm tra
            System.out.println("User input map: " + mp);

            // In ra playerData để kiểm tra
            for (Map.Entry<Map<String, String>, Player> entry : playerData.entrySet()) {
                System.out.println("Player data map: " + entry.getKey() + ", Player: " + entry.getValue());
            }

            if ("minhduy".equals(username) && "123".equals(password) && "Ban điều hành".equals(role)) {
                View_BanDieuHanh.hien();
                Window window = SwingUtilities.getWindowAncestor(loginPanel);
                if (window != null) {
                    window.dispose();
                }
            } else if ("minhduy".equals(username) && "123".equals(password) && "Ban huấn luyện".equals(role)) {
                View_BanHuanLuyen.hien();
                Window window = SwingUtilities.getWindowAncestor(loginPanel);
                if (window != null) {
                    window.dispose();
                }
            } else if ("Cầu thủ".equals(role)) {               

                if (playerData.containsKey(mp)) {
                    System.out.println("Co");
                    Player x = playerData.get(mp);
                    View_Cau_Thu_1minh z = new View_Cau_Thu_1minh();
                    z.set(x);
                    Window window = SwingUtilities.getWindowAncestor(loginPanel);
                    if (window != null) {
                        window.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không chính xác", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không chính xác", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Thêm bảng điều khiển vào backgroundPanel
        backgroundPanel.add(loginPanel, gbc);
        return backgroundPanel;
    }

    public static Map<Map<String, String>, Player> loadPlayerData(String filePath) {
        Map<Map<String, String>, Player> playerData = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2 && !values[0].isEmpty() && !values[values.length - 1].isEmpty()) {
                    String playerName = values[0].replace(" ", "");
                    String md5Password = values[8];

                    Map<String, String> bm = new HashMap<>();
                    bm.put(playerName, md5Password); //Chứa tên đăng nhập và mật khảu chuẩn

                    // Load
                    String name = values[0];
                    String hometown = values[1];
                    String birthDate = values[2];
                    String numberShirt = values[3];
                    String position = values[4];
                    String weight = values[5];
                    String height = values[6];
                    String bodyMass = values[7];

                    Player pr = new Player(name, hometown, birthDate, numberShirt, position, weight, height, bodyMass);

                    playerData.put(bm, pr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Print the player data for verification
        for (Map.Entry<Map<String, String>, Player> entry : playerData.entrySet()) {
            System.out.println("Player: " + entry.getKey() + ", MD5 Password: " + entry.getValue());
        }

        return playerData;
    }

    // Utility function to generate MD5 hash
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
