import java.rmi.*;
import javax.ejb.*;

public interface SearchHome extends EJBHome
{

public Search create()throws RemoteException,CreateException;

}