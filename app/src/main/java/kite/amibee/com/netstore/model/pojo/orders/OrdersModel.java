package kite.amibee.com.netstore.model.pojo.orders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kite.amibee.com.netstore.model.pojo.AddressModel;
import kite.amibee.com.netstore.model.pojo.AddressModel1;

public class OrdersModel {
    @SerializedName("id")
    String id;
    @SerializedName("displayId")
    String displayId;
    @SerializedName("customerId")
    String customerId;
    @SerializedName("websiteId")
    String websiteId;
    @SerializedName("totalItems")
    String totalItems;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
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

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
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

    public List<String> getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(List<String> createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(List<String> updatedDate) {
        this.updatedDate = updatedDate;
    }

    @SerializedName("createdDate")
    List<String> createdDate;
    @SerializedName("updatedDate")
    List<String> updatedDate;

    public List<OrdersItemsModel> getSaleOrderItems() {
        return saleOrderItems;
    }

    public void setSaleOrderItems(List<OrdersItemsModel> saleOrderItems) {
        this.saleOrderItems = saleOrderItems;
    }

    @SerializedName("saleOrderItems")
    List<OrdersItemsModel> saleOrderItems;
    public AddressModel1 getSaleAddress() {
        return saleAddress;
    }

    public void setSaleAddress(AddressModel1 saleAddress) {
        this.saleAddress = saleAddress;
    }

    @SerializedName("saleAddress")
    AddressModel1 saleAddress;
}
