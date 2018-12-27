package kite.amibee.com.netstore.model.pojo.orders;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kite.amibee.com.netstore.model.pojo.AddressModel;

public class OrdersItemsModel {
    @SerializedName("id")
    String id;
    @SerializedName("orderId")
    String orderId;
    @SerializedName("productName")
    String productName;
    @SerializedName("price")
    String price;
    @SerializedName("cost")
    String cost;
    @SerializedName("quantity")
    String quantity;
    @SerializedName("sku")
    String sku;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    @SerializedName("productType")
    String productType;

    @SerializedName("createdDate")
    List<String> createdDate;
    @SerializedName("updatedDate")
    List<String> updatedDate;



}
