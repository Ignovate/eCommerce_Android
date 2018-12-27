package kite.amibee.com.netstore.model;

import android.graphics.drawable.Drawable;

public class MyOrderProductModel {
    String desc;
    String price;
    String price_dt;

    public String getG_Total() {
        return g_Total;
    }

    public void setG_Total(String g_Total) {
        this.g_Total = g_Total;
    }

    String g_Total;
    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    private Drawable image;
    public MyOrderProductModel(Drawable image, String desc, String price, String price_dt, String g_Total){
        this.desc=desc;
        this.price=price;
        this.price_dt=price_dt;
        this.image=image;
        this.g_Total=g_Total;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_dt() {
        return price_dt;
    }

    public void setPrice_dt(String price_dt) {
        this.price_dt = price_dt;
    }
}
