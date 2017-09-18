import javax.ejb.*;
import java.rmi.*;
public interface Auction extends EJBObject
{
public void insert(String bid,int amt,String itemid) throws RemoteException;
}