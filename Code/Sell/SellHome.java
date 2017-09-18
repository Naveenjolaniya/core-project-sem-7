import java.rmi.*;
import javax.ejb.*;
public interface SellHome extends EJBHome
{
public Sell create(String itemid,String itemname,String catid,String desc,String summary,double startprice,double incrprice,int aucdays,String seller)throws CreateException,RemoteException;

public Sell findByPrimaryKey(String itemid)throws FinderException,RemoteException;
}