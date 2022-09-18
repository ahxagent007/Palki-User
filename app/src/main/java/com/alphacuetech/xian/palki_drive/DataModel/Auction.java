package com.alphacuetech.xian.palki_drive.DataModel;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Date;

public class Auction implements Serializable {

    long auction_id;
    String user_id;
    String from, to;
    double from_lat, from_lng, to_lat, to_lng;
    Boolean round_trip_bool, date_future;
    Date date;
    String round_trip;
    String vehicle;


    public Auction() {
    }

    public Auction(long auction_id, String user_id, String from, String to, double from_lat, double from_lng, double to_lat, double to_lng, Boolean round_trip_bool, Boolean date_future, Date date, String round_trip, String vehicle) {
        this.auction_id = auction_id;
        this.user_id = user_id;
        this.from = from;
        this.to = to;
        this.from_lat = from_lat;
        this.from_lng = from_lng;
        this.to_lat = to_lat;
        this.to_lng = to_lng;
        this.round_trip_bool = round_trip_bool;
        this.date_future = date_future;
        this.date = date;
        this.round_trip = round_trip;
        this.vehicle = vehicle;
    }

    public double getFrom_lat() {
        return from_lat;
    }

    public void setFrom_lat(double from_lat) {
        this.from_lat = from_lat;
    }

    public double getFrom_lng() {
        return from_lng;
    }

    public void setFrom_lng(double from_lng) {
        this.from_lng = from_lng;
    }

    public double getTo_lat() {
        return to_lat;
    }

    public void setTo_lat(double to_lat) {
        this.to_lat = to_lat;
    }

    public double getTo_lng() {
        return to_lng;
    }

    public void setTo_lng(double to_lng) {
        this.to_lng = to_lng;
    }

    public long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(long auction_id) {
        this.auction_id = auction_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Boolean getRound_trip_bool() {
        return round_trip_bool;
    }

    public void setRound_trip_bool(Boolean round_trip_bool) {
        this.round_trip_bool = round_trip_bool;
    }

    public Boolean getDate_future() {
        return date_future;
    }

    public void setDate_future(Boolean date_future) {
        this.date_future = date_future;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRound_trip() {
        return round_trip;
    }

    public void setRound_trip(String round_trip) {
        this.round_trip = round_trip;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
