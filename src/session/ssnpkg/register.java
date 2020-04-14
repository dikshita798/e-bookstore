package ssnpkg;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
         HttpSession ssn=request.getSession();
         if(ssn.getAttribute(id+"")==null)
         {
        	 ssn.setAttribute(id+"",qty+"");
        	 b.qoh=b.getQoh()-qty;
         }
         else
         {
        	 b.qoh=b.getQoh()-qty;
             qty=qty+Integer.parseInt((String)ssn.getAttribute(id+""));
             ssn.setAttribute(id+"", qty+"");
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
       
   	  Enumeration <String>s=ssn.getAttributeNames();
   	  while(s.hasMoreElements())
   	  {
   	     Integer bid=Integer.parseInt(s.nextElement());
   	     Integer q=Integer.parseInt((String)ssn.getAttribute(bid+""));
          
   	     html=html+ "<tr>"
  	              + "<td><font face=\"lucida console\" size=4 color=white>"
   	   		      + bid
   	   		      + "</font></td>"
   	              + "<td><font face=\"lucida console\" size=4 color=white>"
  		          + q
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
