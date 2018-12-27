package kite.amibee.com.netstore.model.pojo;

import com.google.gson.annotations.SerializedName;

import kite.amibee.com.netstore.model.pojo.signin.SignInDetailsModel;

public class PlaceOrderModel {
    @SerializedName("code")
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    String message;
}
