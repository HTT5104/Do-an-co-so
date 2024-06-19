package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_BDH_Nhan_su extends JFrame {

    public View_BDH_Nhan_su() {
        setTitle("Nhân sự");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel titleLabel = new JLabel("Nhân sự");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        JButton backButton = new JButton(new ImageIcon(
                new ImageIcon("src/project_do_an_co_so/Image/arrow.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)
        )); // Path to your arrow image, scaled to 30x30 pixels
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View_BanDieuHanh.hien();
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(backButton, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10);
        buttonGbc.fill = GridBagConstraints.HORIZONTAL;

        String[] buttonLabels = {"Ban điều hành", "Ban huấn luyện", "Đội ngũ y tế", "Cầu thủ"};
        int y = 0;
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(200, 50)); // Set preferred size for the buttons
            button.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size
            button.setBackground(new Color(144, 238, 144));
            buttonGbc.gridx = 0;
            buttonGbc.gridy = y++;
            buttonPanel.add(button, buttonGbc);
        }

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        add(panel);
    }

    public static void hien() {
        SwingUtilities.invokeLater(() -> {
            View_BDH_Nhan_su frame = new View_BDH_Nhan_su();
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
