package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.bumptech.glide.request.target.Target;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kite.amibee.com.netstore.activity.home.HomeActivity;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.pojo.home.HomeAllResponse;
import kite.amibee.com.netstore.model.pojo.home.HomeBannerImages;
import kite.amibee.com.netstore.model.pojo.home.newcat.NewCatResponse;
import kite.amibee.com.netstore.util.Constants;

public class HomeOffersAdapter extends RecyclerView.Adapter<HomeOffersAdapter.ViewHolder> {
    private static final String TAG = "HomeOffersAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<HomeBannerImages> cateSingleList;

    public HomeOffersAdapter(Context context, List<?> cateSingleList) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.cateSingleList = (List<HomeBannerImages>) cateSingleList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = mInflater.inflate(R.layout.home_adap_offers, parent, false);

        return new ViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.iv_offers.setId(position);
//        String url = Constants.imageUrl(cateSingleList.get(position).getImageUrl());
//        final String url= Constants.BASEURL + cateSingleList.get(position).getImageUrl();
        final String url= cateSingleList.get(position).getImageUrl();
        Log.e(TAG, "onBindViewHolder: url "+url);
        /*Glide.with(context)
                .load(url)
                .into(holder.cv_adap_offers);*/
        Glide.with(context).
                load(url).
                transition(DrawableTransitionOptions.withCrossFade()).
                listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.iv_offers.setImageResource(R.drawable.logo);
                        Log.e(TAG, "onLoadFailed: url "+url );
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                }).
                into(holder.iv_offers);

        holder.tv_adap_offers.setText(cateSingleList.get(position).getDescription());

        /*platte color */
        /*Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                offersImgList[position]);
        Palette.generateAsync((icon), new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                holder.card_adap_offers.setCardBackgroundColor(palette.getLightMutedColor(0));
            }
        });*/
        if (Build.VERSION.SDK_INT > 19) {
            if (position % 2 == 0) {

                holder.card_adap_offers.setCardBackgroundColor(context.getResources().getColor(R.color.home_card_offers_1));
//                holder.card_adap_offers.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.home_card_offers_1)));
            } else {
                holder.card_adap_offers.setCardBackgroundColor(context.getResources().getColor(R.color.home_card_offers_2));

//                int color = Color.parseColor("#00FFFF");
//                ((GradientDrawable)holder.card_adap_offers.getBackground()).setTintList(ColorStateList.valueOf(color));
            }
        } else {

//            holder.card_adap_offers.setImageResource(context.getResources().getColor(R.color.home_card_offers_1));
        }


        holder.iv_offers.setOnClickListener(new View.OnClickListener() {
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
        CircleImageView cv_adap_offers;
        TextView tv_adap_offers;
        CardView card_adap_offers;
        ImageView iv_offers;

        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);
            cv_adap_offers = (CircleImageView) itemView.findViewById(R.id.cv_adap_offers);
            tv_adap_offers = (TextView) itemView.findViewById(R.id.tv_adap_offers);
            card_adap_offers = (CardView) itemView.findViewById(R.id.card_adap_offers);
            iv_offers= itemView.findViewById(R.id.iv_offers);
        }
    }

    public void updateAdapter(List<HomeBannerImages> cateSingleList) {
        this.cateSingleList = cateSingleList;
        notifyDataSetChanged();
    }
}

