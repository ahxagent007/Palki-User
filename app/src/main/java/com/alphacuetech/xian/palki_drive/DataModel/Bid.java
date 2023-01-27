package com.alphacuetech.xian.palki_drive.DataModel;

public class Bid {
   String bidID;
   String auctionID;
   String driverID;
   String carType;
   String seatCap;
   String bidAmount;
   String rating;
   String condition;
    public Bid() {

    }

    public Bid(String bidID, String auctionID, String driverID, String carType, String seatCap, String bidAmount,String rating,String condition) {
        this.bidID = bidID;
        this.auctionID = auctionID;
        this.driverID = driverID;
        this.carType = carType;
        this.seatCap = seatCap;
        this.bidAmount = bidAmount;
        this.rating=rating;
        this.condition=condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBidID() {
        return bidID;
    }

    public void setBidID(String bidID) {
        this.bidID = bidID;
    }

    public String getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(String auctionID) {
        this.auctionID = auctionID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getSeatCap() {
        return seatCap;
    }

    public void setSeatCap(String seatCap) {
        this.seatCap = seatCap;
    }

    public String getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
    }
}
