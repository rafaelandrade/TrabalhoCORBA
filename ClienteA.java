import DepositApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;

public class ClienteA
{
	static Deposit depositImpl;

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
			String name = "Deposit";
			depositImpl = DepositHelper.narrow(ncRef.resolve_str(name));

			System.out.println("Obtained a handle on server object: " + depositImpl);

			Scanner scan = new Scanner(System.in);
			
			int option = 0;

			/*
			while(option != 5)
			{
				System.out.println("\n -- MENU -- \n");
				System.out.println(" 1 - Add Produto \n");
				System.out.println(" 2 - Remove Produto \n");
				System.out.println(" 3 - Lista Produto \n");
				System.out.println(" 5 - Sair");
				
				option = scan.nextInt();
				double type, quant = 0f;

				switch(option)
				{
					case 1:
							System.out.println("\n Digite qual produto entre (0,1,2,3,4).\n");
							type = scan.nextInt();
							System.out.println("Digite a quantidade do produto. \n");
							quant = scan.nextInt();
							depositImpl.addProd(type, quant);
						break;
					case 2:
							System.out.println("\n Digite qual produto entre (0,1,2,3,4).\n");
							type = scan.nextInt();
							System.out.println("Digite a quantidade do produto. \n");
							quant = scan.nextInt();
							depositImpl.remProd(type, quant);
						break;
					case 3:
							System.out.println("\n Digite qual produto entre (0,1,2,3,4).\n");
							type = scan.nextInt();
							System.out.println(depositImpl.showProd(type));
						break;
					default:
						System.out.println("Error!");
				}
			}
			*/
			double type, quant = 0f;
			quant = scan.nextInt();
			type = scan.nextInt();
			depositImpl.addProd(type, quant);
			System.out.println(depositImpl.showProd(type));
			//System.out.println(depositImpl.sayHello());
			depositImpl.shutdown();

		}catch(Exception e)
		{
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}
}
