package com.jobportal.dao;

import com.jobportal.model.*;
import java.sql.*;

public class UserDAO {
    public static int registerUser(User u) throws SQLException {
        String sql = "INSERT INTO users (role,name,address,email,phone,password,extra_json) VALUES (?,?,?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection(); 
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
        {
            ps.setString(1, u.getRole());
            ps.setString(2, u.getName());
            ps.setString(3, u.address);
            ps.setString(4, u.getEmail());
            ps.setString(5, u.phone);
            ps.setString(6, u.getPassword());
            ps.setString(7, "{}");
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) 
            {
                if (rs.next()) return rs.getInt(1);
            }package com.jobportal.dao;

import com.jobportal.model.*;
import java.sql.*;

public class UserDAO {
    public static int registerUser(User u) throws SQLException {
        String sql = "INSERT INTO users (role,name,address,email,phone,password,extra_json) VALUES (?,?,?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getRole());
            ps.setString(2, u.getName());
            ps.setString(3, u.address);
            ps.setString(4, u.getEmail());
            ps.setString(5, u.phone);
            ps.setString(6, u.getPassword());
            ps.setString(7, "{}");
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return -1;
        }
    }

    public static User login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email); ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    int id = rs.getInt("userid");
                    String name = rs.getString("name");
                    String addr = rs.getString("address");
                    String ph = rs.getString("phone");
                    if ("SEEKER".equals(role)) return new JobSeeker(id, name, addr, email, ph, password);
                    if ("EMPLOYER".equals(role)) return new Employer(id, name, addr, email, ph, password, "", "", "");
                    return new Admin(id, name, addr, email, ph, password);
                }
            }
        }
        return null;
    }
}


            return -1;
        }
    }

    public static User login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection c = DBConnection.getConnection(); 
            PreparedStatement ps = c.prepareStatement(sql)) 
        {
            ps.setString(1, email); ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    int id = rs.getInt("userid");
                    String name = rs.getString("name");
                    String addr = rs.getString("address");
                    String ph = rs.getString("phone");
                    
                    if ("SEEKER".equals(role)) 
                        return new JobSeeker(id, name, addr, email, ph, password);
                    if ("EMPLOYER".equals(role)) 
                        return new Employer(id, name, addr, email, ph, password, "", "", "");
                    return new Admin(id, name, addr, email, ph, password);
                }
            }
        }
        return null;
    }
}

