package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicButtonUI;

public class Menu {

    /**
     * close the program
     **/
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * show login screen
     **/
    public void logout(ActionEvent actionEvent) {
        Parent root;
        try {
            //remove old user info
            Login.userLogin=null;
            root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(false);
            stage.show();
            ((Stage)(((Node)actionEvent.getSource()).getScene().getWindow())).close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * show all flights screen
     **/
    public void all(ActionEvent actionEvent) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("/view/allflights.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(actionEvent.getSource())).getScene().getWindow());
            stage.setTitle("Forget Password");
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(true);
            stage.show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * show booking flights screen
     **/
    public void book(ActionEvent actionEvent) {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("/view/booking.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(actionEvent.getSource())).getScene().getWindow());
            stage.setTitle("Booking flight");
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(true);
            stage.show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
