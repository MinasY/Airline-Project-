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
import javafx.stage.Stage;
import utils.DBHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Forgetpassword implements Initializable {
    private DBHandler dbHandler=DBHandler.getInstance();

    @FXML
    TextField username;
    @FXML
    TextField answer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void close(ActionEvent actionEvent) {

        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void find(ActionEvent actionEvent) {
        String username = this.username.getText();
        if (username == null || "".equals(username)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("username missed");
            alert33.setContentText("Please enter username");
            alert33.setHeaderText("Please enter username");
            alert33.showAndWait();
            return;
        }
        String query = "SELECT * FROM users where  username='" + username+ "'";
        ResultSet rs;

        try {
            rs = dbHandler.executeSelect(query);
            if(rs==null){
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Recovery error");
                alert33.setContentText("username doesn't exist");
                alert33.setHeaderText("username doesn't exist");
                alert33.showAndWait();
                return;
            }
            if (rs.next()) {
                String securityQuestion=rs.getString("securityQuestion");

                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Security question");
                alert33.setContentText(securityQuestion);
                alert33.setHeaderText("your security question is : ");
                alert33.showAndWait();
                return;
            }
            else {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Recovery error");
                alert33.setContentText("username doesn't exist");
                alert33.setHeaderText("username doesn't exist");
                alert33.showAndWait();
                return;
            }
        } catch (Exception e) {

        }
    }
    public void getPassword(ActionEvent actionEvent) {
        String username = this.username.getText();
        String answer = this.answer.getText();
        if (username == null || "".equals(username)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("username missed");
            alert33.setContentText("Please enter username");
            alert33.setHeaderText("Please enter username");
            alert33.showAndWait();
            return;
        }
        if (answer == null || "".equals(answer)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("answer missed");
            alert33.setContentText("Please enter answer");
            alert33.setHeaderText("Please enter answer");
            alert33.showAndWait();
            return;
        }
        String query = "SELECT * FROM users where  username='" + username+ "' and securityanswer='"+answer+"'";
        ResultSet rs;

        try {
            rs = dbHandler.executeSelect(query);
            if(rs==null){
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Recovery error");
                alert33.setContentText("Wrong answer");
                alert33.setHeaderText("Wrong answer");
                alert33.showAndWait();
                return;
            }
            if (rs.next()) {
                String password=rs.getString("password");

                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Security question");
                alert33.setContentText(password);
                alert33.setHeaderText("your password is : ");
                alert33.showAndWait();
                return;
            }
            else {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Recovery error");
                alert33.setContentText("Wrong answer");
                alert33.setHeaderText("Wrong answer");
                alert33.showAndWait();
                return;
            }
        } catch (Exception e) {

        }
    }
}
