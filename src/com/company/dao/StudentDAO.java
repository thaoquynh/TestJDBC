package com.company.dao;

import com.company.common.DatabaseConstants;
import com.company.domain.Student;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class StudentDAO {
    public ArrayList<Student> getAll() throws SQLException {

        // Vì câu này sẽ trả về nhiều student trong database, mình cần 1 cái list
        ArrayList<Student> students = new ArrayList<Student>();
        // file -> project structure -> libraries -> bấm dấu + để tìm đến thư viện

        try {
            OracleConnection connection = getConnection();
            // Sau khi kết nối nó có rồi, mình có thể dùng kết nối đó để tạo 1 câu SQL (statement)
            Statement statement = connection.createStatement();
            // Với cái statement này, mình sẽ viết câu truy vấn ời quăng dô cho nó chạy
            String query = "SELECT STUDENTID, NAME, PHONE FROM STUDENT";
            // Ời, thả query dô, thì nó sẽ trả vè 1 cái ResultSet chưa kết quả á
            ResultSet resultSet = statement.executeQuery(query);
            // Oki, giờ mình sẽ tạo 1 object Student để chưa kết quả nó trả dề nha
            // Mình sẽ lặp trên cái result set, hàm next của nó sẽ ra true nếu nó còn data, và mình lần lượt
            // add dô list
            while (resultSet.next()) {
                Student student = new Student();
                // Truyền tên cột tương ứng để lấy giá trị nha
                // Chú ý là nếu cột đó là Date / Varchar...thì mình phải dùng hàm getXXX tương ứng
                student.StudentId = resultSet.getInt("STUDENTID");
                student.Name = resultSet.getString("NAME");
                student.Phone = resultSet.getString("PHONE");
                students.add(student);
            }
            // Xài xong phải đóng connection
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return students;
    }


    public boolean delete(int id) {
        boolean deleteResult = false;
        try
        {
            OracleConnection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM STUDENT WHERE STUDENTID = ?");
            preparedStatement.setInt(1, id);
            deleteResult = preparedStatement.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return deleteResult;
    }

    private OracleConnection getConnection() throws SQLException {
        // Khởi tạo 1 đối tượng Properties để chứa mấy thông số trên kia
        Properties properties = getConnectionProperties();

        // Khỏi tạo 1 đối tượng OracleDataSource
        // Nó sẽ nhận vô mí thông số trên kia để chỉ cho cái thư viện biết là connect dô đâu
        // Mình sẽ set mí thông số khởi tạo trên kia dô nó
        OracleDataSource oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL(DatabaseConstants.DB_URL);
        oracleDataSource.setConnectionProperties(properties);

        // Sau khi khởi tạo, giờ mình có thể mở 1 kết nối
        OracleConnection connection = (OracleConnection) oracleDataSource.getConnection();

        return connection;
    }

    private Properties getConnectionProperties() {
        Properties properties = new Properties();
        // Này nghĩa là: "user" tương ứng dới "test" (để con chuột dô CONNECTION_PROPERTY_USER_NAME
        // Ctrl Alt B để coi CONNECTION_PROPERTY_USER_NAME thiệt sự nó là gì)
        properties.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DatabaseConstants.DB_USER);
        properties.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DatabaseConstants.DB_PASSWORD);

        return properties;
    }
}
