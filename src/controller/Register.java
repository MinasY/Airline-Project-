package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utils.DBHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Register implements Initializable {
    @FXML
    TextField first;
    @FXML
    TextField last;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    TextField address;
    @FXML
    TextField state;
    @FXML
    TextField zip;
    @FXML
    TextField email;
    @FXML
    TextField ssn;
    @FXML
    TextField question;
    @FXML
    TextField answer;
    private DBHandler dbHandler = DBHandler.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public boolean isEmpty(TextField f) {
        return f.getText() == null || "".equals(f.getText());
    }

    public void register(ActionEvent actionEvent) {
        if (isEmpty(username)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("username missed");
            alert33.setContentText("Please enter username");
            alert33.setHeaderText("Please enter username");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(first)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("first missed");
            alert33.setContentText("Please enter first name");
            alert33.setHeaderText("Please enter first name ");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(last)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("last name missed");
            alert33.setContentText("Please enter last name");
            alert33.setHeaderText("Please enter last name");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(password)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("password missed");
            alert33.setContentText("Please enter password");
            alert33.setHeaderText("Please enter password");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(address)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("address missed");
            alert33.setContentText("Please enter address");
            alert33.setHeaderText("Please enter address");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(state)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("username missed");
            alert33.setContentText("Please enter state");
            alert33.setHeaderText("Please enter state");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(zip)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("zip missed");
            alert33.setContentText("Please enter zip");
            alert33.setHeaderText("Please enter zip");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(email)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("email missed");
            alert33.setContentText("Please enter email");
            alert33.setHeaderText("Please enter email");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(question)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("question missed");
            alert33.setContentText("Please enter question");
            alert33.setHeaderText("Please enter question");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(answer)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("answer missed");
            alert33.setContentText("Please enter answer");
            alert33.setHeaderText("Please enter answer");
            alert33.showAndWait();
            return;
        }
        String select = "SELECT * FROM users where  username='" + username.getText() + "'";
        ResultSet rs;

        try {
            rs = dbHandler.executeSelect(select);
            if (rs == null) {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Registeration error");
                alert33.setContentText("Error quering database");
                alert33.setHeaderText("Error quering database");
                alert33.showAndWait();
                return;
            }
            if (rs.next()) {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Username already used");
                alert33.setContentText("Username already used");
                alert33.setHeaderText("Username already used");
                alert33.showAndWait();
            } else {
                String query = "INSERT INTO users(" +
                        " userName, password,securityQuestion,securityanswer," +
                        "firstName,lastName,address," +
                        "state,email,zip,ssn)VALUES('"
                        + username.getText() +"','"+
                        password.getText() +"','"+
                        question.getText()+"','"+
                        answer.getText()+"','"+
                        first.getText()+"','"+
                        last.getText()+"','"+
                        address.getText()+"','"+
                        state.getText()+"','"+
                        email.getText()+"','"+
                        zip.getText()+"','"+
                        ssn.getText()+ "')";
                dbHandler.executeQuery(query);

            }
        } catch (Exception e) {
            final Alert alert33 = new Alert(Alert.AlertType.ERROR);
            alert33.setTitle("Exception");
            alert33.setContentText(e.getLocalizedMessage());
            alert33.setHeaderText(e.getMessage());
            alert33.showAndWait();
        }
    }
}
