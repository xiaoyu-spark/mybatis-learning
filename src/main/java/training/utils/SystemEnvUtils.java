package training.utils;

public class SystemEnvUtils {
    public static String getSystemEnv(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null) {
            value = System.getProperty(name);
        }
        if(value==null){
            return defaultValue;
        }else {
            return value;
        }
    }
}
