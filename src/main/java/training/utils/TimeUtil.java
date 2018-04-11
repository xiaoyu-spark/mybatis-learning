package training.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /*
     * @param startAt  开始时间，如：[10:00
     * @param endAt  结束时间，如20:00) 半闭合，不包括20:00
     * 支持开始时间大于结束时间，比如[19:00-5:00）
     * @param curTime 需要判断的时间 如10:00
    */
    public static int getCurYear(){
        return new Date().getYear();
    }

    public static boolean isInTime(String startAt,String endAt, String curTime) {
        if (startAt == null ||!startAt.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + startAt);
        }
        if(endAt == null ||!endAt.contains(":")){
            throw new IllegalArgumentException("Illegal Argument arg:" + endAt);
        }
        if (curTime == null || !curTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            long now = sdf.parse(curTime).getTime();
            long start = sdf.parse(startAt).getTime();
            if (endAt.equals("00:00")) {
                endAt = "24:00";
            }
            long end = sdf.parse(endAt).getTime();
            if (end < start) {
                if (now >= end && now < start) {
                    return false;
                } else {
                    return true;
                }
            }else {
                if (now >= start && now < end) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument arg: parse Exception.");
        }

    }
    /*
     * @param startAt  开始时间，如：[10:00
     * @param endAt  结束时间，如20:00) 半闭合，不包括20:00
     * 支持开始时间大于结束时间，比如[19:00-5:00）
    */
    public static boolean isActiveTime(String startAt,String endAt) {
        Date test = new Date();
        int hours = test.getHours();
        int min = test.getMinutes();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(hours).append(":").append(min);
        return isInTime(startAt,endAt,stringBuilder.toString());
    }
}
