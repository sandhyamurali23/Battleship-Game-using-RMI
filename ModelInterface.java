/* 
 * ModelInterface.java 
 * 
 * Version: 
        Version 3.0
 *      11/14/2016
 * 
 * Revisions: 
 *     3 
 */
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ModelInterface extends Remote 
{
	/**
	 * This is a ModelInterface which is a remote Server Interface
	 * 
	 * .
	 *
	 * @author      Sandhya Murali
	 * 
	 */

	
	public int fleet_boundary(int[][] ocean_player,int row,int column,String position,int row_size,int column_size) throws RemoteException; ;
	public int fleet_overlap(int[][] ocean_player,int row,int column,String position,int row_size,int column_size,int ship_size) throws RemoteException; 
	public int[][] place_ships(int[][] ocean_player,int row,int column,int ship_size,String position,int row_size,int column_size) throws RemoteException; 
	public int getCruiser() throws RemoteException; 
	public int getBattleship() throws RemoteException; 
	public int getDestroyer() throws RemoteException; 
	public int getCarrier() throws RemoteException; 
	public void validate() throws RemoteException;
	public void theGame() throws RemoteException;
	public void ClientA_object(ClientInterface client1) throws RemoteException;
	public void ClientB_object(ClientInterface client2) throws RemoteException;
	public int check_grid(int[][] ocean_grid1,int [][] ocean_grid2,int [][] player_grid1,int[][] player_grid2,int row,int column,int row_size,int column_size) throws RemoteException;
	public int grid1(int[][] ocean_grid1,int [][] ocean_grid2,int [][] player_grid1,int[][] player_grid2,int row,int column,int row_size,int column_size) throws RemoteException;
	public int grid2(int[][] ocean_grid1,int [][] ocean_grid2,int [][] player_grid1,int[][] player_grid2,int row,int column,int row_size,int column_size) throws RemoteException;
}
