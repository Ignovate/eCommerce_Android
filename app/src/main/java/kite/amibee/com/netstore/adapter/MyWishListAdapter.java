package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.MyCartModel;

public class MyWishListAdapter extends RecyclerView.Adapter<MyWishListAdapter.ViewHolder> {
    private static final String TAG = "MyWishListAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<MyCartModel> offersNameList;
    public MyWishListAdapter(Context context, List<MyCartModel> offersNameList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.offersNameList=offersNameList;
       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.mywishlist_adap_list, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mywishlist_cate_iv.setImageDrawable(offersNameList.get(position).getImage());
        holder.mywishlist_cate_tv_title.setText(offersNameList.get(position).getDesc().toString());
        holder.mywishlist_cate_tv_price.setText(offersNameList.get(position).getPrice().toString());
        holder.mywishlist_cate_tv_price_dt.setText(offersNameList.get(position).getPrice_dt().toString());

        /*holder.card_adap_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LotionOilsActivity)context).fullView();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return offersNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mywishlist_cate_iv,mywishlist_cate_delete;
        TextView mywishlist_cate_tv_title,mywishlist_cate_tv_unit,mywishlist_cate_tv_price,mywishlist_cate_tv_price_dt;
        RelativeLayout card_adap_offers;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

            mywishlist_cate_iv=(ImageView) itemView.findViewById(R.id.mywishlist_cate_iv);
            mywishlist_cate_delete=(ImageView) itemView.findViewById(R.id.mywishlist_cate_delete);
            mywishlist_cate_tv_unit=(TextView)itemView.findViewById(R.id.mywishlist_cate_tv_unit);
            mywishlist_cate_tv_title=(TextView)itemView.findViewById(R.id.mywishlist_cate_tv_title);
            mywishlist_cate_tv_price=(TextView)itemView.findViewById(R.id.mywishlist_cate_tv_price);
            mywishlist_cate_tv_price_dt=(TextView)itemView.findViewById(R.id.mywishlist_cate_tv_price_dt);
            card_adap_offers=(RelativeLayout)itemView.findViewById(R.id.card_adap_offers);
        }
    }


}
