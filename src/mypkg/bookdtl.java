package mypkg;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class bookdtl
 */
@WebServlet("/bookdtl")
public class bookdtl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookdtl() 
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
          Book b=h.bs.get(id);
          
          String html="<html><body bgcolor=teal>"
          		     + "<center><br><br><br><br>"
          		     + "<div style=\"margin-left:300;margin-right:300\"><form action=choice>"
          		     + "<div style=\"float:left;margin-right:100;width:100;height:100\">"
          		     + "<img src=\""+id+".jpg\">"
          		     + "</div>"
          		     + "<div style=\"float:left;margin-left:100;width:400;height:300\">"
          		     + "<table>"
          		     + "<tr>"
          		     + "<td><font face=\"lucida console\"size=4 color=orange>"
          		     + "Title"
          		     + "<td>&nbsp;</td>"
          		     + "</font></td>"
          		     + "<td><font face=\"lucida console\"size=3 color=white>"
          		     +b.title
          		     + "</font></td>"
          		     + "</tr>"
          		     + "<tr>"
          		     + "<td><font face=\"lucida console\"size=4 color=orange>"
          		     + "Author"
          		     + "<td>&nbsp;</td>"
          		     + "</font></td>"
          		     + "<td><font face=\"lucida console\"size=3 color=white>"
          		     +b.author
          		     + "</font></td>"
          		     + "</tr>"
          		     + "<tr>"
          		     + "<td><font face=\"lucida console\"size=4 color=orange>"
          		     + "Price"
          		     + "<td>&nbsp;</td>"
          		     + "</font></td>"
          		     + "<td><font face=\"lucida console\"size=3 color=white>"
          		     +b.price
          		     + "</font></td>"
          		     + "</tr>" 
          		     + "<tr>"
          		     + "<td><font face=\"lucida console\"size=4 color=orange>"
          		     + "Discount"
          		     + "<td>&nbsp;</td>"
          		     + "</font></td>"
          		     + "<td><font face=\"lucida console\"size=3 color=white>"
          		     +b.discount+" %"
          		     + "</font></td>"
          		     + "</tr>";
          if(b.qoh==0)
          {
        	  html=html+ "<tr>"
           		       + "<td><font face=\"lucida console\"size=4 color=orange>"
           		       + "Out of Stock"
           		       + "</font></td>"
                       + "</tr></table>";
          }
          else
          {
              html=html+ "<tr>"
          		       + "<td><font face=\"lucida console\"size=4 color=orange>"
          		       + "Available Quantity"
          		       + "<td>&nbsp;</td>"
          		       + "</font></td>"
          		       + "<td><font face=\"lucida console\"size=3 color=white>"
          		       +b.qoh
          		       + "</font></td>"
          		       + "</tr></table>"          		     
                       + "<br><br><br><br>"
                       + "<font face=\"lucida console\"size=4 color=orange>"
                       + "Quantity"
                       + "<input type=text name=qty>"
                       + "</font>"
                       + "<br><br>"
                       + "<input type=hidden name=id value="+id+">"
                       + "<input type=submit value=\"Add to Kart\">";
          }
          html=html+ "</form>"
                   + "<form action=home>"
                   + "<br><br><br><br>"
                   + "<input type=hidden>"
                   + "<input type=submit value=\"Continue Shopping\">"
                   + "</form>"
                   + "</div></center></body></html>";
          
          response.getWriter().println(html);
        		  
          
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
