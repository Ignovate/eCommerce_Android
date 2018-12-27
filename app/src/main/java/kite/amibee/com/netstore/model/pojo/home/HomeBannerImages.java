package kite.amibee.com.netstore.model.pojo.home;

import com.google.gson.annotations.SerializedName;

public class HomeBannerImages {
    @SerializedName("imageId")
    public String imageId;
    @SerializedName("bannerId")
    public String bannerId;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @SerializedName("imageUrl")
    public String imageUrl;
}
