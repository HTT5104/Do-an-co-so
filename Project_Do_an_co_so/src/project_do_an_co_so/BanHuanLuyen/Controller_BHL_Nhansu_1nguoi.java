package project_do_an_co_so;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import project_do_an_co_so.Player;

public class Controller_BHL_Nhansu_1nguoi {
    public static String formatNames(String names) {
        return Arrays.stream(names.split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.joining("-"));
    }

    public static void upAnh(Player currentPlayer) {
        // Tạo giao diện người dùng để chọn tệp .png
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true); // Cho phép chọn nhiều tệp
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();

            for (File selectedFile : selectedFiles) {
                String fileName = selectedFile.getName();

                // Đường dẫn thư mục lưu trữ (thay đổi theo yêu cầu của bạn)
                String storagePath = "src/project_do_an_co_so/Image/Player_avatar/" + fileName;
                File storageDir = new File("src/project_do_an_co_so/Image/Player_avatar");

                // Kiểm tra và tạo thư mục nếu chưa tồn tại
                if (!storageDir.exists()) {
                    if (storageDir.mkdirs()) {
                        System.out.println("Folder created: " + storageDir.getAbsolutePath());
                    } else {
                        System.out.println("Can't create a folder to save");
                        return;
                    }
                }

                // Xóa ảnh cũ của cầu thủ nếu tồn tại
                String playerFileName = Controller_Nhansu_1DoiTuong.formatNames(currentPlayer.getName()) + ".png";
                File oldPlayerFile = new File(storageDir, playerFileName);
                if (oldPlayerFile.exists()) {
                    if (oldPlayerFile.delete()) {
                        System.out.println("File deleted: " + oldPlayerFile.getAbsolutePath());
                    } else {
                        System.out.println("Can't delete the old file: " + oldPlayerFile.getAbsolutePath());
                    }
                }

                try {
                    // Sao chép tệp vào thư mục lưu trữ
                    Files.copy(selectedFile.toPath(), new File(storagePath).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Tệp " + fileName + " has copied to " + storagePath);

                    // Đổi tên file đã copy trong folder mới theo tên của Player
                    File newFile = new File(storagePath);
                    File renamedFile = new File("src/project_do_an_co_so/Image/Player_avatar/" + playerFileName);

                    if (newFile.renameTo(renamedFile)) {
                        System.out.println("File's name has changed to: " + renamedFile.getAbsolutePath());
                    } else {
                        System.out.println("Can't change the file's name");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void delennhau_gayqua(String Filecu, String Filemoi) {
        // Đường dẫn tới tệp tin cũ
        File oldFile = new File(Filecu);

        // Đường dẫn tới tệp tin mới
        File newFile = new File(Filemoi);

        // Kiểm tra xem tệp tin cũ có tồn tại không
        if (oldFile.exists()) {
            try {
                // Sao chép tệp tin cũ sang tệp tin mới
                Files.copy(oldFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Success");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("The old file doesn't exist");
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
