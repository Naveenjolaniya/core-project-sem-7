import java.rmi.*;
import javax.ejb.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

public class SearchBean implements SessionBean
{
	DataSource ds;
	public void ejbCreate()throws RemoteException{}
	public void ejbRemove()throws RemoteException{}
	public void ejbActivate()throws RemoteException{}
	public void ejbPassivate()throws RemoteException{}
	public void setSessionContext(SessionContext ctx)throws RemoteException{}
	
	public Vector searchItemInfo(String s)throws RemoteException
	{
		Vector v = new Vector();
		try
		{
			Connection c1 = getConnection();
			PreparedStatement ps = c1.prepareStatement("select * from item_master where summary like ? and enddate>=sysdate");
			ps.setString(1,"%"+s+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String st = rs.getString(1)+"~"+rs.getString(2)+"~"+rs.getString(3)+"~"+rs.getString(4)+"~"+rs.getString(5)+"~"+rs.getString(6)+"~"+rs.getString(7)+"~"+rs.getString(8)+"~"+rs.getString(9)+"~"+rs.getString(10)+"~"+rs.getString(11);
				v.addElement(st);		
			}	
			c1.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return v;
	}
	
	public Vector searchCatInfo(String s)throws RemoteException
	{
		Vector data = new Vector();
		String st="";
		try
		{
				Connection c2 = getConnection();
				PreparedStatement ps = c2.prepareStatement("select catid from category_master where cat_name=?");
				ps.setString(1,s);
				ResultSet rs = ps.executeQuery();
				rs.next();
				String id = rs.getString(1);
				rs.close();
				ps.close();
				if(id != null)
				{
				PreparedStatement psmt = c2.prepareStatement("select * from item_master where catid =? and enddate>=sysdate");
					psmt.setString(1,id);	
					ResultSet recs = psmt.executeQuery();
					while(recs.next())
					{
						st = recs.getString(1)+"~"+recs.getString(2)+"~"+recs.getString(3)+"~"+recs.getString(4)+"~"+recs.getString(5)+"~"+recs.getString(6)+"~"+recs.getString(7)+"~"+recs.getString(8)+"~"+recs.getString(9)+"~"+recs.getString(10)+"~"+recs.getString(11);
						data.addElement(st);
					}
				}
				c2.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}

	public Vector searchCatItemInfo(String sString,String cName)throws RemoteException
	{
		Vector data = new Vector();
		try
		{
			Connection c3 = getConnection();
			PreparedStatement ps = c3.prepareStatement("select catid from category_master where cat_name=?");
			ps.setString(1,cName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String id = rs.getString(1);
			rs.close();
			ps.close();
			if(id != null)
			{
				PreparedStatement psmt = c3.prepareStatement("select * from item_master where catid =? and summary like ? and enddate>=sysdate");
				psmt.setString(1,id);	
				psmt.setString(2,"%"+sString+"%");
				ResultSet recs = psmt.executeQuery();
				while(recs.next())
				{
					String st = recs.getString(1)+"~"+recs.getString(2)+"~"+recs.getString(3)+"~"+recs.getString(4)+"~"+recs.getString(5)+"~"+recs.getString(6)+"~"+recs.getString(7)+"~"+recs.getString(8)+"~"+recs.getString(9)+"~"+recs.getString(10)+"~"+recs.getString(11);
					data.addElement(st);
				}
				c3.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}

	public Connection getConnection()
	{
		Connection gc=null;
		try
		{
			Properties p = new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
			InitialContext ctx  = new InitialContext(p);
			ds = (DataSource)ctx.lookup("auctionDataSource");
			gc = ds.getConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return gc;
	}
}