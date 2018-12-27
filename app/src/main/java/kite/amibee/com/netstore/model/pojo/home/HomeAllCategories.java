package kite.amibee.com.netstore.model.pojo.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeAllCategories {
    @SerializedName("bannerId")
    public String bannerId;
    @SerializedName("layoutName")
    public String layoutName;
    @SerializedName("path")
    public String path;
    @SerializedName("bannerType")
    public String bannerType;

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public List<HomeBannerImages> getBannerImages() {
        return bannerImages;
    }

    public void setBannerImages(List<HomeBannerImages> bannerImages) {
        this.bannerImages = bannerImages;
    }

    @SerializedName("bannerImages")
    public List<HomeBannerImages> bannerImages;

}
