package kite.amibee.com.netstore.model.pojo.category;

import com.google.gson.annotations.SerializedName;

public class Page {
    @SerializedName("size")
    int size;
    @SerializedName("totalElements")
    int totalElements;
    @SerializedName("totalPages")
    int totalPages;
    @SerializedName("number")
    int number;
}
