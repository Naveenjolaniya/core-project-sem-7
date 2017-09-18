import java.rmi.*;
import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;
import java.util.*;
import java.sql.*;

public class SellBean implements EntityBean
{

String seller,itemName,cat,summary,desc,itemid;
double startPrice,incrPrice;
int acDays;

public String ejbCreate(String itemid,String itemname,String cat,String desc,String summary,double startPrice,double incrPrice,int acDays,String seller)throws RemoteException
{
	try{
	this.seller=seller;
	this.itemid=itemid;
	this.itemName=itemname;
	this.cat=cat;
	this.summary=summary;
	this.desc=desc;
	this.startPrice=startPrice;
	this.incrPrice=incrPrice;
	this.acDays=acDays;

	Connection con = getConnection();
	Statement st=con.createStatement();
	int i=st.executeUpdate("insert into item_master values('"+itemid+"','"+cat+"','"+itemName+"','"+desc+"','"+summary+"',"+startPrice+","+incrPrice+",to_date(sysdate),to_date(sysdate+"+acDays+"),'"+seller+"',0) ");
	con.close();
	}catch(Exception e){e.printStackTrace();}
	return itemid;
}

public void ejbPostCreate(String itemid,String itemname,String cat,String desc,String summary,double startPrice,double incrPrice,int acDays,String seller)throws RemoteException{}
public void ejbActivate()throws RemoteException{}
public void ejbPassivate()throws RemoteException{}
public void ejbRemove()throws RemoteException{}
public void setEntityContext(EntityContext ctx)throws RemoteException{}
public void unsetEntityContext()throws RemoteException{}
public void ejbLoad()throws RemoteException{}
public void ejbStore()throws RemoteException{}

public String ejbFindByPrimaryKey(String itemid)throws FinderException,RemoteException
{ return null; }

public Connection getConnection()throws Exception
{
	DataSource ds=null;
	try{
	Properties p = new Properties();
	p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
	InitialContext ctx = new InitialContext(p);
	ds = (javax.sql.DataSource)ctx.lookup("auctionDataSource");
	}catch(Exception e){e.printStackTrace();}
	return ds.getConnection();
}
}