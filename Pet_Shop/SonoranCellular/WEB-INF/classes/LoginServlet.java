//package UACATS.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import UACATS.servlets.*;
//import UACATS.utils.*;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
        public LoginServlet()
        {
                super();
        }

       public void drawHeader(HttpServletRequest req, PrintWriter out)
        {
			out.println("<html>");
			out.println("<head>");
						out.println("<title>Pet Store logged in</title>");
			out.println("</head>");

			out.println("<body>");
			out.println("<p>");
			out.println("<center>");

        }


        public void drawFooter(HttpServletRequest req, PrintWriter out)
        {
			out.println("</center>");
			out.println("</p>");
			out.println("</body>");
			out.println("</html>");
        }


        private void drawUACATSOptions(HttpServletRequest req, PrintWriter out)
        {
		out.println("<font size=5 face=\"Arial,Helvetica\">");
		out.println("<b>Welcome to the Main Menu</b></br>");
		out.println("<font size=4>");
		out.println("<b>Online Pet Adoption Website</b><br></font>");
		out.println("</font>");
                out.println("<hr");
                out.println("<br><br>");
		out.println("<form name=\"adopters\" action=AdopterServlet method=get>");
                out.println("<input type=submit name=\"query\" value=\"ShowAdopters\">");
 		out.println("</form>");
                out.println("<br>");
                out.println("<form name=\"pets\" action=PetServlet method=get>");
		out.println("<input type=submit name=\"query\" value=\"Show Pets\">");
		out.println("</form>");
                out.println("<br>");
                out.println("<form name=\"profile\" action=ProfileServlet method=get>");
		out.println("<input type=submit name=\"query\" value=\"Member Profile\">");
		out.println("</form>");
		out.println("<br>");
                out.println("<form name=\"logout\" action=index.html>");
                out.println("<input type=submit name=\"logoutUACATS\" value=\"Logout\">");
 		out.println("</form>");

        }

 		private void drawFailOptions(HttpServletRequest req, PrintWriter out)
        {
				out.println("<center>");
				out.println("<font size=5 face=\"Arial,Helvetica\">");
				out.println("<b>Sorry, only registered users can log into PetAdopt</b></br>");
				out.println("<font size=4>");
				out.println("<b>Online Pet Adoption Website</b><br></font>");
				out.println("</font>");
				out.println("</center>");
                out.println("<hr");
                out.println("<br><br>");
                out.println("<form name=\"logout\" action=index.html>");
				out.println("<center>");
				out.println("<input type=submit name=\"home\" value=\"Return to home\">");
				out.println("</center>");
 				out.println("</form>");
                out.println("<br>");
	    }


		public void drawLoginSuccess(HttpServletRequest req, PrintWriter out)
		{
				drawHeader(req,out);
				drawUACATSOptions(req,out);
			    drawFooter(req,out);
		}

		public void drawLoginFail(HttpServletRequest req, PrintWriter out)
		{
				drawHeader(req,out);
				drawFailOptions(req,out);
				drawFooter(req,out);
		}
        
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
        {

			Connection conn=null;
			try{
				Class.forName("oracle.jdbc.OracleDriver");  // load drivers
				System.out.println("Attempting to connect 000");
				conn = DriverManager.getConnection(OracleConnect.connect_string,OracleConnect.user_name,OracleConnect.password);
			}
			catch(Exception e){
				e.printStackTrace();
			}

			res.setContentType("text/html");
			PrintWriter out = res.getWriter();

			//if login success, call the following function
			if(loginSuccess(conn , req))
				drawLoginSuccess(req,out);
			//if fail, call the following function
			else
				drawLoginFail(req,out);

			try{
				conn.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
        }


        //Checks to see if the user exists.
        private boolean loginSuccess(Connection conn , HttpServletRequest request){

			if(request.getParameter("emailAddress") != null){
				//Get the id
				String emailAddress = "";
				String[] paramValues = request.getParameterValues("emailAddress");
				try{
					emailAddress = (paramValues[0]);
				}catch(NumberFormatException nfe){
				}

				//Add this stuff to the session...
				HttpSession session = request.getSession();
				session.setAttribute("emailAddress" , "" + emailAddress);

				//Now query the database to see if this user exists......
				try{
					//System.out.println("Select EmailAddress from Member where EmailAddress =" + emailAddress + "");
					PreparedStatement ps = conn.prepareStatement("Select EmailAddress from Member where EmailAddress = ?");
					ps.setString(1, emailAddress);
					ResultSet rs = ps.executeQuery();
					int count = 0;
					while(rs.next())count++;

					if(count == 0){
						System.out.println("Member does not exist");
						return false;
					}
				}catch(SQLException sqle){
					sqle.printStackTrace();
					return false;
				}

				return true;
			}
			else{
				//If the parameter is empty the session must have the info..
				HttpSession session = request.getSession();

				String emailAddress = (String)session.getAttribute("emailAddress");

				if(emailAddress != null){
					try{
						//Now query the database to see if this user exists......
						try{
						//	System.out.println("Select EmailAddress from Member where EmailAddress = '" + emailAddress + "'");
								
							PreparedStatement ps = conn.prepareStatement("Select EmailAddress from Member where EmailAddress = ?");
							ps.setString(1, emailAddress);
							ResultSet rs = ps.executeQuery();
						
							int count = 0;
							while(rs.next())count++;

							if(count == 0){
								System.out.println("Member does not exist");
								return false;
							}
							else{
								return true;
							}
						}catch(SQLException sqle){
							sqle.printStackTrace();
							return false;
						}
					}
					catch(Exception e){
						e.printStackTrace();
						return false;
					}
				}
				else{
					return false;
				}
			}
		}
}


