<%@ page import="java.util.*"%>
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