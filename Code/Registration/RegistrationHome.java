import javax.ejb.*;
import java.rmi.*;
import java.util.*;
public interface RegistrationHome extends EJBHome 
{
 Registration create(String theuser, String password, String fname,String lname,String emailaddress, String phno,String address,String city,String state,String pin,String country,String creditcard) throws CreateException, RemoteException;
 Registration findByPrimaryKey(RegistrationPK theuser) throws FinderException, RemoteException;
}

