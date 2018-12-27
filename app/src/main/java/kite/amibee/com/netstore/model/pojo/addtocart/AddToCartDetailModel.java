package kite.amibee.com.netstore.model.pojo.addtocart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.model.pojo.AddressModel;
import kite.amibee.com.netstore.model.pojo.AddressModel1;

public class AddToCartDetailModel {
    @SerializedName("id")
    int id;
    @SerializedName("customerId")
    String customerId;
    @SerializedName("websiteId")
    String websiteId;
    @SerializedName("totalItems")
    int totalItems;
    @SerializedName("totalItemsQty")
    String totalItemsQty;
    @SerializedName("subTotal")
    String subTotal;
    @SerializedName("grandTotal")
    String grandTotal;
    @SerializedName("shippingAmount")
    String shippingAmount;
    @SerializedName("taxAmount")
    String taxAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalItemsQty() {
        return totalItemsQty;
    }

    public void setTotalItemsQty(String totalItemsQty) {
        this.totalItemsQty = totalItemsQty;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(String shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public ArrayList<AddToCartList> getQuoteOrderItems() {
        return quoteOrderItems;
    }

    public void setQuoteOrderItems(ArrayList<AddToCartList> quoteOrderItems) {
        this.quoteOrderItems = quoteOrderItems;
    }

    @SerializedName("quoteOrderItems")
    ArrayList<AddToCartList> quoteOrderItems;

    public AddressModel1 getQuoteAddress() {
        return quoteAddress;
    }

    public void setQuoteAddress(AddressModel1 quoteAddress) {
        this.quoteAddress = quoteAddress;
    }

    @SerializedName("quoteAddress")
    AddressModel1 quoteAddress;
}
