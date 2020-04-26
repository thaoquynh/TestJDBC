package com.company.view;

import com.company.dao.StudentDAO;
import com.company.domain.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainView {
    JFrame frame;
    JTable table;

    public MainView() throws SQLException {
        frame = new JFrame();
        table = new JTable();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.X_AXIS));

        JButton deleteButton = createDeleteButton();
        toolbarPanel.add(deleteButton);
        mainPanel.add(toolbarPanel);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane);

        frame.add(mainPanel);
    }

    public void show() throws SQLException {
        loadTableData();
        frame.setSize(500, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private JButton createDeleteButton() {
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                StudentDAO studentDAO = new StudentDAO();

                int[] rowIndexes = table.getSelectedRows();
                for (int i : rowIndexes) {
                    int studentId = (int)table.getModel().getValueAt(i, 0);
                    studentDAO.delete(studentId);
                }

                try
                {
                    loadTableData();
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        return deleteButton;
    }

    private void loadTableData() throws SQLException {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAll();
        StudentTableModel studentTableModel = new StudentTableModel(students);
        table.setModel(studentTableModel);
    }
}
