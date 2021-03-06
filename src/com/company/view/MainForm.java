package com.company.view;

import com.company.dao.StudentDAO;
import com.company.domain.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class MainForm {
    private JPanel mainPanel;
    private JPanel toolbarPanel;
    private JButton deleteButton;
    private JScrollPane scrollPanel;
    private JTable studentTable;

    public MainForm() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                StudentDAO studentDAO = new StudentDAO();

                int[] rowIndexes = studentTable.getSelectedRows();
                for (int i : rowIndexes) {
                    int studentId = (int) studentTable.getModel().getValueAt(i, 0);
                    studentDAO.delete(studentId);
                }

                try {
                    loadTableData();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                try {
                    loadTableData();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
    }

    private void loadTableData() throws SQLException {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getAll();
        StudentTableModel studentTableModel = new StudentTableModel(students);
        studentTable.setModel(studentTableModel);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setFocusable(true);
        toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.add(toolbarPanel, BorderLayout.WEST);
        deleteButton = new JButton();
        deleteButton.setPreferredSize(new Dimension(78, 30));
        deleteButton.setText("Delete");
        toolbarPanel.add(deleteButton);
        scrollPanel = new JScrollPane();
        scrollPanel.setHorizontalScrollBarPolicy(31);
        mainPanel.add(scrollPanel, BorderLayout.CENTER);
        studentTable = new JTable();
        scrollPanel.setViewportView(studentTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
