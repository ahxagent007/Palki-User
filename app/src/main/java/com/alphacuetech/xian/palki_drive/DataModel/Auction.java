package com.alphacuetech.xian.palki_drive.DataModel;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class Auction {

    long auction_id;
    String user_id;
    String from, to;
    LatLng from_latLng, to_latLng;
    Boolean round_trip_bool, date_future;
    Date date;
    String round_trip;
    String vehicle;


    public Auction() {
    }

    public Auction(long auction_id, String user_id, String from, String to, LatLng from_latLng, LatLng to_latLng, Boolean round_trip_bool, Boolean date_future, Date date, String round_trip, String vehicle) {
        this.auction_id = auction_id;
        this.user_id = user_id;
        this.from = from;
        this.to = to;
        this.from_latLng = from_latLng;
        this.to_latLng = to_latLng;
        this.round_trip_bool = round_trip_bool;
        this.date_future = date_future;
        this.date = date;
        this.round_trip = round_trip;
        this.vehicle = vehicle;
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

    public LatLng getFrom_latLng() {
        return from_latLng;
    }

    public void setFrom_latLng(LatLng from_latLng) {
        this.from_latLng = from_latLng;
    }

    public LatLng getTo_latLng() {
        return to_latLng;
    }

    public void setTo_latLng(LatLng to_latLng) {
        this.to_latLng = to_latLng;
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
