package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import project_do_an_co_so.Player;

public class Controller_Cau_Thu_1minh{
    // Các thành phần giao diện
    private static JLabel lblCurrentPassword; // Nhãn cho mật khẩu hiện tại
    private static JLabel lblNewPassword; // Nhãn cho mật khẩu mới
    private static JLabel lblConfirmPassword; // Nhãn cho xác nhận mật khẩu mới
    private static JPasswordField pfCurrentPassword; // Ô nhập mật khẩu hiện tại
    private static JPasswordField pfNewPassword; // Ô nhập mật khẩu mới
    private static JPasswordField pfConfirmPassword; // Ô nhập xác nhận mật khẩu mới
    private static JButton btnChangePassword; // Nút để đổi mật khẩu

    public static Player viewController_Cau_Thu_1minh(Player x) {
        JFrame frame = new JFrame("Change Password"); // Tạo khung mới
        // Thiết lập tiêu đề cho khung
        frame.setTitle("Change Password");
        // Thiết lập kích thước cho khung
        frame.setSize(400, 250);
        // Thiết lập hành động khi đóng khung
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Đặt khung ở giữa màn hình
        frame.setLocationRelativeTo(null);               
        // Tạo panel và thiết lập bố cục
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // Bố cục lưới 4 hàng 2 cột với khoảng cách 10 pixel

        // Khởi tạo các thành phần giao diện
        lblCurrentPassword = new JLabel("Current Password:");
        lblNewPassword = new JLabel("New Password:");
        lblConfirmPassword = new JLabel("Confirm Password:");

        pfCurrentPassword = new JPasswordField();
        pfNewPassword = new JPasswordField();
        pfConfirmPassword = new JPasswordField();

        btnChangePassword = new JButton("Change Password");
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi phương thức đổi mật khẩu khi nút được nhấn
                x.setPassword(changePassword(x));
            }
        });

        // Thêm các thành phần vào panel
        // Thêm các thành phần vào panel
        panel.add(lblCurrentPassword);
        panel.add(pfCurrentPassword);
        panel.add(lblNewPassword);
        panel.add(pfNewPassword);
        panel.add(lblConfirmPassword);
        panel.add(pfConfirmPassword);
        panel.add(new JLabel()); // Ô trống để bố cục đẹp hơn
        panel.add(btnChangePassword);

        // Thêm panel vào khung
        frame.add(panel);
        frame.setVisible(true);
        return x;
    }

    // Phương thức đổi mật khẩu
    public static String changePassword(Player x) {
        // Lấy giá trị mật khẩu từ các ô nhập
        String currentPassword = new String(pfCurrentPassword.getPassword());
        String newPassword = new String(pfNewPassword.getPassword());
        String confirmPassword = new String(pfConfirmPassword.getPassword());

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu có trùng khớp không
        if (newPassword.equals(confirmPassword) && x.getPassword().equals(currentPassword)) {
            // Logic để đổi mật khẩu
            // Đây là nơi bạn sẽ thêm logic để kiểm tra mật khẩu hiện tại
            // và cập nhật nó thành mật khẩu mới trong hệ thống của bạn.
            System.out.println(currentPassword);
            System.out.println(newPassword);
            System.out.println(confirmPassword);

            JOptionPane.showMessageDialog(null, "Password changed successfully.");
            return newPassword;
        } else {
            // Hiển thị thông báo nếu mật khẩu mới và xác nhận mật khẩu không trùng khớp            
            System.out.println(currentPassword);
            System.out.println(newPassword);
            System.out.println(confirmPassword);
            JOptionPane.showMessageDialog(null, "New Password and Confirm Password do not match.");                     
            return currentPassword;
        }
    }
}


