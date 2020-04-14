package mypkg;

import java.sql.*;

public class Book 
{
 int bookId,qoh,discount;
 String title,author;
 float price;
  
 public Book(ResultSet rs)
 {
  try
  {
   bookId=rs.getInt("bookId");
   title=rs.getString("title");
   author=rs.getString("author");
   price=rs.getInt("price");
   discount=rs.getInt("discount");
   qoh=rs.getInt("qoh");
  }
  catch(SQLException e)
  {
   System.err.println("\nsql-alert[book] - "+e.getMessage()+"\n");	 
  }
 }
 
 public int getQoh()
 {
  return qoh;
 }
}
