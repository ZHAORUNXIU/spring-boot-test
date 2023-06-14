package com.x.test.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class Text {

    /**
     * Trim the string and convert it to null if it becomes empty after trimming.
     */
    public static final int TRIM_TO_NULL = 1;

    /**
     * Trim the string to an empty string.
     */
    public static final int TRIM_TO_BLANK = 2;

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    /**
     * The empty String
     */
    public static final String EMPTY = "";

    private Text() {
    }

    /**
     * Format a date (or time) object into a string with the specified pattern.
     *
     * @param date    The date (or time) object.
     * @param pattern The formatting pattern.
     * @return The formatted string.
     */
    public static String formatTime(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * Split a string using a single character delimiter.
     *
     * @param value The string to split.
     * @param delim The delimiter character.
     * @return An array of strings.
     */
    public static String[] splitArray(String value, char delim) {
        List<String> res = split(value, delim);

        String[] ret = res.toArray(new String[res.size()]);
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = trimEmpty(ret[i]);
        }
        return ret;
    }

    /**
     * Split a string using a delimiter character.
     *
     * @param value The source string.
     * @param delim The delimiter character.
     * @return A list of strings.
     */
    public static List<String> split(String value, char delim) {
        final int end = value.length();
        final List<String> res = new ArrayList<>();

        int start = 0;
        for (int i = 0; i < end; i++) {
            if (value.charAt(i) == delim) {
                if (start == i) {
                    res.add("");
                } else {
                    res.add(value.substring(start, i));
                }
                start = i + 1;
            }
        }

        if (start == 0) {
            res.add(value);
        } else if (start != end) {
            res.add(value.substring(start, end));
        } else {
            for (int i = res.size() - 1; i >= 0; i--) {
                if (res.get(i).isEmpty()) {
                    res.remove(i);
                } else {
                    break;
                }
            }
        }

        return res;
    }

    /**
     * Get the MD5 hash of a string and convert it to a hexadecimal string.
     *
     * @param src  The source string.
     * @param salt The salt string.
     * @return The MD5 hash as a hexadecimal string.
     */
    public static String md5(String src, String salt) {
        return toHexString(digest("MD5", src, salt));
    }

    /**
     * Get the SHA-256 hash of a string and convert it to a hexadecimal string.
     *
     * @param src  The source string.
     * @param salt The salt string.
     * @return The SHA-256 hash as a hexadecimal string.
     */
    public static String sha256(String src, String salt) {
        return toHexString(digest("SHA-256", src, salt));
    }

    /**
     * Merge two byte arrays.
     *
     * @param x The first byte array.
     * @param y The second byte array.
     * @return The merged byte array.
     */
    public static byte[] merge(byte[] x, byte[] y) {
        byte[] b = new byte[x.length + y.length];

        System.arraycopy(x, 0, b, 0, x.length);
        System.arraycopy(y, 0, b, x.length, y.length);

        return b;
    }

    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param b Byte array
     * @return Hexadecimal string
     */
    public static String toHexString(byte[] b) {
        StringBuilder buf = new StringBuilder(b.length * 2);
        for (byte n : b) {
            int v = n < 0 ? n + 256 : n;
            buf.append(HEX_DIGITS[v >> 4]).append(HEX_DIGITS[v % 16]);
        }
        return buf.toString();
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isBlank(CharSequence cs, int end) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }

        if (end > 0 && end < strLen) {
            strLen = end;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean startsWith(String s, char c) {
        return s.length() != 0 && s.charAt(0) == c;
    }

    public static boolean endsWith(String s, char c) {
        return s.length() != 0 && s.charAt(s.length() - 1) == c;
    }

    public static String trim(String cs) {
        return cs == null ? null : cs.trim();
    }

    public static String trimNull(String cs) {
        String s = trim(cs);
        return isEmpty(s) ? null : s;
    }

    public static String trimEmpty(String cs) {
        String s = trim(cs);
        return s == null ? "" : s;
    }

    public static String trimToFlag(String src, String flag) {
        int index = src.indexOf(flag);
        if (index == -1) {
            return src;
        }

        return src.substring(0, index);
    }

    /**
     * Returns true if the string is null or has a length equal to the specified length.
     *
     * @param s      字符串
     * @param length 长度
     */
    public static boolean validLength(String s, int length) {
        if (s == null) {
            return true;
        }
        return s.length() == length;
    }

    /**
     * Returns true if the string is null or has a length within the specified range (inclusive).
     *
     * @param s   String
     * @param min Minimum length
     * @param max Maximum length
     */
    public static boolean validLength(String s, int min, int max) {
        if (s == null) {
            return true;
        }
        return s.length() >= min && s.length() <= max;
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String randomAlphanumeric(Integer length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    private static byte[] digest(String algorithm, String src, String salt) {
        byte[] a = src.getBytes();

        try {
            if (salt != null && salt.length() > 0) {
                a = merge(a, salt.getBytes());
            }
            return MessageDigest.getInstance(algorithm).digest(a);
        } catch (Exception e) {
            return null;
        }
    }
}
