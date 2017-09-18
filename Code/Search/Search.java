import java.rmi.*;
import javax.ejb.*;
import java.util.Vector;

public interface Search extends EJBObject
{
public Vector searchItemInfo(String s)throws RemoteException;
public Vector searchCatItemInfo(String s,String s1)throws RemoteException;
public Vector searchCatInfo(String s)throws RemoteException;
}