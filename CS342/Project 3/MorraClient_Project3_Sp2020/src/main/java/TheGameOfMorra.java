import com.sun.xml.internal.fastinfoset.AbstractResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;

import java.awt.*;
import java.util.ArrayList;

public class TheGameOfMorra extends Application {

	private Text announcement;
	private HBox announcementBox;

	// Client related stuff
	private String IPAddress;
	private Integer portNumber;
	private Client connection;

	//for client title
	private HBox clientTitleBox;
	private Text clientTitle;

	//for entering in the port number
	private HBox portBox;
	private TextField portNum;
	private Text portNumTxt;

	//for entering ip address
	private HBox ipAddBox;
	private TextField ipAddNum;
	private Text ipAddTxt;

	//connecting to server button
	private HBox connectToServerBox;
	private Button connectToServer;

	//waiting for others
	private HBox waitingOthersBox;
	private Text waitingOthersTxt;

	//player things
	private HBox playerScoreBox;
	private Text player1Score;
	private Text player2Score;
	private HBox guessBox;
	private Text guessTxt;
	private TextField guessTxtField;
	private Button guessButton;

	//choosing number to play
	private Text numberTitle;
	private HBox numberTitleBox;
	private HBox numberBox;
	ImageView hand0;
	ImageView hand1;
	ImageView hand2;
	ImageView hand3;
	ImageView hand4;
	ImageView hand5;
	Button hand0Btn = new Button();
	Button hand1Btn = new Button();
	Button hand2Btn = new Button();
	Button hand3Btn = new Button();
	Button hand4Btn = new Button();
	Button hand5Btn = new Button();
	private VBox choosingNumBox;
	private HBox quitGameBox;
	private Button quitGameBtn;

	//play again things and history of the game
	private Text historyOfGameTitle;
	private HBox historyOfGameTitleBox;
	ListView<String> historyOfGame = new ListView<String>();
	private HBox playAgainBox;
	private Text playAgainTxt;
	private Button y_playAgain;
	private Button n_playAgain;

	//stage
	Stage currentStage;

	//other
	private BorderPane rootPane;
	static Font currentFont = new javafx.scene.text.Font("Comic Sans MC", 20); //just for consistent
	static Font buttonFont = new Font("Comic Sans MC", 12);

	// misc
	private BorderPane rootPane1;

	int counterForEnter = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		rootPane = new BorderPane();
		//currentStage = primaryStage;
		//waitingSceneSetUp(); // HERE
		//playSceneSetUp();
		//outcomeSceneSetUp();
//		connectToServer.setOnAction(event -> {
//			if ((portNum.getText().equals("")) || (ipAddNum.getText().equals("")))
//			{
//				clientTitle.setText("Wrong port or IP Address, try again!");
//			}
//
//			this.IPAddress = ipAddNum.getText();
//			this.portNumber = Integer.valueOf(portNum.getText());
//			waitingForClientsScene();
//		});

		currentStage = primaryStage;

