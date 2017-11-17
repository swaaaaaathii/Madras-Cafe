import bean.food;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class fooddao
{
  public String retrieveCategory(String type)
  {
	  String categories = "";
      final String Driver = "com.mysql.jdbc.Driver";
      final String DBUrl = "jdbc:mysql://localhost:3306/sys";
      final String USER = "root";
      final String pass = "Rainarox<3";
      try{
      Class.forName(Driver);
      Connection conn = DriverManager.getConnection(DBUrl,USER,pass);
      Statement stmt = conn.createStatement();
      String sql = "SELECT distinct(category) FROM "+type+";";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next())
      {
          categories += rs.getString("category")+"+";
      }
	  categories = categories.substring(0, categories.length() - 1);
    }
    catch(Exception e)
    { System.out.println(e); }
    
    return categories;
 }
 
  public String retrieveItemDetails(String type,String item)
  {
	  String details="";
	  String subs = "";
	  String itemname,desc;
	  int price;
      final String Driver = "com.mysql.jdbc.Driver";
      final String DBUrl = "jdbc:mysql://localhost:3306/sys";
      final String USER = "root";
      final String pass = "Rainarox<3";
      try{
      Class.forName(Driver);
      Connection conn = DriverManager.getConnection(DBUrl,USER,pass);
      Statement stmt = conn.createStatement();
      String sql = "SELECT item,description,price FROM "+type+" where category = \""+item+"\";";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next())
      {
          itemname = rs.getString("item");
          desc = rs.getString("description");
		  price = rs.getInt("price");
		  details = itemname + "-" + desc + "-" + price;
		  subs += details + "+";
      }
	  subs = subs.substring(0, subs.length() - 1);
    }
    catch(Exception e)
    { System.out.println(e); }
    
    return subs;
 }
 public int getCount(String type)
 {
	  int count=0;
      final String Driver = "com.mysql.jdbc.Driver";
      final String DBUrl = "jdbc:mysql://localhost:3306/sys";
      final String USER = "root";
      final String pass = "Rainarox<3";
      try{
      Class.forName(Driver);
      Connection conn = DriverManager.getConnection(DBUrl,USER,pass);
      Statement stmt = conn.createStatement();
      String sql = "SELECT count(distinct(category)) as count FROM "+type+";";
      ResultSet rs = stmt.executeQuery(sql);
      if(rs.next())
          count += rs.getInt("count");
    }
    catch(Exception e)
    { System.out.println(e); }
    
    return count;
 }
}