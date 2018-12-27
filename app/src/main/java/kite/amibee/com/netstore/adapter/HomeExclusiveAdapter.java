package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.activity.home.HomeActivity;
import kite.amibee.com.netstore.model.pojo.home.HomeAllResponse;
import kite.amibee.com.netstore.model.pojo.home.HomeBannerImages;
import kite.amibee.com.netstore.model.pojo.home.newcat.NewCatResponse;
import kite.amibee.com.netstore.util.Constants;

public class HomeExclusiveAdapter extends RecyclerView.Adapter<HomeExclusiveAdapter.ViewHolder> {
    private static final String TAG = "EpgDetailChannelAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<HomeBannerImages> cateSingleList;
    public HomeExclusiveAdapter(Context context, List<?> cateSingleList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.cateSingleList= (List<HomeBannerImages>) cateSingleList;

       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.home_adap_exclusive, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.iv_adap_exclusive.setId(0);
//        String url= Constants.imageUrl(cateSingleList.get(position).getImageUrl());
//         String url= Constants.BASEURL + cateSingleList.get(position).getImageUrl();
        String url= cateSingleList.get(position).getImageUrl();
        /*Glide.with(context)
                .load(url)
                .into(holder.iv_adap_exclusive);*/
        Glide.with(context).
                load(url).
                transition(DrawableTransitionOptions.withCrossFade()).
                listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.iv_adap_exclusive.setImageResource(R.drawable.logo);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                }).
                into(holder.iv_adap_exclusive);

        holder.iv_adap_exclusive.setOnClickListener(new View.OnClickListener() {
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
        return this.cateSingleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_adap_exclusive;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

            iv_adap_exclusive=(ImageView)itemView.findViewById(R.id.iv_adap_exclusive);
        }
    }

    public void updateAdapter(List<HomeBannerImages> cateSingleList){
            this.cateSingleList=cateSingleList;
            notifyDataSetChanged();
    }
}
