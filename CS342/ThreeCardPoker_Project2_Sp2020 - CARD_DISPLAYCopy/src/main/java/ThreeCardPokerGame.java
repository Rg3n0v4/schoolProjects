import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.scene.layout.BorderPane;

public class ThreeCardPokerGame extends Application
{
	//general elements of the GUI
	private Text announcement;
	private Button startBtn;
	private HBox announceBox;
	private HBox menuBox;
	private MenuBar menuBar;
	private HBox bottomForm;
	private HBox p1Box;
	private HBox p2Box;
	private HBox anteBox;
	private HBox pairBox;
	private HBox playBox;
	private HBox totalBox;
	private Text anteTxt;
	private Text pairTxt;
	private Text playTxt;
	private Text totalTxt;
	private BorderPane rootPane;

	//for dealer
	private HBox dealerCards;
	private Dealer dealer;

	//for player1
	private Player playerOne;
	private Text p1Txt;
	private HBox playerOneCards;
	private VBox playerOnePane;
	private TextField ante1;
	private TextField pairPlus1;
	private TextField playWager1;
	private TextField totalEarn1;
	private Button foldBtn1;
	private Button playBtn1;

	//for player2
	private Player playerTwo;
	private Text p2Txt;
	private HBox playerTwoCards;
	private VBox playerTwoPane;
	private TextField ante2;
	private TextField pairPlus2;
	private TextField playWager2;
	private TextField totalEarn2;
	private Button foldBtn2;
	private Button playBtn2;

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		final int buttonSizeWidth = 70;
		final int buttonSizeHeight = 50;
		final int textFieldWidth = 50;
		final int textFieldHeight = 50;
		final int textFieldFontSize = 17;


		//on the top of the GUI
		primaryStage.setTitle("Let's Play Three Card Poker!!!");

		//for the announcement
		announcement = new Text();
		announcement.setFont(new Font(20));
		announcement.setTextAlignment(TextAlignment.CENTER);
		announcement.setText("Announcements");

		//for player 1
		p1Txt = new Text();
		p1Txt.setFont(new Font(20));
		p1Txt.setTextAlignment(TextAlignment.CENTER);
		p1Txt.setText("Player 1");

		//for player 2
		p2Txt = new Text();
		p2Txt.setFont(new Font(20));
		p2Txt.setTextAlignment(TextAlignment.CENTER);
		p2Txt.setText("Player 2");

		//for text in the middle of the screen
		anteTxt = new Text();
		anteTxt.setFont(new Font(20));
		anteTxt.setTextAlignment(TextAlignment.CENTER);
		anteTxt.setText("Ante Bet");

		pairTxt = new Text();
		pairTxt.setFont(new Font(20));
		pairTxt.setTextAlignment(TextAlignment.CENTER);
		pairTxt.setText("Pair Plus Bet");

		playTxt = new Text();
		playTxt.setFont(new Font(20));
		playTxt.setTextAlignment(TextAlignment.CENTER);
		playTxt.setText("Play Wager");

		totalTxt = new Text();
		totalTxt.setFont(new Font(20));
		totalTxt.setTextAlignment(TextAlignment.CENTER);
		totalTxt.setText("Total Earnings");


		//buttons that go on the bottom of the screen
		startBtn = new Button("Start");
		foldBtn1 = new Button("Fold");
		playBtn1 = new Button("Play");
		foldBtn2 = new Button("Fold");
		playBtn2 = new Button("Play");

		startBtn.setMinSize(buttonSizeWidth,buttonSizeHeight);
		foldBtn1.setMinSize(buttonSizeWidth,buttonSizeHeight);
		playBtn1.setMinSize(buttonSizeWidth,buttonSizeHeight);
		foldBtn2.setMinSize(buttonSizeWidth,buttonSizeHeight);
		playBtn2.setMinSize(buttonSizeWidth,buttonSizeHeight);

		//player one things
		ante1 = new TextField();
		pairPlus1 = new TextField();
		playWager1 = new TextField();
		totalEarn1 = new TextField();

		ante1.setMinSize(textFieldWidth,textFieldHeight);
		pairPlus1.setMinSize(textFieldWidth,textFieldHeight);
		playWager1.setMinSize(textFieldWidth,textFieldHeight);
		totalEarn1.setMinSize(textFieldWidth,textFieldHeight);
		ante1.setFont(new Font(textFieldFontSize));
		pairPlus1.setFont(new Font(textFieldFontSize));
		playWager1.setFont(new Font(textFieldFontSize));
		totalEarn1.setFont(new Font(textFieldFontSize));

		//player two things
		ante2 = new TextField();
		pairPlus2 = new TextField();
		playWager2 = new TextField();
		totalEarn2 = new TextField();

		ante2.setMinSize(textFieldWidth,textFieldHeight);
		pairPlus2.setMinSize(textFieldWidth,textFieldHeight);
		playWager2.setMinSize(textFieldWidth,textFieldHeight);
		totalEarn2.setMinSize(textFieldWidth,textFieldHeight);

