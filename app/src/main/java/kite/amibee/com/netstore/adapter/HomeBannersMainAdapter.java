package kite.amibee.com.netstore.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.pojo.home.HomeAllCategories;
import kite.amibee.com.netstore.model.pojo.home.HomeBannerImages;

public class HomeBannersMainAdapter  extends RecyclerView.Adapter<HomeBannersMainAdapter.ItemRowHolder> {
    private static final String TAG = "HomeBannersMainAdapter";
    private List<HomeAllCategories> dataList;
    private Context mContext;
    private View.OnClickListener onClickListener;
    ItemRowHolder itemHolder;
    String bannersTitle;
    HomeExclusiveAdapter HomeExclusiveAdapter;
    HomeOffersAdapter homeOffersAdapter;
    HomeTrendsAdapter homeTrendsAdapter;
    String title;
    public HomeBannersMainAdapter(Context context, List<HomeAllCategories> dataList) {
        this.dataList = dataList;
        this.mContext = context;
        onClickListener();
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.banners_horizontalview, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder itemRowHolder, int i) {
        if (i>=2){

        }
        title = dataList.get(i).getLayoutName();
        itemRowHolder.tv_banners_title.setText(title);

        itemRowHolder.rv_banners_subview.setHasFixedSize(true);
        itemRowHolder.rv_banners_subview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));


        itemRowHolder.rv_banners_subview.setNestedScrollingEnabled(false);
        int bannerId= Integer.parseInt(dataList.get(i).bannerId);
        Log.d(TAG, "onBindViewHolder: bannerId "+bannerId);
        if (bannerId==2){
            List<HomeBannerImages> exclusiveList = dataList.get(i).getBannerImages();
            HomeExclusiveAdapter = new HomeExclusiveAdapter(mContext, exclusiveList);
            itemRowHolder.rv_banners_subview.setAdapter(HomeExclusiveAdapter);
        }else if (bannerId==3){
            List<HomeBannerImages> exclusiveList = dataList.get(i).getBannerImages();
            homeOffersAdapter = new HomeOffersAdapter(mContext, exclusiveList);
            itemRowHolder.rv_banners_subview.setAdapter(homeOffersAdapter);
        }else if (bannerId==4){
            List<HomeBannerImages> exclusiveList = dataList.get(i).getBannerImages();
            homeTrendsAdapter = new HomeTrendsAdapter(mContext, exclusiveList);
            itemRowHolder.rv_banners_subview.setAdapter(homeTrendsAdapter);
        }


    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        TextView tv_banners_title;
        RecyclerView rv_banners_subview;

        public ItemRowHolder(View view) {
            super(view);

            tv_banners_title = (TextView) view.findViewById(R.id.tv_banners_title);
            rv_banners_subview = (RecyclerView) view.findViewById(R.id.rv_banners_subview);

        }

    }

    private void onClickListener(){
        onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(view.getContext(), "click event on more, "+title , Toast.LENGTH_SHORT).show();

            }
        };
    }

    public void updateAdapter(List<HomeAllCategories> dataList){
        this.dataList=dataList;
        notifyDataSetChanged();
    }

}
