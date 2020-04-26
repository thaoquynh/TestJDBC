package com.company;

import com.company.dao.StudentDAO;
import com.company.domain.Student;
import com.company.view.MainForm;
import com.company.view.MainView;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {
//        MainView mainView = new MainView();
//        mainView.show();
        MainForm mainForm = new MainForm();
    }
}
