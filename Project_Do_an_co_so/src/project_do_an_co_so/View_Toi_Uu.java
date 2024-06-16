package project_do_an_co_so;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class View_Toi_Uu extends JFrame {

    private static final int BUTTON_SIZE = 65;
    private static final int FIELD_WIDTH = 1080;
    private static final int FIELD_HEIGHT = 800;
    private BufferedImage backgroundImage;
    private int selectedCount = 0; // Counter for selected buttons
    private ArrayList<String> selectedPositions = new ArrayList<>(); // List to keep track of selected positions

    public View_Toi_Uu() {
        try {
            backgroundImage = ImageIO.read(new File("src/project_do_an_co_so/Image/football_filed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Football Field GUI");
        setSize(FIELD_WIDTH, FIELD_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Use a custom JPanel to draw the background image
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);

        // Add BackgroundPanel to JFrame
        add(backgroundPanel, BorderLayout.CENTER);

        addButtons(backgroundPanel);

        // Create a JPanel to contain the bottom bar, OK button, and Back button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCount == 10) {
                    JOptionPane.showMessageDialog(null, "Selected positions: " + String.join(", ", selectedPositions));
                } else {
                    JOptionPane.showMessageDialog(null, "Please select exactly 10 positions.");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View_BanHuanLuyen.hien();
                Window window = SwingUtilities.getWindowAncestor(bottomPanel);
                if (window != null) {
                    window.dispose();
                }
            }
        });

        bottomPanel.add(okButton);
        bottomPanel.add(backButton);

        // Add bottomPanel to JFrame
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addButtons(JPanel panel) {
        String[] positions = {
                "LW", "ST", "ST", "ST", "RW",
                "CF", "CF", "CF",
                "CAM", "CAM", "CAM",
                "LM", "CM", "CM", "CM", "RM",
                "LWB", "LB", "CB", "CB", "CB", "RB", "RWB",
                "CDM", "CDM", "CDM",
                "GK"
        };

        int[][] coordinates = {
                { 800, 600 }, { 850, 330 }, { 850, 480 }, { 850, 180 }, { 800, 50 },
                { 700, 330 }, { 700, 480 }, { 700, 180 },
                { 600, 330 }, { 600, 480 }, { 600, 180 },
                { 450, 600 }, { 450, 330 }, { 450, 480 }, { 450, 180 }, { 450, 50 },
                { 200, 600 }, { 120, 600 }, { 150, 330 }, { 150, 480 }, { 150, 180 }, { 120, 50 }, { 200, 50 },
                { 300, 330 }, { 300, 480 }, { 300, 180 },
                { 70, 330 }
        };

        for (int i = 0; i < positions.length; i++) {
            JButton button = new JButton(positions[i]);
            button.setBounds(coordinates[i][0], coordinates[i][1], BUTTON_SIZE, BUTTON_SIZE);
            button.setBackground(Color.YELLOW);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.getBackground() == Color.YELLOW) {
                        if (selectedCount < 10) {
                            button.setBackground(Color.GREEN);
                            selectedCount++;
                            selectedPositions.add(button.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "You can select up to 10 buttons only.");
                        }
                    } else {
                        button.setBackground(Color.YELLOW);
                        selectedCount--;
                        selectedPositions.remove(button.getText());
                    }
                }
            });
            panel.add(button);
        }
    }

    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw the resized image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void hien() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View_Toi_Uu gui = new View_Toi_Uu();
                gui.setVisible(true);
            }
        });
    }
}
