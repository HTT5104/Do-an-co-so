package project_do_an_co_so;

//import project_do_an_co_so.BanDieuHanh.View_Nhansu_1DoiTuong;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class View_BHL_Nhan_su_BHL {

    private static JFrame frame;
    private static DefaultTableModel tableModel;
    private static final ArrayList<Player> playerList = new ArrayList<>();
    private static String url;

    public static void save2(String filePath, ArrayList<Player> playerList1) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            // Ghi dữ liệu của từng cầu thủ vào file CSV
            for (Player player : playerList1) {
                writer.newLine();
                writer.write(player.getName() + "," + player.getHometown() + "," + player.getBirthDate() + "," + player.getNumberShirt() + ","
                        + player.getPosition() + "," + player.getWeight() + "," + player.getHeight() + "," + player.getBodyMass());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    public static void save3(String filePath, ArrayList<Player> playerList1) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Ghi dữ liệu của từng cầu thủ vào file CSV
            for (Player player : playerList1) {
                System.out.println(player.toString());
                writer.newLine();
                writer.write(player.getName() + "," + player.getHometown() + "," + player.getBirthDate() + "," + player.getNumberShirt() + ","
                        + player.getPosition() + "," + player.getWeight() + "," + player.getHeight() + "," + player.getBodyMass());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void chon(JTable table, ArrayList<Player> playerList) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Player selectedPlayer = playerList.get(selectedRow);
            View_BHL_Nhansu_1nguoi view = new View_BHL_Nhansu_1nguoi();
            view.set(selectedRow, selectedPlayer, table, tableModel, playerList);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Choose a player");
        }
    }

    public static void load(String x) {
        JFileChooser fileChooser = new JFileChooser(x);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath(); // Lấy đường dẫn tuyệt đối của file
            url = filePath;
            loadCSV(selectedFile);
        }
    }

    public static void load2(String x) {
        File file = new File("src/project_do_an_co_so/CSV/Data.csv");
        if (file.exists() && !file.isDirectory()) {
            // Nếu đường dẫn x là hợp lệ và không phải là một thư mục
            loadCSV(file);
        } else {
            System.out.println("Error direction: " + x);
        }
    }

    private static void openAddPlayerForm() {
        // Tạo một form mới để nhập thuộc tính cầu thủ
        JDialog addPlayerDialog = new JDialog(frame, "Add a new player", true);
        addPlayerDialog.setSize(300, 500);
        addPlayerDialog.setLayout(new BoxLayout(addPlayerDialog.getContentPane(), BoxLayout.Y_AXIS));

        // Tạo các trường nhập liệu cho thuộc tính cầu thủ
        JTextField nameField = new JTextField(20);
        JTextField hometownField = new JTextField(20);
        JTextField birthDateField = new JTextField(20);
        JTextField numberShirtField = new JTextField(20);
        JTextField positionField = new JTextField(20);
        JTextField weightField = new JTextField(20);
        JTextField heightField = new JTextField(20);
        JTextField bodyMassField = new JTextField(20);

        // Thêm các trường nhập liệu vào dialog
        addPlayerDialog.add(new JLabel("Name:"));
        addPlayerDialog.add(nameField);
        addPlayerDialog.add(new JLabel("Hometown:"));
        addPlayerDialog.add(hometownField);
        addPlayerDialog.add(new JLabel("DoB:"));
        addPlayerDialog.add(birthDateField);
        addPlayerDialog.add(new JLabel("Number:"));
        addPlayerDialog.add(numberShirtField);
        addPlayerDialog.add(new JLabel("Positions:"));
        addPlayerDialog.add(positionField);
        addPlayerDialog.add(new JLabel("Weight:"));
        addPlayerDialog.add(weightField);
        addPlayerDialog.add(new JLabel("Height:"));
        addPlayerDialog.add(heightField);
        addPlayerDialog.add(new JLabel("Dominant foot:"));
        addPlayerDialog.add(bodyMassField);

        // Thêm nút lưu cầu thủ
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                String name = nameField.getText();
                String hometown = hometownField.getText();
                String birthDate = birthDateField.getText();
                String numberShirt = numberShirtField.getText();
                String position = positionField.getText();
                String weight = weightField.getText();
                String height = heightField.getText();
                String bodyMass = bodyMassField.getText();

                // Kiểm tra xem tên có được nhập hay không
                if (!name.trim().isEmpty()) {
                    // Thêm dữ liệu vào bảng
                    tableModel.addRow(new Object[]{name});

                    // Lưu dữ liệu vào file CSV
                    savePlayerToCSV(name, hometown, birthDate, numberShirt, position, weight, height, bodyMass);

                    // Đóng dialog
                    addPlayerDialog.dispose();
                    frame.dispose();
                    View_BDH_Nhansu_BDH.hien();
                    load2("src/project_do_an_co_so/CSV/Data.csv");
                } else {
                    JOptionPane.showMessageDialog(frame, "Write the player's name");
                }
            }
        });
        addPlayerDialog.add(saveButton);

        // Hiển thị dialog
        addPlayerDialog.setVisible(true);
        }

    // Hàm lưu dữ liệu vào file CSV, được khai báo bên ngoài phương thức createPanel
    private static void savePlayerToCSV(String name, String hometown, String birthDate, String numberShirt,
            String position, String weight, String height, String bodyMass) {
        // Mở hộp thoại cho người dùng chọn nơi lưu file CSV
        //JFileChooser fileChooser = new JFileChooser("src/project_do_an_co_so/CSV/Data.csv");
        //fileChooser.setDialogTitle("Lưu file CSV");
        //int userSelection = fileChooser.showSaveDialog(frame);

        //if (userSelection == JFileChooser.APPROVE_OPTION) {
            //File fileToSave = fileChooser.getSelectedFile();
            File fileToSave = new File("src/project_do_an_co_so/CSV/Data.csv");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave, true))) {
                writer.write(name + "," + hometown + "," + birthDate + "," + numberShirt + "," + position + "," + weight
                        + "," + height + "," + bodyMass);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
    }

    public static void hien() {
        frame = new JFrame("Player");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = createPanel();
        frame.add(panel);
        frame.setVisible(true);
    }

    public static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Player list");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(250, 20, 400, 30);
        panel.add(titleLabel);

        // Create table
        String[] columnNames = {"Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 100, 600, 200);
        panel.add(scrollPane);

        // Add "Thêm" button
        // Add "Xóa" button

        // Add "Chọn" button
        JButton selectButton = new JButton("Choose");
        selectButton.setFont(new Font("Arial", Font.BOLD, 18));
        selectButton.setBackground(new Color(255, 182, 193));
        selectButton.setBounds(150, 350, 120, 50);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chon(table, playerList );
            }
        });
        panel.add(selectButton);

        // Add "Back" button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(new Color(255, 182, 193));
        backButton.setBounds(350, 350, 120, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View_BanHuanLuyen.hien();
                frame.dispose();
            }
        });
        panel.add(backButton);

        // Nút up csv
        JButton loadCsvButton = new JButton("Update");
        loadCsvButton.setFont(new Font("Arial", Font.BOLD, 18));
        loadCsvButton.setBackground(new Color(255, 182, 193));
        loadCsvButton.setBounds(550, 350, 120, 50);
        loadCsvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load2("src/project_do_an_co_so/CSV");
            }
        });
        panel.add(loadCsvButton);

        return panel;
    }

    private static void loadCSV(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Clear the previous list
            playerList.clear();
            tableModel.setRowCount(0);

            // Read the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 5) {
                    continue; // Skip if there are not enough values
                }

                String name = values[0];
                String hometown = values[1];
                String birthDate = values[2];
                String numberShirt = values[3];
                String position = values[4];
                String weight = values[5];
                String height = values[6];
                String bodyMass = values[7];

                Player player = new Player(name, hometown, birthDate, numberShirt, position, weight, height, bodyMass);
                playerList.add(player);
                tableModel.addRow(new Object[]{name});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void set() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hien();
            }
        });
    }
}

