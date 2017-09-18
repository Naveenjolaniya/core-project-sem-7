import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ItemDetails2 extends HttpServlet 
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
	try{
		res.setContentType("text/html");
		pw=res.getWriter();

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
		pw.println("<h2>Item Wise Details</h2>");

		String itid =req.getParameter("id");
		System.out.println(itid);
		Statement stt=con.createStatement();
		ResultSet rss=stt.executeQuery("select itemname from item_master where itemid='"+itid+"' ");
		rss.next();
		String iname=rss.getString(1);
		pw.println("<center><font size=+2 face=Arial ><u>"+iname+"</u></font></center><br>");
		Statement st1 = con.createStatement();
		ResultSet rs1 = st1.executeQuery("select bidderid,bamt from bidding_info where itemid='"+itid+"' and bamt=(select max(bamt) from bidding_info where itemid='"+itid+"')"); 
		String bid="No Bidder Till now";
		int bamt=0;
		if(rs1.next()){
		 bid = rs1.getString(1);
		bamt = rs1.getInt(2);	
		}
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from item_master where itemid='"+itid+"' ");
		String sp="";
		String minincr="";
		String sell="";
		while(rs.next())
		{
			String desp=rs.getString(4);
			sp=rs.getString(6);
			minincr=rs.getString(7);
			java.sql.Date stdate=rs.getDate(8);
			java.sql.Date edate=rs.getDate(9);
			String seller=rs.getString(10);
			sell=seller;
			String nob=rs.getString(11);
			if (bamt==0)
				bamt=Integer.parseInt(sp);
			pw.println("<table align=center border=2>");
			pw.println("<TR><TD>Start Price</TD><td>" + sp +"</td></tr>");
			pw.println("<TR><TD>MinimunIncrement</TD><td>" + minincr +"</td></tr>");
			pw.println("<TR><TD>Number Of Bids</TD><td>" + nob +"</td></tr>");
			pw.println("<TR><TD>Auction Start Date</TD><td>" + stdate +"</td></tr>");
			pw.println("<TR><TD>Auction Ending Date</TD><td>" + edate +"</td></tr>");
			pw.println("<TR><TD>Seller</TD><td>" + seller +"</td></tr>");
			pw.println("<TR><TD>High Bidder</TD><td>" + bid +"</td></tr>");
			pw.println("<tr><td>Current Bid Price</td><td>"+bamt+"</td></tr>");
			pw.println("<TR><TD>Description</TD><td>" + desp +"</td></tr>");
		}

		pw.println("</table><br><br><center>");
		pw.println("<A href=http://localhost:7001/servlet/NewToday>New Items</A><STRONG></STRONG>");
		pw.println("<A href=http://localhost:7001/servlet/EndToday>Closing Items</A>");
		pw.println("<STRONG></STRONG><A href=http://localhost:7001/sell.htm>Sell Items</A>");
		pw.println("<STRONG></STRONG><A href=http://localhost:7001/home.htm>Home</A>");

		}catch(Exception e){e.printStackTrace();}
	}
}
