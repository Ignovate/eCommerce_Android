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

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.MyCartModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartList;

public class DeliveryCartAdapter extends RecyclerView.Adapter<DeliveryCartAdapter.ViewHolder> {
    private static final String TAG = "DeliveryCartAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<AddToCartList> offersNameList;
    public DeliveryCartAdapter(Context context, List<AddToCartList> offersNameList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.offersNameList=offersNameList;
       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.mycart_adap_list, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.sub_cate_iv.setImageDrawable(offersNameList.get(position).getImage());
        Log.e(TAG, "apiResponse: name " + offersNameList.get(position).getProductName().toString());
        Log.e(TAG, "apiResponse: price " + offersNameList.get(position).getPrice());
        Log.e(TAG, "apiResponse: price_dt " + offersNameList.get(position).getCost());
        holder.sub_cate_tv_title.setText(offersNameList.get(position).getProductName().toString());
        holder.sub_cate_tv_price.setText(context.getResources().getString(R.string.priceAED)+offersNameList.get(position).getPrice());
        holder.sub_cate_tv_price_dt.setText(context.getResources().getString(R.string.priceAED)+offersNameList.get(position).getCost());
        holder.sub_cate_tv_price_dt.setPaintFlags(holder.sub_cate_tv_price_dt.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
        final String url=offersNameList.get(position).getImageUrl();
        Glide.with(context).
                load(url).
                transition(DrawableTransitionOptions.withCrossFade()).
                listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.sub_cate_iv.setImageResource(R.drawable.logo);
                        Log.e(TAG, "onLoadFailed: url "+url );
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                }).
                into(holder.sub_cate_iv);
                holder.tv_cart_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return offersNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView sub_cate_iv,sub_cate_fav;
        TextView sub_cate_tv_title,sub_cate_tv_unit,sub_cate_tv_price,sub_cate_tv_price_dt,tv_cart_remove;
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
            tv_cart_remove=itemView.findViewById(R.id.tv_cart_remove);
        }
    }

    public void updateAdapter(List<AddToCartList> offersNameList){
        this.offersNameList=offersNameList;
        notifyDataSetChanged();
    }


}
