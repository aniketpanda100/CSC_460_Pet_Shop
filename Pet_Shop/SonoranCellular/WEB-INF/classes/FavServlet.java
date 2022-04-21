//package UACATS.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import UACATS.utils.*;
import java.sql.*;

public class FavServlet extends HttpServlet
{

	public FavServlet()
	{
		super();
	}

	public void drawHeader(HttpServletRequest req, PrintWriter out)
	{
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Favorite</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>");
		out.println("<center>");
		out.println("<font size=5 face=\"Arial,Helvetica\">");
		out.println("<b>Mark Pet as Favorite</b><br></font>");
		out.println("<hr>");
		out.println("<br><br>");
	}

	public void drawFooter(HttpServletRequest req, PrintWriter out)
	{
		out.println("</center>");
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
	}

	private String dateFormat(String str) {
		String[] month = {"JAN","FEB","MAR", "APR", "MAY", "JUN", "JUL",
				"AUG", "SEP", "OCT", "NOV", "DEC"}; 
		String ret = str.substring(8, 10) + "-"
		       	+ month[Integer.parseInt(str.substring(5, 7)) - 1]
		       	+ "-" + str.substring(2, 4); 
		return ret;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("\nIn AddMerchandiseServlet doGet");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
	
		Connection conn = null;
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver");// register drivers
			System.out.println("Attempting to connect 111");
			conn = DriverManager.getConnection(OracleConnect.connect_string,OracleConnect.user_name,OracleConnect.password);
		}catch(Exception e){
			e.printStackTrace();
		}

		drawHeader(req,out);
		
		try{

			String petInfo = req.getParameter("petInfo");
			HttpSession session = req.getSession();
			String emailMember = (String)session.getAttribute("emailAddress");
			String[] arr = petInfo.split("_DELIM_");
			//out.print(emailMember + " " + arr[0] + " " + arr[1] + " " + arr[2]);
			String birthday = dateFormat(arr[2]);
			//out.print(birthday);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Favorite (EmailAddressMember, EmailAddressPet, PetName, Birthday) VALUES(?, ?, ?, TO_DATE(?, ?))");
			ps.setString(1, emailMember);
			ps.setString(2, arr[0]);
			ps.setString(3, arr[1]);
			ps.setString(4, birthday);
			ps.setString(5, "DD-MON-YY");		
			ps.executeUpdate();
			conn.commit();

			out.print("<center><p><form name='Summary' action=LoginServlet method=get>"
				+ "<input type=submit name='MainMenu' value='Main Menu'> </form></p>");

		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("No..here...here....");
		}	
		drawFooter(req,out);
		
		try{
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
