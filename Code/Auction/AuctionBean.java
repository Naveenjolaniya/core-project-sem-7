import java.rmi.*;
import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;
import java.util.*;
import java.sql.*;

public class AuctionBean implements SessionBean
{
	public void  insert(String bid,int amt,String itemid)throws RemoteException
	{
	try
	{
		Connection con = getConnection();
		Statement st=con.createStatement();
		int i=st.executeUpdate("insert into bidding_info values('"+bid+"',"+amt+",'"+itemid+"',sysdate) ");
		Statement st1=con.createStatement();
		st1.executeUpdate("update item_master set bidcnt=bidcnt+1 where itemid='"+itemid+"' ");
		con.close();
	}catch(Exception e){e.printStackTrace();}
	}
	public void ejbCreate()throws RemoteException{}
	public void ejbRemove()throws RemoteException{}
	public void ejbActivate()throws RemoteException{}
	public void ejbPassivate()throws RemoteException{}
	public void setSessionContext(SessionContext ctx)throws RemoteException{}

	public Connection getConnection()throws Exception
	{
		DataSource ds=null;
		try
		{
			Properties p = new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
			InitialContext ctx = new InitialContext(p);
			ds = (javax.sql.DataSource)ctx.lookup("auctionDataSource");
		}catch(Exception e){e.printStackTrace();}
	return ds.getConnection();
	}
}