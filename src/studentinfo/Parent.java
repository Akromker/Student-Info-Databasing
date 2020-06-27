/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentinfo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class Parent {

    private String name, Sname, Contact, Address;
    private int numberOfchildren;

    public Parent(String fName, String fSname, String fContact, String fAddress, int children) {
        name = fName;
        Sname = fSname;
        Contact = fContact;
        Address = fAddress;
        numberOfchildren = children;
    }

    public void printDetails() {
        System.out.println("Name: " + name + "\nSurname: " + Sname + "\nContact Details: "
                + Contact + "\nAddress: " + Address + "\nchildren:  " + numberOfchildren);
    }

    public String getName() {
        return name;
    }

    public String getSname() {
        return Sname;
    }

    public String getContact() {
        return Contact;
    }

    public String getAddress() {
        return Address;
    }

    public int getChildren() {
        return numberOfchildren;
    }

    public void addParent(Parent parent) {
        try {
            //Load the driver using Class.forName construct and establish connection
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "Administrator", "root");

            Statement stmt = con.createStatement();
            String query = 
"INSERT INTO mydb.parents (Name,Surname,Contact,Address,Children) VALUES ('"+
                    parent.getName() + "', '"+
                    parent.getSname() + "', '"+
                    parent.getContact() + "', '"+
                    parent.getAddress() + "', '"+
                    parent.getChildren() + "')";

            int rs = stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successfully added parent to database");

            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("- Failed to make connection to database(Error: check connection)");
        } catch (SQLException ex) {
            Logger.getLogger(Parent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
