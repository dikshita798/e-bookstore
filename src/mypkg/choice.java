package mypkg;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class choice
 */
@WebServlet("/choice")
public class choice extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public choice() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		  ServletContext sc=request.getServletContext();
		  home h=(home)sc.getAttribute("href");
		
          int id=Integer.parseInt(request.getParameter("id"));
          int qty=Integer.parseInt(request.getParameter("qty"));
          
          Book b=h.bs.get(id);
          String html="<html>";
          
          if(qty>b.getQoh())
          {
        	  html=html+"<body bgcolor=teal><form action=bookdtl>"
        	  		   + "<center><br><br><br><br>"
        	  		   + "<font face=\"lucida console\" size=5 color=orange>"
        	  		   + "Enter Quantity less than Available"
        	  		   + "</font>"
        	  		   + "<br><br><br>"
        	  		   + "<font face=\"lucida console\" size=4 color=white>"
        	  		   + "<a href=bookdtl?&id="+id+">Back</a></font>"
        	  		   + "</center></form></body></html>";
        	  response.getWriter().println(html);
          }
          else
          {
           Cookie arr[]=request.getCookies();
           int uid;
           Hashtable <Integer,Integer>k;
           if(arr==null)
           {
        	  uid=h.kart.size()+101;
        	  
      		  Cookie c=new Cookie("userid",uid+"");
      		  c.setMaxAge(24*60*60);
      		  response.addCookie(c);
      		  
      		  k=new Hashtable<>();
      		  k.put(id,qty);  
       	      b.qoh=b.getQoh()-qty;
        	  h.kart.put(uid,k);
        	  
           }
           else
           {  
        	   uid=Integer.parseInt(arr[0].getValue());
        	   k=h.kart.get(uid);
        	   Integer q=k.get(id);
        	   if(q==null)
        	   {
        		 q=qty;  
        	   }
        	   else
        	   {
        		 q=q+qty;  
        	   }
        	   k.put(id,q);
               b.qoh=b.getQoh()-q;
           }  	  
    	  
           html=html+"<body bgcolor=teal>"
          	     	+ "<center><br><br><br><br>"
          		    + "<table border=1>"
   	   		        + "<tr>"
   	   		        + "<th bgcolor=white>"
   	   		        + "<font face=\"lucida console\" size=5 color=teal>"
   	   		        + "BOOK-ID"
   	   		        + "</font></th>"
   	                + "<th bgcolor=white>"
   	   	            + "<font face=\"lucida console\" size=5 color=teal>"
   	 		        + "QUANTITY"
   	   		        + "</font></th></tr>";
          
     	  Enumeration <Integer>kz=k.keys();
     	  while(kz.hasMoreElements())
     	  {
     	     Integer bid=(Integer)kz.nextElement();
     	     Integer a=k.get(bid);
            
     	     html=html+ "<tr>"
    	              + "<td><font face=\"lucida console\" size=4 color=white>"
     	   		      + bid
     	   		      + "</font></td>"
     	              + "<td><font face=\"lucida console\" size=4 color=white>"
    		          + a
     	   		      + "</font></td>"
     	   	          + "</tr>";
     	  } 
     	  html=html+ "</table>"
     	           + "<form action=home><br><br><br><br>"
 			       + "<font face=\"lucida console\" size=5 color=yellow>"
 			       + "<input type=hidden>"
 			       + "<input type=submit value=Home>"
 			       + "</font>"
 			       + "</form></center></body></html>";
     	 response.getWriter().println(html);
        }      
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
