/* 
 * ClientInterface.java 
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


public interface ClientInterface extends Remote 
{
	/**
	 * This is a remote ClientInterface
	 * 
	 * .
	 *
	 * @author      Sandhya Murali
	 * 
	 */
	public void initialise() throws RemoteException;
	public void fleet1(ModelInterface model) throws RemoteException;
	public int[][] getOcean_grid() throws RemoteException;
	public void setOcean_grid(int[][] ocean_grid) throws RemoteException;
	public int[][] getPlayer_grid() throws RemoteException;
	public void setPlayer_grid(int[][] player_grid) throws RemoteException;
	public int ships(String ship_name,ModelInterface model) throws RemoteException;
	public void display(int[][] ocean_grid) throws RemoteException;
	public int Player(int[][] ocean_grid1,int [][] ocean_grid2,int [][] player_grid1,int[][] player_grid2) throws RemoteException;
	public void Winner(int count1,int count2) throws RemoteException;
	public void hit() throws RemoteException;;
	public void miss() throws RemoteException;
	public void display_player(int[][] player_grid) throws RemoteException;

}
