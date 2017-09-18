import java.rmi.RemoteException;
import javax.ejb.*;
import java.util.*;
import java.text.NumberFormat;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

// Bean-managed persistence using JDBC[TM]

public class RegistrationBean implements EntityBean 
{
	private EntityContext ctx;
	private PreparedStatement ps=null;
	private Connection con=null;
	public String theuser, password, fname,lname,emailaddress,phno,address,city,state,pin,country,creditcard;
	private DataSource ds;
	public Connection getConnection() throws SQLException 
	{
		try
		{
			Properties p = new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
			InitialContext ctx  = new InitialContext(p);
			ds = (DataSource)ctx.lookup("auctionDataSource");
			con = ds.getConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	public boolean verifyPassword(String password) throws RemoteException
	{ 
		return this.password.equals(password);
	}
	public String getEmailAddress() throws RemoteException
	{ 
		return emailaddress;
	}
	public String getUser() throws RemoteException
	{ 
		return theuser;
	}
	public RegistrationPK ejbCreate(String theuser, String password, String fname,String lname,String emailaddress, String phno,String address,String city,String state,String pin,String country,String creditcard) throws CreateException, RemoteException
	{
		this.theuser=theuser;
		this.password=password;
		this.fname=fname;
		this.lname=lname;
		this.emailaddress=emailaddress;
		this.phno=phno;
		this.address=address;
		this.city=city;
		this.state=state;
		this.pin=pin;
		this.country=country;
		this.creditcard=creditcard;
		try
		{
			con=getConnection();
			ps=con.prepareStatement("insert into uinfo_master values (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, theuser);
			ps.setString(2, password);
			ps.setString(3, fname);
			ps.setString(4, lname);
			ps.setString(5, emailaddress);
			ps.setString(6, phno);
			ps.setString(7, address);
			ps.setString(8, city);
			ps.setString(9, state);
			ps.setString(10, pin);
			ps.setString(11, country);
			ps.setString(12, creditcard);
			if (ps.executeUpdate() != 1)
			{
				throw new CreateException ("JDBC did not create any row");
			}
			RegistrationPK primaryKey = new RegistrationPK();
			primaryKey.theuser = theuser;
			return primaryKey;
		}
		catch (CreateException ce)
		{
			throw ce;
		}
		catch (SQLException sqe) 
		{
			throw new CreateException (sqe.getMessage());
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (Exception ignore)
			{
			}
			try
			{
				con.close();
			}
			catch (Exception ignore)
			{
			}
		}
	}
	public void ejbPostCreate(String theuser, String password, String fname,String lname,String emailaddress, String phno,String address,String city,String state,String pin,String country,String creditcard) throws CreateException, RemoteException
	{
	}
	public void setEntityContext(javax.ejb.EntityContext ctx) throws RemoteException
	{
		this.ctx = ctx;
	}
	public void unsetEntityContext() throws RemoteException
	{
		ctx = null;  
	} 
	public void ejbRemove() throws RemoteException, RemoveException
	{
	}
	public void ejbActivate() throws RemoteException
	{
	}
	public void ejbPassivate() throws RemoteException
	{
	}
	public void ejbLoad() throws RemoteException
	{ 
		try
		{
			refresh((RegistrationPK) ctx.getPrimaryKey());
		}
		catch (FinderException fe)
		{
			throw new RemoteException (fe.getMessage());
		}
	}
	public void ejbStore() throws RemoteException
	{ 
	}
	public RegistrationPK ejbFindByPrimaryKey(RegistrationPK pk) throws FinderException, RemoteException
	{
		if ((pk == null) || (pk.theuser == null))
		{
			throw new FinderException ("primary key cannot be null");
		}
		refresh(pk);
		return pk;
	}
	private void refresh(RegistrationPK pk) throws FinderException, RemoteException
	{
		if (pk == null)
		{
			throw new RemoteException ("primary key cannot be null");
		}
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
			con=getConnection();
			ps=con.prepareStatement("select * from uinfo_master where uname = ?");
			ps.setString(1, pk.theuser);
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			if (rs.next())
			{
				theuser = pk.theuser;
				password=rs.getString(2);
				fname=rs.getString(3);
				lname=rs.getString(4);
				emailaddress=rs.getString(5);
				phno=rs.getString(6);
				address=rs.getString(7);
				city=rs.getString(8);
				state=rs.getString(9);
				pin=rs.getString(10);
				country=rs.getString(11);
				creditcard=rs.getString(12);
			}
			else
			{
				throw new FinderException ("Refresh: Registration ("+ pk.theuser + ") not found");
			}
		}
		catch (SQLException sqe)
		{
			throw new RemoteException (sqe.getMessage());
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (Exception ignore)
			{
			}
			try
			{
				con.close();
			}
			catch (Exception ignore)
			{
			}
		}
	}
}