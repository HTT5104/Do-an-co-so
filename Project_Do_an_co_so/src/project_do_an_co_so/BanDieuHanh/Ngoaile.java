package project_do_an_co_so;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
    
    
    public String[] getCountries() {
        // A list of country names
        String[] countries = {
                "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", 
            "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "The Bahamas", "Bahrain", 
            "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", 
            "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", 
            "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", 
            "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Croatia", "Cuba", 
            "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", 
            "Ecuador", "Egypt", "El Salvador", "England", "Equatorial Guinea", "Eritrea", "Estonia", 
            "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "The Gambia", "Georgia", 
            "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", 
            "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", 
            "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", 
            "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", 
            "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", 
            "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", 
            "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", 
            "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Northern Ireland", "North Macedonia", "Norway", 
            "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", 
            "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", 
            "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Scotland", 
            "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", 
            "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", 
            "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", 
            "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United States", 
            "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wales", "Yemen", "Zambia", "Zimbabwe" 
        };
        return countries;
    }

    public void autoComplete(JComboBox<String> comboBox, String text) {
        List<String> list = new ArrayList<>();
        for (String country : getCountries()) {
            if (country.toLowerCase().startsWith(text.toLowerCase())) {
                list.add(country);
            }
        }
        if (list.size() > 0) {
            comboBox.setModel(new DefaultComboBoxModel<>(list.toArray(new String[0])));
            comboBox.setSelectedItem(text);
            comboBox.showPopup();
        } else {
            comboBox.hidePopup();
        }
    }
    
    public String normalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String[] words = name.trim().split("\\s+"); // Tách chuỗi dựa trên một hoặc nhiều dấu cách
        StringBuilder normalized = new StringBuilder();

        for (String word : words) {
            String normalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            normalized.append(normalizedWord).append(" ");
        }

        return normalized.toString().trim(); // Loại bỏ dấu cách thừa ở cuối chuỗi trước khi trả về
    }
    
}
