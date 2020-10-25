package com.example.JUnitTest.config.controller;

import com.example.JUnitTest.config.domain.User;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.UserException;
import org.postgresql.Driver;
import org.postgresql.core.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationControllerTest {

    @Test
    void UsernameTrue() {
        User user1 = new User();
        user1.setUsername("Kolivan-300%");
        user1.setPassword("123");
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        boolean flag = user1.getUsername().matches(pattern);
        assertTrue(flag);

    }

    @Test
    void PasswordTrue() {
        User user1 = new User();
        user1.setUsername("kolivan");
        user1.setPassword("-300K#");
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        boolean flag = user1.getPassword().matches(pattern);
        assertTrue(flag);

    }

    @Test
    void UsernameFalse() {
        User user1 = new User();
        user1.setUsername("kolivan");
        user1.setPassword("123");
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        boolean flag = user1.getUsername().matches(pattern);
        assertFalse(flag);

    }
    @Test
    void PasswordFalse() {
        User user1 = new User();
        user1.setUsername("kolivan");
        user1.setPassword("123");
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        boolean flag = user1.getPassword().matches(pattern);
        assertFalse(flag);

    }

    @Test
    void BDAndUserExistingTrue() throws SQLException {
        User user1 = new User();
        user1.setUsername("Kolivan-300%");
        user1.setPassword("-300K#");
        String login = user1.getUsername();
        String password = user1.getPassword();

        String url = "jdbc:postgresql://localhost/userss";
        try {
            DriverManager.registerDriver(new Driver());
            Connection conn = DriverManager.getConnection(url,"postgres", "123");
            Statement stat = conn.createStatement();

            Statement stH2 = (Statement) conn.createStatement();
            String insert = "SELECT * FROM usr WHERE username = '"+login+"' and password = '"+password+"'";


            boolean flag1 = (stH2.executeQuery(insert)!=null);
            assertTrue(flag1);




        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    @Test
    void BDAndUserExistingFalse() throws SQLException {
        User user1 = new User();
        user1.setUsername("KROL");
        user1.setPassword("322");
        String login = user1.getUsername();
        String pas = user1.getPassword();

        String url = "jdbc:postgresql://localhost/userss";
        try {
            DriverManager.registerDriver(new Driver());
            Connection conn = DriverManager.getConnection(url,"postgres", "123");
            Statement stat = conn.createStatement();

            Statement stH2 = (Statement) conn.createStatement();
            String insert = "SELECT * FROM usr WHERE username = '"+login+"' and password = '"+pas+"'";


            boolean flag1 = (stH2.executeQuery(insert)!=null);
            assertFalse(flag1);




        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }
}
