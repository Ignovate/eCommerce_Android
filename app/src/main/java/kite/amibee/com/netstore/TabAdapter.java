package kite.amibee.com.netstore;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    private static final String TAG = "TabAdapter";
    final int PAGE_COUNT = 3;



    private Context context;
    public TabAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new Tab1Fragment();
            case 1:
                return new TabFragmentBrand();
            case 2:
                return new TabFragmentSort();
            case 3:
                return new TabFragmentPrice();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }



}