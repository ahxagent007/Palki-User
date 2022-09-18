package com.alphacuetech.xian.palki_drive.DataModel;

public class Bid {
    long bid_id;
    String driver_id;
    int bid_amount;
    long car_id;
    String car_model;
    int car_seat;
    boolean car_ac;
    String car_condition;
    long auction_id;
    String car_image;
    String car_registration_no;

    public Bid() {
    }

    public Bid(long bid_id, String driver_id, int bid_amount, long car_id, String car_model, int car_seat, boolean car_ac, String car_condition, long auction_id, String car_image, String car_registration_no) {
        this.bid_id = bid_id;
        this.driver_id = driver_id;
        this.bid_amount = bid_amount;
        this.car_id = car_id;
        this.car_model = car_model;
        this.car_seat = car_seat;
        this.car_ac = car_ac;
        this.car_condition = car_condition;
        this.auction_id = auction_id;
        this.car_image = car_image;
        this.car_registration_no = car_registration_no;
    }

    public String getCar_registration_no() {
        return car_registration_no;
    }

    public void setCar_registration_no(String car_registration_no) {
        this.car_registration_no = car_registration_no;
    }

    public long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(long auction_id) {
        this.auction_id = auction_id;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }

    public long getBid_id() {
        return bid_id;
    }

    public void setBid_id(long bid_id) {
        this.bid_id = bid_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public int getBid_amount() {
        return bid_amount;
    }

    public void setBid_amount(int bid_amount) {
        this.bid_amount = bid_amount;
    }

    public long getCar_id() {
        return car_id;
    }

    public void setCar_id(long car_id) {
        this.car_id = car_id;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public int getCar_seat() {
        return car_seat;
    }

    public void setCar_seat(int car_seat) {
        this.car_seat = car_seat;
    }

    public boolean isCar_ac() {
        return car_ac;
    }

    public void setCar_ac(boolean car_ac) {
        this.car_ac = car_ac;
    }

    public String getCar_condition() {
        return car_condition;
    }

    public void setCar_condition(String car_condition) {
        this.car_condition = car_condition;
    }
}