		currentStage.setScene(new Scene(waitingSceneSetUp(), 400, 600));
		currentStage.show();
	}

	public void stopConnection() throws Exception
	{
		connection.closeConnection();
	}

	//scene 1
	public Parent waitingSceneSetUp()
	{
		rootPane = new BorderPane();
		final int textFieldWidth = 100;
		final int textFieldHeight = 50;
		final int buttonSizeWidth = 70;
		final int buttonSizeHeight = 50;

		clientTitle = new Text();
		clientTitle.setFont(currentFont);
		clientTitle.setText("Announcement: Enter valid port & IP Address");
		clientTitleBox = new HBox(20,clientTitle);
		clientTitleBox.setLayoutX(10);
		clientTitleBox.setLayoutY(10);

		//port set up
		portNumTxt = new Text();
		portNumTxt.setFont(currentFont);
		portNumTxt.setText("Enter port number:");
		portNum = new TextField("5555");
		portNum.setMinSize(textFieldWidth, textFieldHeight);

		portBox = new HBox(20,portNumTxt,  portNum);
		portBox.setLayoutX(10);
		portBox.setLayoutY(70);

		// enter ip address set up
		ipAddTxt = new Text();
		ipAddTxt.setFont(currentFont);
		ipAddTxt.setText("Enter ip address:");
		ipAddNum = new TextField("127.0.0.1");
		ipAddNum.setMinSize(textFieldWidth, textFieldHeight);

		ipAddBox = new HBox(20, ipAddTxt,  ipAddNum);
		ipAddBox.setLayoutX(10);
		ipAddBox.setLayoutY(130);

		//connect button
		connectToServer = new Button("Connect to Server");
		connectToServer.setFont(buttonFont);
		connectToServer.setMinSize(buttonSizeWidth + 100, buttonSizeHeight);
		connectToServerBox = new HBox(20, connectToServer);
		connectToServerBox.setLayoutX(10);
		connectToServerBox.setLayoutY(190);
		System.out.println(ipAddNum.getText());
		System.out.println(portNum.getText());

		connectToServer.setOnAction(event -> {
			if ((portNum.getText().equals("")) || (ipAddNum.getText().equals("")))
			{
				clientTitle.setText("Wrong port or IP Address, try again!");
			}

			this.IPAddress = ipAddNum.getText();
			this.portNumber = Integer.valueOf(portNum.getText());
			waitingForClientsScene();
		});

		rootPane.getChildren().addAll(clientTitleBox, portBox, ipAddBox, connectToServerBox);
		return rootPane;

	}

	// scene 2
	// waiting screen if only one client joined the server\
	private void waitingForClientsScene()
	{


		System.out.println("Entered waiting for clients scene");
		rootPane1 = new BorderPane();
		this.connection = createClient();
		try {
			this.connection.startConnection();
		}
		catch (Exception e)
		{
			System.out.println("Unable to create a client");
		}

		waitingOthersTxt = new Text();
		waitingOthersTxt.setFont(currentFont);
		waitingOthersTxt.setText("Waiting for opponent...");
		waitingOthersBox = new HBox(20, waitingOthersTxt);
		waitingOthersBox.setLayoutX(10);
		waitingOthersBox.setLayoutY(180);

		rootPane1.getChildren().addAll(waitingOthersBox);
		Scene scene = new Scene(rootPane1, 400, 600);
		currentStage.setScene(scene);
		currentStage.setTitle("(Client) Let's Play Morra!!!");
		currentStage.show();

	}

	//scene 3
	public void playSceneSetUp()
	{
		rootPane = new BorderPane();
		final int picSize = 50;
		final int textFieldWidth = 50;
		final int textFieldHeight = 50;
		final int buttonSizeWidth = 70;
		final int buttonSizeHeight = 50;

		announcement = new Text();
		announcement.setFont(currentFont);
		announcement.setText("Instruction: Enter total guess, then click number to play");
		announcementBox = new HBox(20, announcement);
		announcementBox.setLayoutX(10);
		announcementBox.setLayoutY(10);

		//player things
		player1Score = new Text();
		player1Score.setFont(currentFont);
		player1Score.setText("Client#0 Score: #");
		player2Score = new Text();
		player2Score.setFont(currentFont);
		player2Score.setText("\tClient#1 Score: #");
		playerScoreBox = new HBox(20, player1Score, player2Score);
		playerScoreBox.setLayoutX(10);
		playerScoreBox.setLayoutY(40);

		//guess box
		guessTxt = new Text();
		guessTxt.setFont(currentFont);
		guessTxt.setText("Enter total guess: ");
		guessTxtField = new TextField();
		guessTxtField.setMinSize(textFieldWidth, textFieldHeight);

		// guess button
		guessButton = new Button("Enter");
		guessButton.setFont(buttonFont);
		guessButton.setMinSize(buttonSizeWidth, buttonSizeHeight);

		guessBox = new HBox(20, guessTxt, guessTxtField, guessButton);
		guessBox.setLayoutX(10);
		guessBox.setLayoutY(80);

		//for images title
		numberTitle = new Text();
		numberTitle.setFont(currentFont);
		numberTitle.setText("Choose what number to play:");
		numberTitleBox = new HBox(20, numberTitle);

		controlButtonDisable();

		// action event handler for enter button
		guessButton.setOnAction(event -> {
			try
			{
				if (counterForEnter % 2 == 0)
				{
					controlButtonEnable();
				}
				connection.send("!Guess"+guessTxtField.getText());
				counter++;
				//controlButtonEnable();
			}
			catch (Exception e)
			{
				// do nothing
			}

		});

		//hand pics
		Image hand0Pic = new Image("hand0.JPG");
		hand0 = new ImageView(hand0Pic);

		hand0.setFitHeight(picSize);
		hand0.setFitWidth(picSize);
		hand0.setPreserveRatio(true);
		hand0Btn.setGraphic(hand0);
		//System.out.println("Entered before hand 0 printout");


		Image hand1Pic = new Image("hand1.JPG");
		hand1 = new ImageView(hand1Pic);

		hand1.setFitHeight(picSize);
		hand1.setFitWidth(picSize);
		hand1.setPreserveRatio(true);
		hand1Btn.setGraphic(hand1);



		Image hand2Pic = new Image("hand2.JPG");
		hand2 = new ImageView(hand2Pic);

		hand2.setFitHeight(picSize);
		hand2.setFitWidth(picSize);
		hand2.setPreserveRatio(true);
		hand2Btn.setGraphic(hand2);

		Image hand3Pic = new Image("hand3.JPG");
		hand3 = new ImageView(hand3Pic);

		hand3.setFitHeight(picSize);
		hand3.setFitWidth(picSize);
		hand3.setPreserveRatio(true);
		hand3Btn.setGraphic(hand3);


		Image hand4Pic = new Image("hand4.JPG");
		hand4 = new ImageView(hand4Pic);

		hand4.setFitHeight(picSize);
		hand4.setFitWidth(picSize);
		hand4.setPreserveRatio(true);
		hand4Btn.setGraphic(hand4);


		Image hand5Pic = new Image("hand5.JPG");
		hand5 = new ImageView(hand5Pic);

		hand5.setFitHeight(picSize);
		hand5.setFitWidth(picSize);
		hand5.setPreserveRatio(true);
		hand5Btn.setGraphic(hand5);

		hand0Btn.setOnAction(event->{
			//System.out.println("Clicked 0");
			sendHandInfo(0);
			controlButtonDisable();
		});

		hand1Btn.setOnAction(event->{
			//System.out.println("Clicked 1");
			sendHandInfo(1);
			controlButtonDisable();
		});

		hand2Btn.setOnAction(event->{
			//System.out.println("Clicked 2");
			sendHandInfo(2);
			controlButtonDisable();
		});

		hand3Btn.setOnAction(event->{
			//System.out.println("Clicked 3");
			sendHandInfo(3);
			controlButtonDisable();
		});
		hand4Btn.setOnAction(event->{
			//System.out.println("Clicked 4");
			sendHandInfo(4);
			controlButtonDisable();
		});


		hand5Btn.setOnAction(event->{
				//System.out.println("Clicked 5");
				sendHandInfo(5);
				controlButtonDisable();
		});

		numberBox = new HBox(20, hand0Btn, hand1Btn, hand2Btn, hand3Btn, hand4Btn, hand5Btn);


		choosingNumBox = new VBox(20, numberTitleBox, numberBox);

		choosingNumBox.setLayoutX(10);
		choosingNumBox.setLayoutY(140);

		//history of game things
		historyOfGameTitle = new Text();
		historyOfGameTitle.setFont(currentFont);
		historyOfGameTitle.setText("History of Game:");

		historyOfGameTitleBox = new HBox(historyOfGameTitle);
		historyOfGameTitleBox.setLayoutX(10);
		historyOfGameTitleBox.setLayoutY(250);

		//historyOfGame.getItems().add("You're my favorite pizza place");
		BorderPane borderPane = new BorderPane();
		borderPane.setLayoutX(10);
		borderPane.setLayoutY(280);
		borderPane.setCenter(historyOfGame);
		borderPane.setMinWidth(450);
		borderPane.setMinHeight(250);

		//play again thing
		playAgainTxt = new Text();
		playAgainTxt.setFont(currentFont);
		playAgainTxt.setText("Play again? ");
		y_playAgain = new Button("Yes");
		y_playAgain.setMinSize(buttonSizeWidth, buttonSizeHeight);
		y_playAgain.setFont(buttonFont);
		y_playAgain.setOnAction(event->{
			historyOfGame.getItems().clear();
			list.clear();
					//this.connection = createClient();
			
			playSceneSetUp();
		});
		quitGameBtn = new Button("Quit Game");
		quitGameBtn.setFont(buttonFont);
		quitGameBtn.setMinSize(buttonSizeWidth, buttonSizeHeight);
		quitGameBtn.setOnAction( event ->
		{
			Stage stage = (Stage) quitGameBtn.getScene().getWindow();
			stage.close();

		});
		playAgainBox = new HBox(30, playAgainTxt, y_playAgain, quitGameBtn);
		playAgainBox.setLayoutX(10);
		playAgainBox.setLayoutY(540);

		rootPane.getChildren().addAll(announcementBox, playerScoreBox, guessBox, choosingNumBox, historyOfGameTitleBox, borderPane, playAgainBox);
		Scene scene = new Scene(rootPane, 470, 650);
		currentStage.setScene(scene);
		currentStage.setTitle("(Client) Let's Play Morra!!!");
		currentStage.show();
	}

	// sends info to the server based on which hand the client chooses
	private void sendHandInfo(int index)
	{
		String message = Integer.toString(index);
		//this.controlButtonDisable();
		try
		{
			connection.send("!Hand"+message);
		}
		catch (Exception e)
		{
			System.out.println("Can't send the number chosen by client to the user");
		}
	}



	// scene whatever
	public void controlButtonDisable()
	{
		this.hand0Btn.setDisable(true);
		this.hand1Btn.setDisable(true);
		this.hand2Btn.setDisable(true);
		this.hand3Btn.setDisable(true);
		this.hand4Btn.setDisable(true);
		this.hand5Btn.setDisable(true);
	}

	// scene whatever
	public void controlButtonEnable()
	{
		this.hand0Btn.setDisable(false);
		this.hand1Btn.setDisable(false);
		this.hand2Btn.setDisable(false);
		this.hand3Btn.setDisable(false);
		this.hand4Btn.setDisable(false);
		this.hand5Btn.setDisable(false);
	}

	// arrayList to store the numbers until the round is over and then display them
	List <String> list = new ArrayList<>();

	int counter = -1;

	private Client createClient()
	{
		return new Client(this.IPAddress, this.portNumber,
				info -> {
					Platform.runLater(()->{

						if (info.toString().contains("!Guess"))
						{
							String dataTemp = info.toString().replace("!Guess","");

							System.out.println("Entered");
							controlButtonEnable();
							list.add(dataTemp.toString() + " is guessed");
						}
						else if (info.toString().contains("!Hand"))
						{
							String dataTemp = info.toString().replace("!Hand","");

							list.add(dataTemp.toString() + " fingers chosen");

						}
						else if(info.toString().contains("You are client#"))
						{
							historyOfGame.getItems().add(info.toString());
						}
						else
						{
							list.add(info.toString());
						}

					});},
				info -> {
					Platform.runLater(()->{
						String s = info.toString();
						if (s.contains("!clientNum2"))
						{
							playSceneSetUp();
						}
						if (s.contains("!noWaiting"))
						{
							controlButtonEnable();
						}
						if (s.contains("!Game Over"))
						{
							controlButtonDisable();
							guessButton.setDisable(true);
							for (int i = list.size()-2; i < list.size(); i++)
							{
								historyOfGame.getItems().add(list.get(i)+"\n");
							}
							 // need to enable the start button when game is over
							// outcomeSceneSetUp();
						}
						if (s.contains("!Score"))
						{
							/*historyOfGame.getItems().add(info.toString())*/;
							for (int i = 0; i < list.size(); i++)
							{
								historyOfGame.getItems().add(list.get(i));
							}
							historyOfGame.getItems().add("\n");
							s = s.replace("!Score:", "");
							historyOfGame.getItems().add("\n");
							historyOfGame.getItems().add(s+"\n");
							char temp1 = info.toString().charAt(17);
							player1Score.setText("Client#0 Score: " + temp1);
							char temp2 = info.toString().charAt(37);
							player2Score.setText("Client#1 Score: " + temp2);
						}
					});
				}
		);
	}

}
