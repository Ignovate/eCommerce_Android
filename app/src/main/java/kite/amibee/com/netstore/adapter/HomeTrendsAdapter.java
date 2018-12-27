package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.activity.home.HomeActivity;
import kite.amibee.com.netstore.model.pojo.home.HomeAllResponse;
import kite.amibee.com.netstore.model.pojo.home.HomeBannerImages;
import kite.amibee.com.netstore.model.pojo.home.newcat.NewCatResponse;
import kite.amibee.com.netstore.util.Constants;

public class HomeTrendsAdapter extends RecyclerView.Adapter<HomeTrendsAdapter.ViewHolder> {
    private static final String TAG = "HomeTrendsAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<HomeBannerImages> trendsList ;
    public HomeTrendsAdapter(Context context,List<?> trendsList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.trendsList= (List<HomeBannerImages>) trendsList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = mInflater.inflate(R.layout.home_adap_trends, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.lay_adap_trends.setId(position);
//        String url= Constants.imageUrl(trendsList.get(position).getImageUrl());
//        String url= Constants.BASEURL + trendsList.get(position).getImageUrl();
        String url= trendsList.get(position).getImageUrl();
        Log.e(TAG, "onBindViewHolder: url "+url );
        Glide.with(context).asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        holder.iv_adap_trends.setImageBitmap(resource);

                        Palette.generateAsync((resource), new Palette.PaletteAsyncListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onGenerated(Palette palette) {
                                if (Build.VERSION.SDK_INT > 19) {
                                    holder.lay_adap_trends.setBackgroundTintList(ColorStateList.valueOf(palette.getVibrantColor(0)));
                                }else{
                                    holder.lay_adap_trends.setBackgroundColor(palette.getVibrantColor(0));
                                }

//                holder.cv_adap_trends.setCardBackgroundColor(palette.getVibrantColor(0));
                            }
                        });
                    }

                });
//.onLoadFailed(context.getResources().getDrawable(R.drawable.logo)

        if (position==0){
            holder.lay_adap_trends.setBackgroundResource(R.drawable.lay_leftbot_corner);
        }else if (trendsList.size() -1 ==position){
            holder.lay_adap_trends.setBackgroundResource(R.drawable.lay_rightbot_corner);
        }else {
            holder.lay_adap_trends.setBackgroundResource(R.drawable.lay_normal_corner);
        }


        holder.lay_adap_trends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=view.getId();
                if (id==0 || id==1){
                    id=2;
                }
                ((HomeActivity)context).sendCatId(id,"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.trendsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_adap_trends;
        //        CardView cv_adap_trends;
        RelativeLayout lay_adap_trends;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

            iv_adap_trends=(ImageView)itemView.findViewById(R.id.iv_adap_trends);
//            cv_adap_trends=(CardView)itemView.findViewById(R.id.cv_adap_trends);
            lay_adap_trends=(RelativeLayout)itemView.findViewById(R.id.lay_adap_trends);
        }
    }
public void updateAdapter(List<HomeBannerImages> trendsList){
        this.trendsList=trendsList;
        notifyDataSetChanged();
}

}
