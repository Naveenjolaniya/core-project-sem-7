import java.rmi.*;
import javax.ejb.*;
public interface AuctionHome extends EJBHome
{
public Auction create()throws CreateException,RemoteException;
}