import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.layout.BorderPane;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CoffeeShop extends Application {

	//top part of GUI
	private HBox titleBox;
	private Text title;

	//left part of the GUI
	private Text buttonTitle;
	private Button placeOrder;
	private Button deleteOrder;
	private Button finishOrder;
	private HBox buttonBox;
	private Text buttonTitleTwo;
	private Button includeShot;
	private Button includeCream;
	private Button includeSugar;
	private Button includeCaramel;
	private Button includeSoymilk;
	private VBox buttonBoxTwo;

	//right part of the GUI
	private Text receiptTitle;
	private ListView<String> orderStackUp;
	private HBox receiptBox;
	private Text runningTotal;
	private HBox totalBox;

	//other
	private BorderPane rootPane;
	static Font currentFont = new Font("Comic Sans MC", 20); //just for consistent
	static Font buttonFont = new Font("Comic Sans MC", 12);
	Coffee order; //for creating the coffee
	private static DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Who want's coffee!!!");
		final int buttonSizeWidth = 120;
		final int buttonSizeHeight = 50;

		//things for a fresh start
		orderStackUp = new ListView<String>();

	//for the title
		title = new Text();
		title.setFont(currentFont);
		title.setTextAlignment(TextAlignment.CENTER);
		title.setText("Welcome to the Coffee Shop!");

	//buttons for creating/deleting/finishing order
		buttonTitle = new Text();
		buttonTitle.setFont(currentFont);
		buttonTitle.setTextAlignment(TextAlignment.CENTER);
		buttonTitle.setText("Choose either to :");

		//order button texts
		placeOrder = new Button("Place Order");
		deleteOrder = new Button("Delete Order");
		finishOrder = new Button("Finish Order");

		placeOrder.setFont(buttonFont);
		deleteOrder.setFont(buttonFont);
		finishOrder.setFont(buttonFont);

		//order button sizing
		placeOrder.setMinSize(buttonSizeWidth,buttonSizeHeight);
		deleteOrder.setMinSize(buttonSizeWidth,buttonSizeHeight);
		finishOrder.setMinSize(buttonSizeWidth,buttonSizeHeight);

		//button colors
		placeOrder.setStyle("-fx-background-color: limegreen");
		deleteOrder.setStyle("-fx-background-color: crimson");
		finishOrder.setStyle("-fx-background-color: mediumorchid");

		//disable these buttons because not needed for initial screen
		deleteOrder.setDisable(true);
		finishOrder.setDisable(true);

		//all of the order buttons and their functionality
		placeOrder.setOnAction(event->{
			orderStackUp.getItems().clear(); //resets what's on the GUI
			orderStackUp.getItems().add("Black Coffee: $3.99");
			order = new BasicCoffee();

			//enable ALL add-on buttons
			includeShot.setDisable(false);
			includeCream.setDisable(false);
			includeSugar.setDisable(false);
			includeCaramel.setDisable(false);
			includeSoymilk.setDisable(false);

			//enable these buttons because user can use them during ordering process
			deleteOrder.setDisable(false);
			finishOrder.setDisable(false);

			placeOrder.setDisable(true); //disable itself

			runningTotal.setText("");
		});

		deleteOrder.setOnAction(event->{
			orderStackUp.getItems().clear(); //resets what's on the GUI
			orderStackUp.getItems().add("Black Coffee: $3.99");
			order = new BasicCoffee();
		});

		finishOrder.setOnAction(event->{
			placeOrder.setDisable(false);
			deleteOrder.setDisable(true);
			finishOrder.setDisable(true);

			includeShot.setDisable(true);
			includeCream.setDisable(true);
			includeSugar.setDisable(true);
			includeCaramel.setDisable(true);
			includeSoymilk.setDisable(true);

			double cost = order.makeCoffee();
			runningTotal.setText("Total: $"+df.format(cost));
			System.out.println("Total: $"+df.format(cost));
		});

	//buttons that go on the left of the screen
		buttonTitleTwo = new Text();
		buttonTitleTwo.setFont(currentFont);
		buttonTitleTwo.setTextAlignment(TextAlignment.CENTER);
		buttonTitleTwo.setText("Extras!");

		//texts for the buttons
		includeShot = new Button("Extra Shot ($1.20)");
		includeCream = new Button("Cream ($0.50)");
		includeSugar = new Button("Sugar ($0.50)");
		includeCaramel = new Button("Caramel ($0.75)");
		includeSoymilk = new Button("Soy Milk ($0.85)");

		includeShot.setFont(buttonFont);
		includeCream.setFont(buttonFont);
		includeSugar.setFont(buttonFont);
		includeCaramel.setFont(buttonFont);
		includeSoymilk.setFont(buttonFont);

		//add-on button sizing
		includeShot.setMinSize(buttonSizeWidth,buttonSizeHeight);
		includeCream.setMinSize(buttonSizeWidth,buttonSizeHeight);
		includeSugar.setMinSize(buttonSizeWidth,buttonSizeHeight);
		includeCaramel.setMinSize(buttonSizeWidth,buttonSizeHeight);
		includeSoymilk.setMinSize(buttonSizeWidth,buttonSizeHeight);

		//button colors
		includeShot.setStyle("-fx-background-color: burlywood");
		includeCream.setStyle("-fx-background-color: cornsilk");
		includeSugar.setStyle("-fx-background-color: whitesmoke");
		includeCaramel.setStyle("-fx-background-color: goldenrod");
		includeSoymilk.setStyle("-fx-background-color: cornflowerblue");

		//disable all the buttons on initial load up of GUI
		includeShot.setDisable(true);
		includeCream.setDisable(true);
		includeSugar.setDisable(true);
		includeCaramel.setDisable(true);
		includeSoymilk.setDisable(true);

		//all the add-on buttons and their functionality
		includeShot.setOnAction(event->{
			orderStackUp.getItems().add(" + extra shot: $1.20");
			order = new ExtraShot(order);
		});

		includeCream.setOnAction(event->{
			orderStackUp.getItems().add(" + cream: $.50");
			order = new Cream(order);
		});

		includeSugar.setOnAction(event->{
			orderStackUp.getItems().add(" + sugar: $.50");
			order = new Sugar(order);
		});

		includeCaramel.setOnAction(event->{
			orderStackUp.getItems().add(" + pump of caramel: $.75");
			order = new Caramel(order);
		});

		includeSoymilk.setOnAction(event->{
			orderStackUp.getItems().add(" + soy milk: $.85");
			order = new Soymilk(order);
		});

	//for displaying things on the GUI
		buttonBox = new HBox(10, buttonTitle, placeOrder, deleteOrder, finishOrder);
		buttonBox.setLayoutX(20);
		buttonBox.setLayoutY(60);

		buttonBoxTwo = new VBox(10, buttonTitleTwo, includeShot, includeCream, includeSugar, includeCaramel, includeSoymilk);
		buttonBoxTwo.setLayoutX(20);
		buttonBoxTwo.setLayoutY(125);

		titleBox = new HBox(title);
		titleBox.setLayoutX(175);
		titleBox.setLayoutY(20);

		receiptTitle = new Text();
		receiptTitle.setFont(currentFont);
		receiptTitle.setText("Receipt:");
		receiptBox = new HBox(receiptTitle);
		receiptBox.setLayoutX(200);
		receiptBox.setLayoutY(125);

		runningTotal = new Text();
		runningTotal.setFont(currentFont);
		totalBox = new HBox(runningTotal);

		totalBox.setLayoutX(200);
		totalBox.setLayoutY(525);

		BorderPane orderPane = new BorderPane();

		orderPane.setLayoutX(200);
		orderPane.setLayoutY(160);
		orderPane.setCenter(orderStackUp);

		orderPane.setMinHeight(350);
		orderPane.setMinWidth(350);

		rootPane = new BorderPane();
		rootPane.getChildren().addAll(titleBox, buttonBox, buttonBoxTwo, receiptBox, orderPane, totalBox);
		rootPane.setStyle("-fx-background-color: seagreen");

		Scene scene = new Scene(rootPane,600,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
