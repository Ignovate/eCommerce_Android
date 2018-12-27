package kite.amibee.com.netstore.model.pojo.category;

import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("self")
    public Self self;

    public class Self{
        @SerializedName("href")
        String href;
    }
}
