package kite.amibee.com.netstore.model.pojo.productAttr;

import com.google.gson.annotations.SerializedName;

public class ProductFullAttrModel {
    @SerializedName("id")
    String id;
    @SerializedName("productId")
    String productId;
    @SerializedName("name")
    String name;
    @SerializedName("usage")
    String usage;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("description")
    String description;


}
