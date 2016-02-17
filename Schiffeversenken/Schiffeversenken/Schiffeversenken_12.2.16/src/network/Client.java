package network;

import game.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
	
public class Client extends JFrame {
	
	public static Socket _Socket = null;
	public static PrintStream _out = null;
	public static BufferedReader _in = null;
	
	public Client(){
		super();
		initiateSetPhase();
	}
	
	private static void init() throws UnknownHostException{
		try{
		_Socket = new Socket("10.10.100.10", 8080);  
		_out = new PrintStream(_Socket.getOutputStream(), true);     
		_in = new BufferedReader(new InputStreamReader(_Socket.getInputStream()));          
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Es konnte keine Verbindung hergestellt werden");
		}
	}
	
	private void initiateSetPhase(){
		Player player = new Player();
		SetPhase setShipFrame = new SetPhase(player);		
	        setShipFrame.setResizable(false);  
	        setShipFrame.buttons(setShipFrame.getContentPane());
	        setShipFrame.pack();
	        setShipFrame.setTitle("Spieler A: Schiffe setzen");
	        setShipFrame.setVisible(true);
	        setShipFrame.setLocationRelativeTo(null);
	        JOptionPane.showMessageDialog(setShipFrame, "Du kannst jetzt deine Schiffe setzen\n"
	        		+ "Klick dazu immer auf das Anfangs- und Endfeld");
	}
	
	public static void initiateShootPhase(Player player){
		ShootPhase shootFrame = new ShootPhase(player);
		shootFrame.setResizable(false);  
        shootFrame.buttons(shootFrame.getContentPane());
        shootFrame.pack();
        shootFrame.setName("Spieler A: Felder beschie�en");
        shootFrame.setVisible(true);
        shootFrame.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(shootFrame, "Du kannst jetzt auf das Feld deines Gegners schie�en");
	}
	
	public boolean run(int x, int y, int meth) throws IOException{    	                            
		try{
			_Socket.setSoTimeout(500);
			_out.print(x+"|"+y +"|"+ meth + "\n");   
			_out.flush();
	        String serverResponse = null;	       
            while ((serverResponse = _in.readLine()) != null){
            	if(serverResponse.equals("true")){            	
            		System.out.println(serverResponse);
            		return true;
            	}
            }                  
		} catch (UnknownHostException e) {
        } catch (IOException e) {       
        }
		return false;
	
    } // run()*/
}
