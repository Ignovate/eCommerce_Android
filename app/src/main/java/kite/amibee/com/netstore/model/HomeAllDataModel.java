package kite.amibee.com.netstore.model;

import java.util.ArrayList;

import kite.amibee.com.netstore.model.pojo.home.HomeBannerImages;

public class HomeAllDataModel {
    private String bannersTitle;

    public String getBannersTitle() {
        return bannersTitle;
    }

    public void setBannersTitle(String bannersTitle) {
        this.bannersTitle = bannersTitle;
    }

    public ArrayList<HomeBannerImages> getBannersList() {
        return bannersList;
    }

    public void setBannersList(ArrayList<HomeBannerImages> bannersList) {
        this.bannersList = bannersList;
    }

    private ArrayList<HomeBannerImages> bannersList;


    public HomeAllDataModel() {

    }
    public HomeAllDataModel(String headerTitle, ArrayList<HomeBannerImages> bannersList) {
        this.bannersTitle = bannersTitle;
        this.bannersList = bannersList;
    }


}
