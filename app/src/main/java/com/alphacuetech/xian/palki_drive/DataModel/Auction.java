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


}
