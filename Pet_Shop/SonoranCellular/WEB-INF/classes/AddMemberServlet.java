import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class AddMemberServlet extends HttpServlet
{
	String EmailAddress;
	String Name;    	
	String SelfIntroduction;
	String Location;
	
	String Biryear; 
	String Birmonth;
	String Birday; 

	String IsAdopter;		
	String IsSender;		
	String AdoptingExperience;

	String PetName;
	
	String PetBiryear;
	String PetBirmonth;
	String Petday;

	String IsSterilized;
	String Breed;
	String DaysWalk;
	String BiteWire;
	
	String UserAlreadyExists = "ERROR: username already exists";
	String AllOtherErrors = "ERROR: the request could not be complied";
	
	int ErrorCode = 0;
	
	public AddMemberServlet() {
		super();
	}

	public void drawUpdateMessages(HttpServletRequest req, PrintWriter out)
	{
		String EmailAddress = req.getParameter("EmailAddress");
		String Name = req.getParameter("Name");
		String SelfIntroduction = req.getParameter("SelfIntroduction");
		String Location = req.getParameter("Location");
		String Biryear = req.getParameter("Biryear");
		String Birmonth = req.getParameter("Birmonth");
		String Birday = req.getParameter("Birday");
		String IsAdopter = req.getParameter("IsAdopter");
		String IsSender = req.getParameter("IsSender");
		String AdoptingExperience = req.getParameter("AdoptingExperience");
		
		String PetName = req.getParameter("PetName");
		String PetBiryear = req.getParameter("PetBiryear");
		String PetBirmonth = req.getParameter("PetBirmonth");
		String Petday = req.getParameter("Petday");
		String IsSterilized = req.getParameter("IsSterilized");
		String Breed = req.getParameter("Breed");
		String DaysWalk = req.getParameter("DaysWalk");
		String BiteWire = req.getParameter("BiteWire");
		
		String Blank = "";
		
		if(AdoptingExperience.equals(Blank))
		{
			AdoptingExperience = "None";
		}
		
		if(PetName.equals(Blank))
		{
			PetName = "None";
		}
		
		if(PetBiryear.equals(Blank))
		{
			PetBiryear = "None";
		}
		
		if(PetBirmonth.equals(Blank))
		{
			PetBirmonth = "None";
		}
		
		if(Petday.equals(Blank))
		{
			Petday = "None";
		}
		
		if(IsSterilized.equals(Blank))
		{
			IsSterilized = "None";
		}
		
		if(Breed.equals(Blank))
		{
			Breed = "None";
		}
		
		if(DaysWalk.equals(Blank))
		{
			DaysWalk = "None";
		}
		
		if(BiteWire.equals(Blank))
		{
			BiteWire = "None";
		}
		
		out.println("<h2 align=\"center\">Successfully Become a Member!</h2>");
		out.println("<br>");

		out.println("<p><b>Name:</b>  " + Name + "</p>");
		out.println("<p><b>Date of Birth:</b>  " + Birmonth + "/" + Birday + "/" + Biryear + "</p>");
		out.println("<p><b>Email:</b>  " + EmailAddress + "</p>");
		out.println("<p><b>Location:</b>  " + Location + "</p>");
		out.println("<p><b>Self - Introduction:</b>  " + SelfIntroduction + "</p>");
		out.println("<p><b>Are you an adopter?:</b>  " + IsAdopter + "</p>");
		out.println("<p><b>Are you a sender?:</b>  " + IsSender + "</p>");
		out.println("<p><b>Adopting Experience:</b>  " + AdoptingExperience + "</p>");

		out.println("<p><b>Pet Name:</b>  " + PetName + "</p>");
		out.println("<p><b>Pet's Birthday:</b>  " + PetBirmonth + "/" + Petday + "/" + PetBiryear + "</p>");
		out.println("<p><b>Have your pet sterilized?:</b>  " + IsSterilized + "</p>");
		out.println("<p><b>What kind of animal?:</b>  " + Breed + "</p>");
		out.println("<p><b>How many times a day do you walk your dog?:</b>  " + DaysWalk + "</p>");
		out.println("<p><b>If your rabbit bite the wire?:</b>  " + BiteWire + "</p>");

		out.println("<br><br><br>");

		out.println("<form name=\"MainMenu\" action=LoginServlet>");
		out.println("<input type=submit name=\"MainMenu\" value=\"MainMenu\">");
		out.println("</form>");

		out.println("<br>");

		out.println("<form name=\"logout\" action=LogoutServlet method=get>");
		out.println("<input type=submit name=\"logoutUACATS\" value=\"Logout\">");
		out.println("</form>");
	}

	public void drawHeader(HttpServletRequest req, PrintWriter out)
	{
		out.println("<html>");
		out.println("<head>");
		out.println("<title>MemberRegister</title>");
		out.println("</head>");

		out.println("<body>");
		out.println("<p>");
		out.println("<center>");
		out.println("<font size=5 face=\"Arial,Helvetica\">");
		out.println("<b>PetAdopt---Register for PetAdopt as Member</b><br></font>");

		out.println("<hr");
		out.println("<br><br>");
	}
	
	public void drawFooter(HttpServletRequest req, PrintWriter out)
	{
		out.println("</center>");
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
	}

	public void drawFailOption(HttpServletRequest req, PrintWriter out)
	{
		out.println("<h2 align=\"center\">ERROR: the request could not be complied</h2>");
		out.println("<form name=\"logoutbad\" action=index.html>");
		out.println("<center>");
		out.println("<input type=submit name=\"tohomebad\" value=\"Return to home\">&nbsp&nbsp");
		out.println("</center>");
		out.println("</form>");
	}

	public void drawAddMemberMenu(HttpServletRequest req, PrintWriter out)
	{
		out.println("<h2 align=\"center\">You Are Going to Become a Member!</h2>");
		out.println("<br>");
		
		out.println("<form name=\"AddMember\" action=AddMemberServlet method=get>");
		out.println("<br><br>");
		out.println("<font size=3>");
		out.println("<p>");

		out.println("<b>Name:</b>");
		out.println("<input type=text name=\"Name\">");
		out.println("<br>");
		out.println("</p>");

		out.println("<h4 align=\"center\">Date of Birth");
		
		out.println("<p align=\"center\">");
		out.println("<select size=\"12\" name=\"Birmonth\">");
		out.println("<option selected value=\"null\">Month</option>");
		out.println("<option value=\"JAN\">January</option>");
		out.println("<option value=\"FEB\">February</option>");
		out.println("<option value=\"MAR\">March</option>");
		out.println("<option value=\"APR\">April</option>");
		out.println("<option value=\"MAY\">May</option>");
		out.println("<option value=\"JUN\">June</option>");
		out.println("<option value=\"JUL\">July</option>");
		out.println("<option value=\"AUG\">August</option>");
		out.println("<option value=\"SEP\">September</option>");
		out.println("<option value=\"OCT\">October</option>");
		out.println("<option value=\"NOV\">November</option>");
		out.println("<option value=\"DEC\">December</option>");
		out.println("</select>");
		out.println("</p>");

		out.println("<p align=\"center\">");
		out.println("<select size=\"1\" name=\"Birday\">");
		out.println("<option selected value=\"null\">Day</option>");
		out.println("<option value=\"01\">01</option>");
		out.println("<option value=\"02\">02</option>");
		out.println("<option value=\"03\">03</option>");
		out.println("<option value=\"04\">04</option>");
		out.println("<option value=\"05\">05</option>");
		out.println("<option value=\"06\">06</option>");
		out.println("<option value=\"07\">07</option>");
		out.println("<option value=\"08\">08</option>");
		out.println("<option value=\"09\">09</option>");
		out.println("<option value=\"10\">10</option>");
		out.println("<option value=\"11\">11</option>");
		out.println("<option value=\"12\">12</option>");
		out.println("<option value=\"13\">13</option>");
		out.println("<option value=\"14\">14</option>");
		out.println("<option value=\"15\">15</option>");
		out.println("<option value=\"16\">16</option>");
		out.println("<option value=\"17\">17</option>");
		out.println("<option value=\"18\">18</option>");
		out.println("<option value=\"19\">19</option>");
		out.println("<option value=\"20\">20</option>");
		out.println("<option value=\"21\">21</option>");
		out.println("<option value=\"22\">22</option>");
		out.println("<option value=\"23\">23</option>");
		out.println("<option value=\"24\">24</option>");
		out.println("<option value=\"25\">25</option>");
		out.println("<option value=\"26\">26</option>");
		out.println("<option value=\"27\">27</option>");
		out.println("<option value=\"28\">28</option>");
		out.println("<option value=\"29\">29</option>");
		out.println("<option value=\"30\">30</option>");
		out.println("<option value=\"31\">31</option>");
		out.println("</select>");
		out.println("</p>");

		out.println("<p align=\"center\"><b>Birth Year:</b>");
		out.println("<input type=text name=\"Biryear\"<br><br><br>");
		out.println("</p>");

		out.println("<b>Email:</b>");
		out.println("<input type=text name=\"EmailAddress\" size=20<br><br>");
		out.println("</p>");
		
		out.println("<b>Self - Introduction:</b>");
		out.println("<input type=text name=\"SelfIntroduction\"<br><br>");
		out.println("</p>");
		
		out.println("<b>Location:</b>");
		out.println("<input type=text name=\"Location\"<br><br>");
		out.println("</p>");
		
		out.println("<b>Are you an adopter? (y/n):</b>");
		out.println("<input type=text name=\"IsAdopter\"<br><br>");
		out.println("</p>");
		
		out.println("<b>Are you a sender? (y/n):</b>");
		out.println("<input type=text name=\"IsSender\"<br><br>");
		out.println("</p>");

		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");

		out.println("<input type=submit name=\"Submit\" value=\"AddMe\">&nbsp&nbsp");

		out.println("</td");
		out.println("</tr>");
		out.println("</form>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<form name=\"Cancel\" action=AddMemberServlet method=get>");
		out.println("<input type=submit name=\"Cancel\" value=\"Cancel\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</td>");
		out.println("</tr>");


		out.println("<tr>");
		out.println("<td>");

		out.println("<form name=\"logout\" action=index.html>");
		out.println("<input type=submit name=\"tohome\" value=\"Return to home\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</tr>");
		out.println("</table>");

		out.println("<br><br><br>");
	}

	public void drawIsAdopterMenu(HttpServletRequest req, PrintWriter out)
	{
		out.println("<form name=\"IsAdopter\" action=AddMemberServlet method=get>");
		out.println("<br><br>");
		out.println("<font size=3>");
		out.println("<p>");

		out.println("<b>Adoption Experience:</b>");
		out.println("<input type=text name=\"AdoptingExperience\">");
		out.println("<br>");
		out.println("</p>");

		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");

		out.println("<input type=submit name=\"SubmitAdopter\" value=\"AddMe\">&nbsp&nbsp");

		out.println("</td");
		out.println("</tr>");
		out.println("</form>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<form name=\"Cancel\" action=AddMemberServlet method=get>");
		out.println("<input type=submit name=\"CancelAdopter\" value=\"Cancel\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</td>");
		out.println("</tr>");


		out.println("<tr>");
		out.println("<td>");

		out.println("<form name=\"logout\" action=index.html>");
		out.println("<input type=submit name=\"tohome\" value=\"Return to home\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</tr>");
		out.println("</table>");

		out.println("<br><br><br>");
	}

	public void drawIsSenderMenu(HttpServletRequest req, PrintWriter out)
	{
		out.println("<form name=\"IsSender\" action=AddMemberServlet method=get>");
		out.println("<br><br>");
		out.println("<font size=3>");
		out.println("<p>");
		
		out.println("<p>");
		out.println("<b>Pet Name:</b>");
		out.println("<input type=text name=\"PetName\">");
		out.println("<br>");
		out.println("</p>");

		out.println("<h4 align=\"center\">Pet's Birthday");
		
		out.println("<p align=\"center\">");
		out.println("<select size=\"12\" name=\"PetBirmonth\">");
		out.println("<option selected value=\"null\">Month</option>");
		out.println("<option value=\"JAN\">January</option>");
		out.println("<option value=\"FEB\">February</option>");
		out.println("<option value=\"MAR\">March</option>");
		out.println("<option value=\"APR\">April</option>");
		out.println("<option value=\"MAY\">May</option>");
		out.println("<option value=\"JUN\">June</option>");
		out.println("<option value=\"JUL\">July</option>");
		out.println("<option value=\"AUG\">August</option>");
		out.println("<option value=\"SEP\">September</option>");
		out.println("<option value=\"OCT\">October</option>");
		out.println("<option value=\"NOV\">November</option>");
		out.println("<option value=\"DEC\">December</option>");
		out.println("</select>");
		out.println("</p>");

		out.println("<p align=\"center\">");
		out.println("<select size=\"1\" name=\"Petday\">");
		out.println("<option selected value=\"null\">Day</option>");
		out.println("<option value=\"01\">01</option>");
		out.println("<option value=\"02\">02</option>");
		out.println("<option value=\"03\">03</option>");
		out.println("<option value=\"04\">04</option>");
		out.println("<option value=\"05\">05</option>");
		out.println("<option value=\"06\">06</option>");
		out.println("<option value=\"07\">07</option>");
		out.println("<option value=\"08\">08</option>");
		out.println("<option value=\"09\">09</option>");
		out.println("<option value=\"10\">10</option>");
		out.println("<option value=\"11\">11</option>");
		out.println("<option value=\"12\">12</option>");
		out.println("<option value=\"13\">13</option>");
		out.println("<option value=\"14\">14</option>");
		out.println("<option value=\"15\">15</option>");
		out.println("<option value=\"16\">16</option>");
		out.println("<option value=\"17\">17</option>");
		out.println("<option value=\"18\">18</option>");
		out.println("<option value=\"19\">19</option>");
		out.println("<option value=\"20\">20</option>");
		out.println("<option value=\"21\">21</option>");
		out.println("<option value=\"22\">22</option>");
		out.println("<option value=\"23\">23</option>");
		out.println("<option value=\"24\">24</option>");
		out.println("<option value=\"25\">25</option>");
		out.println("<option value=\"26\">26</option>");
		out.println("<option value=\"27\">27</option>");
		out.println("<option value=\"28\">28</option>");
		out.println("<option value=\"29\">29</option>");
		out.println("<option value=\"30\">30</option>");
		out.println("<option value=\"31\">31</option>");
		out.println("</select>");
		out.println("</p>");

		out.println("<p align=\"center\"><b>Pet Birth Year:</b>");
		out.println("<input type=text name=\"PetBiryear\"<br><br><br>");
		out.println("</p>");
		
		out.println("<b>Have your pet sterilized?:</b>");
		out.println("<input type=text name=\"IsSterilized\"<br><br>");

		out.println("<b>What kind of animal?:</b>");
		out.println("<input type=text name=\"Breed\"<br><br>");
		
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");

		out.println("<input type=submit name=\"SubmitSender\" value=\"AddMe\">&nbsp&nbsp");

		out.println("</td");
		out.println("</tr>");
		out.println("</form>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<form name=\"Cancel\" action=AddMemberServlet method=get>");
		out.println("<input type=submit name=\"CancelSender\" value=\"Cancel\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");

		out.println("<form name=\"logout\" action=index.html>");
		out.println("<input type=submit name=\"tohome\" value=\"Return to home\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</tr>");
		out.println("</table>");

		out.println("<br><br><br>");
	}

	public void drawIsDogMenu(HttpServletRequest req, PrintWriter out)
	{
		out.println("<form name=\"IsDog\" action=AddMemberServlet method=get>");
		out.println("<br><br>");
		out.println("<font size=3>");
		out.println("<p>");
		
		out.println("<p>");
		out.println("<b>How many times a day do you walk your dog?:</b>");
		out.println("<input type=text name=\"DaysWalk\">");
		out.println("<br>");
		out.println("</p>");
		
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");

		out.println("<input type=submit name=\"SubmitDog\" value=\"AddMe\">&nbsp&nbsp");

		out.println("</td");
		out.println("</tr>");
		out.println("</form>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<form name=\"Cancel\" action=AddMemberServlet method=get>");
		out.println("<input type=submit name=\"CancelDog\" value=\"Cancel\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");

		out.println("<form name=\"logout\" action=index.html>");
		out.println("<input type=submit name=\"tohome\" value=\"Return to home\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</tr>");
		out.println("</table>");

		out.println("<br><br><br>");
	}
	
	public void drawIsRabbitMenu(HttpServletRequest req, PrintWriter out)
	{
		out.println("<form name=\"IsRabbit\" action=AddMemberServlet method=get>");
		out.println("<br><br>");
		out.println("<font size=3>");
		out.println("<p>");
		
		out.println("<p>");
		out.println("<b>If your rabbit bite the wire?:</b>");
		out.println("<input type=text name=\"BiteWire\">");
		out.println("<br>");
		out.println("</p>");

		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");

		out.println("<input type=submit name=\"SubmitRabbit\" value=\"AddMe\">&nbsp&nbsp");

		out.println("</td");
		out.println("</tr>");
		out.println("</form>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<form name=\"Cancel\" action=AddMemberServlet method=get>");
		out.println("<input type=submit name=\"CancelRabbit\" value=\"Cancel\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");

		out.println("<form name=\"logout\" action=index.html>");
		out.println("<input type=submit name=\"tohome\" value=\"Return to home\">&nbsp&nbsp");
		out.println("</form>");
		out.println("</tr>");
		out.println("</table>");

		out.println("<br><br><br>");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("\nIn AddMemberServlet doGet");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		Connection conn=null;
		try{
			Class.forName ("oracle.jdbc.OracleDriver");
			System.out.println("Attempting to connect 111");
			conn = DriverManager.getConnection(OracleConnect.connect_string,OracleConnect.user_name,OracleConnect.password);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		drawHeader(req,out);

		if(req.getParameter("Submit") == null || !enterInfo(req, conn))
		{
			drawAddMemberMenu(req, out);
			
			if(req.getParameter("IsAdopter") == "y")
			{
				drawIsAdopterMenu(req, out);
			}
			
			if(req.getParameter("IsSender") == "y")
			{
				drawIsSenderMenu(req, out);
			
				if(req.getParameter("Breed") == "Dog")
				{
					drawIsDogMenu(req, out);
				}
				else if(req.getParameter("Breed") == "Rabbit")
				{
					drawIsRabbitMenu(req, out);
				}
			}
		}
		else
		{
			drawUpdateMessages(req,out);
		}

		drawFooter(req, out);
		try{
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	boolean enterInfo(HttpServletRequest request , Connection conn)
	{
		System.out.println("\nIn AddMemberServlet enterInfo");

		String[] paramValues = request.getParameterValues("Name");
		Name = paramValues[0];

		paramValues = request.getParameterValues("Birday");
		Birday = paramValues[0];

		paramValues = request.getParameterValues("Birmonth");
		Birmonth = paramValues[0];

		paramValues = request.getParameterValues("Biryear");
		Biryear = paramValues[0];

		paramValues = request.getParameterValues("EmailAddress");
		EmailAddress = paramValues[0];

		paramValues = request.getParameterValues("SelfIntroduction");
		SelfIntroduction = paramValues[0];
		
		paramValues = request.getParameterValues("Location");
		Location = paramValues[0];

		paramValues = request.getParameterValues("IsAdopter");
		IsAdopter = paramValues[0];

		paramValues = request.getParameterValues("IsSender");
		IsSender = paramValues[0];

		paramValues = request.getParameterValues("AdoptingExperience");
		AdoptingExperience = paramValues[0];
		
		paramValues = request.getParameterValues("PetName");
		PetName = paramValues[0];

		paramValues = request.getParameterValues("PetBiryear");
		PetBiryear = paramValues[0];
		
		paramValues = request.getParameterValues("PetBirmonth");
		PetBirmonth = paramValues[0];

		paramValues = request.getParameterValues("Petday");
		Petday = paramValues[0];
		
		paramValues = request.getParameterValues("IsSterilized");
		IsSterilized = paramValues[0];

		paramValues = request.getParameterValues("Breed");
		Breed = paramValues[0];

		paramValues = request.getParameterValues("DaysWalk");
		DaysWalk = paramValues[0];

		paramValues = request.getParameterValues("BiteWire");
		BiteWire = paramValues[0];

		try{
			Statement s = conn.createStatement();

			ResultSet rs = s.executeQuery("Select EmailAddress from Member where EmailAddress = '"
				       	+ EmailAddress + "'");
			int count = 0;
			while(rs.next())count++;

			if(count > 0){
				ErrorCode = 1;
				return false;
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("No..here...here....");
			ErrorCode = 2;
			return false;
		}
		
		int id = -1;
		try{
			Statement s = conn.createStatement();

			ResultSet rs = s.executeQuery("Select EmailAddress from Member");
			while(rs.next()){
				int temp = rs.getInt(1);
				if(temp > id)
					id = temp;
			}

			System.out.println("Old id is "+id);

			//Insert info into table Member 
			s.executeUpdate("INSERT INTO Member VALUES('"+EmailAddress+"' , '"+Name+"' , '"+SelfIntroduction+"' , '"+Location+"' , '"+Birday+"-"+Birmonth+"-"+Biryear+"', '"+IsAdopter+"' , '"+IsSender+"' , '"+AdoptingExperience + ")");

			//Insert info into table Pet 
			s.executeUpdate("INSERT INTO Pet VALUES('"+EmailAddress+"' , '"+PetName+"' , '"+Petday+"-"+PetBirmonth+"-"+PetBiryear+"' , '"+IsSterilized+"' , '"+Breed+"' , "+DaysWalk+" , '"+BiteWire+"')");

		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("No..here...over here....");
			ErrorCode = 2;
			return false;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("emailAddress" , ""+EmailAddress);
		return true;	
	}
}
