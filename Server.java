/* 
 * Server.java 
 * 
 * Version: 
        Version 3.0
 *      11/14/2016
 * 
 * Revisions: 
 *     3 
 */
import java.rmi.Naming;


public class Server 
{
	/**
	 * This is a Server class which binds the object in the registry
	 * 
	 * .
	 *
	 * @author      Sandhya Murali
	 * 
	 */

	
	public static void main(String[] args) 
	{
		try
		{  
			ModelInterface stub=new Model();  
			Naming.rebind("rmi://localhost:5000/hello",stub); 
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
	}  

	}


