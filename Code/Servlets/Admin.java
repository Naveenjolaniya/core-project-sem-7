import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Admin extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		pw.println("<BODY bgColor=#a3d881 leftMargin=0 topMargin=0 MARGINHEIGHT=0 MARGINWIDTH=0><!-- ImageReady Slices (Untitled-1) -->");
		pw.println("<DIV id=Layer1 style='Z-INDEX: 1; LEFT: 410px; WIDTH: 328px; POSITION: absolute; TOP: 65px; HEIGHT: 35px'>");
		pw.println("<FORM name=form1 action='http://localhost:7001/servlet/SearchServlet'  method=post ><INPUT name=sstring> ");
		pw.println("<SELECT  name=category > <option >Choose Category</option>");
		pw.println("<OPTION>Electronics</OPTION> <OPTION>Computers</OPTION> ");
		pw.println("  <OPTION>Mobiles</OPTION> <OPTION>Jewellery</OPTION> <OPTION>Art &amp; ");
		pw.println("  Collections</OPTION> <OPTION>Home &amp; Life</OPTION> ");
		pw.println("<OPTION>Travel</OPTION></SELECT> <INPUT type=submit value=Go > ");
		pw.println("</FORM></DIV><!-- End ImageReady Slices -->");
		pw.println("<TABLE cellSpacing=0 cellPadding=0 width=781 border=0>");
		pw.println("  <TBODY>  <TR>    <TD width=340 rowSpan=5><IMG height=100 alt='' src='http://localhost:7001/home_files/name-copy_01.gif' width=340></TD>");
		pw.println("    <TD colSpan=2 rowSpan=3><IMG height=69 alt='' src='http://localhost:7001/home_files/name-copy_02.gif' width=81></TD>");
		pw.println("    <TD colSpan=12><IMG height=39 alt='' src='http://localhost:7001/home_files/name-copy_03.gif'      width=359></TD>");
		pw.println("    <TD width=20><IMG height=39 alt='' src='http://localhost:7001/home_files/spacer.gif'   width=1></TD></TR>");
		pw.println("  <TR>    <TD width=51><A href='http://localhost:7001/home.htm'><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_04.gif' width=51 border=0></A></TD>");
		pw.println("    <TD width=4><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_05.gif'       width=4></TD>");
		pw.println("    <TD width=55><A href='http://localhost:7001/login.htm'><IMG height=12       alt='' src='http://localhost:7001/home_files/name-copy_06.gif' width=55 border=0></A></TD>");
		pw.println("    <TD width=4><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_07.gif'       width=4></TD>");
		pw.println("    <TD width=65><A href='http://localhost:7001/registration.htm'><IMG       height=12 alt='' src='http://localhost:7001/home_files/name-copy_08.gif' width=65     border=0></A></TD>");
		pw.println("    <TD width=7><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_09.gif'       width=7></TD>");
		pw.println("    <TD width=40><A href='http://localhost:7001/sell.htm'><IMG height=12       alt='' src='http://localhost:7001/home_files/name-copy_10.gif' width=40 border=0></A></TD>");
		pw.println("    <TD width=5><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_11.gif'       width=5></TD>");
		pw.println("    <TD width=46><A href='http://localhost:7001/help.htm'><IMG height=12      alt='' src='http://localhost:7001/home_files/name-copy_12.gif' width=46 border=0></A></TD>");
		pw.println("    <TD width=6><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_13.gif'       width=6></TD>");
		pw.println("    <TD width=64><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_14.gif'       width=64></TD>");
		pw.println("    <TD width=12><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_15.gif'       width=12></TD>");
		pw.println("    <TD><IMG height=12 alt='' src='http://localhost:7001/home_files/spacer.gif' width=1></TD></TR>");
		pw.println("    <TR>  <TD colSpan=12 rowSpan=3><IMG height=49 alt=''       src='http://localhost:7001/home_files/name-copy_16.gif' width=359></TD>");
		pw.println("    <TD><IMG height=18 alt='' src='http://localhost:7001/home_files/spacer.gif' width=1></TD></TR>");
		pw.println("    <TR>  <TD width=60><IMG height=16 alt='' src='http://localhost:7001/home_files/name-copy_17.gif'       width=60></TD>");
		pw.println("    <TD width=21 rowSpan=2><IMG height=31 alt=''       src='http://localhost:7001/home_files/name-copy_18.gif' width=21></TD>");
		pw.println("    <TD><IMG height=16 alt='' src='http://localhost:7001/home_files/spacer.gif' width=1></TD></TR>  <TR>");
		pw.println("    <TD><IMG height=15 alt='' src='http://localhost:7001/home_files/name-copy_19.gif' width=60></TD>");
		pw.println("    <TD><IMG height=15 alt='' src='http://localhost:7001/home_files/spacer.gif'   width=1></TD></TR></TBODY></TABLE><br><br>");
		String root=req.getParameter("usr");
		String pass=req.getParameter("password");
		if(root.equals("admin") && pass.equals("netaucs"))
		{
		pw.println("<center><h2>Administrator Page</h2><br>");
		pw.println("<table><tr><td><a href='http://localhost:7001/servlet/DelItemCat'>Delete a Item Category</a> </td></tr>");
		pw.println("<tr><td><a href='http://localhost:7001/servlet/DelBidItem'>Delete Bid Item </a> </td></tr>");
		pw.println("</table><br>");
		}
		else
		{
		pw.println("<center><h2>Sorry u r not Valid Admin User </h2><br><br><br>");
		pw.println("<input type=button value=back onClick='history.back()'>");
		}
		pw.println("<br><br><br><hr><CENTER><br><br>");
		pw.println("<A href=http://localhost:7001/servlet/NewToday><i>New Items</A>");
		pw.println("<A href=http://localhost:7001/servlet/EndToday>Closing Items</A>");
		pw.println("<A href=http://localhost:7001/sell.htm>Sell Items</A>");
		pw.println("<A href=http://localhost:7001/home.htm>Home</A>");
		pw.println("<br><br>");
	}
}
