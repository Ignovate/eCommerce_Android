package kite.amibee.com.netstore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

public class PreferencesHelper {

    private final SharedPreferences mPref;



    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void putUserRegister(boolean accessToken) {
        mPref.edit().putBoolean(Constants.PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Nullable
    public boolean getUserRegister() {
        return mPref.getBoolean(Constants.PREF_KEY_ACCESS_TOKEN,false);
    }

    public void setCartCount(int cartCount) {
        mPref.edit().putInt(Constants.PREF_KEY_CARTCOUNT, cartCount).apply();
    }

    @Nullable
    public int getCartCount() {
        return mPref.getInt(Constants.PREF_KEY_CARTCOUNT,0);
    }

    public void setCusId(int cartCount) {
        mPref.edit().putInt(Constants.PREF_KEY_CUSID, cartCount).apply();
    }

    @Nullable
    public int getCusId() {
        return mPref.getInt(Constants.PREF_KEY_CUSID,0);
    }

    public void setQuoteId(int cartCount) {
        mPref.edit().putInt(Constants.PREF_KEY_QUOTEID, cartCount).apply();
    }

    @Nullable
    public int getQuoteId() {
        return mPref.getInt(Constants.PREF_KEY_QUOTEID,0);
    }


    public void setEmail(String email) {
        mPref.edit().putString(Constants.PREF_KEY_EMAIL, email).apply();
    }

    @Nullable
    public String getEmail() {
        return mPref.getString(Constants.PREF_KEY_EMAIL,null);
    }

    public void setUserName(String userName) {
        mPref.edit().putString(Constants.PREF_KEY_USERNAME, userName).apply();
    }

    @Nullable
    public String getUserName() {
        return mPref.getString(Constants.PREF_KEY_USERNAME,null);
    }

    public void setMobile(int cartCount) {
        mPref.edit().putInt(Constants.PREF_KEY_MOBILE, cartCount).apply();
    }

    @Nullable
    public int getMobile() {
        return mPref.getInt(Constants.PREF_KEY_MOBILE,0);
    }

    public void setAddressId(int cartCount) {
        mPref.edit().putInt(Constants.PREF_KEY_ADDRESSID, cartCount).apply();
    }

    @Nullable
    public int getAddressId() {
        return mPref.getInt(Constants.PREF_KEY_ADDRESSID,0);
    }



    public void setWebsiteId(int websiteId) {
        mPref.edit().putInt(Constants.PREF_KEY_WEBSITEID, websiteId).apply();
    }

    @Nullable
    public int getWebsiteId() {
        return mPref.getInt(Constants.PREF_KEY_WEBSITEID,0 );
    }



}

