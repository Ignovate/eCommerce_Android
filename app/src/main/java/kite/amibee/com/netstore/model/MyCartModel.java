package kite.amibee.com.netstore.model;

import android.graphics.drawable.Drawable;

public class MyCartModel {
    String desc;
    String price;
    String price_dt;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    private Drawable image;
    public MyCartModel(Drawable image, String desc, String price, String price_dt){
        this.desc=desc;
        this.price=price;
        this.price_dt=price_dt;
        this.image=image;
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
