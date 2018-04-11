package training.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {

    public static boolean isDigits(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^\\d+$|^\\d+\\.\\d+$|-\\d+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    public static long converterYuanToFen(double yuan) {
        BigDecimal b1 = new BigDecimal(Double.toString(yuan));
        BigDecimal b2 = new BigDecimal(Double.toString(100));
        return b1.multiply(b2).longValue();
    }

    public static String converterFenToYuan(long fen) {
        BigDecimal b1 = new BigDecimal(Double.toString(fen));
        BigDecimal b2 = new BigDecimal(Double.toString(100));
        BigDecimal r = b1.divide(b2, 2, BigDecimal.ROUND_HALF_DOWN);
        DecimalFormat df = new DecimalFormat("#");
        return df.format(r);
    }
}
