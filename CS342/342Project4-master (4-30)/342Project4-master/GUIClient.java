import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;
//import GUIServerExample.Client; import your game Client Class here
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUIClient extends Application{
	int playerNum = -1; //Tracks what player number this client is
    GameCommunicator info;
    Client clientConnection;
	final String[] categories = new String[]{"Super_heroes","Desserts","Transportation"};
    //Variables in GUI client
	Stage stage;
	//Variables in first scene
	BorderPane pane1 = new BorderPane();
	Label helloLabel = new Label("Hello,Player.Connect to Server First.");
	Label ipLabel = new Label("Please enter an IP Address:");
	Label portLabel = new Label("Please enter a port number:");
	TextField port = new TextField("5555");
	TextField ip = new TextField("127.0.0.1");
	Button powerClient = new Button("Enter");
	
	//Variables in second scene
	BorderPane pane2 = new BorderPane();
	Label attemptLabel = new Label("Attemp #: "); // Give the hint that which attempt that client are current on
	Label attNumberLabel = new Label("");// Show the number of attempt that client are current on
	Label  guesswordLabel= new Label("Let's guess words!!!");
	Label pickCategoryLabel = new Label("Pick a category!");
	Label choiceCate = new Label(""); // Store the category that player picked.
	Label NumhintLabel = new Label("You are player #: "); // Give a message to show player number
	Label playerNumberLabel = new Label("");
	ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList("Super_heroes","Desserts","Transportation"));
	Button nextButton = new Button("Next");//For Scene 2 Next button;
    
	//Variables in third scene
	BorderPane pane3 = new BorderPane();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Client) Starting Word Guess!!!");
		this.stage = primaryStage;
		
		
		powerClient.setOnAction(e-> {primaryStage.setScene(clientTwo());
		primaryStage.setTitle("Playing Game...");
		clientConnection = new Client(data->{
			Platform.runLater(()->{ info = (GameCommunicator) data; });
		});
		clientConnection.start();
	});
		//for category choiceBox listen event
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			   
			public void changed(@SuppressWarnings("rawtypes") ObservableValue observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				choiceCate.setText(categories[newValue.intValue()]);
				String cate = choiceCate.getText();
				int i=-1;
				if(cate.equals("Super_heroes")) {
					i=0;
				}
				else if(cate.equals("Desserts")) {
					i=1;
				}
				else if (cate.equals("Transportation")) {
						   i=2;
				}
				else {
					choiceCate.setText(""); // No category picked
				}
			
					
			  // info.setCategory(i);//if you use number for category.
			  // info,setCategory(cate);//if you use string for category.
			   cb.setDisable(true);
			}

		});
		
		nextButton.setOnAction(e->{  
			if(choiceCate.getText() =="") {
				return;
			}
			primaryStage.setScene(clientThree());
			}
		);
		primaryStage.setScene(clientOne());
		primaryStage.show();
	}
	
	
	//This is where the port/IP input  scene
	public Scene clientOne() {
		helloLabel.setStyle("-fx-font-size: 25");
		portLabel.setStyle("-fx-font-size: 25");
		ipLabel.setStyle("-fx-font-size: 25");
		
		HBox portBox = new HBox(25, portLabel, port);
		HBox ipBox = new HBox(25, ipLabel, ip);
		HBox enterBox = new HBox(50,powerClient);
		
		helloLabel.setAlignment(Pos.CENTER);
		portBox.setAlignment(Pos.CENTER);
		ipBox.setAlignment(Pos.CENTER);
		enterBox.setAlignment(Pos.CENTER);
		
		powerClient.setStyle("-fx-pref-width: 150px");
		powerClient.setAlignment(Pos.CENTER);
		
		VBox setupBox = new VBox(50,helloLabel, ipBox, portBox,enterBox);
        pane1.setCenter(setupBox);
		Scene scene = new Scene(pane1,600,600);
		return scene;
	}
	
	public Scene clientTwo() {
		attemptLabel.setTextFill(Color.web("white"));
		guesswordLabel.setTextFill(Color.web("white"));
		pickCategoryLabel.setTextFill(Color.web("white"));
		nextButton.setTextFill(Color.web("Black"));
		
		HBox setCatBox = new HBox(20,pickCategoryLabel,cb);
		setCatBox.setAlignment(Pos.CENTER);
		VBox setup2Box = new VBox(20,attemptLabel,guesswordLabel,setCatBox);
		setup2Box.setStyle("-fx-font-size: 25");
		setup2Box.setAlignment(Pos.TOP_CENTER);
		
		HBox nextBox = new HBox(200,nextButton);
		nextBox.setStyle("-fx-font-size: 25");
		nextBox.setAlignment(Pos.BASELINE_CENTER);
		
		HBox setPnumBox = new HBox(20,NumhintLabel,playerNumberLabel);
		setPnumBox.setStyle("-fx-font-size: 25");
		setPnumBox.setAlignment(Pos.BOTTOM_RIGHT);
		
		VBox setallBox = new VBox(300,setup2Box,nextButton);
		setallBox.setAlignment(Pos.CENTER);
		setallBox.setStyle("-fx-font-size: 25");
		pane2.setCenter(setallBox);
		pane2.setBottom(setPnumBox);
		pane2.setStyle("-fx-background-image:url(./wordguess.jpg);-fx-background-repeat:no-repeat");

		Scene scene = new Scene(pane2,600,600);
		scene.setFill(Color.TRANSPARENT);
		return scene;
	}
    public Scene clientThree() {
    	attemptLabel.setStyle("-fx-font-size: 25");
    	attemptLabel.setTextFill(Color.web("black"));
    	
    	attNumberLabel.setStyle("-fx-font-size: 25");
        attNumberLabel.setTextFill(Color.web("black"));
    	HBox attBox = new HBox(20,attemptLabel,attNumberLabel);
    	
    	
    	pane3.setCenter(attBox);
    	Scene scene = new Scene(pane3,600,600);
    	return scene;
    }
	
}
