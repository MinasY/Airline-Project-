package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import model.City;
import model.Flight;
import utils.DBHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Booking extends BaseController {
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
    public ComboBox<City> to;
    @FXML
    public ComboBox<City> from;
    public TableColumn ticket;
    public TableColumn flight;
    public TableView<Book> tableTickets;
    public DatePicker datepicker;
    public Button filter;
    @FXML
    private TableView<Flight> table;
    Flight currentFlight;
    Book currentBook;
    private ObservableList<City> cities;
    public void close(ActionEvent actionEvent) {

        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cities=getCities();
        from.setItems(cities);
        to.setItems(cities);
        if(cities.size()>0){
        from.setValue(cities.get(0));
        to.setValue(cities.get(0));
        }
        showBooking();
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

                    currentFlight = row.getItem();
                }
            });
            return row ;
        });
        tableTickets.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY) {

                    currentBook = row.getItem();
                }
            });
            return row ;
        });
        table.setItems(flights);
        table.refresh();
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


        ticket.setCellFactory(new Callback<TableColumn<Book,String >, TableCell<Book,String >>() {
            @Override
            public TableCell<Book,String > call(TableColumn<Book,String > param) {
                return new TableCell<Book,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Book l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(l.getTicketNumber()+"");
                    }
                };
            }
        });

        flight.setCellFactory(new Callback<TableColumn<Book,String >, TableCell<Book,String >>() {
            @Override
            public TableCell<Book,String > call(TableColumn<Book,String > param) {
                return new TableCell<Book,String >(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            return;

                        int currentIndex = indexProperty()
                                .getValue() < 0 ? 0
                                : indexProperty().getValue();
                        Book l=param.getTableView().getItems()
                                .get(currentIndex);
                        setText(l.getFlightId()+"");
                    }
                };
            }
        });

    }
    public ObservableList<Book> getBooks(){
        ObservableList<Book> books = FXCollections.observableArrayList();
        String query = "SELECT * FROM books where userid="+"'"+Login.userLogin.getId()+"'";
        ResultSet rs;
        try {
            rs = dbHandler.executeSelect(query);
            Book book;
            while(rs.next()) {
                 book = new Book();
                 book.setTicketNumber(rs.getInt("ticket"));
                 book.setFlightId(rs.getInt("flightid"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public void showBooking(){
        tableTickets.setItems(getBooks());
        tableTickets.refresh();
    }

    public void unbook(ActionEvent actionEvent) {
        if(currentBook==null)
        {
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("Error");
            alert33.setContentText("Select ticket first");
            alert33.setHeaderText("Select ticket first");
            alert33.showAndWait();
            return;
        }
        String  q="delete from books where ticket='"+currentBook.getTicketNumber()+"'";
        dbHandler.executeQuery(q);
        showBooking();
    }
    /**
     * book flight
     * **/
    public void book(ActionEvent actionEvent) {
        //chck if user selected flight in taable
        if(currentFlight==null){
            final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
            alert33.setTitle("No flight is selected");
            alert33.setContentText("select flight");
            alert33.setHeaderText("select flight");
            alert33.showAndWait();
            return;
        }
        try {
            //check if user didn't book the flight before
            String query="select * from books where flightid='"+currentFlight.getFlightId()
                    +"' and "+" userid="+"'"+Login.userLogin.getId()+"'";;
            ResultSet rs;
            rs = dbHandler.executeSelect(query);
            while(rs.next()) {

                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Book Error");
                alert33.setContentText("You have already booked this flight");
                alert33.setHeaderText("you can book only one seat per flight");
                alert33.showAndWait();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //check if the flight still have available seats and not full
            String query="select * from books where flightid='"+currentFlight.getFlightId()+"'";
            ResultSet rs;
            rs = dbHandler.executeSelect(query);
            System.out.println(rs.getFetchSize());
            if((rs.getFetchSize()>=Integer.parseInt(currentFlight.getNumberOfseats()))) {
                final Alert alert33 = new Alert(Alert.AlertType.INFORMATION);
                alert33.setTitle("Book Error");
                alert33.setContentText("flight is full");
                alert33.setHeaderText("flight is full");
                alert33.showAndWait();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //book flight
        try {
            String query="INSERT INTO books (userid, flightid) VALUES ('"+
                    Login.userLogin.getId()+"', '"+currentFlight.getFlightId()+"');";

             dbHandler.executeQuery(query);
            showBooking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hold the state of filter button
     **/
    public  boolean isFiter;
    /**
     *filter  flights based on city and date
     **/
    public void filter(ActionEvent actionEvent) {

        if (isFiter) {
            table.setItems(getFlightsList());
            table.refresh();
            filter.setText("Filter");
            isFiter=!isFiter;
            return;
        }
        isFiter=!isFiter;
        System.out.println(datepicker.getValue().format(DateTimeFormatter.ISO_DATE));
        ObservableList<Flight> FlightList = FXCollections.observableArrayList();
        String query = "SELECT * FROM flights where departurecity ='"+from.getValue().getId()+"' and arrivalcity ='"+to.getValue().getId()
                +"' and departuredate='"+ datepicker.getValue().format(DateTimeFormatter.ISO_DATE) +"'";
        ResultSet rs;
        try {
            rs = dbHandler.executeSelect(query);
            Flight Flight;
            while(rs.next()) {
                Flight = new Flight(rs.getInt("id"),
                        rs.getInt("departurecity")
                        ,rs.getInt("arrivalcity")
                        ,rs.getString("departuredate")
                        ,rs.getString("departuretime")
                        ,rs.getString("price")
                        ,rs.getString("seats"));
                FlightList.add(Flight);
            }
            table.setItems(FlightList);
            table.refresh();
            filter.setText("Remove filter");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
