package com.jobportal.dao;

import com.jobportal.model.Job;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    public static int addJob(Job j) throws SQLException {
        String sql = "INSERT INTO jobs (employer_id,title,jd,pay,type) VALUES (?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection(); 
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, j.getEmployerId());
            ps.setString(2, j.getTitle());
            ps.setString(3, j.getJd());
            ps.setString(4, j.getPay());
            ps.setString(5, j.getType());
            
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { 
                if (rs.next()) return rs.getInt(1); 
            }
            return -1;
        }
    }

    public static List<Job> searchByTitle(String keyword) throws SQLException {
        String sql = "SELECT * FROM jobs WHERE title LIKE ?";
        List<Job> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); 
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Job(
                        rs.getInt("jobid"), 
                        rs.getInt("employer_id"), 
                        rs.getString("title"), 
                        rs.getString("jd"), 
                        rs.getString("pay"), 
                        rs.getString("type")
                    ));
                }
            }
        }
        return list;
    }

    public static Job getById(int id) throws SQLException {
        String sql = "SELECT * FROM jobs WHERE jobid=?";
        try (Connection c = DBConnection.getConnection(); 
             PreparedStatement ps = c.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new Job(
                    rs.getInt("jobid"), 
                    rs.getInt("employer_id"), 
                    rs.getString("title"), 
                    rs.getString("jd"), 
                    rs.getString("pay"), 
                    rs.getString("type")
                );
            }
        }
        return null;
    }
}