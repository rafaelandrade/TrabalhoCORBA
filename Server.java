import EmailApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.Properties;

//its here that i creat my methods
class EmailImpl extends EmailPOA
{
	private ORB orb;

		public void setORB(ORB orb_val)
		{
			orb = orb_val;
		}

		//implementes sayHello() method "for test"
		public String sayHello()
		{
			return "\n Hello World !! \n";
		}

		public String message(String msg)
		{
			return msg;			
		}

		//implements shutdown method
		public void shutdown()
		{
			orb.shutdown(false);
		}
}


public class Server
{
	public static void main(String args[])
	{
		try{
			//create and initialize the ORB
			ORB orb = ORB.init(args, null);

			//get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			//create servant and register it with the ORB
			EmailImpl emailImpl = new EmailImpl();
			emailImpl.setORB(orb);

			//get reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(emailImpl);
			Email href = EmailHelper.narrow(ref);

			//get the root naming context
			// NameService invokes the name service
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			//Use NamingContextExt which is part of the Interoperable
			//Naming Service (INS) specification
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			//bind the Object Reference in Naming
			String name = "Email";
			NameComponent path[] = ncRef.to_name( name );
			ncRef.rebind(path, href);

			System.out.println("Server ready and waiting ...");

			//waiting for invocations from clients
			orb.run(); 


		}catch(Exception e)
		{
			System.err.println("Error:" + e);
			e.printStackTrace(System.out);
		}

		System.out.println("Server Exiting ...");
	}
}
