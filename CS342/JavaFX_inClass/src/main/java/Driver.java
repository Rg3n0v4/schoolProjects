import java.util.HashMap;

import javafx.animation.PauseTransition;
import javafx.application.Application;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Driver extends Application{

    private TextField text;
    private Button btn;
    private VBox paneCenter;
    private Scene scene;
    private MenuBar menuBar;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        text = new TextField();

        btn = new Button("press me");

        menuBar = new MenuBar();

        Menu mOne = new Menu("option 1");
        Menu mTwo = new Menu("option 2");
        Menu mThree = new Menu("option 3");

        MenuItem iOne = new MenuItem("turn red");
        MenuItem iTwo = new MenuItem("increase the font");
        MenuItem iThree = new MenuItem("clear the text");

        iThree.setOnAction(e->text.clear());

        iTwo.setOnAction(e->{text.setStyle("-fx-font-size: 25;"); menuBar.setStyle("-fx-font-size: 25;");
            btn.setStyle("-fx-font-size: 25;");
        });

        iOne.setOnAction(e->{paneCenter.setStyle("-fx-background-color: red;" + "-fx-border-color: hotpink;"+
                "-fx-border-width: 12;");
            text.setStyle("-fx-background-color: coral;"); menuBar.setStyle("-fx-background-color: plum;");
            btn.setStyle("-fx-background-color: purple;");});

        mOne.getItems().addAll(iOne,iTwo,iThree);

        menuBar.getMenus().addAll(mOne,mTwo,mThree);


        Image pic = new Image("jelly.jpg");
        ImageView imagePic = new ImageView(pic);

        imagePic.setFitHeight(250);
        imagePic.setFitWidth(250);
        imagePic.setPreserveRatio(true);

        imagePic.setOnMouseClicked(e-> text.setText("clicked on image"));


		/*
		//good if you want several widgets to do the same thing when clicked
		EventHandler<ActionEvent> handler1 = new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				text.setText("inside event handler");
			}
		};

		btn.setOnAction(handler1);
		*/

		/*
		//anonymous class: good if widget has a unique action when clicked
		btn.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				text.setText("inside annonymous class");
			}
		});
		*/

        btn.setOnAction(e->text.setText("inside lambda expression"));


        paneCenter = new VBox(20,menuBar,text, btn, imagePic);

        scene = new Scene(paneCenter, 700, 700);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}