package kite.amibee.com.netstore.model;

import android.graphics.drawable.Drawable;

public class MyOrderIdModel {

    String order_date;
    String order_id;


    public MyOrderIdModel(String order_date,String order_id,String order_deli_date){
        this.order_date=order_date;
        this.order_id=order_id;
        this.order_deli_date=order_deli_date;
    }
    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_deli_date() {
        return order_deli_date;
    }

    public void setOrder_deli_date(String order_deli_date) {
        this.order_deli_date = order_deli_date;
    }

    String order_deli_date;

}
