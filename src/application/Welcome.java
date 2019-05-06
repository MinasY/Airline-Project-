package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Welcome extends Application {
    public final static String dbUserName="root";
    public final static String dbPassword="root";
    public final static String dbName="mydb";
    public final static String dbServer="localhost";
    public final static String dbPort="3306";
    public final static String connectionString="jdbc:mysql://"+dbServer+":"+dbPort+"/"+dbName;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Text welcome = new Text("Welcome");
        welcome.setFill(Color.BLACK);
        welcome.setStyle("-fx-font: 24 arial;");
        StackPane root = new StackPane(welcome);
        primaryStage.setScene(new Scene(root, 300, 120));
        primaryStage.centerOnScreen();
        primaryStage.show();
        new ss(primaryStage).start();




    }
    class ss extends Thread {
        Stage primaryStage;
        public ss(Stage primaryStage){
            this.primaryStage=primaryStage;
        }
        @Override
        public void run() {
            //make something (here thrad.sleep)
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try{
                        Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                                "/view/login.fxml"));
                        Scene scene = new Scene(parent);
                        Stage s=new Stage();
                        s.setScene(scene);
                        primaryStage.close();
                        s.show();}catch (Exception e){

                    }
                }
            });
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}