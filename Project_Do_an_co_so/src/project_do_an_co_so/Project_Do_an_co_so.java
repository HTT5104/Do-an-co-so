package vn.viettuts.swing;
 
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
 
public class Project_Do_an_co_so {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
 
    public Project_Do_an_co_so() {
        prepareGUI();
    }
 
    public static void main(String[] args) {
        Project_Do_an_co_so demo = new Project_Do_an_co_so();
        demo.showCardLayoutDemo();
    }
 
    private void prepareGUI() {
        mainFrame = new JFrame("Vi du CardLayout trong Java Swing");
        mainFrame.setSize(400, 350);
        mainFrame.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
 
    private void showCardLayoutDemo() {
        headerLabel.setText("Layout in action: CardLayout");
        final JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setSize(300, 300);
        CardLayout layout = new CardLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        panel.setLayout(layout);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(new JButton("OK"));
        buttonPanel.add(new JButton("Cancel"));
        JPanel textBoxPanel = new JPanel(new FlowLayout());
        textBoxPanel.add(new JLabel("Name:"));
        textBoxPanel.add(new JTextField(20));
        panel.add("Button", buttonPanel);
        panel.add("Text", textBoxPanel);
 
        final DefaultComboBoxModel panelName = new DefaultComboBoxModel();
        panelName.addElement("Button");
        panelName.addElement("Text");
 
        final JComboBox listCombo = new JComboBox(panelName);
        listCombo.setSelectedIndex(0);
        JScrollPane listComboScrollPane = new JScrollPane(listCombo);
        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "";
                if (listCombo.getSelectedIndex() != -1) {
                    CardLayout cardLayout = (CardLayout) (panel.getLayout());
                    cardLayout.show(panel, 
                            (String) listCombo.getItemAt(listCombo.getSelectedIndex()));
                }
                statusLabel.setText(data);
            }
        });
 
        controlPanel.add(listComboScrollPane);
        controlPanel.add(showButton);
        controlPanel.add(panel);
        mainFrame.setVisible(true);
    }
}