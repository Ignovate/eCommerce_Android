package kite.amibee.com.netstore;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import java.util.HashMap;

public class MyApp extends MultiDexApplication {


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    Context context;

    public HashMap<Integer, Integer> getCartValue() {
        return cartValue;
    }

    public void setCartValue(HashMap<Integer, Integer> cartValue) {
        this.cartValue = cartValue;
    }

    HashMap<Integer,Integer> cartValue;
}

