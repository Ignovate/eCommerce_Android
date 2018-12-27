package kite.amibee.com.netstore.model.pojo.productAttr;

import com.google.gson.annotations.SerializedName;

public class ProductFullPriceModel {
    @SerializedName("id")
    String id;
    @SerializedName("prodId")
    String prodId;
    @SerializedName("price")
    String price;
    @SerializedName("cost")
    String cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
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

    public String getSplPrice() {
        return splPrice;
    }

    public void setSplPrice(String splPrice) {
        this.splPrice = splPrice;
    }

    @SerializedName("specialPrice")
    String splPrice;




}
