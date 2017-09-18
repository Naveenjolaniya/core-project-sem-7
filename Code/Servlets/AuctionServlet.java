import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.ejb.*;
import java.rmi.*;
import javax.naming.*;
import java.util.*;

public class AuctionServlet extends HttpServlet 
{
	Connection con;
	PrintWriter pw;
 
	public void init(ServletConfig sc) throws ServletException
	{
	try
	{
		super.init(sc);
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con=DriverManager.getConnection("jdbc:odbc:auction","auction","auction");

	}catch(Exception ce) { ce.printStackTrace();}
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		pw=res.getWriter();
	try{
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
		pw.println("  <TR>    <TD colSpan=12 rowSpan=3><IMG height=49 alt=''       src='http://localhost:7001/home_files/name-copy_16.gif' width=359></TD>");
		pw.println("    <TD><IMG height=18 alt='' src='http://localhost:7001/home_files/spacer.gif' width=1></TD></TR>");
		pw.println("  <TR>    <TD width=60><IMG height=16 alt='' src='http://localhost:7001/home_files/name-copy_17.gif'       width=60></TD>");
		pw.println("    <TD width=21 rowSpan=2><IMG height=31 alt=''       src='http://localhost:7001/home_files/name-copy_18.gif' width=21></TD>");
		pw.println("    <TD><IMG height=16 alt='' src='http://localhost:7001/home_files/spacer.gif' width=1></TD></TR>  <TR>");
		pw.println("    <TD><IMG height=15 alt='' src='http://localhost:7001/home_files/name-copy_19.gif' width=60></TD>");
		pw.println("    <TD><IMG height=15 alt='' src='http://localhost:7001/home_files/spacer.gif'   width=1></TD></TR></TBODY></TABLE><br><br>");

		Statement st=con.createStatement();
		String bidder=req.getParameter("bidder");
		String pwd=req.getParameter("password");
		String amount=req.getParameter("amount");
		if(amount.length()>0)
		{
		int amt=Integer.parseInt(amount);
		String id=req.getParameter("id");
		int hbid=Integer.parseInt(req.getParameter("hbid"));
		int min=Integer.parseInt(req.getParameter("min"));
		ResultSet rs=st.executeQuery("select pwd from uinfo_master where uname='"+bidder+"' ");
		if(rs.next())
		{
			String dpwd=rs.getString(1);
			if(!pwd.equals(dpwd))
				pw.println("<center><b>in valid password</b></center><br<br>");
			else if(amt>=(hbid+min))
			{
				Statement stmt=con.createStatement();
				ResultSet rst=stmt.executeQuery("select enddate from item_master where itemid='"+id+"' ");
				rst.next();
				java.sql.Date dt=rst.getDate(1);
				Statement stmt2=con.createStatement();
				ResultSet rst1=stmt2.executeQuery("select sysdate from dual");
				rst1.next();
				java.sql.Date dt1=rst1.getDate(1);
				java.util.StringTokenizer stok=new java.util.StringTokenizer(dt.toString(),"-");
				StringTokenizer token=new StringTokenizer(dt.toString(),"-");
				int year=Integer.parseInt(token.nextToken());
				int month=Integer.parseInt(token.nextToken());
				int day=Integer.parseInt(token.nextToken());
		
				StringTokenizer token1=new StringTokenizer(dt1.toString(),"-");
				int year1=Integer.parseInt(token1.nextToken());
				int month1=Integer.parseInt(token1.nextToken());
				int day1=Integer.parseInt(token1.nextToken());
/*				if((year >= year1))
				{
					if((month >= month1))
					{
						if((day >= day1))
						{*/
							java.util.Properties p = new java.util.Properties();
							p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
							InitialContext ctx = new InitialContext(p);
			
							AuctionHome home = (AuctionHome)ctx.lookup("AuctionHome");
							Auction remote = (Auction)home.create();

							remote.insert(bidder,amt,id);
							pw.println("<center><b>congratulations u have sucessfully bid for the item</b><br<br></center>");
/*						}
						else
						{
							pw.println("<center><b>auction is closed a "+(day1-day)+" day before</b><br<br></center>");
						}
					}
					else
					{
						pw.println("<center><b>auction closed a "+(month1-month)+" month before</b><br<br></center>");
					}
				}
				else
				{
					pw.println("<center><b>auction closed a "+(year1-year)+" year before</b><br<br></center>");
				}*/
			}
			else
			{
				pw.println("<center><b>bid more than current value + mininmum increament</b><br<br></center>");	
			}
		}
		else
		{
			pw.println("<center><b>User Name doesnot exist</b></center><br<br>");
		}
		}
		else
		{
			pw.println("<br><center>specify the bid amount</center>");
		}

	}catch(NumberFormatException ne) { pw.println("<br><b><center>enter a numeric value</center></b><br><br>");
	}catch(Exception e) { e.printStackTrace(); }
		pw.println("<br><br><br><CENTER><STRONG></STRONG>");
		pw.println("<A href=http://localhost:7001/servlet/NewToday>New Items</A><STRONG></STRONG>");
		pw.println("<A href=http://localhost:7001/servlet/EndToday>Closing Items</A>");
		pw.println("<STRONG></STRONG><A href=http://localhost:7001/sell.htm>Sell Items</A>");
		pw.println("<STRONG></STRONG><A href=http://localhost:7001/home.htm>Home</A>");

	}
}