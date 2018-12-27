package kite.amibee.com.netstore.util.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import kite.amibee.com.netstore.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static kite.amibee.com.netstore.util.Constants.BASE_URL_ATTR;

public class ApiClient {
    private static final String TAG = "ApiClient";
    private static Retrofit retrofit = null;
    private static String URL = null;
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getClient(String apiTag) {
        Log.e(TAG, "getClient: apiTag "+apiTag );
        /*if (apiTag.equals(Constants.ATTR)){
            URL= Constants.BASE_URL_ATTR;
        }else {
            URL=Constants.BASE_URL;
        }*/

        URL=Constants.BASE_URL;
        if (retrofit==null) {
            Log.e(TAG, "getClient: URL "+URL );
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
