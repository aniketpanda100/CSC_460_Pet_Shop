//package UACATS.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import UACATS.utils.*;
import java.sql.*;

public class PetServlet extends HttpServlet
{

	public PetServlet()
	{
		super();
	}

	public void drawHeader(HttpServletRequest req, PrintWriter out)
	{
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Pets</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>");
		out.println("<center>");
		out.println("<font size=5 face=\"Arial,Helvetica\">");
		out.println("<b>Showing All Pets</b><br></font>");
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
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Pet");
			ResultSetMetaData rsmeta = rs.getMetaData();
			int columns = rsmeta.getColumnCount();
			out.print("<table border='1px' cellspacing='0' cellpadding='3'>");
			for (int i = 1; i<=columns; i++){
				out.print("<th>" + rsmeta.getColumnName(i) + "</th>");
			}
			out.print("<th>MAKE FAVORITE</th>");
			while(rs.next()){
				out.print("<tr>");
				out.print("<td>" + rs.getString(1) + "</td>");
				out.print("<td>" + rs.getString(2) + "</td>");
				String date = rs.getDate(3).toString();
				out.print("<td>" + date.substring(5) + "-" + date.substring(0, 4) + "</td>");
				out.print("<td>" + rs.getString(4) + "</td>");
				out.print("<td>" + rs.getString(5) + "</td>");
				if (rs.getString(5).compareTo("Dog") == 0) {
					out.print("<td>" + rs.getInt(6) + "</td>");
				} else {
					out.print("<td></td>");	
				}
				if (rs.getString(5).compareTo("Rabbit") == 0) {
					out.print("<td>" + rs.getString(7) + "</td>");
				} else {
					out.print("<td></td>");	
				}
				String delim = "_DELIM_";
				String petInfo = rs.getString(1) + delim + rs.getString(2) + delim
					+ rs.getString(3).substring(0, 10);
				out.println("<td><form name=\"Favform\" action=FavServlet <center>");
				out.println("<button type=\"submit\" name=\"petInfo\" value='" +
						petInfo + "'>Favorite</button>");
 				out.println("</center></form></td>");
				out.print("</tr>");
			}
			out.print("</table>");
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
