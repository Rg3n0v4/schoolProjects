
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * THis is the server client of the game WordGuesser
 */
public class WordGuessServer extends Application {

	//Put all variables that need to be changed here
	Server serverConnect;
	static int port = 0;
	GameCommunicator info;

	//Master game tracker of all games
	ArrayList<GameTracker> tracker = new ArrayList<GameTracker>();		//player number -1

	//List of words
	ArrayList<String> superheros = new ArrayList<String>();
	ArrayList<String> dessert = new ArrayList<String>();
	ArrayList<String> transportation = new ArrayList<String>();

	//This function fills the arraylist with words
	void fillCategories() {
		superheros.add("Superman");
		superheros.add("Spiderman");
		superheros.add("Batman");

		dessert.add("Cake");
		dessert.add("Ice Cream");
		dessert.add("Cupcake");

		transportation.add("Car");
		transportation.add("Train");
		transportation.add("Plane");
	}

	//This function is used trying to fill the remaining words to guess (and also making the words to start)
	//It'll check if GameCommunicator guess char is at it's default value ' '. If it is, then make the empty string
	//if not, then fill in words based on guess in GameCommunicator
	public void fillWord(GameCommunicator com) {	// _ _ _ _ _   _ _ _ _ _
		boolean makeWord = false;	//boolean to check if we need to fill in letters or make the blank word
		if(com.guess == ' ') {
			makeWord = true;
		}

		//Find the client number and find their word for the current category they are at
		String wordToGuess;
		int clientNum = com.clientNum;
		if(com.category == 1) {
			wordToGuess = tracker.get(clientNum-1).category1Word;
		}
		else if(com.category == 2) {
			wordToGuess = tracker.get(clientNum-1).category2Word;
		}
		else {	//category 3
			wordToGuess = tracker.get(clientNum-1).category3Word;
		}

		//if user just selected category and we have to show user what letters are needed to be guessed
		String emptyWord = "";
		if(makeWord) {
			for(int i = 0; i < wordToGuess.length(); i++) {
				//Add characters to avoid blurring out here
				if(wordToGuess.charAt(i) == ' ')
					emptyWord += wordToGuess.charAt(i) + " ";
					//Letter user can guess here
				else
					emptyWord += "_ ";
			}

			com.word = emptyWord;
			return;
		}

		//User is guessing word and need to fill in letters
		else {
			char charGuess = com.guess;
			char[] newWord = com.word.toCharArray();
			System.out.println(newWord.length);
			// - - -   - - - - -
			// ice cream
			for(int i = 0; i < wordToGuess.length(); i++) {
				if(wordToGuess.charAt(i) == charGuess) {
					newWord[2*i] = charGuess;
				}
			}

			com.word = String.valueOf(newWord);
			return;
		}

	}

	//This function checks to see if category is won or lost or game is still going.
	//If category is lost or won, this will check also if whole game is lost or won.
	void checkGameState(GameCommunicator com) {
		GameTracker curr = tracker.get(com.clientNum-1);

		//Check if category is lost
		if(com.numGuesses == 0) {
			com.categoryLose = true;
			com.word = "";
			com.numLetters = -1;
			Random rand = new Random();

			//Add category attempt to category
			if(com.category == 1) {
				curr.category1Attempt++;
				com.attemptCat1++;

				//Keep looping until you find a word that isn't used
				String cat = superheros.get(rand.nextInt(3));
				while(curr.isInArr(cat)) {
					cat = superheros.get(rand.nextInt(3));
				}

				//Assign new word to category
				curr.category1Word = cat;

			}
			else if(com.category == 2) {
				curr.category2Attempt++;
				com.attemptCat2++;

				//Keep looping until you find a word that isn't used
				String cat = dessert.get(rand.nextInt(3));
				while(curr.isInArr(cat)) {
					cat = dessert.get(rand.nextInt(3));
				}

				//Assign new word to category
				curr.category2Word = cat;

			}
			else {	//category 3
				curr.category3Attempt++;
				com.attemptCat3++;

				//Keep looping until you find a word that isn't used
				String cat = transportation.get(rand.nextInt(3));
				while(curr.isInArr(cat)) {
					cat = transportation.get(rand.nextInt(3));
				}

				//Assign new word to category
				curr.category3Word = cat;

			}

			//Check if game is lost
			if(curr.category1Attempt == 3 ||
					curr.category2Attempt == 3 ||
					curr.category3Attempt == 3) {

				com.gameLost = true;
			}

			return;
		}

		//Check if category is won
		boolean didWin = true;
		for(int i = 0; i < com.word.length(); i++) {
			if(com.word.charAt(i) == '_') {
				didWin = false;
				break;
			}
		}

		if(didWin) {
			//change booleans for which category is won/ can be picked
			if(com.category == 1) {
				curr.guessedCategory1 = true;
				com.canPickCat1 = false;
			}
			else if(com.category == 2) {
				curr.guessedCategory2 = true;
				com.canPickCat2 = false;
			}
			else {
				curr.guessedCategory3 = true;
				com.canPickCat3 = false;
			}

			//Check if won whole game
			if(curr.guessedCategory1 && curr.guessedCategory2 && curr.guessedCategory3) {
				com.gameWon = true;
			}

			return;
		}

		//Currently category is still not done and return back
		return;
	}

