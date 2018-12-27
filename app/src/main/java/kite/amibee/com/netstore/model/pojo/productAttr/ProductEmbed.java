package kite.amibee.com.netstore.model.pojo.productAttr;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductEmbed {
    @SerializedName("productAttrEntities")
    public List<ProductFullViewModel> productAttrEntities;

    public class AttributesList{
        @SerializedName("productId")
        String productId;
        @SerializedName("name")
        String name;
        @SerializedName("gender")
        String gender;
        @SerializedName("brand")
        String brand;
        @SerializedName("measurement")
        String measurement;
        @SerializedName("usage")
        String usage;

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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getMeasurement() {
            return measurement;
        }

        public void setMeasurement(String measurement) {
            this.measurement = measurement;
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

}
