import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.io.Serializable;

// contains all the data members for the server :- NOTE: METHODS NEED TO BE IMPLEMENTED IN BOTH SERVER AND CLIENT
public class MorraInfo implements Serializable {

    // data members for player 1
    private int player1Score; // stores player 1 score
    private int player1ActualTotal; // stores what the player thinks will be the total sum
    private int player1ActualGuess; // stores what he has decided to play for the round
    ObservableList<Integer> player1ObservableList; // for sending player1 list view to player2 if player2 wins
    ListView<Integer> player1ListView; // for displaying player1 list view

    // data members for player 2
    private int player2Score; // stores player 1 score score
    private int player2ActualTotal; // stores what the player thinks will be the total sum
    private int player2ActualGuess; // stores what he has decided to play for the round
    ObservableList<Integer> player2ObservableList; // for sending player 2 list view to player1 if player1 wins
    ListView<Integer> player2ListView; // for displaying player2 list view

    // To keep track of game status
    private String gameStatus;
    private int roundOver;
    private int counter;
    private int winner;
    private int roundNumber;

    // data members for server
    boolean has2players; // checks to see if both players played before starting the game
    boolean player1Played; // checks to see if player one played
    boolean player2Played; // checks to see if player two played
    boolean player1Won; // checks to see if player 1 won
    boolean player2Won; // checks to see if player 2 won
    boolean player1PlayAgain; // checks to see if player one wants to play again after game is over
    boolean player2PlayAgain; // checks to see if player two wants to play again after game is over

    // setters for player 1
    public void setPlayer1Score(int score) {
        this.player1Score = score;
    }

    public void setPlayer1ActualTotal(int expectedGuess) {
        this.player1ActualTotal = expectedGuess;
    }

    public void setPlayer1ActualGuess(int guess) {
        this.player1ActualGuess = guess;
    }

    // not sure if the GUI stuff are supposed to have setters and getters, probably not

    // getters for player 1
    public int getPlayer1Score()
    {
        return this.player1Score;
    }

    public int getPlayer1ActualTotal()
    {
        return this.player1ActualTotal;
    }

    public int getPlayer1ActualGuess()
    {
        return this.player1ActualGuess;
    }

    // setters for player2
    public void setPlayer2Score(int score) {
        this.player2Score = score;
    }

    public void setPlayer2ActualTotal(int expectedGuess) {
        this.player2ActualTotal = expectedGuess;
    }

    public void setPlayer2ActualGuess(int guess) {
        this.player2ActualGuess = guess;
    }

    // getters for player 2
    public int getPlayer2Score()
    {
        return this.player2Score;
    }

    public int getPlayer2ActualTotal()
    {
        return this.player2ActualTotal;
    }

    public void addMove(int player, int guess)
    {
        if(player == 0 && counter == 0)
        {
            player1ActualGuess = guess;
        }
        else if(player == 1 && counter == 1)
        {
            player2ActualGuess = guess;
        }
        else if(player == 0 && counter%2 == 0)
        {
            player1ActualTotal = guess;
        }
        else if(player == 1 && counter%2 != 0)
        {
            player2ActualTotal = guess;
        }
        counter ++;
    }
    // Logic for the server to evaluate who won the round
    // player 1
    public int evaluatePlayers ()
    {
        if (player1ActualGuess == (player1ActualTotal + player2ActualTotal) && player2ActualGuess == (player1ActualTotal + player2ActualTotal))
        {// Both won
            return 3;
        }
        if (player1ActualGuess == (player1ActualTotal + player2ActualTotal))
        {// Player 1 won
            player1Score++;
            return 1;
        }
        if (player2ActualGuess == (player1ActualTotal + player2ActualTotal))
        {// Player 2 won
            player2Score++;
            return 2;
        }
        // if no one won the round
        return 0;
    }


    // to determine if both players have finished playing
    public boolean roundOver()
    {
        this.roundNumber++;
        if (this.counter == 4)
        {
            this.counter = 0;
            return true;
        }
        else
        {
            return false;
        }
    }

    // printing the necessary information after each round is done
    public String printInfo ()
    {
        int winner = this.winner;
        String s = new String();
        if (winner == 0)
        {
            s+= "Server: No one won\n";
        }
        if (winner == 1)
        {
            s+= "Server: Client#0 won\n";
        }
        if (winner == 2)
        {
            s+= "Server: Client#1 won\n";
        }
        if (winner == 3)
        {
            s+= "Server: No one won\n";
        }
        return s;
    }
    // Default constructor for MorraInfo class
    MorraInfo()
    {
        // initializing player 1 data members
        this.player1Score = 0;
        this.player1ActualTotal = 0;
        this.player1ActualGuess = 0;

        // initializing player 2 data members
        this.player2Score = 0;
        this.player2ActualTotal = 0;
        this.player2ActualGuess = 0;

        // To see if the round is over
        this.roundOver = 0;
        this.counter = 0;
        this.winner = 0;
        this.roundNumber = 0;
    }

    // prints out the score of each client
    public String scoreString()
    {
        return "Client#0: " + this.player1Score + " points, Client#1: " + this.player2Score + " points";
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
}
