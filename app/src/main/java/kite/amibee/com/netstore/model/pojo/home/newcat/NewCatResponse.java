package kite.amibee.com.netstore.model.pojo.home.newcat;

import com.google.gson.annotations.SerializedName;

public class NewCatResponse {
    @SerializedName("categoryid")
    String categoryid;
    @SerializedName("categoryname")
    String categoryname;
    @SerializedName("categorypageurl")
    String categorypageurl;
    @SerializedName("categoryimageurl")
    String categoryimageurl;
    @SerializedName("categorythumbnailurl")
    String categorythumbnailurl;

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategorypageurl() {
        return categorypageurl;
    }

    public void setCategorypageurl(String categorypageurl) {
        this.categorypageurl = categorypageurl;
    }

    public String getCategoryimageurl() {
        return categoryimageurl;
    }

    public void setCategoryimageurl(String categoryimageurl) {
        this.categoryimageurl = categoryimageurl;
    }

    public String getCategorythumbnailurl() {
        return categorythumbnailurl;
    }

    public void setCategorythumbnailurl(String categorythumbnailurl) {
        this.categorythumbnailurl = categorythumbnailurl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @SerializedName("position")
    String position;

}
