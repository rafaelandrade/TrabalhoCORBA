import EmailApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class ClienteA
{
	static Email emailImpl;

	public static void main(String args[])
	{
		try{
			// create and initicialize the ORB
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			// Use NamingContextExt instead of NamingContex. This is
			// part of the Interoperable naming Service
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// Resolv the Object Reference in Maning
			String name = "Email";
			emailImpl = EmailHelper.narrow(ncRef.resolve_str(name));

			System.out.println("Obtained a handle on server object: " + emailImpl);

			String txt = "POTATOESSSS!";
			System.out.println(emailImpl.message(txt));
			//System.out.println(emailImpl.sayHello());
			emailImpl.shutdown();

		}catch(Exception e)
		{
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}
}
