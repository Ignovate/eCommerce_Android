package kite.amibee.com.netstore.model.pojo.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embeded {
    @SerializedName("categoriesDetailsVms")
    public List<Cate> categoriesDetailsVms;

    public class Cate {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrlKey() {
            return urlKey;
        }

        public void setUrlKey(String urlKey) {
            this.urlKey = urlKey;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        @SerializedName("id")
        int id;
        @SerializedName("categoryId")
        int categoryId;
        @SerializedName("name")
        String name;
        @SerializedName("urlKey")
        String urlKey;
        @SerializedName("image")
        String image;
        @SerializedName("thumbnail")
        String thumbnail;

    }
}
