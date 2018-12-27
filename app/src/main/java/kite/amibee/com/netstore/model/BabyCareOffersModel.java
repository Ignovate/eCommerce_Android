package kite.amibee.com.netstore.model;

public class BabyCareOffersModel {
    String desc;
    String price;
    String price_dt;
    public BabyCareOffersModel(String desc,String price,String price_dt){
        this.desc=desc;
        this.price=price;
        this.price_dt=price_dt;
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
