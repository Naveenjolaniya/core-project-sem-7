import javax.ejb.*;
import java.rmi.*;
import java.util.*;

public interface Registration extends EJBObject {
   boolean verifyPassword(String password) throws RemoteException;
   String getEmailAddress() throws RemoteException;
   String getUser() throws RemoteException;
//   int adjustAccount(double amount) throws RemoteException;
//   double getBalance() throws RemoteException;
}