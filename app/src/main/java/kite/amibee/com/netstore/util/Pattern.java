package kite.amibee.com.netstore.util;

public class Pattern  {

    /**
     * Do not create this static utility class.
     */
    public static final String PASSWORD_VALIDE ="(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%&*()_+=|<>?{}\\[\\]~-]).{6,15}";
    public static final String MOBILE_VALIDE ="^[+]?[0-9]{10,13}$";
}
