/* File is included in source package */
package source;


/*
 * To change this license header, choose Licensce Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection; /*import java sql class to establish connection*/
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Srinath
 */
public class Add_dao {  /*public class which establish database connections.*/

    static Connection con; /*creating static con object */

    public Add_dao() { /*default constructor for public class whic makes secure database connection*/
        try {

            con = null; /* initializing object*/
            Class.forName("com.mysql.jdbc.Driver"); /*loading class into memory*/

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flogin", "root", ""); /*using driver manager class and get connection method to establisg connection by passing required parameters called as protocdol*/

            System.out.println("Connection establist");
        } //jdbc:mysql://localhost:3306/mysql [root on Default schema]
        catch (Exception e) { /*to catch exception*/
            System.out.println(e);
        }

    }
 /*Public class to store details of Seeker*/
    public int seekerDetails(String fname, String lname, String pnum, String eml, String st, String city, String state, String zip, String pwd) {
        System.out.println("****************************************************");
        int i = 0;
        String em = eml;/* initializing object*/

        try {
            /* creating object which takes sql query as parameter add pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO seeker_info values(null,?,?,?,?,?,?,?,?,?)");
//            pstmt.setInt(1,s_id);
            pstmt.setString(1, fname);/* Set strings*/
            pstmt.setString(2, lname);
            pstmt.setString(3, pnum);
            pstmt.setString(4, eml);
            pstmt.setString(5, st);
            pstmt.setString(6, city);
            pstmt.setString(7, state);
            pstmt.setString(8, zip);
            pstmt.setString(9, pwd);
            i = pstmt.executeUpdate();/* set strings*/
            System.out.println("Added Successfully!");/* if added to database print success msg in console*/
        } catch (Exception e) {
            System.out.println(e);/*catch exception */
            e.printStackTrace();
        }
        System.out.println("value of i before return \t****" + i);
        System.out.println("****************************************************");
        return i;
    }
/* public class which store details of landlord*/
    public int landlordDetails(String fname, String lname, String pnum, String eml, String st, String city, String state, String zip, String pwd) {
        System.out.println("****************************************************");
        int i = 0;
        String em = eml;
/* initializing object*/

        try {
            /* creating object which takes sql query as parameter and pass to db after executing the query*/

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO landlord_info values(null,?,?,?,?,?,?,?,?,?)");
//            pstmt.setInt(1,s_id);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, pnum);
            pstmt.setString(4, eml);
            pstmt.setString(5, st);
            pstmt.setString(6, city);
            pstmt.setString(7, state);
            pstmt.setString(8, zip);
            pstmt.setString(9, pwd);
            i = pstmt.executeUpdate();
            System.out.println("Added Successfully!");/*if added to database print success msg in console*/
        } catch (Exception e)/*catch exception when occurs*/ {
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println("value of i before return \t****" + i);
        System.out.println("****************************************************");
        return i;
    }
/* public class which store details of property information*/
    public int propertyInfo(String p_type, String p_no_property,String p_brooms,
            String p_address, String p_zip, String p_rent, String p_area, String p_filename, String landlord_eml) {
        System.out.println("****************************************************");
        int i = 0;
        try {
            /* creating object which takes sql query as parameter and pass to db after executing*/

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO property_info values(null,?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, p_type);/*Setting the values*/
            pstmt.setString(2, p_no_property);
            pstmt.setString(3, p_brooms);
            pstmt.setString(4, p_address);
            pstmt.setString(5, p_zip);
            pstmt.setString(6, p_rent);
            pstmt.setString(7, p_area);
            pstmt.setString(8, p_filename);
            pstmt.setString(9, landlord_eml);

            i = pstmt.executeUpdate();/* excuting sql query */
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("value of i \t****" + i);
        System.out.println("****************************************************");
        return i;

    }
/* public class which checks weather seeker email is apready in the database or not*/
    public boolean checkValidity(String email) {
        boolean flag = false;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = con.prepareStatement("select * from seeker_info where email=?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
/* public class which checks weather landlord email is apready in the database or not*/
    public boolean l_checkValidity(String email) {
        boolean flag = false;
        ResultSet rs = null;
        try {
            
 /* creating prepare statement object which takes sql query as parameter add pass to db after executing*/

            PreparedStatement pstmt = con.prepareStatement("select * from landlord_info where email=?");
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();/* excuting sql query */
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;/* return true or false*/
    }
/* public class which checks weather  email is registered or not*/
   
    public boolean logins_checkValidity(String email, String password) {
        boolean flag = false;
        ResultSet rs = null;
        try {
            /* creating prepare statement object which takes sql query as parameter add pass to db after executing*/

            PreparedStatement pstmt = con.prepareStatement("select email, password from seeker_info");
//                    pstmt.setString(1,email);
//                    pstmt.setString(2,password);
            System.out.println("****************************************************");
            /* Execute the query and return the result to the result set object*/
            rs = pstmt.executeQuery();
            /* excuting sql query */
            while (rs.next()) {
                String email1 = rs.getString("email");
                String password1 = rs.getString("password");
                
            /* check condition if entered email is same in the database or not. */
                if ((email.equals(email1)) && (password.equals(password1))) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
            /* public class which checks weather  email is registered or not*/
    public boolean loginl_checkValidity(String email, String password) {
        boolean flag = false;
        ResultSet rs = null;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("select email, password from landlord_info");
//                    pstmt.setString(1,email);
//                    pstmt.setString(2,password);
            System.out.println("****************************************************");
             /* Execute the query and return the result to the result set object*/
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String email1 = rs.getString("email");
                String password1 = rs.getString("password");
                if ((email.equals(email1)) && (password.equals(password1))) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public String lforgotPassword(String email) {
        String password = "no";
        boolean flag = false;
        ResultSet rs = null;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("select email,password from landlord_info where email=?");
             /*Setting the values*/
            pstmt.setString(1, email);
             /* Execute the query and return the result to the result set object*/
            rs = pstmt.executeQuery();
            if (rs.next()) {

                password = rs.getString("password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("value of i \t****");
        System.out.println("*****************************pass for l**********");
        return password;

    }

    public String sforgotPassword(String email) {
        String password = "no";
        boolean flag = false;
        ResultSet rs = null;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("select email,password from seeker_info where email=?");
             /*Setting the values*/
            pstmt.setString(1, email);
             /* Execute the query and return the result to the result set object*/
            rs = pstmt.executeQuery();
            if (rs.next()) {

                password = rs.getString("password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("value of i \t****");
        System.out.println("*****************************pass for l**********");
        return password;

    }

    public int propertyInfoUpdate(String p_type, String p_no_property,String p_brooms,
            String p_address, String p_zip, String p_rent, String p_area, String p_img, int p_id) {
        /*System.out.println("****************************************************");
        System.out.println(p_id+"****************");
                 System.out.println(p_type);
                   System.out.println(p_no_property);
                   System.out.println(p_address);
                   System.out.println(p_zip); 
                   System.out.println(p_rent); 
                   System.out.println(p_area); 
                   System.out.println(p_filename); */
        int i = 0;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("UPDATE property_info set p_no_property=?,p_brooms=?,p_address=?,p_zip=?,p_rent=?,p_area=?,p_img=?,p_type=? WHERE p_id=?");
             /*Setting the values*/
            pstmt.setString(1, p_no_property);
            pstmt.setString(2, p_brooms);
            pstmt.setString(3, p_address);
            pstmt.setString(4, p_zip);
            pstmt.setString(5, p_rent);
            pstmt.setString(6, p_area);
            pstmt.setString(7, p_img);
            pstmt.setString(8, p_type);
            pstmt.setInt(9, p_id);
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("value of i \t****" + i);
        System.out.println("****************************************************");
        return i;

    }

    public int propertyInfoDelete(int p_id) {
        System.out.println("****************************************************");
        System.out.println(p_id + "****************");

        int i = 0;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM property_info  WHERE p_id=?");
            /*Setting the values*/
            pstmt.setInt(1, p_id);
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("value of i \t****" + i);
        System.out.println("****************************************************");
        return i;

    }

    public String getLand_eml(int p_id) {

        String landlord_eml = "no";
        java.sql.ResultSet rs = null;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("select * from property_info where p_id=?");
             /*Setting the values*/
            pstmt.setInt(1, p_id);
             /* Execute the query and return the result to the result set object*/
            rs = pstmt.executeQuery();
            if (rs.next()) {
                landlord_eml = rs.getString("landlord_eml");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return landlord_eml;
    }

//     
//        public int propertyDocUpload()
//                 
//         {
//        System.out.println("****************************************************");
//        int i = 0;
//        try {
//            PreparedStatement pstmt = con.prepareStatement("INSERT INTO property_info values(null,?,?,?,?,?,?,?,?)");
//            
//            pstmt.setString(1, p_type);
//            pstmt.setString(2, p_no_property);
//            pstmt.setString(3, p_address);
//            pstmt.setString(4, p_zip);
//            pstmt.setString(5, p_rent);
//            pstmt.setString(6, p_area);
//            pstmt.setString(7, p_filename);
//            pstmt.setString(8, landlord_eml);
//           
//            
//            i = pstmt.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println("value of i \t****" + i);
//        System.out.println("****************************************************");
//        return i;
//
//    }
    public boolean updatePassword(String email, String password) {
        boolean flag = false;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("update seeker_info set password=? where email=?");
             /*Setting the values*/
            pstmt.setString(1, password);
            pstmt.setString(2, email);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            } else {
                 /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
                PreparedStatement pstmt1 = con.prepareStatement("update landlord_info set password=? where email=?");
                 /*Setting the values*/
                pstmt1.setString(1, password);
                pstmt1.setString(2, email);
                int j = pstmt1.executeUpdate();
                if (j > 0) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public ResultSet getLeaseDetails(String username) {
        ResultSet rs = null;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("select * from lease where landlord_eml=?");
             /*Setting the values*/
            pstmt.setString(1,username);
             /* Execute the query and return the result to the result set object*/
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
     public ResultSet getLeaseDoc(String username) {
        ResultSet rs = null;
        try {
             /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("select * from lease where seeker_eml=?");
             /*Setting the values*/
            pstmt.setString(1,username); 
            /* Execute the query and return the result to the result set object*/
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    /* public class which updates the path of a file*/
      public boolean updatePath(int p_id,String path) {
        boolean flag = false;
        try {
            
 /* creating prepare statement object which takes sql query as parameter and pass to db after executing*/
            PreparedStatement pstmt = con.prepareStatement("update lease set path=? where p_id=?");
            /*Setting the values*/
            pstmt.setString(1,path);
            pstmt.setInt(2,p_id);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
  }
