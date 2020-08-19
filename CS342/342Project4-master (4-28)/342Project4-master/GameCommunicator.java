/*
 * This class is the object passed around between the client and the server. 
 * It includes variables that tell what both the server and client info on the current game state
 * and moves the game along.
 */
public class GameCommunicator {
	int clientNum;	//tracks what client this is
	int category;	//what category current client is on
	int numLetters;	//number of letters in word to guess
	int numGuesses;	//number of guesses left in this category
	char guess;		//character the client guessed
	String word;	//words remaining 
	boolean categoryWon;	//Flags true when user guessed the word in current category
	boolean gameWon;	//flags when user wins the whole game
	boolean gameLost;	//flags when user losses the whole game
	boolean playAgain;	//flag whether to play again or not at the end of the game
	int attemptCat1;	//how many attempts in category 1
	int attemptCat2;	//how many attempts in category 2
	int attemptCat3;	//how many attempts in category 3
	
	//This constructor takes an int to assign which client number the current client is when
	//a client connects the the server. The server will first make this object and send it to the client.
	GameCommunicator(int num){
		clientNum = num;
		category = -1;
		numLetters = -1;
		numGuesses = 6;
		guess = ' ';
		word = "";
		categoryWon = false;
		gameWon = false;
		gameLost = false;
		playAgain = false;
		attemptCat1 = 0;
		attemptCat2 = 0;
		attemptCat3 = 0;
	}
}
