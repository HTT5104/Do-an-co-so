package project_do_an_co_so;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class Ngoaile {
    public JPanel createDobPanel() {
        JPanel dobPanel = new JPanel();
        dobPanel.setLayout(new FlowLayout());

        // Tạo JComboBox cho ngày
        JComboBox<String> dayComboBox = new JComboBox<>();
        for (int i = 31; i >= 1; i--) {
            dayComboBox.addItem(String.format("%02d", i));
        }

        // Tạo JComboBox cho tháng
        JComboBox<String> monthComboBox = new JComboBox<>();
        for (int i = 12; i >= 1; i--) {
            monthComboBox.addItem(String.format("%02d", i));
        }

        // Tạo JComboBox cho năm
        JComboBox<String> yearComboBox = new JComboBox<>();
        for (int i = 2024; i >= 1900; i--) {
            yearComboBox.addItem(Integer.toString(i));
        }

        dobPanel.add(dayComboBox);
        dobPanel.add(monthComboBox);
        dobPanel.add(yearComboBox);

        return dobPanel;
    }

    public String getDobFromComboBoxes(JPanel dobPanel) {
        JComboBox<String> dayComboBox = (JComboBox<String>) dobPanel.getComponent(0);
        JComboBox<String> monthComboBox = (JComboBox<String>) dobPanel.getComponent(1);
        JComboBox<String> yearComboBox = (JComboBox<String>) dobPanel.getComponent(2);

        String day = (String) dayComboBox.getSelectedItem();
        String month = (String) monthComboBox.getSelectedItem();
        String year = (String) yearComboBox.getSelectedItem();

        return day + "/" + month + "/" + year;
    }

    public String openPositionSelectionDialog(JButton positionButton, JDialog editDialog) {
        JDialog positionDialog = new JDialog(editDialog, "Select Positions", true);
        positionDialog.setSize(300, 300);
        positionDialog.setLayout(new BorderLayout());

        JPanel positionPanel = new JPanel();
        positionPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Cách đều giữa các ô

        String[] positions = {"GK", "LB", "CB", "RB", "LWB", "RWB", "CDM", "CM", "CAM", "LM", "RM", "LW", "RW", "CF", "ST"};
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        for (String position : positions) {
            JCheckBox positionCheckBox = new JCheckBox(position);
            positionPanel.add(positionCheckBox);
            checkBoxes.add(positionCheckBox);
        }

        JButton savePositionsButton = new JButton("Save");
        savePositionsButton.addActionListener(e -> positionDialog.dispose());

        positionDialog.add(positionPanel, BorderLayout.CENTER);
        positionDialog.add(savePositionsButton, BorderLayout.SOUTH);

        positionDialog.setLocationRelativeTo(editDialog);
        positionDialog.setVisible(true);

        StringBuilder selectedPositions = new StringBuilder();
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                if (selectedPositions.length() > 0) {
                    selectedPositions.append("; ");
                }
                selectedPositions.append(checkBox.getText());
            }
        }
        return selectedPositions.toString();
    }
}
