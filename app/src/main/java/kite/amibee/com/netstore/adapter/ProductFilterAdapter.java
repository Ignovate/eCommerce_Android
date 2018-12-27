package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import kite.amibee.com.netstore.activity.product.ProductListActivity;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.pojo.productAttr.Attributes;
import kite.amibee.com.netstore.util.Constants;

public class ProductFilterAdapter extends RecyclerView.Adapter<ProductFilterAdapter.ViewHolder> {
    private static final String TAG = "ProductFilterAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    Integer[] offersImgList;
    List<Attributes> productList;
    public ProductFilterAdapter(Context context, List<Attributes> offersNameList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.productList=offersNameList;
       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.lotion_adap_list, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.card_adap_offers.setId(position);
//        Log.e(TAG, "onBindViewHolder: productId "+productList.get(position).getProductId() );
//        Log.e(TAG, "onBindViewHolder: price "+productList.get(position).getPrice() );
//        Log.e(TAG, "onBindViewHolder: price_dis "+productList.get(position).getSpecialPrice() );
//        holder.sub_cate_iv.setImageResource(offersImgList[position]);
        holder.sub_cate_tv_title.setText(productList.get(position).getImageLabel());
        holder.sub_cate_tv_price.setText(context.getResources().getString(R.string.priceAED)+productList.get(position).getSpecialPrice());
        holder.sub_cate_tv_price_dt.setText(context.getResources().getString(R.string.priceAED)+productList.get(position).getPrice());
        holder.sub_cate_tv_price_dt.setPaintFlags(holder.sub_cate_tv_price_dt.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
//        String url= Constants.imageUrl(productList.get(position).getImageUrl());
//        String url= Constants.BASE_URL + cateSingleList.get(position).getImageUrl();
        String url=productList.get(position).getImageUrl();
//        Log.e(TAG, "instantiateItem: image url "+url );
        /*Glide.with(context)
                .load(url)
                .into(holder.sub_cate_iv);*/

        Glide.with(context).
                load(url).
                transition(DrawableTransitionOptions.withCrossFade()).
                listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.sub_cate_iv.setImageResource(R.drawable.logo);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                }).
                into(holder.sub_cate_iv);
                 holder.card_adap_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ProductListActivity)context).fullView(productList.get(view.getId()).getProductId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView sub_cate_iv,sub_cate_fav;
        TextView sub_cate_tv_title,sub_cate_tv_unit,sub_cate_tv_price,sub_cate_tv_price_dt;
        RelativeLayout card_adap_offers;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

            sub_cate_iv=(ImageView) itemView.findViewById(R.id.sub_cate_iv);
            sub_cate_fav=(ImageView) itemView.findViewById(R.id.sub_cate_fav);
            sub_cate_tv_unit=(TextView)itemView.findViewById(R.id.sub_cate_tv_unit);
            sub_cate_tv_title=(TextView)itemView.findViewById(R.id.sub_cate_tv_title);
            sub_cate_tv_price=(TextView)itemView.findViewById(R.id.sub_cate_tv_price);
            sub_cate_tv_price_dt=(TextView)itemView.findViewById(R.id.sub_cate_tv_price_dt);
            card_adap_offers=(RelativeLayout)itemView.findViewById(R.id.card_adap_offers);
        }
    }

    public void updateAdapter(List<Attributes> productList){
            this.productList=productList;
            notifyDataSetChanged();
    }
}
