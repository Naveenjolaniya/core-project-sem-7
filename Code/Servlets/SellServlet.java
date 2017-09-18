import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.ejb.*;
import javax.naming.*;
import java.rmi.*;
public class SellServlet extends HttpServlet 
{
	protected SessionCache sessionCache;
	protected long flushTimeout=600000;
	protected long sessionTimeout=7200000;
	static
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException 
	{
		return DriverManager.getConnection("jdbc:odbc:auction","auction","auction");
    	}
    	public void init(ServletConfig config) throws ServletException  
	{
    		super.init(config);
    		sessionCache = new SessionCache (flushTimeout);
    	}
    	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
   	{
     		Cookie thisCookie=null;
     		boolean activeSession;
     		String cmd;
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<BODY bgColor=#a3d881 leftMargin=0 topMargin=0 MARGINHEIGHT=0 MARGINWIDTH=0><!-- ImageReady Slices (Untitled-1) -->");
		pw.println("<DIV id=Layer1 style='Z-INDEX: 1; LEFT: 410px; WIDTH: 328px; POSITION: absolute; TOP: 65px; HEIGHT: 35px'>");
		pw.println("<FORM name=form1 action='http://localhost:7001/servlet/SearchServlet'  method=post ><INPUT name=sstring> ");
		pw.println("<SELECT  name=category > <option >Choose Category</option>");
		pw.println("<OPTION>Electronics</OPTION> <OPTION>Computers</OPTION> ");
		pw.println("<OPTION>Mobiles</OPTION> <OPTION>Jewellery</OPTION> <OPTION>Art &amp; ");
		pw.println("Collections</OPTION> <OPTION>Home &amp; Life</OPTION> ");
		pw.println("<OPTION>Travel</OPTION></SELECT> <INPUT type=submit value=Go > ");
		pw.println("</FORM></DIV><!-- End ImageReady Slices -->");
		pw.println("<TABLE cellSpacing=0 cellPadding=0 width=781 border=0>");
		pw.println("<TBODY>  <TR>    <TD width=340 rowSpan=5><IMG height=100 alt='' src='http://localhost:7001/home_files/name-copy_01.gif' width=340></TD>");
		pw.println("<TD colSpan=2 rowSpan=3><IMG height=69 alt='' src='http://localhost:7001/home_files/name-copy_02.gif' width=81></TD>");
		pw.println("<TD colSpan=12><IMG height=39 alt='' src='http://localhost:7001/home_files/name-copy_03.gif'      width=359></TD>");
		pw.println("<TD width=20><IMG height=39 alt='' src='http://localhost:7001/home_files/spacer.gif'   width=1></TD></TR>");
		pw.println("<TR><TD width=51><A href='http://localhost:7001/home.htm'><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_04.gif' width=51 border=0></A></TD>");
		pw.println("<TD width=4><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_05.gif'       width=4></TD>");
		pw.println("<TD width=55><A href='http://localhost:7001/login.htm'><IMG height=12       alt='' src='http://localhost:7001/home_files/name-copy_06.gif' width=55 border=0></A></TD>");
		pw.println("<TD width=4><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_07.gif'       width=4></TD>");
		pw.println("<TD width=65><A href='http://localhost:7001/registration.htm'><IMG       height=12 alt='' src='http://localhost:7001/home_files/name-copy_08.gif' width=65     border=0></A></TD>");
		pw.println("<TD width=7><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_09.gif'       width=7></TD>");
		pw.println("<TD width=40><A href='http://localhost:7001/sell.htm'><IMG height=12       alt='' src='http://localhost:7001/home_files/name-copy_10.gif' width=40 border=0></A></TD>");
		pw.println("<TD width=5><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_11.gif'       width=5></TD>");
		pw.println("<TD width=46><A href='http://localhost:7001/help.htm'><IMG height=12      alt='' src='http://localhost:7001/home_files/name-copy_12.gif' width=46 border=0></A></TD>");
		pw.println("<TD width=6><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_13.gif'       width=6></TD>");
		pw.println("<TD width=64><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_14.gif'       width=64></TD>");
		pw.println("<TD width=12><IMG height=12 alt='' src='http://localhost:7001/home_files/name-copy_15.gif'       width=12></TD>");
		pw.println("<TD><IMG height=12 alt='' src='http://localhost:7001/home_files/spacer.gif' width=1></TD></TR>");
		pw.println("<TR><TD colSpan=12 rowSpan=3><IMG height=49 alt=''       src='http://localhost:7001/home_files/name-copy_16.gif' width=359></TD>");
		pw.println("<TD><IMG height=18 alt='' src='http://localhost:7001/home_files/spacer.gif' width=1></TD></TR>");
		pw.println("<TR><TD width=60><IMG height=16 alt='' src='http://localhost:7001/home_files/name-copy_17.gif'       width=60></TD>");
		pw.println("<TD width=21 rowSpan=2><IMG height=31 alt=''       src='http://localhost:7001/home_files/name-copy_18.gif' width=21></TD>");
		pw.println("<TD><IMG height=16 alt='' src='http://localhost:7001/home_files/spacer.gif' width=1></TD></TR>  <TR>");
		pw.println("<TD><IMG height=15 alt='' src='http://localhost:7001/home_files/name-copy_19.gif' width=60></TD>");
		pw.println("<TD><IMG height=15 alt='' src='http://localhost:7001/home_files/spacer.gif'   width=1></TD></TR></TBODY></TABLE><br><br>");
   		Session session = validateSession (request, response);
     		setNoCache(request, response);
		try
		{	
         		if (session == null ) 
			{
				String seller=request.getParameter("seller");
				String pwd=request.getParameter("password");
				String itemname=request.getParameter("itemname");
				String catname=request.getParameter("category");
				String desc=request.getParameter("description");
				String summary=request.getParameter("summary");
				double startprice=Double.parseDouble(request.getParameter("startprice"));
				double incrprice=Double.parseDouble(request.getParameter("incrprice"));
				int acDays=Integer.parseInt(request.getParameter("auctiondays"));
				Connection con=getConnection();
				Statement st=con.createStatement();
				ResultSet ress=st.executeQuery("select pwd from uinfo_master where uname='"+seller+"'");
				boolean bol=ress.next();
				if(bol==true)
				{
					String password=ress.getString(1);
					if(password.equals(pwd))
					{
						session = startSession(request.getParameter("seller"), request.getParameter ("password"), response);
						PreparedStatement ps1=con.prepareStatement("select catid from category_master where cat_name=?");
						ps1.setString(1,catname);
						ResultSet rs=ps1.executeQuery();
						rs.next();
						String catid=rs.getString(1);
						int cword=catid.charAt(0);
						PreparedStatement ps2=con.prepareStatement("select max(itemid) from item_master where itemid like '"+(char)cword+"%' ");
						ResultSet rs2=ps2.executeQuery();
						rs2.next();
						String temp="";
						int titemid=0;
						temp=""+rs2.getString(1);
						 if(temp.equals("null"))
	                        		titemid=0;
						else
							titemid=Integer.parseInt(temp.substring(1));
						String itemid="";
						titemid++;
			                	itemid=""+(char)cword+"000"+titemid;
						Properties p=new Properties();
						p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
						InitialContext ctx=new InitialContext(p);
						SellHome sh=(SellHome)ctx.lookup("SellHome");
						Sell s=(Sell)sh.create(itemid,itemname,catid,desc,summary,startprice,incrprice,acDays,seller);
						pw.println("<b> U have Sucess fully have posted u r item to auction online<b><br>");
						pw.println("<br> <center>u r item id is: <b> "+itemid);
						con.close();
					}
					else
					{
						pw.println("<br><br>wrong password");
					}
				}
				else
				{
					pw.println("<br><br>user name does not exist");
				}
			}
			pw.println("</b><br><br><br></CENTER><CENTER><STRONG></STRONG>");
			pw.println("<A href=http://localhost:7001/servlet/NewToday>New Items</A><STRONG></STRONG>");
			pw.println("<A href=http://localhost:7001/servlet/EndToday>Closing Items</A>");
			pw.println("<STRONG></STRONG><A href=http://localhost:7001/sell.htm>Sell Items</A>");
			pw.println("<STRONG></STRONG><A href=http://localhost:7001/home.htm>Home/Search</A>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	protected boolean verifyPassword(String theuser, String password)
	{
		String originalPassword=null;
		try
		{
			Connection con=getConnection();
			Statement stmt= con.createStatement();
			stmt.executeQuery("select pwd from uinfo_master where uname='"+theuser+"'");
			ResultSet rs = stmt.getResultSet();
			if(rs.next())
				originalPassword=rs.getString(1);
			stmt.close();
			con.close();
			if(originalPassword.equals(password))
				return true;
			else
				return false;
		}
		 catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	protected Session startSession (String theuser, String password, HttpServletResponse response)
	{
		Session session = null;
		if ( verifyPassword(theuser, password) )
		{
			session = new Session (theuser);
			session.setExpires(sessionTimeout + System.currentTimeMillis());
			sessionCache.put(session);
			Cookie c = new Cookie("AUCTION", String.valueOf (session.getId()));
			c.setMaxAge (-1);
			response.addCookie(c);
		}
		return session;
	}
	private Session validateSession(HttpServletRequest request, HttpServletResponse response) 
	{
		Cookie c[] = request.getCookies();
		Session session = null;
		if ( c != null )
		{
			for (int i=0; i < c.length && session == null; i++ )
			{
				if(c[i].getName().equals("AUCTION"))
				{
					String key = String.valueOf (c[i].getValue());
					session=sessionCache.get (key);
				}
			}
		}
		return session;
	}
    protected void endSession (Session session) {
       synchronized (sessionCache) {
          sessionCache.remove (session);
       }
    }

    private void setNoCache (HttpServletRequest request, 
                                       HttpServletResponse response) {

       if(request.getProtocol().compareTo ("HTTP/1.0") == 0) {
           response.setHeader ("Pragma", "no-cache");
       } else if (request.getProtocol().compareTo ("HTTP/1.1") == 0) {
           response.setHeader ("Cache-Control", "no-cache");
       }
       response.setDateHeader ("Expires", 0);
    }

}

