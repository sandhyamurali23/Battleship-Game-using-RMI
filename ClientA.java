/* 
 * ClientA.java 
 * 
 * Version: 
        Version 3.0
 *      11/14/2016
 * 
 * Revisions: 
 *     3 
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class ClientA implements ClientInterface
{
	/**
	 * This is a client 1 Class
	 * 
	 * .
	 *
	 * @author      Sandhya Murali
	 * 
	 */

	int[][] ocean_grid; // grid for player
	int[][] player_grid; // grid to display and record hit and miss
	int row_size,column_size; // row and column size for the initializing the grid
	int flag1,flag2; // flags 
	int ship_size=0;  // set and retrieve ship size
	String position;
	String[] ship={"Cruiser","Battleship","Destroyer","Carrier"}; // array for string type to store ship names
	Scanner scanner=new Scanner(System.in); // object of scanner class
	int row,column; //represents the row and column input
	int choice;
	static ModelInterface stub; // remote server object
	int option; //flag value sent by the server
	
	
	public void initialise() throws RemoteException
	{  
		/**
		 * Initialises the grid
		*/

		System.out.println("Enter row");
		row_size=scanner.nextInt();
		System.out.println("Enter column");
		column_size=scanner.nextInt();
		
		ocean_grid=new int[row_size][column_size];
		player_grid=new int[row_size][column_size];
			
	}
	
	public void fleet1(ModelInterface model) throws RemoteException 
	{
		/**
		 * Places the fleet for player1
		*/
        
		for(int i=0;i<4;i++)  // condition to ensure all 4 ships are placed 
		{
			System.out.println("Enter position for " +ship[i]+" h or v");
			position=scanner.next();
			ship_size=ships(ship[i],model);
		
			do
			{	
				System.out.println("Enter row");//accepts row
				row=scanner.nextInt();
		
				System.out.println("Enter column"); //accepts column
				column=scanner.nextInt();
				//check for all the boundry conditions
				flag1=model.fleet_boundary(this.ocean_grid,row,column,position,row_size,column_size); 	
				//check if the the fleet is overlapping at any position
				flag2=model.fleet_overlap(this.ocean_grid,row,column,position,row_size,column_size,ship_size);	
				
				if(flag1==0 && flag2==0)
					System.out.println("Out of Bounds and Overlapping");
				else if(flag1==0)
					System.out.println("Out of Bounds");
				else if(flag2==0)
					System.out.println("Overlapping");
				else
					System.out.println("Perfect");
				
			}
			while(flag2==0 || flag1==0);
			//place the ships and create the entire fleet
			this.ocean_grid=model.place_ships(this.ocean_grid, row, column,ship_size,position,row_size,column_size);
					
		}
		//
		this.setOcean_grid(ocean_grid);
		this.display(ocean_grid);
		model.validate();
		
		
		
		
	}
	

	public int[][] getOcean_grid() 
	{
		/**
		 * Retrieves Ocean grid of player1
		*/
		return ocean_grid;
	}




	public void setOcean_grid(int[][] ocean_grid) 
	{
		/**
		 * Sets Ocean grid of player1
		*/
		this.ocean_grid = ocean_grid;
	}




	public int[][] getPlayer_grid() 
	{
		/**
		 * Retrieves Player grid of player1
		*/
		return player_grid;
	}




	public void setPlayer_grid(int[][] player_grid) 
	{
		/**
		 * Sets Player grid of player1
		*/
		this.player_grid = player_grid;
	}




	public int ships(String ship_name,ModelInterface model) throws RemoteException
	{
		/**
		 * Determines the size of the ships based on the type
		*/
        
		
		if(ship_name.equals("Cruiser"))
			ship_size=model.getCruiser();
		
		if(ship_name.equals("Battleship"))
			ship_size=model.getBattleship();
		
		if(ship_name.equals("Destroyer"))
			ship_size=model.getDestroyer();
		
		if(ship_name.equals("Carrier"))
			ship_size=model.getCarrier();
		
		return ship_size;
	}
	
	
	public void display(int[][] ocean_grid)
	{
		/**
		 *Displays the ocean grid
		*/
		for(int i=0;i<row_size;i++)
		{
			for(int j=0;j<column_size;j++)
			{
				System.out.print(ocean_grid[i][j]);
			}
			
			System.out.println();
		}
	}
	
	public void display_player(int[][] player_grid)
	{
		/**
		 *Displays the player grid
		*/
		for(int i=0;i<row_size;i++)
		{
			for(int j=0;j<column_size;j++)
			{
				System.out.print(player_grid[i][j]);
			}
			
			System.out.println();
		}
	}
	
	
	public int Player(int[][] ocean_grid1,int [][] ocean_grid2,int [][] player_grid1,int[][] player_grid2) throws RemoteException
	{
		/**
		 * Determines Player A's turn
		*/
        System.out.println("Player A");
		do{
			
			System.out.println("Enter row");
			row=scanner.nextInt();
			
			System.out.println("Enter column");
			column=scanner.nextInt();
			
			flag1=stub.check_grid(ocean_grid1, ocean_grid2, player_grid1, player_grid2,row,column,row_size,column_size);
		}
		while(flag1==0);
		
		
		option=stub.grid1(ocean_grid1, ocean_grid2, player_grid1, player_grid2, row, column, row_size, column_size);
		
		return option;
		
		
	
	}
	
	
	public void Winner(int count1,int count2) 
	{
		/**
		 * Determines the winner
		*/
		
		if(count1==0)
		{
			System.out.println("Player B won");
		}
		
		else
			System.out.println("You won");
	}
	
	public void hit()
	{
		/**
		 * Prints hit
		*/
		System.out.println("It is a hit!");
	}
	

	public void miss()
	{
		/**
		 * Prints miss
		*/
		
		System.out.println("It is a miss!");
	}
	
	public static void main(String[] args) 
	{
		/**
		 * This is the main class
		*/
		try
		{  
			stub=(ModelInterface)Naming.lookup("rmi://localhost:5000/hello");
			ClientA client1 = new ClientA();
			stub.ClientA_object((ClientInterface) UnicastRemoteObject.exportObject(client1,0));
			client1.initialise();
			client1.fleet1(stub);		 
			
		}
		
		catch(Exception e)
		{
			
		}  

	}

}
