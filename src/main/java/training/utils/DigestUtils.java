package training.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {
    public static String md5Hex(File file) throws IOException {
        return encodeHex(md5(file));
    }

    public static byte[] md5(File file) throws IOException {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return md5(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static byte[] md5(InputStream in) throws IOException {
        MessageDigest digest = getMessageDigest("MD5");
        byte[] buffer = new byte[8 * 1024];
        int read;
        while ((read = in.read(buffer)) > -1) {
            digest.update(buffer, 0, read);
        }
        return digest.digest();
    }

    public static byte[] md5(String text) {
        MessageDigest digest = getMessageDigest("MD5");
        return digest.digest(text.getBytes());
    }

    public static byte[] md5(byte[] bytes) {
        MessageDigest digest = getMessageDigest("MD5");
        return digest.digest(bytes);
    }

    public static String md5Hex(String text) {
        return encodeHex(md5(text));
    }

    public static String md5Hex(byte[] bytes) {
        return encodeHex(md5(bytes));
    }

    public static byte[] sha(byte[] bytes) {
        MessageDigest digest = getMessageDigest("SHA");
        return digest.digest(bytes);
    }

    public static String shaHex(byte[] bytes) {
        return encodeHex(sha(bytes));
    }

    public static byte[] sha(InputStream in) throws IOException {
        MessageDigest digest = getMessageDigest("SHA");
        byte[] buffer = new byte[8 * 1024];
        int read;
        while ((read = in.read(buffer)) > -1) {
            digest.update(buffer, 0, read);
        }
        return digest.digest();
    }

    public static String shaHex(InputStream in) throws IOException {
        return encodeHex(sha(in));
    }

    public static String encodeHex(byte[] bytes) {
        return bytesToHex(bytes);
    }

    public static String encodeHex(byte[] bytes, int offset, int length) {
        return bytesToHex(bytes, offset, length);
    }

    public static byte[] decodeHex(String text) {
        int len = text.length();
        if ((len & 0x01) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = Character.digit(text.charAt(j), 16) << 4;
            j++;
            f = f | Character.digit(text.charAt(j), 16);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    private static MessageDigest getMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such digest algorithm: " + algorithm);
        }
    }

    /**
     * Convenience method to convert a byte to a hex string.
     *
     * @param data the byte to convert
     * @return String the converted byte
     */
    public static String byteToHex(byte data) {
        StringBuffer buf = new StringBuffer();
        buf.append(toHexChar((data >>> 4) & 0x0F));
        buf.append(toHexChar(data & 0x0F));
        return buf.toString();
    }

    /**
     * Convenience method to convert a byte array to a hex string.
     *
     * @param data the byte[] to convert
     * @return String the converted byte[]
     */
    public static String bytesToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            buf.append(byteToHex(data[i]));
        }
        return buf.toString();
    }

    public static String bytesToHex(byte[] data, int off, int len) {
        len += off;
        StringBuffer buf = new StringBuffer();
        for (int i = off; i < len; i++) {
            buf.append(byteToHex(data[i]));
        }
        return buf.toString();
    }

    /**
     * Convenience method to convert an int to a hex char.
     *
     * @param i the int to convert
     * @return char the converted char
     */
    public static char toHexChar(int i) {
        if ((0 <= i) && (i <= 9))
            return (char) ('0' + i);
        else
            return (char) ('a' + (i - 10));
    }

}
