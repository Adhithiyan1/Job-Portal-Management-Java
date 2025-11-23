package com.jobportal.util;

import java.util.Scanner;

public class InputUtil 
{
    private static final Scanner sc = new Scanner(System.in);
    public static String readLine(String prompt) 
    {
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }
    public static int readInt(String prompt) 
    {
        while (true) 
        {
            try 
            { 
                return Integer.parseInt(readLine(prompt)); 
            }
            catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
        }
    }
}
