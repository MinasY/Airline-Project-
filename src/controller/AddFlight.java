package controller;

import interfaces.onFlightsChange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.City;
import model.Flight;
import utils.DBHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class AddFlight extends BaseController {
    @FXML
    public TextField seats;
    @FXML
    public TextField flightid;
    @FXML
    public TextField price;
    @FXML
    public ComboBox<City> arrivalcity;
    @FXML
    public ComboBox<String> departingtime;
    @FXML
    public DatePicker departingdate;
    @FXML
    public ComboBox<City> departingCity;
    @FXML
    public TextField newcity;
    private Flight current;
    private ObservableList<City> cities;
    ObservableList<String>times=  FXCollections.observableArrayList();
    private onFlightsChange onFlightsChanged;

    public ObservableList<City> getCities(){
        ObservableList<City> citiesList = FXCollections.observableArrayList();
        String query = "SELECT * FROM citties ";
        ResultSet rs;
        try {
            rs = dbHandler.executeSelect(query);
            City city;
            while(rs.next()) {
                city = new City(rs.getInt("id"),
                        rs.getString("city"));
                citiesList.add(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return citiesList;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cities=getCities();
        departingCity.setItems(cities);
        arrivalcity.setItems(cities);
        if(cities.size()>0){

            departingCity.setValue(cities.get(0));
            arrivalcity.setValue(cities.get(0));
        }
        //add time combobox
        for(int i=0;i<10;i++)
            times.add("0"+i+":00");
        for(int i=10;i<23;i++)
            times.add(i+":00");
        departingtime.setItems(times);
        departingtime.setValue(times.get(0));
    }
    /**
     * add new flight with error code for invalid fields
     * **/
    public void addFlight(ActionEvent actionEvent) {
        if (isEmpty(flightid)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("flight id missed");
            alert33.setContentText("Please enter flight id");
            alert33.setHeaderText("Please enter flight id");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(price)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("price missed");
            alert33.setContentText("Please enter price");
            alert33.setHeaderText("Please enter price");
            alert33.showAndWait();
            return;
        }
        if (isEmpty(seats)) {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("seats missed");
            alert33.setContentText("Please enter seats");
            alert33.setHeaderText("Please enter seats");
            alert33.showAndWait();
            return;
        }
        if(current!=null){
            try {
            String  q="UPDATE 'mydb'.'flights' SET " +
                    " 'departurecity' = '"+departingCity.getValue().getId() +"', " +
                    "'arrivalcity' = '"+ arrivalcity.getValue().getId()+"'," +
                    " 'departuredate' = '"+departingdate.getValue().format(DateTimeFormatter.ISO_DATE) +"'," +
                    " 'departuretime' = '"+ departingtime.getValue()+"'," +
                    " 'price' = '"+price.getText()+"', 'seats' = '"+ seats.getText()+"' " +
                    "WHERE ('id' = '"+ flightid.getText()+"')";
            dbHandler.executeQuery(q);
            if(onFlightsChanged!=null)
                onFlightsChanged.onFlightsChange();
            return;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        String select = "SELECT * FROM flights where  id='" + flightid.getText() + "'";
        ResultSet rs;

        try {
            rs = dbHandler.executeSelect(select);
            if (rs == null) {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Add error");
                alert33.setContentText("Error quering database");
                alert33.setHeaderText("Error quering database");
                alert33.showAndWait();
                return;
            }
            if (rs.next()) {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("id already used");
                alert33.setContentText("id already used");
                alert33.setHeaderText("id already used");
                alert33.showAndWait();
            } else {
                String query = "INSERT INTO flights (id, departurecity, arrivalcity, departuredate, " +
                        "departuretime,price,seats) VALUES " +
                        "('" +
                        flightid.getText()
                        +"', '" +
                        departingCity.getValue().getId() +
                        "', '" +
                        arrivalcity.getValue().getId() +
                        "', '" +
                        departingdate.getValue().format(DateTimeFormatter.ISO_DATE) +
                        "', '" +
                        departingtime.getValue() +
                        "', '" +
                        price.getText() +
                        "', '" +
                        seats.getText() +
                        "');";
                System.out.println(query);
                dbHandler.executeQuery(query);
                if(onFlightsChanged!=null)
                    onFlightsChanged.onFlightsChange();
            }
        } catch (Exception e) {
            final Alert alert33 = new Alert(Alert.AlertType.ERROR);
            alert33.setTitle("Exception");
            alert33.setContentText(e.getLocalizedMessage());
            alert33.setHeaderText(e.getMessage());
            alert33.showAndWait();
        }
    }
    /**
     * add new city
     * **/
    public void addcity(ActionEvent actionEvent) {
        if(isEmpty(newcity)){
        final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
        alert33.setTitle("New city name is missed");
        alert33.setContentText("New city name is missed");
        alert33.setHeaderText("New city name is missed");
        alert33.showAndWait();
        return;
        }
        String q="INSERT INTO 'citties' ('city') VALUES ('"+newcity.getText().toString().trim()+"');";
        dbHandler.executeQuery(q);
        departingCity.setItems(getCities());
        arrivalcity.setItems(getCities());

    }
    /**
     * set onflight add or update listener
     *
     * @param onFlightsChanged listener to notify when add or update occur
     * **/
    public void setOnFlightsChanged(onFlightsChange onFlightsChanged){
        if(onFlightsChanged!=null)
            this.onFlightsChanged=onFlightsChanged;
    }
    /**
     * set fields values to flight
     * @param current flight to update
     **/
    public void setFlight(Flight current) {
        this.current=current;
        cities=getCities();
        departingCity.setItems(cities);
        arrivalcity.setItems(cities);
        if(current!=null){
            for(int i=0;i<cities.size();i++)if(cities.get(i).getId()==current.getArrivingCityId())
            {
                if(current.getArrivingCityId()==cities.get(i).getId())
                    arrivalcity.setValue(cities.get(i));
                if(current.getDepartureCityId()==cities.get(i).getId())
                    departingCity.setValue(cities.get(i));
            }
            departingdate.setValue(LOCAL_DATE(current.getDepartureDate()));
            flightid.setText(current.getFlightId()+"");
            flightid.setEditable(false);
            departingtime.setValue(current.getDepartureTime());
            price.setText(current.getBasePrice()+"");
            seats.setText(current.getNumberOfseats()+"");
        }
    }
}
