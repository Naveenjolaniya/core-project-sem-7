public class RegistrationPK implements java.io.Serializable 
{
	public String theuser;
	public RegistrationPK() {}
	public int hashCode()
	{
		return theuser.hashCode();
	} 
	public boolean equals(Object o) 
	{
		if (o instanceof RegistrationPK) 
		{
			return theuser.equals(((RegistrationPK)o).theuser);
		}
		else
		{
			return false;
		}
  	}
}
