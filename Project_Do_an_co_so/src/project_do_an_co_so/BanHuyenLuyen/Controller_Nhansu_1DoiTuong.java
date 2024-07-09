package project_do_an_co_so.BanHuyenLuyen;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import project_do_an_co_so.Player;

public class Controller_Nhansu_1DoiTuong {
    public static String formatNames(String names) {
        return Arrays.stream(names.split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.joining("-"));
    }
    public static void delennhau_gayqua(String Filecu, String Filemoi) {
        // Đường dẫn tới tệp tin cũ
        File oldFile = new File(Filecu);

        // Đường dẫn tới tệp tin mới
        File newFile = new File(Filemoi);

        // Kiểm tra xem tệp tin cũ có tồn tại không
        if (oldFile.exists()) {
            // Kiểm tra xem tệp tin mới có tồn tại không và xóa nó nếu có
            if (newFile.exists()) {
                boolean deleteSuccess = newFile.delete();
                if (!deleteSuccess) {
                    System.out.println("Không thể xóa tệp tin đích.");
                    return;
                }
            }
            // Đổi tên tệp tin
            boolean renameSuccess = oldFile.renameTo(newFile);
            if (renameSuccess) {
                System.out.println("Thành công");
            } else {
                System.out.println("Thất bại");
            }
        } else {
            System.out.println("Tệp tin cũ không tồn tại");
        }
    }    
    public static void upAnh(Player currentPlayer) {
        // Tạo giao diện người dùng để chọn tệp .png
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();

            // Đường dẫn thư mục lưu trữ (thay đổi theo yêu cầu của bạn)
            String storagePath = "src/project_do_an_co_so/Image/Player_avatar/" + fileName;
            File storageDir = new File("images");

            // Kiểm tra và tạo thư mục nếu chưa tồn tại
            if (!storageDir.exists()) {
                if (storageDir.mkdirs()) {
                    System.out.println("Thu muc luu tru da duoc tao: " + storageDir.getAbsolutePath());
                } else {
                    System.out.println("Khong the tao thu muc luu tru");
                    return;
                }
            }

            try {
                // Đọc dữ liệu từ tệp tải lên
                FileInputStream inputStream = new FileInputStream(selectedFile);
                byte[] data = new byte[(int) selectedFile.length()];
                inputStream.read(data);

                // Ghi dữ liệu vào thư mục lưu trữ
                FileOutputStream outputStream = new FileOutputStream(storagePath);
                outputStream.write(data);

                System.out.println("Tep " + fileName + " da duoc luu vao " + storagePath);

                // Đóng các luồng
                inputStream.close();
                outputStream.close();
                delennhau_gayqua(selectedFile.getAbsolutePath(),
                        "src/project_do_an_co_so/Image/Player_avatar/" + Controller_Nhansu_1DoiTuong.formatNames(currentPlayer.getName()) + ".png");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size to 18
        button.setPreferredSize(new Dimension(200, 60)); // Increase button size
        button.setBackground(new Color(70, 130, 180)); // Steel blue background color
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false); // Remove focus paint
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
    }
    
    public static void clearCSVFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(""); // Ghi đè lên file CSV với chuỗi rỗng để xóa sạch nội dung
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void addLabelAndTextField(JPanel panel, JLabel labelTitle, JLabel label, GridBagConstraints gbc,
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
