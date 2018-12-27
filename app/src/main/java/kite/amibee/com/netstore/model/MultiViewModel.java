package kite.amibee.com.netstore.model;

import android.graphics.drawable.Drawable;

import java.util.List;

public class MultiViewModel {

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }


    private ItemType type;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    private Object object;
    public enum ItemType {
        ITEM_DELIVERY, ITEM_PRICE,ITEM_PROMO,ITEM_LISTDETIALS;
    }

    public MultiViewModel(Object object, ItemType type) {
        this.object = object;
        this.type = type;
    }
}
