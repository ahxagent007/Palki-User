package com.alphacuetech.xian.palki_drive.DataModel;

public class RentalData {
    String carType;
    String seatcap;
    String currentLoc;
    String destLoc;
    String date_;
    String time_;
    String roundTrip;
    String note;
    String user_id;
    String auction_id;
    String from_latLng;
    String to_latLng;
    String promo;
    String running;

    public RentalData() {

    }

    public RentalData(String carType, String seatcap, String currentLoc, String destLoc, String date_, String time_, String roundTrip, String note, String user_id, String auction_id, String from_latLng, String to_latLng,String promo,String running) {
        this.carType = carType;
        this.seatcap = seatcap;
        this.currentLoc = currentLoc;
        this.destLoc = destLoc;
        this.date_ = date_;
        this.time_ = time_;
        this.roundTrip = roundTrip;
        this.note = note;
        this.user_id = user_id;
        this.auction_id = auction_id;
        this.from_latLng = from_latLng;
        this.to_latLng = to_latLng;
        this.promo=promo;
        this.running=running;
    }

    public String getRunning() {
        return running;
    }

    public void setRunning(String running) {
        this.running = running;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getSeatcap() {
        return seatcap;
    }

    public void setSeatcap(String seatcap) {
        this.seatcap = seatcap;
    }

    public String getCurrentLoc() {
        return currentLoc;
    }

    public void setCurrentLoc(String currentLoc) {
        this.currentLoc = currentLoc;
    }

    public String getDestLoc() {
        return destLoc;
    }

    public void setDestLoc(String destLoc) {
        this.destLoc = destLoc;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
    }

    public String getRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(String roundTrip) {
        this.roundTrip = roundTrip;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(String auction_id) {
        this.auction_id = auction_id;
    }

    public String getFrom_latLng() {
        return from_latLng;
    }

    public void setFrom_latLng(String from_latLng) {
        this.from_latLng = from_latLng;
    }

    public String getTo_latLng() {
        return to_latLng;
    }

    public void setTo_latLng(String to_latLng) {
        this.to_latLng = to_latLng;
    }
}
