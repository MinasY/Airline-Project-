package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.City;
import model.Flight;
import utils.DBHandler;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *     abstract class for holding mutual variables and methods between controllers
 * </p>
 * **/
public abstract class BaseController implements Initializable {
    /**
     * <p>
     *     Object for accessing database and executing queries
     * </p>
     * **/
    DBHandler dbHandler = DBHandler.getInstance();
    public ObservableList<City> cities; //ObservableList object with City array passed in

    /**
     * @param f textfield to check it contain text or not
     *
     * @return true if it doesn\t contain text and false if it does
     * **/
    public boolean isEmpty(TextField f) {
        return f.getText() == null || "".equals(f.getText());
    }


    /**
     * get all flights list
     * @return return flights list
     **/
    public ObservableList<Flight> getFlightsList(){
        ObservableList<Flight> FlightList = FXCollections.observableArrayList();
        String query = "SELECT * FROM flights ";
        ResultSet rs;
        try {
            rs = dbHandler.executeSelect(query);
            Flight Flight;
            while(rs.next()) {
                Flight = new Flight(rs.getInt("id"),
                        rs.getInt("departurecity"),
                        rs.getInt("arrivalcity"),
                        rs.getString("departuredate"),
                        rs.getString("departuretime"),
                        rs.getString("price"),
                        rs.getString("seats"));
                FlightList.add(Flight);
            }
        } catch (Exception e) {
            e.printStackTrace(); //show error stack
        }
        return FlightList;
    }

    /**
     * get all flights list filtered by id
     * @param id id of flight to filter
     * @return return flights list
     **/
    public ObservableList<Flight> getFlightsList(int id){
        ObservableList<Flight> FlightList = FXCollections.observableArrayList();
        String query = "SELECT * FROM flights ";
        ResultSet rs;
        try {
            rs = dbHandler.executeSelect(query);
            Flight Flight;
            while(rs.next()) {
                Flight = new Flight(rs.getInt("id"),
                        rs.getInt("departurecity"),
                        rs.getInt("arrivalcity"),
                        rs.getString("departuredate"),
                        rs.getString("departuretime"),
                        rs.getString("price"),
                        rs.getString("seats"));
                if(Flight.getFlightId()==id||-1==id)
                    FlightList.add(Flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FlightList;
    }
    /**
     * return cities list
     * @return cities list
     **/
    public ObservableList<City> getCities(){
        ObservableList<City> citiesList = FXCollections.observableArrayList();
        String query = "SELECT * FROM cities ";
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
        cities=citiesList;
        return citiesList;
    }


    protected String getCityName(int arrivingCityId) {
        for (City c:cities)
            if(c.getId()==arrivingCityId)
                return c.getName();
        return "";
    }


    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
