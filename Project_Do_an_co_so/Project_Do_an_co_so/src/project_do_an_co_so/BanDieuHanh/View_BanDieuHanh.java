package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FlatArrowButton extends JButton {
    private Image arrowImage;

    public FlatArrowButton(String text, ImageIcon icon) {
        super(text);
        arrowImage = icon.getImage();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.BLACK); // Đặt màu chữ phù hợp với nền mũi tên
        setFont(new Font("Arial", Font.BOLD, 18));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ hình mũi tên tùy chỉnh
        int arrowWidth = getWidth();
        int arrowHeight = getHeight();
        int arrowHeadWidth = arrowHeight / 2;
        
        Polygon arrow = new Polygon();
        arrow.addPoint(0, arrowHeight / 2);
        arrow.addPoint(arrowHeadWidth, 0);
        arrow.addPoint(arrowHeadWidth, arrowHeight / 4);
        arrow.addPoint(arrowWidth - 50, arrowHeight / 4); // Điều chỉnh độ dài thân mũi tên
        arrow.addPoint(arrowWidth - 50, 3 * arrowHeight / 4); // Điều chỉnh độ dài thân mũi tên
        arrow.addPoint(arrowHeadWidth, 3 * arrowHeight / 4);
        arrow.addPoint(arrowHeadWidth, arrowHeight);
        arrow.addPoint(0, arrowHeight / 2);
        
        g2.setColor(new Color(210, 180, 140)); // Màu mũi tên
        g2.fill(arrow);

        g2.setColor(Color.BLACK); // Viền mũi tên
        g2.draw(arrow);

        // Vẽ văn bản lên trên và căn giữa
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        int x = (getWidth() - textWidth) / 2 - 6;
        int y = (getHeight() + textHeight) / 2 - 1 ; // Điều chỉnh y để văn bản nằm chính xác ở giữa
        
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);
        
        g2.dispose();
    }
}

public class View_BanDieuHanh {
    private static JFrame frame;
    
    public static void hien() {
        frame = new JFrame("Trang chu");
        frame.setTitle("Soccer App");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel homePanel = createHomePanel();        
        frame.add(homePanel);
        frame.setVisible(true);
    }

    public static JPanel createHomePanel() {
        JPanel homePanel = new JPanel(null);
        homePanel.setBackground(Color.WHITE);

        Font font = new Font("Arial", Font.BOLD, 18);

        JLabel titleLabel = new JLabel("Trang chủ");
        titleLabel.setFont(font);
        titleLabel.setBounds(350, 30, 100, 30);
        homePanel.add(titleLabel);

        // Tạo nút Đăng xuất với hình mũi tên tùy chỉnh
        ImageIcon arrowIcon = new ImageIcon("src/project_do_an_co_so/Image/arrow.png");
        FlatArrowButton logoutButton = new FlatArrowButton("Đăng xuất", arrowIcon);
        logoutButton.setBounds(30, 30, 200, 80); // Điều chỉnh kích thước nút cho mũi tên ngắn lại và chữ nằm hoàn toàn trong mũi tên
        homePanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    Login.Hien();
                    frame.dispose(); // Đóng cửa sổ hiện tại
                }
            }
        });

        JButton personnelButton = createButton("Nhân sự", 200, 200);
        personnelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code xử lý sự kiện cho nút Nhân sự
                View_BDH_Nhansu_BDH.set();
                frame.dispose();
            }
        });
        homePanel.add(personnelButton);

        JButton scheduleButton = createButton("Lịch thi đấu", 400, 200);
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code xử lý sự kiện cho nút Nhân sự
                View_BDH_LichThiDau.main();
                frame.dispose();
            }
        });
        homePanel.add(scheduleButton);

        JButton rankingButton = createButton("Bảng xếp hạng", 600, 200);
        homePanel.add(rankingButton);

        return homePanel;
    }

    public static JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(144, 238, 144));
        button.setBounds(x, y, 170, 50); // Tăng chiều rộng nút để hiển thị đủ chữ
        return button;
    }
}
