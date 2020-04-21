<%@ page import="java.sql.*,java.util.*,mypkg.Book" session="false"%>
<%!
Connection cn;
Hashtable <Integer,Book>bs=new Hashtable<>();

public void jspInit()
{	
 try
 {
  Class.forName("com.mysql.cj.jdbc.Driver");
  cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/bookstore","root","jain@mysql");
  
  Statement st=cn.createStatement();
  ResultSet rs=st.executeQuery("select * from books");
  
  System.out.println();
  while(rs.next())
  {
   System.out.println("adding book - "+rs.getInt("bookid"));
   bs.put(rs.getInt("bookid"),new Book(rs));
  }
  
 }
 catch(ClassNotFoundException e)
 {
  System.err.println("\ndriver alert - "+e.getMessage()+"\n");
 }
 catch(SQLException e)
 {
  System.err.println("\nsql alert[init] - "+e.getMessage()+"\n");
 }		
}

public void jspDestroy()
{
 try
 {		  
  PreparedStatement pst=cn.prepareStatement("update books set qoh=? where bookid=?");		  
  System.out.println();
  
  Enumeration <Integer> kz=bs.keys();
  while(kz.hasMoreElements())
  {	  
   Integer id=(Integer)kz.nextElement();
   Book b=bs.get(id);
   System.out.println("updating book - "+id);
   
   pst.setInt(1,b.getQoh());
   pst.setInt(2, id);
   
   pst.executeUpdate();
  }
  cn.close();
 }
 catch(SQLException e)
 {
  System.err.println("\nsql alert[init] - "+e.getMessage()+"\n");
 }	
}
%>
<%
 application.setAttribute("htref", bs);
%>
<html>
 <body bgcolor=teal>
  <form action=bookdtl.jsp> 
   <center><br><br><br><br>
<%	
 if(request.getSession(false)!=null) 
 { 
  int count=0;
  Enumeration <String>s=request.getSession(false).getAttributeNames();
  while(s.hasMoreElements())
  {
   s.nextElement();
   count++;
  } 
  if(count!=0)
  {	  
%>    	
   <div style="float:right">
   <font face="lucida console" size=3 color=white>
   <a href="view.jsp">Kart : <%=count%></a>
   &nbsp; 
   </font></div>
<%    	 
  }
 }
 Enumeration <Integer>kz=bs.keys();
 while(kz.hasMoreElements())
 {
  int id=kz.nextElement();
  if(bs.get(id).discount!=0)
  {
%>
   <div style="float:left;margin:50;width:200;height:300">
   <a href="bookdtl.jsp?&id=<%=id%>"><img src="<%=id%>.jpg"></a>
   <br><br>
   <table>
   <tr><td>
   <font face="lucida console"size=3 color=orange>
   Title 
   </font></td>
   <td> &nbsp; </td>
   <td>
   <font face="lucida console"size=3 color=white>
   <%=bs.get(id).title%>
   </font></td></tr>
   <tr><td>
   <font face="lucida console"size=3 color=orange>
   Discount 
   </font></td>
   <td> &nbsp; </td>
   <td>
   <font face="lucida console"size=3 color=white>
   <%=bs.get(id).discount%> %
   </font></td></tr>
   </table>
   </div>
<%        	
  }
 }
%>
   </center> 
  </form>
 </body>
</html>