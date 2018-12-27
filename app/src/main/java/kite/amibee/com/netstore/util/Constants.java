package kite.amibee.com.netstore.util;

import android.util.Log;

public class Constants {
    private static final String TAG = "Constants";
    public static String BASE_URL="http://167.99.153.79:8081/";
    public static String BASE_URL_ATTR="http://167.99.153.79:8081/gaia-ecom-service/";
    public static String COTEGORY_URL="api/v1.0/categoriesdetails";
    public static String ATTR="attr";
    public static String IMGURL="http://167.99.153.79:8081/gaia-ecom-service/resources/import/banner/portal/";
    public static String BASEURL="http://167.99.153.79:8081";
    public static String CUSTOMERID="24";
    public static int ADDRESSID=1;
    public static final String PREF_FILE_NAME = "NetStore";
    public static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    public static final String PREF_KEY_CARTCOUNT = "PREF_KEY_CARTCOUNT";
    public static final String PREF_KEY_CUSID = "PREF_KEY_CUSID";
    public static final String PREF_KEY_QUOTEID = "PREF_KEY_QUOTEID";
    public static final String PREF_KEY_EMAIL = "PREF_KEY_EMAIL";
    public static final String PREF_KEY_USERNAME = "PREF_KEY_USERNAME";
    public static final String PREF_KEY_MOBILE = "PREF_KEY_MOBILE";
    public static final String PREF_KEY_ADDRESSID = "PREF_KEY_ADDRESSID";
    public static final String PREF_KEY_ADDRESSFIRSTTIME = "PREF_KEY_ADDRESSFIRSTTIME";
    public static final String PREF_KEY_ADDRESSNAME = "PREF_KEY_ADDRESSNAME";
    public static final String PREF_KEY_WEBSITEID = "PREF_KEY_WEBSITEID";



    public static String imageUrl(String path){
           String url=new String(path);
            String array1[]= url.split("/");
                for (int i=0;i<array1.length;i++){
                    if (i==array1.length  -1){
                        url=IMGURL + array1[i];
                        Log.e(TAG, "imageUrl: url "+url );
                    }

                }
           return url;
    }
}
