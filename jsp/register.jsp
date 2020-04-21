<%@ page import="java.sql.*,java.util.*,mypkg.Book"%>
<%   
 Hashtable<Integer,Book> bs=(Hashtable<Integer,Book>)application.getAttribute("htref");
 int id=Integer.parseInt(request.getParameter("id"));
 int qty=Integer.parseInt(request.getParameter("qty"));
 Book b=bs.get(id);
 if(qty>b.getQoh())
 {	 
%>	 
<html> 
 <body bgcolor=teal>
  <form action=bookdtl.jsp>
   <center><br><br><br><br>
   <font face="lucida console" size=5 color=orange>
   Enter Quantity less than Available
   </font>
   <br><br><br>
   <font face="lucida console" size=4 color=white>
   <a href="bookdtl.jsp?&id=<%=id%>">Back</a></font>
   </center>
  </form>
 </body>
</html>
<% 
 }
 else
 {
  if(session.getAttribute(id+"")==null)
  {
 	 session.setAttribute(id+"",qty+"");
 	 b.qoh=b.getQoh()-qty;
  }
  else
  {
 	 b.qoh=b.getQoh()-qty;
     qty=qty+Integer.parseInt((String)session.getAttribute(id+""));
     session.setAttribute(id+"",qty+"");
  }
%>
<html>
 <body bgcolor=teal>
  <center><br><br><br><br>
  <table border=1>
  <tr>
  <th bgcolor=white>
  <font face="lucida console" size=5 color=teal>
  BOOK-ID
  </font></th>
  <th bgcolor=white>
  <font face="lucida console" size=5 color=teal>
  QUANTITY
  </font></th></tr>
<%
 Enumeration <String>s=session.getAttributeNames();
 while(s.hasMoreElements())
 {
  Integer bid=Integer.parseInt(s.nextElement());
  Integer q=Integer.parseInt((String)session.getAttribute(bid+""));
%>          
  <tr>
  <td><font face="lucida console" size=4 color=white>
  <%=bid%>
  </font></td>
  <td><font face="lucida console" size=4 color=white>
  <%=q%>
  </font></td>
  </tr>
<%
 }
%> 
  </table>  
  <form action=home.jsp><br><br><br><br>
  <font face="lucida console" size=5 color=yellow>
  <input type=hidden>
  <input type=submit value=Home>
  </font>
  </form>
  </center>
 </body>
</html>
<%
 } 
%>