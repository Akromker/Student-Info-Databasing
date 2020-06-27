/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class Student {

    private String name, Sname, DOB, gender, parent;
    private int grade;

    public Student(String name, String Sname, String dateOfBirth, String gender, int grade) {
        this.name = name;
        this.Sname = Sname;
        this.DOB = dateOfBirth;
        this.gender = gender;
        this.grade = grade;
    }

    public void printDetails() {
        System.out.println("Name: " + name + "\nSurname: " + Sname + "\nDOB: "
                + DOB + "\ngender: " + gender + "\ngrade: " + String.valueOf(grade));
    }

    public String getName() {
        return name;
    }

    public String getSname() {
        return Sname;
    }

    public String getDOB() {
        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setParent(String newP) {
        parent = newP;
    }

    public String getParent() {
        return parent;
    }

    public static void addStudent(Student student) {
        try {
            //Load the driver using Class.forName construct and establish connection
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "Administrator", "root");
            Statement stmt = con.createStatement();

            String query = "INSERT INTO mydb.children (Name,Surname,Gender,Grade, Parent) VALUES ('" + student.getName() + "', '"
                    + student.getSname() + "','" + student.getGender() + "', '" + student.getGrade() + "', '" + student.getParent() + "')";

            int rs = stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successfully added student to database");

            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("- Failed to make connection to database");
        } catch (SQLException ex) {
            System.out.println("- Failed to add student to database");
        }
    }
}
