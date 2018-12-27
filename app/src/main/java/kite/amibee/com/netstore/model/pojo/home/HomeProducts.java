package kite.amibee.com.netstore.model.pojo.home;

import com.google.gson.annotations.SerializedName;

import kite.amibee.com.netstore.model.pojo.home.newcat.Gaiaresponse;

public class HomeProducts {
    @SerializedName("responseType")
    public String responseType;
//    @SerializedName("gaiaresponse")
//    public List<HomeAllResponse> gaiaresponse;//old
    @SerializedName("gaiaresponse")
    public Gaiaresponse gaiaresponse;

}

