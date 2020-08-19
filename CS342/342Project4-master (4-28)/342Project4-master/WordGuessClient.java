import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * WordGuessClient.java
 * date: 2020/04/27, 10:51 PM
 * 
 */

public class WordGuessClient extends Application {

	// Constants for the size of the window
	public static final int WIDTH = 480;
	public static final int HEIGHT = 270;
	
	// Values for what attempt this is, what player we are, etc
	int attemptNumber, playerNumber, guessesLeft;
	String category = "blank.";
	String currentWord = "____";
	
	// Map that contains the different scenes
	HashMap<String, Scene> sceneMap;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		primaryStage.setTitle("Word Guess Client");
		
		// Create the map of all the scenes
		sceneMap = new HashMap<String, Scene>();
		sceneMap.put("start", createStartScene(primaryStage));
		sceneMap.put("category", createCategoryScene(primaryStage));
		sceneMap.put("gameplay", createGameplayScene(primaryStage));
		
		primaryStage.setScene(sceneMap.get("start"));
		primaryStage.show();
		
	}
	
	// Method that creates the first scene to put in the server info
	public Scene createStartScene(Stage stage)
	{
		
		// Create labels that go next to fields
		Label greeting, enterPort, enterIP;
		greeting = new Label("Greetings!");
		enterPort = new Label("Enter port #: ");
		enterIP = new Label("Enter IP address: ");
		
		// Create the fields for entering port and ip
		TextField portField, ipField;
		portField = new TextField();
		ipField = new TextField();
		
		// Create the button to try and connect to the server
		Button enterButton = new Button("Enter");
		enterButton.setOnAction(e -> {
			
			stage.setScene(sceneMap.get("category"));
			
		});
		
		// Organize all the nodes
		HBox portLine = new HBox(8, enterPort, portField);
		HBox ipLine = new HBox(8, enterIP, ipField);
		
		portLine.setAlignment(Pos.CENTER);
		ipLine.setAlignment(Pos.CENTER);
		
		VBox sceneLayout = new VBox(8, greeting, portLine, ipLine, enterButton);
		sceneLayout.setAlignment(Pos.CENTER);
		sceneLayout.setPadding(new Insets(16, 16, 16, 16));
		
		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(sceneLayout);
		mainPane.setStyle("-fx-background-color: #ff00ff");
		
		return new Scene(mainPane, WIDTH, HEIGHT);
		
	}// end createStartScene()

	
	// Method that creates the scene to pick a category
	public Scene createCategoryScene(Stage stage)
	{
		
		// Label that contains the attempt number
		attemptNumber = 1;
		Label attemptLabel = new Label("Attempt #" + attemptNumber);
		attemptLabel.setAlignment(Pos.TOP_LEFT);
		attemptLabel.setPadding(new Insets(8, 8, 8, 8));
		
		// Label that contains the player number
		playerNumber = 1; // TODO Figure this value out when you connect to the server
		Label playerLabel = new Label("You are player #" + playerNumber);
		playerLabel.setAlignment(Pos.BOTTOM_RIGHT);
		playerLabel.setPadding(new Insets(8, 8, 8, 8));
		
		Label titleLabel = new Label("Let's Guess Words!");
		Label categoryLabel = new Label("Pick a category! ");
		
		// Create the dropdown list
		ArrayList<String> categoryStrings = new ArrayList<String>();
		categoryStrings.add("Superheroes");
		categoryStrings.add("Desserts");
		categoryStrings.add("Transportation");

		ComboBox<String> categoriesMenu = new ComboBox<String>();
		categoriesMenu.getItems().addAll(categoryStrings);
		categoriesMenu.setValue("<Select>");
		
		// Create the next button
		Button nextButton = new Button("Next");
		nextButton.setOnAction(e -> {
			
			// Don't continue if a category hasn't been chosen
			if (categoriesMenu.getValue().equals("<Select>")) {
				return;
			}
			
			// Go to the guessing scene
			stage.setScene(sceneMap.get("gameplay"));
			
		});
		
		// Organize everything now
		BorderPane mainPane = new BorderPane();
		
		HBox categoryLine = new HBox(8, categoryLabel, categoriesMenu);
		categoryLine.setAlignment(Pos.CENTER);
		
		VBox sceneLayout = new VBox(8, titleLabel, categoryLine, nextButton);
		sceneLayout.setAlignment(Pos.TOP_CENTER);
		sceneLayout.setPadding(new Insets(16, 16, 16, 16));
		
		mainPane.setTop(attemptLabel);
		mainPane.setCenter(sceneLayout);
		mainPane.setBottom(playerLabel);
		
		mainPane.setStyle("-fx-background-color: #ff00ff");
		
		return new Scene(mainPane, WIDTH, HEIGHT);
		
	}// end createCategoryScene()
	
	
	// Method that creates the gameplay scene where you guess the letters
	public Scene createGameplayScene(Stage stage)
	{
		
		// Label that contains the attempt number
		Label attemptLabel = new Label("Attempt #" + attemptNumber);
		attemptLabel.setAlignment(Pos.TOP_LEFT);
		attemptLabel.setPadding(new Insets(8, 8, 8, 8));
		
		// Label that contains the player number
		Label playerLabel = new Label("You are player #" + playerNumber);
		playerLabel.setAlignment(Pos.BOTTOM_RIGHT);
		playerLabel.setPadding(new Insets(8, 8, 8, 8));
		
		Label titleLabel = new Label("Category: " + category);
		
		Label wordLabel = new Label("Word: " + currentWord);
		Label guessesLabel = new Label("Guesses left: " + guessesLeft);
		
		Label enterGuess = new Label("Enter letter guess: ");
		TextField guessField = new TextField();
		Button guessButton = new Button("Guess");
		guessButton.setOnAction(e -> {
			
			// TODO Probably disable this button and wait for response from server.
			// When the result comes in, update the word and its respective label or decrease
			// the score if you missed. If guesses left is 0, then change scene to loss scene. (to be made)
			
		});
		
		// Organize everything now
		BorderPane mainPane = new BorderPane();
		
		HBox guessRow = new HBox(8, enterGuess, guessField, guessButton);
		guessRow.setAlignment(Pos.CENTER);
		
		VBox sceneLayout = new VBox(8, titleLabel, wordLabel, guessesLabel, guessRow);
		sceneLayout.setAlignment(Pos.TOP_CENTER);
		sceneLayout.setPadding(new Insets(16, 16, 16, 16));
		
		mainPane.setTop(attemptLabel);
		mainPane.setCenter(sceneLayout);
		mainPane.setBottom(playerLabel);
		
		mainPane.setStyle("-fx-background-color: #ff00ff");
		
		return new Scene(mainPane, WIDTH, HEIGHT);
		
	}// end createGameplayScene()
	
}
