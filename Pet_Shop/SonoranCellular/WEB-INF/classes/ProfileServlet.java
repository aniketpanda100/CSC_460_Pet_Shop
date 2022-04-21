//package UACATS.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import UACATS.utils.*;
import java.sql.*;

public class ProfileServlet extends HttpServlet
{

	public ProfileServlet()
	{
		super();
	}

	public void drawHeader(HttpServletRequest req, PrintWriter out)
	{
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Adopters</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>");
		out.println("<center>");
		out.println("<font size=5 face=\"Arial,Helvetica\">");
		out.println("<b>Member Profile</b><br></font>");
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
		//System.out.println("\nIn AddMerchandiseServlet doGet");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		Connection conn = null;
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");// register drivers
			System.out.println("Attempting to connect 111");
			conn = DriverManager.getConnection(OracleConnect.connect_string,OracleConnect.user_name,OracleConnect.password);
		} catch(Exception e){
			e.printStackTrace();
		}

		drawHeader(req,out);
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("emailAddress");
		//String email = "ash@email.com";
		if (email == null) { out.print("ERROR: Email Address NULL"); }
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Member WHERE EmailAddress =?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmeta = rs.getMetaData();
			int columns = rsmeta.getColumnCount();
			out.print("<h3>My Info</h3>");
			out.print("<table border='1px' cellspacing='0' cellpadding='3'>");
			for (int i = 1; i<=columns; i++){
				out.print("<th>" + rsmeta.getColumnName(i) + "</th>");
			}
			while(rs.next()){
				out.print("<tr>");
				out.print("<td>" + rs.getString(1) + "</td>");
				out.print("<td>" + rs.getString(2) + "</td>");
				out.print("<td>" + rs.getString(3) + "</td>");
				out.print("<td>" + rs.getString(4) + "</td>");
				String date = rs.getDate(5).toString();
				out.print("<td>" + date.substring(5) + "-" + date.substring(0, 4) + "</td>");
				out.print("<td>" + rs.getString(6) + "</td>");
				out.print("<td>" + rs.getString(7) + "</td>");
				out.print("<td>" + rs.getString(8) + "</td>");
				out.print("</tr>");
			}
			out.print("</table><br>");
			ps = conn.prepareStatement("SELECT Name, Birthday, Breed FROM Pet WHERE EmailAddress = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			rsmeta = rs.getMetaData();
			columns = rsmeta.getColumnCount();
			out.print("<h3>Pets I Own</h3>");
			out.print("<table border='1px' cellspacing='0' cellpadding='3'>");
			for (int i = 1; i<=columns; i++){
				if (i == 2) { out.print("<th>AGE</th>"); }
				else { out.print("<th>" + rsmeta.getColumnName(i) + "</th>"); }
			}
			int age = 0;
			while(rs.next()){
				out.print("<tr>");
				out.print("<td>" + rs.getString(1) + "</td>");
				age = 2021 - Integer.parseInt(rs.getString(2).substring(0, 4));
				Calendar cal = Calendar.getInstance();
				int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
				int bMonth = Integer.parseInt(rs.getString(2).substring(5, 7));
				int bday = Integer.parseInt(rs.getString(2).substring(8, 10));
				if ( age > 0 && (bMonth > 5 || bMonth == 5 && bday > dayOfMonth) ) { age--; }
				out.print("<td>" + age + "</td>");
				out.print("<td>" + rs.getString(3) + "</td>");
				out.printf("</tr>");
			}
			out.print("</table><br>");
			out.print("<h3>My Favorite Pets</h3>");
			out.print("<table border='1px' cellspacing='0' cellpadding='3'>");
			out.print("<th>NAME</th>");
			ps = conn.prepareStatement("SELECT PetName FROM Favorite WHERE EmailAddressMember = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()){
				out.print("<tr>");
				out.print("<td>" + rs.getString(1) + "</td>");
				out.print("</tr>");
			}
			out.print("</table><br>");
			out.print("<center><p><form name='Summary' action=LoginServlet method=get>"
				+ "<input type=submit name='MainMenu' value='Main Menu'> </form></p>");

		} catch(SQLException sqle){
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
