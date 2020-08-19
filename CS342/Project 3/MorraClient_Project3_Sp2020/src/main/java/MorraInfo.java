import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.Serializable;

public class MorraInfo implements Serializable {

    // data members for player 1
    private int player1Score; // stores player 1 score
    private int player1ExpectedTotal; // stores what the player thinks will be the total sum
    private int player1ActualGuess; // stores what he has decided to play for the round
    ObservableList<Integer> player1ObservableList; // for sending player1 list view to player2 if player2 wins
    ListView<Integer> player1ListView; // for displaying player1 list view

    // data members for player 2
    private int player2Score; // stores player 1 score score
    private int player2ExpectedTotal; // stores what the player thinks will be the total sum
    private int player2ActualGuess; // stores what he has decided to play for the round
    ObservableList<Integer> player2ObservableList; // for sending player 2 list view to player1 if player1 wins
    ListView<Integer> player2ListView; // for displaying player2 list view

    // To keep track of game status
    private String gameStatus;
    private int roundOver;

    // data members for server
    boolean has2players; // checks to see if both players played before starting the game
    boolean player1Played; // checks to see if player one played
    boolean player2Played; // checks to see if player two played
    boolean player1Won; // checks to see if player 1 won
    boolean player2Won; // checks to see if player 2 won
    boolean player1PlayAgain; // checks to see if player one wants to play again after game is over
    boolean player2PlayAgain; // checks to see if player two wants to play again after game is over
}