	//This function takes care of the behavior of what to do when server gets a letter guess
	//and change variables accordingly
	void guessBehavior(GameCommunicator comm) {
		//Grabs the gameTracker of current client
		GameTracker curr = tracker.get(comm.clientNum-1);

		String currWord = "";	//store word of current category
		//Check to see if letter is in the word of current category
		//First check what category word
		if(comm.category == 1) {
			currWord = curr.category1Word;
		}
		else if(comm.category == 2) {
			currWord = curr.category2Word;
		}
		else {	//category 3
			currWord = curr.category3Word;
		}

		boolean charInWord = false;
		//Second check to see if guess is in word
		for(int i = 0; i < currWord.length(); i++) {
			if(currWord.charAt(i) == comm.guess) {
				charInWord = true;
				break;
			}
		}

		/*Third: Based on if character is in word-
		 * 	1. If character is not in word
		 * 		-subtract numGuesses from both GameComm and tracker
		 * 		-change guess back to default ' '
		 * 	2. If character is in word
		 * 		-call fillWord function (this function fills in word in GameCommunicator)
		 * 		-change guess back to default ' '
		 */
		if(!charInWord) {	//character not in word
			comm.numGuesses -= 1;
			curr.numGuesses -= 1;
			comm.guess = ' ';
		}
		else {	//character in word
			fillWord(comm);
			comm.guess = ' ';
		}

		//Lastly, Call function to check state of game
		checkGameState(comm);
	}

	//Inner class Server that deals with making server thread and storing clients
	public class Server{

		int count = 1;
		ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
		TheServer server;
		private Consumer<Serializable> callback;


		Server(Consumer<Serializable> call){

			callback = call;
			server = new TheServer();
			server.start();
		}

		public class TheServer extends Thread{

			public void run() {

				try(ServerSocket mysocket = new ServerSocket(port)){
					System.out.println("Server Connected on Port: " + port);


					while(true) {

						ClientThread c = new ClientThread(mysocket.accept(), count);
						Platform.runLater( new Runnable() {
							public void run() {
								System.out.println("client has connected to server: " + "client #" + count);
							}
						});
						tracker.add(new GameTracker());	//Note the count might be 1 off

						//get random word from each category and store it in GameTracker
						GameTracker curr = tracker.get(count-1);
						Random r1 = new Random();
						curr.category1Word = superheros.get(r1.nextInt(3));
						curr.category2Word = dessert.get(r1.nextInt(3));
						curr.category3Word = transportation.get(r1.nextInt(3));
						//Adds category words into used words
						curr.usedWords.add(curr.category1Word);
						curr.usedWords.add(curr.category2Word);
						curr.usedWords.add(curr.category3Word);

						clients.add(c);
						c.start();

						count++;

					}
				}//end of try
				catch(Exception e) {
					Platform.runLater( new Runnable() {
						public void run() {
							System.out.println("Server socket did not launch");
						}
					});
				}
			}//end of while
		}
t

		class ClientThread extends Thread{

			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			GameCommunicator c;

			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;
			}

			//This updates certain clients with GameCommunicator
			//the index of the client is count - 1
			public void updateClients(GameCommunicator s, int count) {
				ClientThread t = clients.get(count - 1);
				try {
					t.out.writeObject(s);
					t.out.reset();
				}
				catch(Exception e) {}
			}

			public void run(){

				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}

				System.out.println("Client connected");
				c = new GameCommunicator(count);
				updateClients(c, count);

				while(true) {
					try {
						System.out.println("Recieved item from client");
						GameCommunicator data = (GameCommunicator) in.readObject();

						//When user picks category, send user number of letters and word
						if(data.category != -1 && data.numLetters == -1) {
							if(data.category == 1) {
								data.numLetters = tracker.get(data.clientNum-1).category1Word.length();
							}
							else if(data.category == 2) {
								data.numLetters = tracker.get(data.clientNum-1).category2Word.length();
							}
							else {	//category is 3
								data.numLetters = tracker.get(data.clientNum-1).category3Word.length();
							}

							//This changes GameCommunicator.word string to be empty blanks based on word
							//Ex. _ _ _ _ _
							fillWord(data);
							updateClients(data, data.clientNum);
						}

						//User entered a guess and guess being sent back to server
						else if(data.category != -1 && data.numLetters != -1 &&
								data.guess != ' ') {
							guessBehavior(data);
							updateClients(data, data.clientNum);
						}

						else if(data.playAgain) {
							tracker.get(data.clientNum-1).resetGame();
							data.completeReset();

						}
						else {
							//Extra case I didn't account for?
						}

					}
					//Usually when a client leaves
					catch(Exception e) {
						Platform.runLater( new Runnable() {
							public void run() {
								System.out.println("Client " + count + " quit the game.");
							}
						});

						//updateClients(info);
						clients.remove(this);
						break;
					}
				}
			}//end of run


		}//end of client thread
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	//This is the starting scene to enter port number for server
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Server) Word Guesser");

		fillCategories();

		Scene scene = new Scene(new BorderPane(),600,600);
		primaryStage.setScene(scene);
		primaryStage.show();

		//Get port input
		System.out.println("Enter Port number");
		Scanner s = new Scanner(System.in);

		port = s.nextInt();
		System.out.println(port);
		s.close();

		serverConnect = new Server((data) -> {
			Platform.runLater(()->{
				info = (GameCommunicator)data;
			});

		});

	}


}