		ante2.setFont(new Font(textFieldFontSize));
		pairPlus2.setFont(new Font(textFieldFontSize));
		playWager2.setFont(new Font(textFieldFontSize));
		totalEarn2.setFont(new Font(textFieldFontSize));

		//for the options
		menuBar = new MenuBar();
		Menu mOne = new Menu("Options");

		MenuItem exitOp = new MenuItem("Exit"); //should exit game
		MenuItem iTwo = new MenuItem("Fresh Start"); //gives a fresh start of the program and resets each players winnings
		MenuItem iThree = new MenuItem("New Look"); //changes the look of the board

		exitOp.setOnAction(e->primaryStage.close());

		startBtn.setStyle("-fx-background-color: green;");
		foldBtn1.setStyle("-fx-background-color: gray;");
		foldBtn2.setStyle("-fx-background-color: gray;");
		playBtn1.setStyle("-fx-background-color: gray;");
		playBtn2.setStyle("-fx-background-color: gray;");

		//iTwo.setOnAction(e->{	/*"resets" the game*/		});

		//FOR NEW LOOK
		startBtn.setOnAction(e->{
			startBtn.setStyle("-fx-background-color: gray;");
			foldBtn1.setStyle("-fx-background-color: green;");
			foldBtn2.setStyle("-fx-background-color: green;");
			playBtn1.setStyle("-fx-background-color: green;");
			playBtn2.setStyle("-fx-background-color: green;");

			startGame();
//			drawCards();
		});

//		startGame();
//		EventHandler<ActionEvent> handler1 = new EventHandler<ActionEvent> () {
//			public void handle(ActionEvent action) {
//				announcement.setText("output");
//			}
//		};
//		startBtn.setOnAction(handler1);

		mOne.getItems().addAll(exitOp,iTwo,iThree);

		menuBar.getMenus().addAll(mOne);



		// Need to write a function that will see if the dealer is qualified to play? (at least got a queen or higher).
		// if both the player and the dealer get the same type of pairing of cards, then something to evaluate them.

		//startBtn.setOnAction(e->announcement.setText("inside lambda expression"));

		rootPane = new BorderPane();

		menuBox = new HBox(20, menuBar);
		announceBox = new HBox(20, announcement);

		playerOnePane = new VBox(20, ante1, pairPlus1, playWager1, totalEarn1);
		playerTwoPane = new VBox(20, ante2, pairPlus2, playWager2, totalEarn2);

		playerOnePane.setLayoutX(450);
		playerOnePane.setLayoutY(300);
		playerTwoPane.setLayoutX(770);
		playerTwoPane.setLayoutY(300);

		anteBox = new HBox(anteTxt);
		pairBox = new HBox(pairTxt);
		playBox = new HBox(playTxt);
		totalBox = new HBox(totalTxt);
		bottomForm = new HBox( 40, playBtn1, foldBtn1,  startBtn, playBtn2, foldBtn2);

		p1Box = new HBox(p1Txt);
		p2Box = new HBox(p2Txt);

		announceBox.setLayoutX(575);
		announceBox.setLayoutY(50);

		anteBox.setLayoutX(595);
		anteBox.setLayoutY(310);

		pairBox.setLayoutX(580);
		pairBox.setLayoutY(380);

		playBox.setLayoutX(585);
		playBox.setLayoutY(450);

		totalBox.setLayoutX(575);
		totalBox.setLayoutY(520);

		p1Box.setLayoutX(175);
		p1Box.setLayoutY(200);

		p2Box.setLayoutX(1000);
		p2Box.setLayoutY(200);

		bottomForm.setLayoutX(375);
		bottomForm.setLayoutY(600);

