import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.awt.*;

public class TheGameOfMorra extends Application {
	//for client title
	private HBox clientTitleBox;
	private Text clientTitle;

	//for entering in the port number
	private HBox portBox;
	private TextField portNum;
	private Text portNumTxt;
	private Button portBtn;

	//for entering ip address
	private HBox ipAddBox;
	private TextField ipAddNum;
	private Text ipAddTxt;
	private Button ipAddBtn;

	//connecting to server button
	private HBox connectToServerBox;
	private Button connectToServer;

	//player things
	private HBox playerScoreBox;
	private Text player1Score;
	private Text player2Score;
	private HBox guessBox;
	private Text guessTxt;
	private TextField guessTxtField;

	//choosing number to play
	private HBox numberBox;
	ImageView hand0;
	ImageView hand1;
	ImageView hand2;
	ImageView hand3;
	ImageView hand4;
	ImageView hand5;

	//play again things
	private HBox playAgainBox;
	private Text playAgainTxt;
	private Button y_playAgain;
	private Button n_playAgain;

	//different scenes
	private Scene waitingScene;
	private Scene playScene;
	private Scene outcomeScene;

	//temporary menu
	private MenuBar menuBar;
	private VBox menuBox;

	//other
	private BorderPane rootPane;
	static Font currentFont = new javafx.scene.text.Font("Comic Sans MC", 20); //just for consistent
	static Font buttonFont = new Font("Comic Sans MC", 12);


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Client) Let's Play Morra!!!");
		rootPane = new BorderPane();

		menuBar = new MenuBar();
		Menu mOne = new Menu("Options");

		MenuItem scene1 = new MenuItem("Loading"); //loading screen
		MenuItem scene2 = new MenuItem("Play"); //play scene
		MenuItem scene3 = new MenuItem("Outcome"); //outcome

		mOne.getItems().addAll(scene1, scene2, scene3);
		menuBar.getMenus().addAll(mOne);
		menuBox = new VBox(20,menuBar);

		//waitingSceneSetUp();
		playSceneSetUp();
		rootPane.getChildren().addAll(menuBox);
		
		Scene scene = new Scene(rootPane,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	//scene 1
	public void waitingSceneSetUp()
	{
		final int textFieldWidth = 100;
		final int textFieldHeight = 50;
		final int buttonSizeWidth = 70;
		final int buttonSizeHeight = 50;

		//port set up
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
		portBox.setLayoutY(10);

		// enter ip address set up
		ipAddTxt = new Text();
		ipAddTxt.setFont(currentFont);
		ipAddTxt.setText("Enter ip address:");
		ipAddNum = new TextField();
		ipAddNum.setMinSize(textFieldWidth, textFieldHeight);
		ipAddBtn = new Button("Enter");
		ipAddBtn.setMinSize(buttonSizeWidth, buttonSizeHeight);
		ipAddBtn.setFont(buttonFont);
		ipAddBox = new HBox(20,portNumTxt,  portNum, portBtn);
		ipAddBox.setLayoutX(10);
		ipAddBox.setLayoutY(60);

		//connect button
		connectToServer = new Button("Connect to Server");
		connectToServer.setFont(buttonFont);
		connectToServer.setMinSize(buttonSizeWidth + 100, buttonSizeHeight);
		connectToServerBox = new HBox(connectToServer);
		connectToServerBox.setLayoutX(60);
		connectToServerBox.setLayoutY(80);

		rootPane.getChildren().addAll(portBox, ipAddBox, connectToServer);
	}

	//scene 2
	public void playSceneSetUp()
	{
		final int picSize = 50;

		//hand pics
		Image hand0Pic = new Image("hand0.JPG");
		hand0 = new ImageView(hand0Pic);

		hand0.setFitHeight(picSize);
		hand0.setFitWidth(picSize);
		hand0.setPreserveRatio(true);

		Image hand1Pic = new Image("hand1.JPG");
		hand1 = new ImageView(hand1Pic);

		hand1.setFitHeight(picSize);
		hand1.setFitWidth(picSize);
		hand1.setPreserveRatio(true);

		Image hand2Pic = new Image("hand2.JPG");
		hand2 = new ImageView(hand2Pic);

		hand2.setFitHeight(picSize);
		hand2.setFitWidth(picSize);
		hand2.setPreserveRatio(true);

		Image hand3Pic = new Image("hand3.JPG");
		hand3 = new ImageView(hand3Pic);

		hand3.setFitHeight(picSize);
		hand3.setFitWidth(picSize);
		hand3.setPreserveRatio(true);

		Image hand4Pic = new Image("hand4.JPG");
		hand4 = new ImageView(hand4Pic);

		hand4.setFitHeight(picSize);
		hand4.setFitWidth(picSize);
		hand4.setPreserveRatio(true);

		Image hand5Pic = new Image("hand5.JPG");
		hand5 = new ImageView(hand5Pic);

		hand5.setFitHeight(picSize);
		hand5.setFitWidth(picSize);
		hand5.setPreserveRatio(true);

		hand0.setDisable(true);

		numberBox = new HBox(20, hand0, hand1, hand2, hand3, hand4, hand5);

		numberBox.setLayoutX(50);
		numberBox.setLayoutY(300);

		rootPane.getChildren().addAll(numberBox);
	}

	//scene 3
	public void outcomeSceneSetUp()
	{

	}

}
