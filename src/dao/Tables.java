package dao;

import javax.swing.JOptionPane;
import java.sql.*;

public class Tables {

    public static void main(String[] args) {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            String appuser = "CREATE TABLE user (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(200), dob VARCHAR(50), mobileNumber VARCHAR(11), email VARCHAR(200), userName VARCHAR(50), password VARCHAR(50), address VARCHAR(200), securityQuestion VARCHAR(200), answer VARCHAR(200), UNIQUE(email))";
            String admindetails = "insert into user(name,dob,mobileNumber,email,userName,password,address,securityQuestion,answer) values('Abdul Kuddus','12-01-1980','01678954685','kuddus@gmail.com','Admin','kuddus','Dhaka','What is your hobby?','Eating')";
            DbOperations.setDataOrDelete(appuser, "User Table Created Succesfully");
             DbOperations.setDataOrDelete(admindetails, "Admin details added Succesfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
