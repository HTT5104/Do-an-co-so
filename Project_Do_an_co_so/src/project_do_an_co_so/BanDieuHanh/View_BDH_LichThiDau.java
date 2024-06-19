package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class View_BDH_LichThiDau {
    public static void main() {
        // Create the main frame
        JFrame frame = new JFrame("Lịch thi đấu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create the main panel with GridLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        // Create a panel for each row of data
        for (int i = 0; i < 3; i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1, 6, 10, 10));

            // Add labels to the row panel
            rowPanel.add(createLabel("Thời gian"));
            rowPanel.add(createLabel("Giải đấu"));
            rowPanel.add(createLabel("Đối thủ"));
            rowPanel.add(createLabel("Địa điểm"));
            rowPanel.add(createLabel("Tỷ số (nếu đã đá)"));

            // Add button to upload CSV
            JButton uploadButton = new JButton("Up CSV");
            uploadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        // Do something with the selected file
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    }
                }
            });
            rowPanel.add(uploadButton);

            // Add row panel to main panel
            mainPanel.add(rowPanel);
        }

        // Create a panel for the back button
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View_BanDieuHanh.hien();
                frame.dispose();
            }
        });
        backPanel.add(backButton);

        // Add the main panel and the back panel to the frame
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(backPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Method to create a JLabel with specified text and styling
    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(173, 216, 230)); // Light blue background
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }
}
