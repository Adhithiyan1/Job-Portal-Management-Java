package com.jobportal;

import com.jobportal.util.InputUtil;
import com.jobportal.model.*;
import com.jobportal.dao.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static User currentUser = null;

    public static void main(String[] args) 
    {
        System.out.println("--- Welcome to JobPortal Console App ---");
        while (true) 
        {
            System.out.println("Select role: 1) Job Seeker  2) Employer  3) Admin  0) Exit");
            int r = InputUtil.readInt("Choice");
            try 
            {
                if (r==0) 
                    { 
                        System.out.println("Bye"); break;
                    }
                handleRoleChoice(r);
            } catch (Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static void handleRoleChoice(int r) throws SQLException 
    {
        switch (r) 
        {
            case 1: roleFlow("SEEKER"); 
                break;
            case 2: roleFlow("EMPLOYER"); 
                break;
            case 3: roleFlow("ADMIN"); 
                break;
            default: System.out.println("Invalid");
        }
    }

    private static void roleFlow(String role) throws SQLException 
    {
        System.out.println("1) Register 2) Login 0) Back");
       
        int c = InputUtil.readInt("Choice");
        
        if (c==0) 
            return;
        if (c==1) 
            { register(role); }
        if (c==2) 
            { login(role); }
    }

    private static void register(String role) {
        try {
            String name = InputUtil.readLine("Name");
            String addr = InputUtil.readLine("Address");
            String email = InputUtil.readLine("Email");
            String phone = InputUtil.readLine("Phone");
            String pass = InputUtil.readLine("Password");
            
            User u = null;
            
            if ("SEEKER".equals(role)) u = new JobSeeker(0, name, addr, email, phone, pass);
            
            else if ("EMPLOYER".equals(role)) 
            {
                String pocName = InputUtil.readLine("Point of Contact Name");
                String pocEmail = InputUtil.readLine("POC Email");
                String pocPhone = InputUtil.readLine("POC Phone");
                u = new Employer(0, name, addr, email, phone, pass, pocName, pocEmail, pocPhone);
            } else u = new Admin(0, name, addr, email, phone, pass);

            int id = UserDAO.registerUser(u);
            System.out.println("Registered with id: " + id);
        } catch (SQLException e) 
        {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void login(String role) {
        try 
        {
            String email = InputUtil.readLine("Email");
            String pass = InputUtil.readLine("Password");
            User u = UserDAO.login(email, pass);
            if (u==null || !u.getRole().equals(role)) 
                { 
                    System.out.println("Invalid credentials or role mismatch"); 
                    return; 
                }
            
            currentUser = u;
            
            System.out.println("Welcome " + u.getName() + " (" + u.getRole() + ")");
            
            if (u.getRole().equals("SEEKER")) 
                seekerMenu((JobSeeker)u);
            else if (u.getRole().equals("EMPLOYER")) 
                employerMenu((Employer)u);
            else adminMenu((Admin)u);
        } catch (SQLException e) 
       
        { 
            System.out.println("Login error: " + e.getMessage()); 
        }
    }

    private static void seekerMenu(JobSeeker s) 
    {
        while (true) 
        {
            System.out.println("1) Browse Jobs 2) Edit Profile 3) View Applications 0) Logout");
            int c = InputUtil.readInt("Choice");
            if (c==0) 
                { 
                    currentUser = null; 
                    break; 
                }
            
            try 
            {
                if (c==1) 
                    browseJobsAndApply(s);
                if (c==2) 
                    System.out.println("Edit profile - feature stub (update DB)");
                if (c==3) 
                    System.out.println("View applications - feature stub (query DB)");
            } catch (Exception e) 
            { 
                System.out.println("Error: " + e.getMessage()); 
            }
        }
    }

    private static void browseJobsAndApply(JobSeeker s) throws SQLException, IOException 
    {
        String k = InputUtil.readLine("Search by job title (keyword)");
        List<Job> list = JobDAO.searchByTitle(k);
        if (list.isEmpty()) 
            { 
                System.out.println("No jobs"); 
                return; 
            }
        for (Job j : list) 
            System.out.println(j.getJobId() + ": " + j.getTitle() + " [" + j.getType() + "]");
        int id = InputUtil.readInt("Enter job id to view/apply (0 to cancel)");
        
        if (id==0) 
            return;
        Job job = JobDAO.getById(id);
        
        if (job==null) 
            { 
                System.out.println("Job not found"); 
                return; 
            }
        System.out.println("Title: " + job.getTitle());
        System.out.println("JD: " + job.jd);
        System.out.println("1) Apply 0) Back");
        
        int c = InputUtil.readInt("Choice");
        
        if (c==1) 
        {
            System.out.println("Paste resume text (will be saved to file):");
            String resumeText = InputUtil.readLine("Resume (single line or base64 not required)");
            String dir = "resumes";
            new File(dir).mkdirs();
            String path = dir + File.separator + "resume_user_" + s.getUserId() + "_job_" + job.getJobId() + ".txt";
            
            try (FileOutputStream fos = new FileOutputStream(path)) 
            {
                fos.write(resumeText.getBytes());
            }
            int appId = ApplicationDAO.apply(job.getJobId(), s.getUserId(), path);
            System.out.println("Applied. Application id: " + appId);
        }
    }

    private static void employerMenu(Employer e) 
    {
        while (true) {
            System.out.println("1) Add Job 2) Edit Profile 3) View Jobs 0) Logout");
            int c = InputUtil.readInt("Choice");
            if (c==0) 
                { 
                    currentUser=null; 
                    break; 
                }
            try 
            {
                if (c==1) 
                    addJob(e);
                if (c==2) 
                    System.out.println("Edit profile - feature stub");
                if (c==3) 
                    System.out.println("View jobs - feature stub (query DB)");
            } catch (Exception ex) 
            { 
                System.out.println("Err: " + ex.getMessage()); 
            }
        }
    }

    private static void addJob(Employer e) throws SQLException 
    {
        String title = InputUtil.readLine("Job title");
        String jd = InputUtil.readLine("Job description");
        String pay = InputUtil.readLine("Pay");
        String type = InputUtil.readLine("Type (REMOTE/ONSITE/HYBRID)");
        Job job = new Job(0, e.getUserId(), title, jd, pay, type);
        
        int id = JobDAO.addJob(job);
        
        System.out.println("Added job id: " + id);
    }

    private static void adminMenu(Admin a) 
    {
        while (true) 
        {
            System.out.println("1) Create Admin Account (stub) 2) View All Users 0) Logout");
            
            int c = InputUtil.readInt("Choice");
            
            if (c==0) 
                { 
                    currentUser=null; 
                    break; 
                }
            
            if (c==1) 
                System.out.println("Create admin - use register flow with ADMIN role");
            if (c==2) 
                System.out.println("View users - feature stub (query DB)");
        }
    }
}
