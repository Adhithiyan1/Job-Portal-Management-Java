package com.jobportal.dao;

import java.sql.*;
import com.jobportal.model.JobApplication;

public class ApplicationDAO 
{
    public static int apply(int jobId, int seekerId, String resumePath) throws SQLException 
    {
        String sql = "INSERT INTO applications (jobid,seeker_id,resume_path) VALUES (?,?,?)";
        try (Connection c = DBConnection.getConnection(); 
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
        {
            ps.setInt(1, jobId); ps.setInt(2, seekerId); ps.setString(3, resumePath);
            ps.executeUpdate(); try (ResultSet rs = ps.getGeneratedKeys()) 
            { 
                if (rs.next()) 
                    return rs.getInt(1); 
            }
            return -1;
        }
    }

    public static void updateStage(int appId, String stage) throws SQLException 
    {
        String sql = "UPDATE applications SET stage=? WHERE appid=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) 
        {
            ps.setString(1, stage); ps.setInt(2, appId); ps.executeUpdate();
        }
    }
}

