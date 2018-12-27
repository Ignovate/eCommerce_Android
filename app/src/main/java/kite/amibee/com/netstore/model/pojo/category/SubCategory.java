package kite.amibee.com.netstore.model.pojo.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategory implements Parcelable {
    public SubCategory(){

    }

    protected SubCategory(Parcel in) {
        categoryId = in.readString();
        categoryName = in.readString();
        categoryUrl = in.readString();
        subCategories = in.createTypedArrayList(SubCategory.CREATOR);
    }

    public static final Creator<SubCategory> CREATOR = new Creator<SubCategory>() {
        @Override
        public SubCategory createFromParcel(Parcel in) {
            return new SubCategory(in);
        }

        @Override
        public SubCategory[] newArray(int size) {
            return new SubCategory[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(categoryId);
        parcel.writeString(categoryName);
        parcel.writeString(categoryUrl);
        parcel.writeTypedList(subCategories);
    }
}
