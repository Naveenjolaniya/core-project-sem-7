import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Pay extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			
			PrintWriter pw=res.getWriter();
			pw.println("<BODY bgColor=#a3d881 leftMargin=0 topMargin=0 MARGINHEIGHT=0 MARGINWIDTH=0><!-- ImageReady Slices (Untitled-1) -->");
			pw.println("<DIV id=Layer1 style='Z-INDEX: 1; LEFT: 410px; WIDTH: 328px; POSITION: absolute; TOP: 65px; HEIGHT: 35px'>");
			pw.println("<FORM name=form1 action='SearchServlet'  method=post ><INPUT name=sstring> ");
			pw.println("<SELECT  name=category > <option >Choose Category</option>");
			pw.println("<OPTION>Electronics</OPTION> <OPTION>Computers</OPTION> ");
			pw.println("  <OPTION>Mobiles</OPTION> <OPTION>Jewellery</OPTION> <OPTION>Art &amp; ");
			pw.println("  Collections</OPTION> <OPTION>Home &amp; Life</OPTION> ");
			pw.println("<OPTION>Travel</OPTION></SELECT><INPUT type=submit value=Go ></form> ");
			
			String uname=req.getParameter("theuser");
			pw.println("<script language=javascript>");
			pw.println("function check()");
			pw.println("{if(payform.ccard.value.length != 16 && isNaN(payform.ccard.value)) alert('Invalid card number'); else if(payform.ctype.value == 'visa' && payform.ccard.value.indexOf('4') != 0) alert('Invalid card number'); else payform.submit();}");
			pw.println("</script>");
			pw.println("<form action=Thanku name = payform>");
			pw.println("<br><br>Welcome Mr. "+uname+" thank you for purchase<br><br>");
			pw.println("Name : <input type=text name=nm value="+uname+">");
			pw.println("<br>Card type : ");
			pw.println("<select name = ctype>");
			pw.println("<option value = visa>Visa</option>");
			pw.println("<option value = mastercard>MasterCard</option>");
			pw.println("<option value = americanexpress>AmericanExpress</option>");
			pw.println("</select>");
			pw.println("<br>CrediCard NO : <input type=password name=ccard>");
			pw.println("<br>Where to Deliver : <input type=text name=del>");
			pw.println("<br><center><input type=button onclick = check() value=Pay></form>");	
			
		}
		catch(Exception exp)
		{}
	}
}	
	
	
	

