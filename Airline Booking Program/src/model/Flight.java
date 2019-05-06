package model;

public class Flight {
    int  arrivingCityId;
    int departureCityId;
    String departureDate;
    String departureTime;
    String basePrice;
    int flightId;
    String numberOfseats;

    public Flight(int flightId,int departureCityId, int arrivingCityId,
                  String departureDate, String departureTime,
                  String basePrice,  String numberOfseats) {
        this.arrivingCityId = arrivingCityId;
        this.departureCityId = departureCityId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.basePrice = basePrice;
        this.flightId = flightId;
        this.numberOfseats = numberOfseats;
    }

    public int getArrivingCityId() {
        return arrivingCityId;
    }

    public void setArrivingCityId(int arrivingCityId) {
        this.arrivingCityId = arrivingCityId;
    }

    public int getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(int departureCityId) {
        this.departureCityId = departureCityId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getNumberOfseats() {
        return numberOfseats;
    }

    public void setNumberOfseats(String numberOfseats) {
        this.numberOfseats = numberOfseats;
    }
}
