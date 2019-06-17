package db;

import java.sql.*;
import javax.swing.JOptionPane;

public class DB {
    public static Connection con;
    public static Statement st;
    public static PreparedStatement complain,complainID,getComID,getInfo,searchC,stId,aprove;
    
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/IMS?autoReconnect=true&useSSL=false","root","12345678");
            st=con.createStatement();
            complain = con.prepareStatement("insert into complain_info (ctype,sid,sname,loc,remarks,status) values (?,?,?,?,?,?)");
            aprove = con.prepareStatement("update complain_info set status=? where cid=?");
            getComID = con.prepareStatement("select max(cid) from complain_info");
            getInfo = con.prepareStatement("select * from complain_info where status=?");
            searchC = con.prepareStatement("select * from complain_info where cid = ?");
            stId = con.prepareStatement("select * from student where sid=?");
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static ResultSet getInfo(String st){
        try{
            getInfo.setString(1, st);
            ResultSet rs=getInfo.executeQuery();
            return rs;
        }catch(Exception ex){
            return null;
        }
    }
    
    public static String approve(String st,int cid){
        try{
            aprove.setString(1, st);
            aprove.setInt(2, cid);
            int r=aprove.executeUpdate();
            if(r==1)
                return "done";
        }catch(Exception ex){
            return ex.toString();
        }
        return "error";
    }
}