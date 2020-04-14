package mypkg;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	Connection cn;
	
	Hashtable <Integer,Book>bs=new Hashtable<>();
	Hashtable <Integer,Hashtable<Integer,Integer>>kart=new Hashtable<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see 
	 * Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		 super.init(config);
	     ServletContext sc=getServletContext();
		 sc.setAttribute("href", this); 
		
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

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy()
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Cookie arr[]=request.getCookies();
		String html="<html><body bgcolor=teal><form action=bookdtl>"
				   + "<center><br><br><br><br>";

		if(arr!=null)
		{	
			int uid=Integer.parseInt(arr[0].getValue());
			 html=html+"<div style=\"float:right\">"
			  		  + "<font face=\"lucida console\"size=3 color=white>"
					  + "Kart : "+kart.get(uid).size()
					  +" &nbsp; "
					  + "</font></div>";
		}
		Enumeration <Integer>kz=bs.keys();
		while(kz.hasMoreElements())
		{
	        int id=kz.nextElement();
	        if(bs.get(id).discount!=0)
	        {
		        html=html+"<div style=\"float:left;margin:50;width:200;height:300\">"
		        		 + "<a href=\"bookdtl?id="+id+"\"><img src=\""+id+".jpg\"></a>"
		        		 + "<br><br>"
		        		 + "<table>"
		        		 + "<tr><td>"
		        		 + "<font face=\"lucida console\"size=3 color=orange>"
		        		 + "Title "
		        		 + "</font></td>"
		        		 + "<td> &nbsp; </td>"
		        		 + "<td>"
		        		 + "<font face=\"lucida console\"size=3 color=white>"
		        		 + bs.get(id).title
		                 +"</font></td></tr>"
		                 + "<tr><td>"
		        		 + "<font face=\"lucida console\"size=3 color=orange>"
		        		 + "Discount "
		        		 + "</font></td>"
		        		 + "<td> &nbsp; </td>"
		        		 + "<td>"
		        		 + "<font face=\"lucida console\"size=3 color=white>"
		        		 + bs.get(id).discount+" %"
		                 +"</font></td></tr>"
		                 + "</table>"
		        		 + "</div>";	        	
	        }
			
		}
		html=html+"</center></form></body></html>";
		response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
	}

}
