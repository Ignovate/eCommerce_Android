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

import java.util.ArrayList;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.activity.addToCart.MyCartActivity;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartList;
import kite.amibee.com.netstore.util.Utils;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    private static final String TAG = "MyCartAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    ArrayList<AddToCartList> offersNameList;
    Utils utils;
    int product=0;
    int quantity=0;

    public MyCartAdapter(Context context, ArrayList<AddToCartList> offersNameList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.offersNameList=offersNameList;
        utils= new Utils();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.mycart_adap_list, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.sub_cate_iv.setImageDrawable(offersNameList.get(position).getImage());
        holder.tv_cart_remove.setId(position);
        quantity= Integer.parseInt(offersNameList.get(position).getQuantity());
        product= Integer.parseInt(offersNameList.get(position).getProductId());
        Log.d(TAG, "onBind: getProductId " + product);
        Log.d(TAG, "onBind: quanty " + quantity);
        Log.d(TAG, "onBind: name " + offersNameList.get(position).getProductName().toString());
        Log.d(TAG, "onBind: price " + offersNameList.get(position).getPrice());
        Log.d(TAG, "onBind: price_dt " + offersNameList.get(position).getCost());

        holder.filter_stock_num.setText(""+quantity);


        holder.sub_cate_tv_title.setText(offersNameList.get(position).getProductName().toString());
        holder.sub_cate_tv_price.setText(context.getResources().getString(R.string.priceAED)+offersNameList.get(position).getPrice());
        holder.sub_cate_tv_price_dt.setText(context.getResources().getString(R.string.priceAED)+offersNameList.get(position).getCost());
        holder.sub_cate_tv_price_dt.setPaintFlags(holder.sub_cate_tv_price_dt.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
        String url=offersNameList.get(position).getImageUrl();
        Log.e(TAG, "onBindViewHolder: url "+url );
        Glide.with(context).
                load(url).
                transition(DrawableTransitionOptions.withCrossFade()).
                listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder. sub_cate_iv.setImageResource(R.drawable.logo);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        return false;
                    }

                }).
                into(holder.sub_cate_iv);
        holder.tv_cart_remove.setId(position);
        holder.filter_stock_max.setId(position);
        holder.filter_stock_min.setId(position);
        holder.tv_cart_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productId=offersNameList.get(view.getId()).getProductId();
                String quantity="0";
                Log.e(TAG, "onClick: quantity "+quantity );
                ((MyCartActivity)context).cartDelete(productId,quantity);

            }
        });

        holder.filter_stock_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stock = Integer.parseInt(holder.filter_stock_num.getText().toString());
                if (stock > 0) {
                    stock = stock - 1;
                    holder.filter_stock_num.setText("" + stock);
                }
            }
        });
        holder.filter_stock_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int stock = Integer.parseInt(holder.filter_stock_num.getText().toString());
                int stockCountMax = Integer.parseInt(offersNameList.get(view.getId()).getQuantity());
                if (stock < stockCountMax) {
                    stock = stock + 1;
                    holder.filter_stock_num.setText("" + stock);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return offersNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView sub_cate_iv,sub_cate_fav;
        TextView sub_cate_tv_title,sub_cate_tv_unit,sub_cate_tv_price,sub_cate_tv_price_dt,tv_cart_remove,
                filter_stock_max,filter_stock_min,filter_stock_num;
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
            filter_stock_max=itemView.findViewById(R.id.filter_stock_max);
            filter_stock_min=itemView.findViewById(R.id.filter_stock_min);
            filter_stock_num=itemView.findViewById(R.id.filter_stock_num);
        }
    }

    public void updateAdapter(ArrayList<AddToCartList> offersNameList){
        this.offersNameList=offersNameList;
        notifyDataSetChanged();
    }


}
