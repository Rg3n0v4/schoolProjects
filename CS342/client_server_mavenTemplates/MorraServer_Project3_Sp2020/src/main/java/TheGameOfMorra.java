import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class TheGameOfMorra extends Application {

	//for server title
	private HBox serverTitleBox;
	private Text serverTitle;

	//for entering in the port number
	private HBox portBox;
	private TextField portNum;
	private Text portNumTxt;
	private Button portBtn;

	//for controlling the server to turn on or off
	private HBox serverOnOffBox;
	private Text serverOnOffTxt;
	private Button turnServerOn;
	private Button turnServerOff;

	//for number of clients online
	private HBox numOfClientsBox;
	private Text numOfClientsTxt;

	//player 1 things
	private VBox player1Box;
	private Text player1Played;
	private Text player1Score;

	//player 2 things
	private VBox player2Box;
	private Text player2Played;
	private Text player2Score;

	//status of the game things
	private VBox statusOfGameBox;
	private Text statusOfGameTitle;
	private Text statusOfGameTxt;

	//play again things
	private HBox playAgainBox;
	private Text playAgainTxt;
	private Button y_playAgain;
	private Button n_playAgain;

	//other
	private BorderPane rootPane;
	static javafx.scene.text.Font currentFont = new javafx.scene.text.Font("Comic Sans MC", 20); //just for consistent
	static javafx.scene.text.Font buttonFont = new Font("Comic Sans MC", 12);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Server) Let's Play Morra!!!");
		final int textFieldWidth = 100;
		final int textFieldHeight = 50;
		final int buttonSizeWidth = 70;
		final int buttonSizeHeight = 50;

	//things for the GUI
		//server title
		serverTitle = new Text();
		serverTitle.setFont(currentFont);
		serverTitle.setText("Game of Morra (Server)");
		serverTitleBox = new HBox(serverTitle);
		serverTitleBox.setLayoutX(50);
		serverTitleBox.setLayoutY(10);

		//port num
		portNumTxt = new Text();
		portNumTxt.setFont(currentFont);
		portNumTxt.setText("Enter port number:");
		portNum = new TextField();
		portNum.setMinSize(textFieldWidth, textFieldHeight);
		portBtn = new Button("Enter");
		portBtn.setMinSize(buttonSizeWidth, buttonSizeHeight);
		portBtn.setFont(buttonFont);
		portBox = new HBox(20,portNumTxt,  portNum, portBtn);
		portBox.setLayoutX(10);
		portBox.setLayoutY(50);

		//controls for on/off
		serverOnOffTxt = new Text();
		serverOnOffTxt.setFont(currentFont);
		serverOnOffTxt.setText("Turn sever on/off:");
		turnServerOn = new Button("On");
		turnServerOn.setMinSize(buttonSizeWidth, buttonSizeHeight);
		turnServerOn.setFont(buttonFont);
		turnServerOff = new Button("Off");
		turnServerOff.setMinSize(buttonSizeWidth, buttonSizeHeight);
		turnServerOff.setFont(buttonFont);
		serverOnOffBox = new HBox(20, serverOnOffTxt, turnServerOn, turnServerOff);
		serverOnOffBox.setLayoutX(10);
		serverOnOffBox.setLayoutY(110);

		//number of clients
		numOfClientsTxt = new Text();
		numOfClientsTxt.setFont(currentFont);
		numOfClientsTxt.setText("No. of clients: TBD :)"); //will continuously be updated so not set in stone yet
		numOfClientsBox = new HBox(numOfClientsTxt);
		numOfClientsBox.setLayoutX(10);
		numOfClientsBox.setLayoutY(170);

		//player 1 box and things
		player1Played = new Text();
		player1Played.setFont(currentFont);
		player1Played.setText("Player 1 played: #"); //will continuously be updated so not set in stone yet
		player1Score = new Text();
		player1Score.setFont(currentFont);
		player1Score.setText("Player 1 score: #"); //will continuously be updated so not set in stone yet
		player1Box = new VBox(20, player1Played, player1Score);
		player1Box.setLayoutX(10);
		player1Box.setLayoutY(230);

		//player 2 box and things
		player2Played = new Text();
		player2Played.setFont(currentFont);
		player2Played.setText("Player 2 played: #"); //will continuously be updated so not set in stone yet
		player2Score = new Text();
		player2Score.setFont(currentFont);
		player2Score.setText("Player 2 score: #"); //will continuously be updated so not set in stone yet
		player2Box = new VBox(20, player2Played, player2Score);
		player2Box.setLayoutX(180);
		player2Box.setLayoutY(230);

		//status of game stuff
		statusOfGameTitle = new Text();
		statusOfGameTitle.setFont(currentFont);
		statusOfGameTitle.setText("Status of Game:");
		statusOfGameTxt = new Text();
		statusOfGameTxt.setFont(currentFont);
		statusOfGameTxt.setText("Player 1 vs Player 2 ");
		statusOfGameBox = new VBox(20, statusOfGameTitle, statusOfGameTxt);
		statusOfGameBox.setLayoutX(10);
		statusOfGameBox.setLayoutY(330);

		//play again things
		playAgainTxt = new Text();
		playAgainTxt.setFont(currentFont);
		playAgainTxt.setText("Play again?: ");
		y_playAgain = new Button("Yes");
		y_playAgain.setMinSize(buttonSizeWidth, buttonSizeHeight);
		y_playAgain.setFont(buttonFont);
		n_playAgain = new Button("No");
		n_playAgain.setMinSize(buttonSizeWidth, buttonSizeHeight);
		n_playAgain.setFont(buttonFont);
		playAgainBox = new HBox(20, playAgainTxt, y_playAgain, n_playAgain);
		playAgainBox.setLayoutX(10);
		playAgainBox.setLayoutY(450);

		//things for the scene
		rootPane = new BorderPane();
		rootPane.getChildren().addAll(serverTitleBox, portBox, serverOnOffBox, numOfClientsBox, player1Box,
										player2Box, statusOfGameBox, playAgainBox);
		rootPane.setStyle("-fx-background-color: seagreen");
		Scene scene = new Scene(rootPane,400,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
