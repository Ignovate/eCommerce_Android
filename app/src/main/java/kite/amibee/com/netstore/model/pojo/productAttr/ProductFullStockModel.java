package kite.amibee.com.netstore.model.pojo.productAttr;

import com.google.gson.annotations.SerializedName;

public class ProductFullStockModel {
    @SerializedName("id")
    String id;
    @SerializedName("productId")
    String productId;
    @SerializedName("stock")
    String stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    @SerializedName("stockStatus")
    String stockStatus;



}