		rootPane.getChildren().addAll(menuBar, announceBox,
										p1Box, p2Box, playerOnePane, playerTwoPane, anteBox, pairBox,
										playBox, totalBox, bottomForm);
		Scene scene = new Scene(rootPane);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();

	}

	//for starting the game and the drawing of cards
	public void startGame()
	{
		dealer = new Dealer();
		playerOne = new Player();
		playerTwo = new Player();
		dealer.dealHand(); //dealer gives themselves cards
		System.out.println("DealVal: " + dealer.dealHand().get(0).getValue());
		dealer.DrawCards(playerOne); //dealer gives playerOne cards
		dealer.DrawCards(playerTwo); //dealer gives playerTwo cards

		System.out.println("p1Val: " + playerOne.getHand().get(0).getValue());
		System.out.println("p2Val: " + playerTwo.getHand().get(0).getValue());

		drawCards(dealer, playerOne, playerTwo);
	}

	//restarting the game when "Fresh Start" is chosen
	public void restartGame()
	{
		dealer = new Dealer();
	}

	//draws cards for the for both players and dealer
	public void drawCards(Dealer d, Player p1, Player p2)
	{
		final int cardSize = 150;
		char dealerChar1 = d.dealersHand.get(0).getSuit();
//		char dealerChar2 = dealer.dealersHand.get(1).getSuit();
//		char dealerChar3 = dealer.dealersHand.get(2).getSuit();
//		char p1Char1 = playerOne.hand.get(0).getSuit();
//		char p1Char2 = playerOne.hand.get(1).getSuit();
//		char p1Char3 = playerOne.hand.get(2).getSuit();
//		char p2Char1 = playerTwo.hand.get(0).getSuit();
//		char p2Char2 = playerTwo.hand.get(1).getSuit();
//		char p2Char3 = playerTwo.hand.get(2).getSuit();
//
		int dealerVal1 = d.dealersHand.get(0).getValue();
//		int dealerVal2 = dealer.dealersHand.get(1).getValue();
//		int dealerVal3 = dealer.dealersHand.get(2).getValue();
//		int p1Val1 = playerOne.hand.get(0).getValue();
//		int p1Val2 = playerOne.hand.get(1).getValue();
//		int p1Val3 = playerOne.hand.get(2).getValue();
//		int p2Val1 = playerTwo.hand.get(0).getValue();
//		int p2Val2 = playerTwo.hand.get(1).getValue();
//		int p2Val3 = playerTwo.hand.get(2).getValue();

		String dealerCard1 = ""+dealerVal1+dealerChar1+".png";
//		String dealCard1 = ""+dealerChar1+dealerVal1;
//		String dealCard1 = ""+dealerChar1+dealerVal1;
//		String dealCard1 = ""+dealerChar1+dealerVal1;
//		String dealCard1 = ""+dealerChar1+dealerVal1;
//		String dealCard1 = ""+dealerChar1+dealerVal1;
//		String dealCard1 = ""+dealerChar1+dealerVal1;
//		String dealCard1 = ""+dealerChar1+dealerVal1;
//		String dealCard1 = ""+dealerChar1+dealerVal1;
//		String dealCard1 = ""+dealerChar1+dealerVal1;

		//for dealer's cards
		Image dealCardImage = new Image(dealerCard1);
		ImageView dealCard1 = new ImageView(dealCardImage);

		dealCard1.setFitHeight(cardSize);
		dealCard1.setFitWidth(cardSize);
		dealCard1.setPreserveRatio(true);

		Image dealCardImage2 = new Image("AC.png");
		ImageView dealCard2 = new ImageView(dealCardImage2);

		dealCard2.setFitHeight(cardSize);
		dealCard2.setFitWidth(cardSize);
		dealCard2.setPreserveRatio(true);

		Image dealCardImage3 = new Image("AD.png");
		ImageView dealCard3 = new ImageView(dealCardImage3);

		dealCard3.setFitHeight(cardSize);
		dealCard3.setFitWidth(cardSize);
		dealCard3.setPreserveRatio(true);

		//player1's cards
		Image play1CardImage = new Image("AS.png");
		ImageView play1Card1 = new ImageView(play1CardImage);

		play1Card1.setFitHeight(cardSize);
		play1Card1.setFitWidth(cardSize);
		play1Card1.setPreserveRatio(true);

		Image play1CardImage2 = new Image("AC.png");
		ImageView play1Card2 = new ImageView(play1CardImage2);

		play1Card2.setFitHeight(cardSize);
		play1Card2.setFitWidth(cardSize);
		play1Card2.setPreserveRatio(true);

		Image play1CardImage3 = new Image("AD.png");
		ImageView play1Card3 = new ImageView(play1CardImage3);

		play1Card3.setFitHeight(cardSize);
		play1Card3.setFitWidth(cardSize);
		play1Card3.setPreserveRatio(true);

		//player 2's cards
		Image play2CardImage = new Image("AS.png");
		ImageView play2Card1 = new ImageView(play2CardImage);

		play2Card1.setFitHeight(cardSize);
		play2Card1.setFitWidth(cardSize);
		play2Card1.setPreserveRatio(true);

		Image play2CardImage2 = new Image("AC.png");
		ImageView play2Card2 = new ImageView(play2CardImage2);

		play2Card2.setFitHeight(cardSize);
		play2Card2.setFitWidth(cardSize);
		play2Card2.setPreserveRatio(true);

		Image play2CardImage3 = new Image("AD.png");
		ImageView play2Card3 = new ImageView(play2CardImage3);

		play2Card3.setFitHeight(cardSize);
		play2Card3.setFitWidth(cardSize);
		play2Card3.setPreserveRatio(true);

		dealerCards = new HBox(20, dealCard1, dealCard2, dealCard3);
		playerOneCards = new HBox(20, play1Card1, play1Card2, play1Card3);
		playerTwoCards = new HBox(20, play2Card1, play2Card2, play2Card3);

		dealerCards.setLayoutX(475);
		dealerCards.setLayoutY(100);
		playerOneCards.setLayoutX(50);
		playerOneCards.setLayoutY(300);
		playerTwoCards.setLayoutX(880);
		playerTwoCards.setLayoutY(300);

		rootPane.getChildren().addAll(dealerCards, playerOneCards, playerTwoCards);
	}

}
