/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_do_an_co_so.BanHuyenLuyen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class View_BHL_BXH {
    static String[][] rankingData = {
            {"1", "Manchester City", "38", "27", "5", "6", "86"},
            {"2", "Manchester United", "38", "21", "11", "6", "74"},
            {"3", "Liverpool", "38", "20", "9", "9", "69"},
            {"4", "Chelsea", "38", "19", "10", "9", "67"},
            {"5", "Leicester City", "38", "20", "6", "12", "66"}
    };

    // Tên cột của bảng xếp hạng
    static String[] columnNames = {"#", "Đội bóng", "Số trận", "Thắng", "Hòa", "Thua", "Điểm"};

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> View_BHL_BXH.hien());
    }

    public static void hien() {
        // Tạo cửa sổ mới để hiển thị bảng xếp hạng
        JFrame rankingFrame = new JFrame("Bảng xếp hạng");
        rankingFrame.setSize(600, 400);

        // Tạo bảng xếp hạng
        JTable table = new JTable(rankingData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Thêm bảng vào cửa sổ
        rankingFrame.add(scrollPane, BorderLayout.CENTER);

        // Hiển thị cửa sổ bảng xếp hạng
        rankingFrame.setVisible(true);
    }
}
