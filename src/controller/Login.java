package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import utils.DBHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Login extends BaseController {
    @FXML
    TextField password;
    @FXML
    TextField user;
    /**
     * vairable for holding curent logged user
     **/
    static User userLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * <p>
     *     show forget password screen
     * </p>
     * **/
    public void forget(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/forgetpassword.fxml"));
            Stage stage = new Stage();
            //prevent user interation to login screen until closing forget password screen
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) (actionEvent.getSource())).getScene().getWindow());
            stage.setTitle("Forget Password");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void login(ActionEvent actionEvent) {
        String pass = password.getText();
        String username = user.getText();
        //check if user entered username
        if (username == null || "".equals(username)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("password missed");
            alert33.setContentText("Please enter username");
            alert33.setHeaderText("Please enter username");
            alert33.showAndWait();
            return;
        }
        //check if user entered password
        if (pass == null || "".equals(pass)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("password missed");
            alert33.setContentText("Please enter password");
            alert33.setHeaderText("Please enter password");
            alert33.showAndWait();
            return;
        }
        String query = "SELECT * FROM users where  username='" + username + "' and password='" + pass + "'";
        ResultSet rs;
        try {
            //query the database for username and password
            rs = dbHandler.executeSelect(query);
            //if data is returned then user login successfully
            if (rs.next()) {
                try {
                    userLogin = new User();
                    userLogin.setId(rs.getInt("id"));
                    userLogin.setType(rs.getInt("type"));
                    Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                            "/view/menu.fxml"));
                    Stage stage = (Stage) password.getScene().getWindow();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                } catch (Exception io) {
                    io.printStackTrace();
                }
            } else {
                //warn user for wrong username and password
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Login error");
                alert33.setContentText("username and password doesn't match");
                alert33.setHeaderText("username and password doesn't match");
                alert33.showAndWait();
                return;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * <p>
     *     show forget register screen
     * </p>
     * **/
    public void register(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
            Stage stage = new Stage();
            //prevent user interation to login screen until closing register screen
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) (actionEvent.getSource())).getScene().getWindow());
            stage.setTitle("Register");
            stage.setScene(new Scene(root, 600, 500));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
