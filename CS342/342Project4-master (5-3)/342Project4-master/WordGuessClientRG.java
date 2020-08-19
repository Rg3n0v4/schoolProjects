import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;
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
public class WordGuessClientRG extends Application {
	
	//Game info variables
	int clientNum = -1;
	GameCommunicator info;
	Client clientConnection;

	//sends the player's guess to the server to be checked
	public void playerGuess(GameCommunicator player)
	{
		//allow the player to pick which category they want
		//player.category = (user pick);
		info.category = player.category;
		//word is picked and the number
		//take in user input and add it to player and info
		//player.guess = (user input);
		info.guess = player.guess; //send it back to the server

		player.nextRound(); //sets up for the next round of guessing
	}
	
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
			    System.out.println("Connected to server");
			}
			catch(Exception e) {System.out.println("Error");}
			
			while(true) {
				 
				try
				{
					GameCommunicator message = (GameCommunicator) in.readObject();
					Platform.runLater( new Runnable() {
						public void run() {
							System.out.println("client connected");
						}
					});
					//When client needs to choose from a category
					if(message.category == -1 && message.numLetters == -1) {
						//Disable / Enable categories users can choose based on booleans
						
						//Get user input on what category they chose
						System.out.println("Pick Category");
						Scanner s = new Scanner(System.in);
						int cat = s.nextInt();
						s.close();
						System.out.println("Picked:" + cat);
						message.category = cat;
						send(message);
					}
					
					//When client gets object back after it sends category in
					else if(message.category != -1 && message.numLetters != -1) {
						
						//Display word and number of letters to guess
						System.out.println(message.word);
						System.out.println("Number of Letters: " + message.numLetters);
						System.out.println("Number of Guesses: " + message.numGuesses + "\n");
						
						System.out.println("Enter a character");
						Scanner s = new Scanner(System.in);
						String str = "";
						if(s.hasNextLine())
							str = s.nextLine();
						
						char g = str.charAt(0);
						s.close();
						System.out.println("Entered: " + g);
						
						//Assign guess to message object
						message.guess = g;
						send(message);
					}
					
					else if(message.categoryLose || message.categoryWon) {
						
						//Resets variables for next round
						message.nextRound();
						
						
						//show screen and player gets to choose what they want to do
						
						
						//Get user input on what category they chose
						System.out.println("Pick Category");
						Scanner s = new Scanner(System.in);
						int cat = s.nextInt();
						s.close();
						System.out.println("Picked:" + cat);
						message.category = cat;
						send(message);
					}
					
					else if(message.gameLost || message.gameWon) {
						//Change screen to win/ lose screen
						
						//Get user input on if they want to play again or not
						System.out.println("Play Again?");
						Scanner s = new Scanner(System.in);
						char choice = s.nextLine().charAt(0);
						s.close();
						System.out.println("Choice:" + choice);
						if(choice == 'y') {
							message.playAgain = true;
						}
						else {
							System.exit(0);
						}
					}	
				
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

		clientConnection = new Client((data) -> {
			Platform.runLater(()->{
				info = (GameCommunicator)data;
			});

		});
		
		clientConnection.start();
	}

}
