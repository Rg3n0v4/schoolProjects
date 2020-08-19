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
	boolean canPickCat1;	//tells client if they are allowed to pick category 1
	boolean canPickCat2;	//tells client if they are allowed to pick category 2
	boolean canPickCat3;	//tells client if they are allowed to pick category 3
	
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
		canPickCat1 = true;
		canPickCat2 = true;
		canPickCat3 = true;
	}

	//this "resets" the player for the next round of the game
	public void nextRound()
	{
		category = -1;
		numLetters = -1;
		numGuesses = 6;
		guess = ' ';
		word = "";
		categoryWon = false;
	}

	//for if the player decides to play again
	//basically reinitalizes ALL variables for new game
	public void completeReset()
	{
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
		canPickCat1 = true;
		canPickCat2 = true;
		canPickCat3 = true;
	}

	//checks the player's guess against the word
	public void checkGuess() //<-may need to change accordingly to how gameplay is wanted
	{
		if(numGuesses == 6) //max number of guesses they can have
		{
			return; //break out of the function beause they've already used up their guesses
		}
		else
		{
			numGuesses++;//increment the number of guesses
		}

		//boolean isLetterFound = false; //<-might need but idk
		for(char l: word.toCharArray())//go through each letter in the array
		{
			if(l == guess)
				//if the player's guess is in the word (***need to figure out for duplicate letters unless
				// its already covered with this logic***)
			{
				numLetters--; //decrease the number of letters left for the player to guess
				//isLetterFound = true;
			}
		}
	}

	//deciding factor if they win in that category (NOT WINNING WORD)
	//*** need to figure out the logic for this ***
	public void winInCategory()
	{
		//if category 1 is chosen then mess with attemptCat1 and canPickCat1
		//if category 2 is chosen then mess with attemptCat1 and canPickCat2
		//if category 3 is chosen then mess with attemptCat1 and canPickCat3

		//if attemptCat# is at 3
		//how do we know if the player won ALL 3 maybe they won 2, 1 or none
		//"lock" the category by not having it show up by setting canPickCat# to false

		//if the attemptCat# is NOT at 3
		//continue on to the next category or same category
		//
	}
}
