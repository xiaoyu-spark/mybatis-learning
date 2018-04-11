package training.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleUtils {
    public static Locale parseLocale(String localeStr) {
        String language;
        String country;
        int index;
        if (localeStr != null && localeStr.length() > 0 && (index = localeStr.indexOf("_")) > -1) {
            language = localeStr.substring(0, index);
            country = localeStr.substring(index+1);
        } else {
            language = localeStr;
            country = "";
        }
        if ((language == null) || (language.length() == 0)) {
            throw new IllegalArgumentException("Missing language component for locale");
        }
        return new Locale(language, country);
    }

    public static String getString(String messageKey) {
        return getString(messageKey, "???" + messageKey + "???");
    }

    public static String getString(String messageKey, String defaultText) {
        return getString("default", messageKey, defaultText);
    }

    public static String getString(String bundleName, String messageKey, String defaultText) {
        return getString(bundleName, Locale.getDefault(), messageKey, defaultText);
    }

    public static String getString(String bundleName, Locale locale, String messageKey, String defaultText) {
        ResourceBundle bundle = getResourceBundle(bundleName, locale);
        return getString(bundle, messageKey, defaultText);
    }

    public static String getString(ResourceBundle bundle, String name, String defaultText) {
        String text = null;
        if (bundle != null) {
            try {
                text = bundle.getString(name);
            } catch (Exception e) {
            }
        }
        if (text == null) {
            text = defaultText;
        }
        return text;
    }

    public static ResourceBundle getResourceBundle(String baseName) {
        return getResourceBundle(baseName, Locale.getDefault());
    }

    public static ResourceBundle getResourceBundle(String baseName, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale);
    }
}
