package dao;

import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.util.ArrayList;

public class UserDao {
//sign up
    public static void save(User user) {
     String query = "INSERT INTO user(name, dob, mobileNumber, email, userName, password, address, securityQuestion, answer) VALUES ('" + user.getName() + "','" + user.getDob() + "','" + user.getMobileNumber() + "','" + user.getEmail() + "','" + user.getUserName() + "','" + user.getPassword() + "','" + user.getAddress() + "','" + user.getSecurityQuestion() + "','" + user.getAnswer() + "')";
        DbOperations.setDataOrDelete(query, "Registered Successfully");
    }

    //login
    public static User login(String email, String password) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("select *from user where email = '" + email + "' and password = '" + password + "'");
            while (rs.next()) {
                user = new User();
                // user.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }
//change password
//    public static void update(String email, String newPassword) {
//        String query = "update user set password = '" + newPassword + "' where email = '" + email + "'";
//        DbOperations.setDataOrDelete(query, "Password changed Successfully");
//    }
//
//    public static void changePassword(String email, String oldPassword, String newPassword) {
//        try {
//            ResultSet rs = DbOperations.getData("select *from user where email = '" + email + "' and password = '" + oldPassword + "'");
//            if (rs.next()) {
//                update(email, newPassword);
//            } else {
//                JOptionPane.showMessageDialog(null, "Old Password is wrong");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
////view user
//    public static ArrayList<User> getAllRecords(String email) {
//        ArrayList<User> arrayList = new ArrayList<>();
//        try {
//            ResultSet rs = DbOperations.getData("select *from user where email like '%" + email + "%'");
//            //ResultSet rs = DbOperations.getData("select *from user");
//            while (rs.next()) {
//                User user = new User();
//                user.setId(rs.getInt("id"));
//                user.setName(rs.getString("name"));
//                user.setEmail(rs.getString("email"));
//                user.setMobileNumber(rs.getString("mobileNumber"));
//                user.setAddress(rs.getString("address"));
//                arrayList.add(user);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        return arrayList;
//    }
//    //update user
//    public static void delete(String email) {
//        String query = "delete from user where email = '" + email + "'";
//        DbOperations.setDataOrDelete(query, " deleted Successfully");
//    }

    public static User getSecurityQuestion(String email) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("select *from user where email = '" + email + "'");
            while (rs.next()) {
                user = new User();
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static void update(String email, String newPassword) {
        String query = "update user set password = '" + newPassword + "' where email = '" + email + "'";
        DbOperations.setDataOrDelete(query, "Password changed Successfully");
    }

    public static boolean isUserNameTaken(String userName) {
        boolean isTaken = false;
        try (ResultSet rs = DbOperations.getData("select *from user where userName = '" + userName + "'")) {
            if (rs.next()) {
                isTaken = true;
            }
        } catch (SQLException e) {
            // Handle database errors
            e.printStackTrace();
        }
        return isTaken;
    }

}
