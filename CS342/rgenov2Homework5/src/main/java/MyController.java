//package application;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MyController implements Initializable {

    //top part of GUI
    @FXML
    private HBox buttonBox;
    @FXML
    private Button placeOrder;
    @FXML
    private Button deleteOrder;
    @FXML
    private Button finishOrder;

    //left part of the GUI
    @FXML
    private Button includeShot;
    @FXML
    private Button includeCream;
    @FXML
    private Button includeSugar;
    @FXML
    private Button includeCaramel;
    @FXML
    private Button includeSoymilk;
    @FXML
    private VBox buttonBoxTwo;

    //right part of the GUI
    @FXML
    private ListView<String> orderStackUp;
    @FXML
    private HBox receiptBox;
    @FXML
    private Text runningTotal;
    @FXML
    private HBox totalBox;

    //other
	@FXML
	private BorderPane rootPane;
    Coffee order;
    private static DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        deleteOrder.setDisable(true);
        finishOrder.setDisable(true);
        includeShot.setDisable(true);
        includeCream.setDisable(true);
        includeSugar.setDisable(true);
        includeCaramel.setDisable(true);
        includeSoymilk.setDisable(true);
	}

    public void placeOrderFtn(ActionEvent e) throws IOException
    {
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
    }

	public void deleteOrderFtn(ActionEvent e) throws IOException
    {
        orderStackUp.getItems().clear(); //resets what's on the GUI
        orderStackUp.getItems().add("Black Coffee: $3.99");
        order = new BasicCoffee();
	}
	
	public void finishOrderFtn(ActionEvent e) throws IOException
    {
        placeOrder.setDisable(false);
        deleteOrder.setDisable(true);
        finishOrder.setDisable(true);

        includeShot.setDisable(true);
        includeCream.setDisable(true);
        includeSugar.setDisable(true);
        includeCaramel.setDisable(true);
        includeSoymilk.setDisable(true);

        double cost = order.makeCoffee();

	}

	public void includeShotFtn(ActionEvent e) throws IOException
    {
        orderStackUp.getItems().add(" + extra shot: $1.20");
        order = new ExtraShot(order);
    }

    public void includeCreamFtn(ActionEvent e) throws IOException
    {
        orderStackUp.getItems().add(" + cream: $.50");
        order = new Cream(order);
    }
    public void includeSugarFtn(ActionEvent e) throws IOException
    {
        orderStackUp.getItems().add(" + sugar: $.50");
        order = new Sugar(order);
    }
    public void includeCaramelFtn(ActionEvent e) throws IOException
    {
        orderStackUp.getItems().add(" + pump of caramel: $.75");
        order = new Caramel(order);
    }
    public void includeSoymilkFtn(ActionEvent e) throws IOException
    {
        orderStackUp.getItems().add(" + soy milk: $.85");
        order = new Soymilk(order);
    }

}
