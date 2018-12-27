package kite.amibee.com.netstore.model.pojo.signin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignInDetailsModel {
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public List<String> getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(List<String> createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(List<String> updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    @SerializedName("customerId")
    String customerId;
    @SerializedName("firstName")
    String firstName;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("gender")
    String gender;
    @SerializedName("email")
    String email;
    @SerializedName("mobile")
    String mobile;
    @SerializedName("username")
    String username;
    @SerializedName("password")
    String password;
    @SerializedName("active")
    String active;
    @SerializedName("createdAt")
    List<String> createdAt;
    @SerializedName("updatedAt")
    List<String> updatedAt;
    @SerializedName("quoteId")
    String quoteId;




}
