package kite.amibee.com.netstore.model.pojo.home;

import com.google.gson.annotations.SerializedName;

public class HomeAllResponse {
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

    public String getCategoryurl() {
        return categoryurl;
    }

    public void setCategoryurl(String categoryurl) {
        this.categoryurl = categoryurl;
    }

    public String getCategorylevel() {
        return categorylevel;
    }

    public void setCategorylevel(String categorylevel) {
        this.categorylevel = categorylevel;
    }

    public String getProductcount() {
        return productcount;
    }

    public void setProductcount(String productcount) {
        this.productcount = productcount;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }

    @SerializedName("categoryid")
    String categoryid;
    @SerializedName("categoryname")
    String categoryname;
    @SerializedName("categoryurl")
    String categoryurl;
    @SerializedName("categorylevel")
    String categorylevel;
    @SerializedName("productcount")
    String productcount;
    @SerializedName("parentid")
    String parentid;
    @SerializedName("imageurl")
    String imageurl;
    @SerializedName("thumbnailurl")
    String thumbnailurl;
}
