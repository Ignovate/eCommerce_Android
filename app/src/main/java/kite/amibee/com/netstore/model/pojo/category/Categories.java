package kite.amibee.com.netstore.model.pojo.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {
    public Categories(){

    }
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @SerializedName("categoryId")
    String categoryId;
    @SerializedName("categoryName")
    String categoryName;
    @SerializedName("categoryUrl")
    String categoryUrl;
    @SerializedName("subCategories")
    List<SubCategory> subCategories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    String name;


}
