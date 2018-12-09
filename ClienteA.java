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
		String _ior = null;
		String _usage = "Usage: JAConsole iiop://<host>:<port>\n";

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
			int type, quant = 0;

			while(option != 5)
			{
				System.out.println("\n -- MENU -- \n");
				System.out.println(" 1 - Add Produto \n");
				System.out.println(" 2 - Remove Produto \n");
				System.out.println(" 3 - Lista Produto \n");
				System.out.println(" 5 - Sair\n");
				System.out.println("Resposta: ");
				option = scan.nextInt();
				

				switch(option)
				{
					case 1:
							System.out.println("\n Digite qual produto entre (0,1,2,3,4). E a quantidade.\n");
							System.out.println("Resposta: ");
							type = scan.nextInt();
							quant = scan.nextInt();
							//System.out.println("Digite a quantidade do produto. \n");
							//System.out.println("Resposta: ");					
							depositImpl.addProd(quant, type);
						break;
					case 2:
							System.out.println("\n Digite qual produto entre (0,1,2,3,4).\n");
							System.out.println("Resposta: ");
							type = scan.nextInt();
							System.out.println("Digite a quantidade do produto. \n");
							System.out.println("Resposta: ");
							quant = scan.nextInt();
							depositImpl.remProd(type, quant);
						break;
					case 3:
							int test = 0;
							System.out.println("\n Digite qual produto entre (0,1,2,3,4).\n");
							System.out.println("Resposta: ");
							test = scan.nextInt();
							System.out.println(depositImpl.showProd(test));
						break;
					default:
						System.out.println("Error!");
				}
			}



			//depositImpl.shutdown();
		}
		catch(org.omg.CORBA.COMM_FAILURE cf)
		{
			 // The server is not running, or the specified URL is
           // wrong. 
             /*System.out.println(
              "Error: could not connect to server at " + _ior + "\n"
             + "Make sure the specified address is correct and the "
             + "server is running.\n\n" + _usage ); */

            System.out.println("\n Problema na conexao, rede lenta. \n\n");

            long startTime = System.currentTimeMillis();
			long elapsedTime = 0L;

			while (elapsedTime < 2*15*10000) {
				 //perform db poll/check
				   elapsedTime = (new Date()).getTime() - startTime;
			}

			System.out.println("Error: não foi possível conectar no servidor. \n\n");
			System.out.println("Tente novamente mais tarde.");
		}
		catch (Exception e) 
		{
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
        }
		return;
	}

}
