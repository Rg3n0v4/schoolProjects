import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * 
 */
public class WordGuessClient extends Application {
	
	//Game info variables
	GameCommunicator info;
	Client clientConnection;
	
	//This is a inner class that constructs a client socket
	public class Client extends Thread{

		
		Socket socketClient;
		
		ObjectOutputStream out;
		ObjectInputStream in;
		
		private Consumer<Serializable> callback;
		
		Client(Consumer<Serializable> call){
		
			callback = call;
		}
		
		//Sends data to server
		public void send(GameCommunicator data) {
			
			try {
				out.writeObject(data);
				out.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//This is where the program runs and takes care of all incoming data
		public void run() {
			
			try {
				socketClient= new Socket("127.0.0.1", 5555);
			    out = new ObjectOutputStream(socketClient.getOutputStream());
			    in = new ObjectInputStream(socketClient.getInputStream());
			    socketClient.setTcpNoDelay(true);
			}
			catch(Exception e) {}
			
			while(true) {
				 
				try {
					GameCommunicator message = (GameCommunicator) in.readObject();
					//callback.accept(message);

					//OPERATE WHATEVER YOU NEED IN HERE
					GameCommunicator 

					//send(message); //once done withe everything
				
				}
				catch(Exception e) {}
			}
		
	    }


	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Client) Let's Play Morra!!!");
		
		Scene scene = new Scene(new BorderPane(), 600 ,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
