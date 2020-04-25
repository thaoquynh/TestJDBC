package com.company.view;

import com.company.domain.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// Gíup xác định cấu trúc bảng: có cột tên gì, ở mỗi ô hiển thị dữ liệu nào
public class StudentTableModel extends AbstractTableModel {
    private List<Student> students;
    private String[] columns;

    public StudentTableModel(List<Student> students) {
        super();
        this.students = students;
        // Bảng sẽ có 3 cột này
        columns = new String[] { "Id", "Name", "Phone" };
    }

    // Thí dụ cột 0 thì tên sẽ là "Id"
    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    // Xác định giá trị của ô
    // Ví dụ, truyền 1, 1 vô
    // Thì row #1 sẽ là dòng thứ 2
    // col 1 sẽ là cột Name
    // -> lấy ra Name của Student thứ 2 trong dnah sách
    @Override
    public Object getValueAt(int row, int col) {
        Student student = students.get(row);
        switch (col) {
            case 0:
                return student.StudentId;
            case 1:
                return student.Name;
            case 2:
                return student.Phone;
            default:
                return null;
        }
    }
}
