/* 
 * Model.java 
 * 
 * Version: 
        Version 3.0
 *      11/14/2016
 * 
 * Revisions: 
 *     3 
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Model extends UnicastRemoteObject implements ModelInterface
{
	/**
	 * This is a Model class which implements the remote Server interface
	 * 
	 * .
	 *
	 * @author      Sandhya Murali
	 * 
	 */

	int flag2=1; //flag1->checks boundary and overlapping condition
	int Cruiser =3; //length of the Cruiser
	int Battleship=4;   //length of the Battleship
	int Destroyer=2;   //length of the Destroyer
	int Carrier=5;
	int client=0; // client->total clients
	static ClientInterface client1; //client1->object of client1
	static ClientInterface client2; //client2->object of client2
	int count1=Cruiser+Destroyer+Battleship+Carrier;
	//count1->total count of ships for player1
	int count2=Cruiser+Destroyer+Battleship+Carrier;
	//count2->total count of ships for player2
	int hit1=0,miss1=0,hit2=0,miss2=0;
	//hit1->hits for player1
	//miss1->miss for player1
	//hit2->hits for player2
	//miss2->miss for player2

	protected Model() throws RemoteException 
	{
		super();
		
	}

	public int fleet_boundary(int[][] ocean_player, int row, int column,
			String position, int row_size, int column_size) 
	{
		/**
		 * checks the boundary condition
		*/
			
		if(position.equals("h"))
		{
			if(row<0 || row>row_size || column<0 || column>column_size)
			{
				
				return 0;
			}
			
			
			else
			{
				
			return 1;
			}
		}
		
		else if(position.equals("v"))
		{
			if(row<0 || row>row_size || column<0 || column>column_size)
			{
				
				return 0;
			}
			
			
			
			else
			{
				
			return 1;
			}
		}
		
		else
		{
			return 0;
		}
		
		
	}

	public int fleet_overlap(int[][] ocean_player, int row, int column,
			String position, int row_size, int column_size, int ship_size) 
	{
		/**
		 * Checks overlapping condition
		*/
		
		if(position.equals("h"))
		{
			while(ship_size!=0)
			{
				if(row>=0 && row<row_size && column>=0 && column<column_size && ocean_player[row][column]==1)
				{
					flag2=0;
					break;
				}
				 else
				 {
					 flag2=1;
				 }
				
				
				ship_size--;
				column++;
			}
		}	
		
		
		 if(position.equals("v"))
		{
			 while(ship_size!=0)
				{
				 if(row>=0 && row<row_size && column>=0 && column<column_size && ocean_player[row][column]==1)
					{
						flag2=0;
						break;
					}
				 
				 else
				 {
					 flag2=1;
				 }
					
					ship_size--;
					row++;
				}
		}
		
		
		return flag2;		
				
		
		
		
		
	}

	public int[][] place_ships(int[][] ocean_player, int row, int column,
			int ship_size, String position, int row_size, int column_size) 
	{
		/**
		 * Places the ships
		*/
		
		if(position.equals("h"))
		{
		
			while(ship_size!=0)
			{
			ocean_player[row][column]=1;
			ship_size--;
			column++;
			
			}
		}
		
		if(position.equals("v"))
		{
			
			
			
			while(ship_size!=0)
			{
				ocean_player[row][column]=1;
				ship_size--;
				row++;
			}
			
		}
		
		
		
		
		return ocean_player;
		
	}
	
	
	
	 public int getCruiser() 
	 {
		 /**
		 * Determines the size of Cruiser ship
		 */
			return Cruiser;
	 }



	public int getBattleship() 
	{
		/**
		 * Determines the size of Battleship ship
		 */
			return Battleship;
	}



	public int getDestroyer() 
	{
		/**
		 * Determines the size of Destroyer ship
		 */
			return Destroyer;
	}




	public int getCarrier() 
	{
		/**
		 * Determines the size of Carrier ship
		 */
			return Carrier;
	}
	
	public void ClientA_object(ClientInterface client1)
	{
		/**
		 * Retrives object of Client1
		*/
		this.client1=client1;
	}
	
	public void ClientB_object(ClientInterface client2)
	{
		/**
		 * Retrieves object of Client2
		*/
		this.client2=client2;
	}
	
	
		
	public void validate() throws RemoteException 
	{
		/**
		 * Validates to start the game
		*/
		client++;
		
		if(client==2)
		{
			theGame();
			
		}
		
		
	}
	
	public void theGame() throws RemoteException
	{
		/**
		 * Plays Game
		*/
		
		int choice=1;
		
		while(count1!=0 && count2!=0)
		{
			switch(choice)
			{
			
                   
			
			case 1: System.out.println("Player A");
					choice=client1.Player(client1.getOcean_grid(),client2.getOcean_grid(),client1.getPlayer_grid(),client1.getPlayer_grid());
					
					break;
					
			case 2:System.out.println("Player B");
					choice=client2.Player(client1.getOcean_grid(),client2.getOcean_grid(),client1.getPlayer_grid(),client1.getPlayer_grid());
					
					break;
			}
			
		
		}
		
		client1.Winner(count1, count2);
		client2.Winner(count1, count2);
		
	}
	
	
	
	
	public int check_grid(int[][] ocean_grid1,int [][] ocean_grid2,int [][] player_grid1,int[][] player_grid2,int row,int column,int row_size,int column_size)
	{
		/**
		 * Boundary condition while playing the game
		*/
		if(row<0 || row>row_size || column<0 || column>column_size)
		{
			
			return 0;
		}
		
		else
		{
			return 1;
		}
	}
	
	
	
	public int grid1(int[][] ocean_grid1,int [][] ocean_grid2,int [][] player_grid1,int[][] player_grid2,int row,int column,int row_size,int column_size) throws RemoteException
	{
		/**
		 * Determines hit or miss in player1
		*/
		
		if(ocean_grid2[row][column]==1)
		{
			hit1++;
			count2--;
			
			player_grid1[row][column]=2;  //2-> indicates hit on player1 grid
			client1.setPlayer_grid(player_grid1);			
			client1.hit();
			return 1;
			
	
		}
		
		//checks if already there is a hit
		else if(player_grid1[row][column]==2)
		{
			player_grid1[row][column]=2;
			client1.hit();
			return 1;
		}
		
		//checks if there is a miss already
		else if(player_grid1[row][column]==3)
		{
			
			player_grid1[row][column]=3;
			client1.miss();
			return 2;
		
		}
		
		else
		{
			
			miss1++;
			
			
			player_grid1[row][column]=3;//3->indicates miss on player grid
			client1.miss();		
			return 2;
		
		}
		
	
		
	}
	
	
	public int grid2(int[][] ocean_grid1,int [][] ocean_grid2,int [][] player_grid1,int[][] player_grid2,int row,int column,int row_size,int column_size) throws RemoteException
	{
		/**
		 * Determines hit or miss in player2
		*/
		
		
		if(ocean_grid1[row][column]==1)
		{
		
			hit2++;
			count1--;
		
			
			player_grid2[row][column]=2;
			client2.hit();
			return 2;
		
			
		}
		
		else if(player_grid2[row][column]==2)
		{
			player_grid2[row][column]=2;
			client2.hit();
			return 2;
		}
		
		else if(player_grid2[row][column]==3)
		{
			
			player_grid2[row][column]=3;
			client2.miss();
			return 1;
		
		}
		
		else
		{
			
			miss2++;
		
			
			player_grid2[row][column]=3;
			client2.miss();
			return 1;
			
		
		}			

	
		
		
	}
	




}
