package kite.amibee.com.netstore.model.pojo.signin;

import com.google.gson.annotations.SerializedName;

public class SignInModel {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignInDetailsModel getCustomer() {
        return customer;
    }

    public void setCustomer(SignInDetailsModel customer) {
        this.customer = customer;
    }

    @SerializedName("customer")
    SignInDetailsModel customer;
}
