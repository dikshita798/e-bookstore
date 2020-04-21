<%@ page import="java.sql.*,java.util.*,mypkg.Book" session="false"%>
<%
 Hashtable<Integer,Book> bs=(Hashtable<Integer,Book>)application.getAttribute("htref");
 int id=Integer.parseInt(request.getParameter("id"));
 Book b=bs.get(id);
%>        
<html>
 <body bgcolor=teal>  
  <center><br><br><br><br>  
  <div style="margin-left:300;margin-right:300">
  <form action=register.jsp>  
  <div style="float:left;margin-right:100;width:100;height:100">  
  <img src="<%=id%>.jpg">  
  </div>  
  <div style="float:left;margin-left:100;width:400;height:300">  
  <table>  
  <tr>  
  <td><font face="lucida console" size=4 color=orange>  
  Title  
  </font></td>  
  <td>&nbsp;</td>  
  <td><font face="lucida console" size=3 color=white>  
  <%=b.title%>
  </font></td>  
  </tr>  
  <tr>  
  <td><font face="lucida console" size=4 color=orange>  
  Author  
  </font></td>   
  <td>&nbsp;</td>   
  <td><font face="lucida console" size=3 color=white>  
  <%=b.author%>
  </font></td>  
  </tr>  
  <tr>  
  <td><font face="lucida console" size=4 color=orange> 
   Price  
   </font></td>    
   <td>&nbsp;</td>   
   <td><font face="lucida console" size=3 color=white> 
   <%=b.price%>
   </font></td>  
   </tr>   
   <tr>  
   <td><font face="lucida console" size=4 color=orange> 
   Discount  
   </font></td>     
   <td>&nbsp;</td>  
   <td><font face="lucida console" size=3 color=white>   
   <%=b.discount%> %  
   </font></td>  
   </tr> 
<%    
 if(b.qoh==0)
 {
%>
   <tr> 
   <td><font face="lucida console" size=4 color=orange>
   Out of Stock
   </font></td>
   </tr></table>
<%   
 }
 else
 {
%>
   <tr>
   <td><font face="lucida console" size=4 color=orange>
   Available Quantity
   </font></td>
   <td>&nbsp;</td>
   <td><font face="lucida console" size=3 color=white>
   <%=b.getQoh()%>
   </font></td>
   </tr></table>          		     
   <br><br><br><br>
   <font face="lucida console" size=4 color=orange>
   Quantity
   </font>
   <input type=text name=qty>
   <br><br>
   <input type=hidden name=id value="<%=id%>">
   <input type=submit value="Add to Kart">
<% 
 }
%>
   </form>
   <form action=home.jsp>
   <br><br><br><br>
   <input type=hidden>
   <input type=submit value="Continue Shopping">
   </form>
   </div>
  </center>
 </body>
</html>