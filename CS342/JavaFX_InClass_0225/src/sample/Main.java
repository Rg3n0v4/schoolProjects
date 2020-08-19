package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Button btn1;
    private Button btn2;
    private TextField txt1;
    private TextField txt2;
    private BorderPane paneCenter;
    private VBox buttonPane;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        btn1 = new Button("button 1");
        btn2 = new Button("button 2");
        txt1 = new TextField();
        txt2 = new TextField();

        btn1.setOnAction(e->
        {
            String s = txt1.getText();
            s += "said the human!";
            txt2.setText(s);
            txt1.clear();
        });

        btn2.setOnAction(e->txt2.clear());

        buttonPane = new VBox(btn1, btn2);

        paneCenter = new BorderPane();
        paneCenter.setCenter(txt1);
        paneCenter.setRight(txt2);
        paneCenter.setLeft(buttonPane);

        scene = new Scene(paneCenter, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
