package kite.amibee.com.netstore.model.pojo.productAttr;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductFullViewModel {
    @SerializedName("id")
    String id;
    @SerializedName("sku")
    String sku;

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    @SerializedName("websiteId")
    String websiteId;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ProductFullAttrModel getAttrEntity() {
        return attrEntity;
    }

    public void setAttrEntity(ProductFullAttrModel attrEntity) {
        this.attrEntity = attrEntity;
    }

    @SerializedName("attribute")
    ProductFullAttrModel attrEntity;

    public ProductFullStockModel getInventoryEntity() {
        return inventoryEntity;
    }

    public void setInventoryEntity(ProductFullStockModel inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
    }

    public ProductFullPriceModel getPriceEntity() {
        return priceEntity;
    }

    public void setPriceEntity(ProductFullPriceModel priceEntity) {
        this.priceEntity = priceEntity;
    }

    public ProductFullImageModel getImage() {
        return image;
    }

    public void setImage(ProductFullImageModel image) {
        this.image = image;
    }

    @SerializedName("image")
    ProductFullImageModel image;
    @SerializedName("inventory")
    ProductFullStockModel inventoryEntity;
    @SerializedName("price")
    ProductFullPriceModel priceEntity;

}
