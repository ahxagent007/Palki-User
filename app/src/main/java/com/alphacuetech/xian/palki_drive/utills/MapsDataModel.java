package com.alphacuetech.xian.palki_drive.utills;

import com.google.android.gms.maps.model.LatLng;

public class MapsDataModel {

    String MODEL, START, END;
    LatLng destLatLng, currentLatLng;

    public MapsDataModel(String MODEL, String START, String END, LatLng currentLatLng, LatLng destLatLng) {
        this.MODEL = MODEL;
        this.START = START;
        this.END = END;
        this.destLatLng = destLatLng;
        this.currentLatLng = currentLatLng;
    }

    public LatLng getDestLatLng() {
        return destLatLng;
    }

    public void setDestLatLng(LatLng destLatLng) {
        this.destLatLng = destLatLng;
    }

    public LatLng getCurrentLatLng() {
        return currentLatLng;
    }

    public void setCurrentLatLng(LatLng currentLatLng) {
        this.currentLatLng = currentLatLng;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getSTART() {
        return START;
    }

    public void setSTART(String START) {
        this.START = START;
    }

    public String getEND() {
        return END;
    }

    public void setEND(String END) {
        this.END = END;
    }
}
