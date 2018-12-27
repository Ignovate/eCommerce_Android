package kite.amibee.com.netstore.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressListModel {
    public List<AddressModel> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressModel> addressList) {
        this.addressList = addressList;
    }

    @SerializedName("content")
    List<AddressModel> addressList;







}
