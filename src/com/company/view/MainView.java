package com.company.view;

import com.company.dao.StudentDAO;
import com.company.domain.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainView {
    JFrame frame;

    public MainView() {
        frame = new JFrame();
    }

    public void show() throws SQLException {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAll();
        StudentTableModel studentTableModel = new StudentTableModel(students);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.X_AXIS));

        JButton deleteButton = new JButton("Delete");
        toolbarPanel.add(deleteButton);
        mainPanel.add(toolbarPanel);

        JTable table = new JTable(studentTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane);

        frame.add(mainPanel);

        frame.setSize(500, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
