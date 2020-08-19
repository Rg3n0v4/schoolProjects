import javafx.application.Application;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class GUIServer extends Application{
	Label welcomeLabel, portLabel, waitingLabel, currentGameLabel;//currentGameLabel show the message on Scene 3 top
	TextField serverport;//Get the port number input from server listening
	Button serverChoice; // This is the button for Scene 1 "Enter" button;
	HashMap<String, Scene> sceneMap;//sceneMap use to store 3 different GUI scenes on server  
	HBox portBox;
	VBox startBox; // a Container to store the all contents such as labels,TextField,button in scene 1
	Scene startScene; //First Scene in server : Scene1
    BorderPane startPane;
    Server serverConnection;
    PauseTransition pause = new PauseTransition(Duration.seconds(5));
    //Client clientConnection;
    ListView<String> listItemsServer;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Server) Let's Play Word Guess!!!");
		
		welcomeLabel = new Label("Welcome to Word Guess!");
		welcomeLabel.setFont(new Font("Cambria", 30));
		welcomeLabel.setTextFill(Color.web("white"));
		
		portLabel = new Label("Enter port #:");
		portLabel.setFont(new Font("Cambria", 30));
		portLabel.setTextFill(Color.web("white"));
		
		this.serverChoice = new Button("Enter");
		serverChoice.setDisable(false);
		this.serverChoice.setStyle("-fx-pref-width: 150px");
		//Set pause event
		pause.setOnFinished(e->serverChoice.setDisable(false));
		//Set button listening 
		this.serverChoice.setOnAction(e->{ 
			String portString= serverport.getText();
			//Test port input is number or not
			if(!GUIServer.isNumeric(portString)) {
				serverChoice.setDisable(true);
				serverport.setText("Input should be a number!");
				pause.play();
			}
			else {
			primaryStage.setScene(sceneMap.get("waiting"));
		    primaryStage.setTitle("Welcome to Word Guess Server");
			serverConnection = new Server(data -> {
				Platform.runLater(()->{
					listItemsServer.getItems().add(data.toString());
				});

			}, Integer.parseInt(serverport.getText()));//For constructor you made
 
			}//end else
        }); //End Enter Button setOnAction
		
		this.serverport = new TextField("Input port number");
		this.serverport.setStyle("-fx-pref-width: 200px");
		
		portBox = new HBox(20,portLabel,serverport);
		this.startBox = new VBox(30, welcomeLabel,portBox,serverChoice);
		//startBox.setStyle("-fx-background-image:url(./wordguess.jpg);-fx-background-repeat:no-repeat");
        //HBox.setMargin(startBox,new Insets(50,50,100,100) );
		VBox.setMargin(startBox, new Insets(200,100,200,50));
		startPane = new BorderPane();
		startPane.setPadding(new Insets(0));
		BorderPane.setMargin(startBox, new Insets(20,50,300,100));
		startPane.setStyle("-fx-background-image:url(./wordguess.jpg);-fx-background-repeat:no-repeat");

		startPane.setCenter(startBox);
		startScene = new Scene(startPane,600,600);
	   
		startScene.setFill(Color.TRANSPARENT);
		
		listItemsServer = new ListView<String>();
		
		sceneMap = new HashMap<String, Scene>();
		sceneMap.put("waiting",  createWaitingGui());
		
		//primaryStage.setScene(sceneMap.get("server"));
		sceneMap.put("server",  createServerGui());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
public Scene createWaitingGui() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: green");
		
		waitingLabel= new Label("Waiting for players......");
		waitingLabel.setFont(new Font("Cambria", 30));
		waitingLabel.setTextFill(Color.web("white"));
		
		pane.setCenter(waitingLabel);
		return new Scene(pane, 600, 600);
		
} 
public Scene createServerGui() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		
		pane.setCenter(listItemsServer);
	
		return new Scene(pane, 600, 600);
		
}
public final static boolean isNumeric(String s) {
    if (s != null && !"".equals(s.trim()))
        return s.matches("^[0-9]*$");
    else
        return false;
}      


}