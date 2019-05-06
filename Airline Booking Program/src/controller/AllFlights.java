package controller;

import interfaces.onFlightsChange;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.City;
import model.Flight;
import utils.DBHandler;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AllFlights extends BaseController {
    @FXML
    public TableColumn id;
    @FXML
    public TableColumn acity;
    @FXML
    public TableColumn dcity;
    @FXML
    public TableColumn dtime;
    @FXML
    public TableColumn ddate;
    @FXML
    public TableColumn price;
    @FXML
    public TableColumn seats;
    @FXML
    public TextField search;
    @FXML
    private TableView<Flight> table;
    private
    Flight current;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cities=getCities();
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                try {
                    if(newValue.length()==0)
                        table.setItems(getFlightsList());
                    else
                        table.setItems(getFlightsList(Integer.parseInt(newValue)));

                    table.refresh();

                }catch (Exception e){
                    final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                    alert33.setTitle("Search error");
                    alert33.setContentText("only number are allowed");
                    alert33.setHeaderText("only number are allowed");
                    alert33.showAndWait();
                    return;
                }
            }
        });
        ObservableList<Flight> flights = getFlightsList();
        table.setRowFactory(tv -> {
            TableRow<Flight> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {

                    current = row.getItem();
                }
            });
            return row ;
        });
        table.setItems(flights);

        id.setCellFactory(new Callback<TableColumn<Flight,String >, TableCell<Flight,String >>() {
            @Override
            public TableCell<Flight,String > call(TableColumn<Flight,String > param) {
                return new TableCell<Flight,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Flight l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(l.getFlightId()+"");
                    }
                };
            }
        });
        acity.setCellFactory(new Callback<TableColumn<Flight,String >, TableCell<Flight,String >>() {
            @Override
            public TableCell<Flight,String > call(TableColumn<Flight,String > param) {
                return new TableCell<Flight,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Flight l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(getCityName(l.getArrivingCityId()));
                    }
                };
            }
        });
        dcity.setCellFactory(new Callback<TableColumn<Flight,String >, TableCell<Flight,String >>() {
            @Override
            public TableCell<Flight,String > call(TableColumn<Flight,String > param) {
                return new TableCell<Flight,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Flight l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(getCityName(l.getDepartureCityId()));
                    }
                };
            }
        });
        ddate.setCellFactory(new Callback<TableColumn<Flight,String >, TableCell<Flight,String >>() {
            @Override
            public TableCell<Flight,String > call(TableColumn<Flight,String > param) {
                return new TableCell<Flight,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Flight l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(l.getDepartureDate());
                    }
                };
            }
        });
        dtime.setCellFactory(new Callback<TableColumn<Flight,String >, TableCell<Flight,String >>() {
            @Override
            public TableCell<Flight,String > call(TableColumn<Flight,String > param) {
                return new TableCell<Flight,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Flight l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(l.getDepartureTime());
                    }
                };
            }
        });
        price.setCellFactory(new Callback<TableColumn<Flight,String >, TableCell<Flight,String >>() {
            @Override
            public TableCell<Flight,String > call(TableColumn<Flight,String > param) {
                return new TableCell<Flight,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Flight l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(l.getBasePrice());
                    }
                };
            }
        });
        seats.setCellFactory(new Callback<TableColumn<Flight,String >, TableCell<Flight,String >>() {
            @Override
            public TableCell<Flight,String > call(TableColumn<Flight,String > param) {
                return new TableCell<Flight,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Flight l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(l.getNumberOfseats());
                    }
                };
            }
        });
    }
    public void delete(ActionEvent actionEvent) {

        try {
            if(Login.userLogin!=null&&Login.userLogin.canDelete())
            {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Permission Error");
                alert33.setContentText("only admins can delete flight");
                alert33.setHeaderText("only admins can delete  flight");
                alert33.showAndWait();
                return;
            }
            if(current==null){
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("No flight is selected");
                alert33.setContentText("select flight");
                alert33.setHeaderText("select flight");
                alert33.showAndWait();
                return;
            }
            String q="delete from flighs where id ='"+current.getFlightId()+"'";
        dbHandler.executeQuery(q);
        table.setItems(getFlightsList());
        table.refresh();
        }
        catch (Exception e){
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("Exceptio");
            alert33.setContentText(e.getMessage());
            alert33.setHeaderText(e.getMessage());
            alert33.showAndWait();
            return;
        }
    }

    public void addfligt(ActionEvent actionEvent) {
        try {

            try {
                if(Login.userLogin!=null&&Login.userLogin.canAdd())
                {
                    final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                    alert33.setTitle("Permission Error");
                    alert33.setContentText("only admins can add flight");
                    alert33.setHeaderText("only admins can add flight");
                    alert33.showAndWait();
                    return;
                }
            Parent root = FXMLLoader.load(getClass().getResource("/view/addflight.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(actionEvent.getSource())).getScene().getWindow());
            stage.setTitle("Forget Password");
            stage.setScene(new Scene(root, 600, 500));
            stage.setResizable(false);
            stage.show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        }
        catch (Exception e){
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("Exception");
            alert33.setContentText(e.getMessage());
            alert33.setHeaderText(e.getMessage());
            alert33.showAndWait();
            return;
        }
    }

    public void update(ActionEvent actionEvent) {
        try {
            if(Login.userLogin!=null&&Login.userLogin.canEdit())
            {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Permission Error");
                alert33.setContentText("only admins can update flight");
                alert33.setHeaderText("only admins can update  flight");
                alert33.showAndWait();
                return;
            }
            if(current==null){
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("No flight is selected");
                alert33.setContentText("select flight");
                alert33.setHeaderText("select flight");
                alert33.showAndWait();
                return;
            }

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/addflight.fxml"));
            Parent root =loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(actionEvent.getSource())).getScene().getWindow());
            AddFlight controller =
                    loader.getController();
            controller.setFlight(current);
            controller.setOnFlightsChanged(new onFlightsChange() {
                @Override
                public void onFlightsChange() {
                    getCities();
                    table.setItems(getFlightsList());
                    table.refresh();
                }
            });
            stage.setTitle("Update Flight");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
        }

        catch (Exception e){
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("Exception");
            alert33.setContentText(e.getMessage());
            alert33.setHeaderText(e.getMessage());
            alert33.showAndWait();
            return;
        }
    }

    public void close(ActionEvent actionEvent) {

        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
