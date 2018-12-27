package kite.amibee.com.netstore.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.activity.home.HomeActivity;
import kite.amibee.com.netstore.model.pojo.home.HomeAllResponse;
import kite.amibee.com.netstore.model.pojo.home.HomeBannerImages;
import kite.amibee.com.netstore.model.pojo.home.newcat.NewCatResponse;
import kite.amibee.com.netstore.util.Constants;

public class HomeSliderAdapter extends PagerAdapter {
    private static final String TAG = "HomeSliderAdapter";
    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;
    List<HomeBannerImages> cateSingleList;
    public HomeSliderAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    public HomeSliderAdapter(Context context, List<HomeBannerImages> cateSingleList) {
        this.context = context;
        this.cateSingleList=cateSingleList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return cateSingleList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        final ImageView iv_home_slide = (ImageView) myImageLayout
                .findViewById(R.id.iv_home_slide);
//        String url= Constants.imageUrl(cateSingleList.get(position).getImageUrl());
//        String url= Constants.BASEURL + cateSingleList.get(position).getImageUrl();
        String url= cateSingleList.get(position).getImageUrl();
        Log.e(TAG, "instantiateItem: slider url "+url );
//        Log.e(TAG, "instantiateItem: image url "+cateSingleList.get(position).getImageurl() );
        /*Glide.with(context)
                .load(url)
                .into(iv_home_slide);*/
        Glide.with(context).
                load(url).
                transition(DrawableTransitionOptions.withCrossFade()).
                listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        iv_home_slide.setImageResource(R.drawable.logo);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                }).
                into(iv_home_slide);

        view.addView(myImageLayout, 0);
        iv_home_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=position;
                if (id==0 || id==1){
                    id=2;
                }
                ((HomeActivity)context).sendCatId(id,"");
            }
        });
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}