package training.utils;

import java.util.Date;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateUtils {
    public static final long MILLISECONDS_IN_MINUTE = 60 * 1000L;
    public static final long MILLISECONDS_IN_HOUR = 60 * MILLISECONDS_IN_MINUTE;
    public static final long MILLISECONDS_IN_HALF_DAY = 12 * MILLISECONDS_IN_HOUR;
    public static final long MILLISECONDS_IN_DAY = 24 * MILLISECONDS_IN_HOUR;

    public static int getMinYear() {
        return 2011;
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getMaxYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + 10;
    }

    public static Date getDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }

    public static Date trimDate(Date datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        calendar.clear();
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    public static Date getDate(Date date, int days) {
        return new Date(date.getTime() + days * MILLISECONDS_IN_DAY);
    }

    public static Date getDate(int days) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        calendar.clear();
        calendar.set(year, month, date + days);
        return calendar.getTime();
    }

    public static int getYear(Date time) {
        return getField(time, Calendar.YEAR);
    }

    public static int getMonth(Date time) {
        return getField(time, Calendar.MONTH) + 1;
    }

    public static int getDate(Date time) {
        return getField(time, Calendar.DATE);
    }

    private static int getField(Date time, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(field);
    }

    public static int getDaysBetween(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / MILLISECONDS_IN_DAY);
    }

    public static boolean isBetweenDates(Date date, Date minDate, Date maxDate) {
        if (minDate != null) {
            if (date.before(minDate)) {
                return false;
            }
        }
        if (maxDate != null) {
            if (date.after(maxDate)) {
                return false;
            }
        }
        return true;
    }

    public static Date parseDate(String date) {
        if (date == null) {
            return null;
        }
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date parseDateTime(String date) {
        if (date == null) {
            return null;
        }
        try {
            int index = date.indexOf(" ");
            if (index > 0) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return format.parse(date);
            } else {
                return genericDateTime(date);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Date genericDateTime(String date) {
        try {
            StringTokenizer st = new StringTokenizer(date, "-_: ");
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            int[] fields = new int[] {
                    Calendar.YEAR,
                    Calendar.MONTH,
                    Calendar.DATE,
                    Calendar.HOUR_OF_DAY,
                    Calendar.MINUTE,
                    Calendar.SECOND,
            };
            for (int field : fields) {
                if (!st.hasMoreTokens()) {
                    break;
                }
                int value = Integer.parseInt(st.nextToken());
                calendar.set(field, field == Calendar.MONTH ? value - 1 : value);
            }
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatTime(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date getStartDate(int year, int month, int date) {
        if (year <= 0) {
            return getDate(1970, 1, 1);
        }
        if (month <= 0) {
            return getDate(year, 1, 1);
        }
        month = Math.min(month, 12);
        if (date <= 0) {
            return getDate(year, month, 1);
        }
        date = Math.min(date, 31);
        return getDate(year, month, date);
    }

    public static Date getEndDate(int year, int month, int date) {
        if (year <= 0) {
            return getDate(getMaxYear() + 1, 1, 1);
        }
        if (month <= 0) {
            return getDate(year + 1, 1, 1);
        }
        month = Math.min(month, 12);
        if (date <= 0) {
            return getDate(year, month + 1, 1);
        }
        date = Math.min(date, 31);
        return getDate(year, month, date + 1);
    }

    public static String getWeekday(int weekday) {
        switch (weekday) {
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            case Calendar.SUNDAY:
                return "Sun";
            default:
                throw new IllegalArgumentException("No such weekday: " + weekday);
        }
    }
}
