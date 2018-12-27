package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
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
import kite.amibee.com.netstore.model.pojo.orders.OrdersItemsModel;

public class MyOrderDetailsCateAdapter extends RecyclerView.Adapter<MyOrderDetailsCateAdapter.ViewHolder> {
    private static final String TAG = "MyOrderDetailsCateAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<OrdersItemsModel> offersNameList;
    public MyOrderDetailsCateAdapter(Context context, List<OrdersItemsModel> offersNameList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.offersNameList=offersNameList;
       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.myorder_details_adap_list, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.myorder_details_cate_iv.setImageDrawable(offersNameList.get(position).getImage());
        holder.myorder_details_cate_tv_title.setText(offersNameList.get(position).getProductName().toString());
        holder.myorder_details_cate_tv_price.setText(offersNameList.get(position).getPrice());
        holder.myorder_details_cate_tv_price_dt.setText(offersNameList.get(position).getCost());
        holder.myorder_details_cate_tv_price_dt.setPaintFlags(holder.myorder_details_cate_tv_price_dt.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
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
        ImageView myorder_details_cate_iv;
        TextView myorder_details_cate_tv_title,myorder_details_cate_tv_unit,myorder_details_cate_tv_price,myorder_details_cate_tv_price_dt;
        RelativeLayout card_adap_offers;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

            myorder_details_cate_iv=(ImageView) itemView.findViewById(R.id.myorder_details_cate_iv);
            myorder_details_cate_tv_unit=(TextView)itemView.findViewById(R.id.myorder_details_cate_tv_unit);
            myorder_details_cate_tv_title=(TextView)itemView.findViewById(R.id.myorder_details_cate_tv_title);
            myorder_details_cate_tv_price=(TextView)itemView.findViewById(R.id.myorder_details_cate_tv_price);
            myorder_details_cate_tv_price_dt=(TextView)itemView.findViewById(R.id.myorder_details_cate_tv_price_dt);
        }
    }

    public void updateAdapter(List<OrdersItemsModel> offersNameList){
        this.offersNameList=offersNameList;
        notifyDataSetChanged();

    }


}
