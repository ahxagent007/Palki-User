package com.alphacuetech.xian.palki_drive.utills;

import java.util.TimeZone;

public class CommonFunctions {

    public long getCurrentTimeMilis(){
        long date = System.currentTimeMillis();
        int offset = TimeZone.getTimeZone("GMT+6").getOffset(date);
        //System.out.printf("%d + %d = %d%n", date, offset, date + offset);

        return date + offset;
    }
}
