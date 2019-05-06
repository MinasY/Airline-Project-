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

/**
 * main class for creating application thread
 */
public class Welcome extends Application {
    /***
     * <p>
     *     mysql server username
     * </p>
     * **/
    public final static String dbUserName="root";

    /***
     * <p>
     *     mysql server password
     * </p>
     * **/
    public final static String dbPassword="root";

    /***
     * <p>
     *     database name
     * </p>
     * **/
    public final static String dbName="mydb";

    /***
     * <p>
     *     mysql server url
     * </p>
     * **/
    public final static String dbServer="localhost";

    /***
     * <p>
     *     mysql server port
     * </p>
     * **/
    public final static String dbPort="3306";
    /**
     * <p>
     *     mydql connection string
     * </p>
     * **/
    public final static String connectionString="jdbc:mysql://"+dbServer+":"+dbPort+"/"+dbName;
    @Override
    public void start(Stage primaryStage) throws Exception {
        //show splash screen with simple text
        Text welcome = new Text("Welcome");
        welcome.setFill(Color.BLACK);
        welcome.setStyle("-fx-font: 24 arial;");
        StackPane root = new StackPane(welcome);
        primaryStage.setScene(new Scene(root, 300, 120));
        primaryStage.centerOnScreen();
        primaryStage.show();
        new ss(primaryStage).start();
    }
    /***
     * <p>
     *the class is responsible for waiting one second and starting the login screen
     * </p>
     * * */
    class ss extends Thread {
        Stage primaryStage;
        public ss(Stage primaryStage){
            this.primaryStage=primaryStage;
        }
        @Override
        public void run() {
            //make something (here thread.sleep)
            try {
                //wait one second
                Thread.sleep(1000);
            }catch (Exception e){

            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try{
                        //start login screen
                        Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                                "/view/login.fxml"));
                        Scene scene = new Scene(parent);
                        Stage s=new Stage();
                        s.setScene(scene);
                        primaryStage.close();
                        s.show();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            });
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}