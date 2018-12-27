package kite.amibee.com.netstore.model.pojo.addtocart;

import com.google.gson.annotations.SerializedName;

public class AddToCartList {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    @SerializedName("id")
    String id;
    @SerializedName("quoteId")
    String quoteId;
    @SerializedName("productId")
    String productId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @SerializedName("imageUrl")
    String imageUrl;

}
