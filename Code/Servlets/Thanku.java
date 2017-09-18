import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Thanku extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
			try
			{
			
				PrintWriter pw=res.getWriter();				
				pw.println("<html><body><center>");
				pw.println("<br>Thank you for payment.");	
				pw.println("<br>You will be get the product at your address.<br>");
				pw.println("<br><br><a href=http://localhost:7001\\home.htm>");
				pw.println("Goto HomePage</a></body></html>");		
			}
			catch(Exception exp)
			{}
	}
}
	