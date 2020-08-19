import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private ListView<String> statusOfGameTxt;

    //other
    private BorderPane rootPane;
    static javafx.scene.text.Font currentFont = new javafx.scene.text.Font("Comic Sans MC", 20); //just for consistent
    static javafx.scene.text.Font buttonFont = new Font("Comic Sans MC", 12);

    // list view
    ListView<String> view = new ListView<>();

    // Server related stuff

    private Server connection;
    private Integer portNumber;

    //-------------
    private TextArea messages = new TextArea();


    //-------------

    Stage currentStage;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }


    //feel free to remove the starter code from this method
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        currentStage = primaryStage;

        primaryStage.setTitle("(Server) Let's Play Morra!!!");

        currentStage.setScene(new Scene(createMain(), 600, 200));
        currentStage.show();
    }

    public void gameplayFunction()
    {
        rootPane = new BorderPane();
        final int textFieldWidth = 100;
        final int textFieldHeight = 50;
        final int buttonSizeWidth = 70;
        final int buttonSizeHeight = 50;
        //number of clients
        numOfClientsTxt = new Text();
        numOfClientsTxt.setFont(currentFont);
        numOfClientsTxt.setText("No. of clients: TBD :)"); //will continuously be updated so not set in stone yet
        numOfClientsBox = new HBox(numOfClientsTxt);
        numOfClientsBox.setLayoutX(10);
        numOfClientsBox.setLayoutY(10);

        //player 1 box and things
        player1Played = new Text();
        player1Played.setFont(currentFont);
        player1Played.setText("Client#0 played: #"); //will continuously be updated so not set in stone yet
        player1Score = new Text();
        player1Score.setFont(currentFont);
        player1Score.setText("Client#0 score: #"); //will continuously be updated so not set in stone yet
        player1Box = new VBox(20, player1Played, player1Score);
        player1Box.setLayoutX(10);
        player1Box.setLayoutY(50);

        //player 2 box and things
        player2Played = new Text();
        player2Played.setFont(currentFont);
        player2Played.setText("Client#1 played: #"); //will continuously be updated so not set in stone yet
        player2Score = new Text();
        player2Score.setFont(currentFont);
        player2Score.setText("Client#1 score: #"); //will continuously be updated so not set in stone yet
        player2Box = new VBox(20, player2Played, player2Score);
        player2Box.setLayoutX(180);
        player2Box.setLayoutY(50);

        //status of game stuff
        statusOfGameTitle = new Text();
        statusOfGameTitle.setFont(currentFont);
        statusOfGameTitle.setText("Status of Game:");

        statusOfGameBox = new VBox(20, statusOfGameTitle);
        statusOfGameBox.setLayoutX(10);
        statusOfGameBox.setLayoutY(130);

        BorderPane borderPane = new BorderPane();
        borderPane.setLayoutX(10);
        borderPane.setLayoutY(160);
        borderPane.setCenter(statusOfGameTxt);
        borderPane.setMinHeight(250);
        borderPane.setMinWidth(380);

//
//        //play again things
//        playAgainTxt = new Text();
//        playAgainTxt.setFont(currentFont);
//        playAgainTxt.setText("Play again? ");
//        y_playAgain = new Button("Yes");
//        y_playAgain.setMinSize(buttonSizeWidth, buttonSizeHeight);
//        y_playAgain.setFont(buttonFont);
//        //y_playAgain.setDisable(true); // disabling
//        n_playAgain = new Button("No");
//        n_playAgain.setMinSize(buttonSizeWidth, buttonSizeHeight);
//        n_playAgain.setFont(buttonFont);
//        playAgainBox = new HBox(20, playAgainTxt, y_playAgain, n_playAgain);
//        playAgainBox.setLayoutX(10);
//        playAgainBox.setLayoutY(430);

        rootPane.getChildren().addAll(numOfClientsBox, player1Box, player2Box, statusOfGameBox, borderPane);//,
                                        //playAgainBox);
        Scene scene = new Scene(rootPane, 400, 500);
        currentStage.setScene(scene);
        currentStage.setTitle("(Server) Let's Play Morra!!!");
        currentStage.show();
    }

    @Override
    public void stop() throws Exception {
        connection.closeConnection();
    }

    private Parent createMain()
    {
        final int textFieldWidth = 100;
        final int textFieldHeight = 50;
        final int buttonSizeWidth = 70;
        final int buttonSizeHeight = 50;

        rootPane = new BorderPane();

        //things for the beginning of the game
        serverTitle = new Text();
        serverTitle.setFont(currentFont);
        serverTitle.setText("Announcement: Enter port number, then turn server on");

        portNum = new TextField();
        portNum.setMinSize(textFieldWidth, textFieldHeight);
        portNum.setText("5555"); //autofill for the default port number

        turnServerOff = new Button("Off");
        turnServerOff.setDisable(true); //when the server boots up nothing has happened yet
        turnServerOn = new Button("On");

        statusOfGameTxt = new ListView<String>();
        //statusOfGameTxt.getItems().add("You're my favorite pizza place");

        turnServerOn.setOnAction(event -> {
            Integer portTempNum = 0;
            try {
                portTempNum = Integer.valueOf(portNum.getText());
                serverTitle.setText("Announcement: Server has started");
                gameplayFunction();
            }
            catch (Exception e){
                serverTitle.setText("Announcement: INVALID PORT");
                return;
            }
            if(portTempNum<1000)
            {
                serverTitle.setText("Announcement: INVALID PORT");
                return;
            }
            this.portNumber = portTempNum;
            turnServerOn.setDisable(true);
            turnServerOff.setDisable(false);
            serverTitle.setText("Announcement: Waiting for connection");

            this.connection = createServer();
            try{
                this.connection.startConnection();
            }catch (Exception e)
            {
                //do nothing
            }
        });

        turnServerOff.setOnAction(event -> {
            turnServerOn.setDisable(false);
            turnServerOff.setDisable(true);
            serverTitle.setText("Announcement: Connection stopped");
            statusOfGameTxt.getItems().clear();

            try{
                this.connection.closeConnection();
            }
            catch (Exception e)
            {
                //do nothing
            }
        });



        //things for the GUI
        //server title

        serverTitleBox = new HBox(serverTitle);
        serverTitleBox.setLayoutX(50);
        serverTitleBox.setLayoutY(10);

        //port num
        portNumTxt = new Text();
        portNumTxt.setFont(currentFont);
        portNumTxt.setText("Enter port number:");

        portBox = new HBox(20, portNumTxt, portNum);
        portBox.setLayoutX(10);
        portBox.setLayoutY(50);

        //controls for on/off
        serverOnOffTxt = new Text();
        serverOnOffTxt.setFont(currentFont);
        serverOnOffTxt.setText("Turn server on/off:");

        turnServerOn.setMinSize(buttonSizeWidth, buttonSizeHeight);
        turnServerOn.setFont(buttonFont);

        turnServerOff.setMinSize(buttonSizeWidth, buttonSizeHeight);
        turnServerOff.setFont(buttonFont);
        serverOnOffBox = new HBox(20, serverOnOffTxt, turnServerOn, turnServerOff);
        serverOnOffBox.setLayoutX(10);
        serverOnOffBox.setLayoutY(110);



        //things for the scene
        //rootPane = new BorderPane();
        rootPane.getChildren().addAll(serverTitleBox, portBox, serverOnOffBox);
        rootPane.setStyle("-fx-background-color: seagreen");
        //gameplayFunction();
        return rootPane;
    }

    int counter = 0;

    List<String> list = new ArrayList<>(4);

    // add list view here instead of messages
    private Server createServer() {
        return new Server(this.portNumber,
                data-> {Platform.runLater(()->{
                    if (data.toString().contains("!Guess"))
                    {

                        list.add(data.toString().replace("!Guess","") + " is guessed");
                        counter++;
                    }
                    else if (data.toString().contains("!Hand"))
                    {
                        String dataTemp = data.toString().replace("!Hand","");
                        if(dataTemp.toString().contains("#0")) {
                            char temp = dataTemp.toString().charAt(11);
                            list.add(dataTemp.toString() + " fingers chosen");
                            player1Played.setText("Client#0 Guess: " + temp);
                            counter++;
                        }
                        else {
                            char temp = dataTemp.toString().charAt(11);
                            list.add(dataTemp.toString() + " fingers chosen");
                            player2Played.setText("Client#1 Guess: " + temp);
                            counter = 0;
                        }
                    }
                    else
                        list.add(data.toString());
                    });},
                    data-> {
                    Platform.runLater(()->{
                        String s = data.toString();
                        if (s.contains("!clientNum")) {
                            this.numOfClientsTxt.setText("No. of Clients: " + s.substring(10));
                        }
                        else if (s.contains("!Score:")) {
                            for (int i = 0; i < list.size(); i++)
                            {
                                statusOfGameTxt.getItems().add(list.get(i));
                            }
                            statusOfGameTxt.getItems().add("\n");
                            s = s.replace("!Score:", "");
                            System.out.println("s value is: " + s);
                            statusOfGameTxt.getItems().add(s +"\n");
                            char temp1 = data.toString().charAt(17);
                            player1Score.setText("Client#0 Score: " + temp1);
                            char temp2  = data.toString().charAt(37);
                            player2Score.setText("Client#1 Score: " + temp2);
                        }
                        else if (s.contains("!NewGame")) {
                            statusOfGameTxt.getItems().clear(); // CHECK THIS
                        }
                        else
                            statusOfGameTxt.getItems().add(s);
                    });
                }
        );

    }




}

