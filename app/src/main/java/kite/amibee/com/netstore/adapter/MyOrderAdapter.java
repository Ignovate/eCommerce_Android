package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.pojo.orders.OrdersItemsModel;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    private static final String TAG = "MyOrderAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<OrdersItemsModel> orderList;
    public MyOrderAdapter(Context context, List<OrdersItemsModel> orderList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.orderList=orderList;
       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.myorder_adap_list_single_test, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

//        holder.myorder_iv.setImageDrawable(offersNameList.get(position).getImage());
        Log.e(TAG, "onBindViewHolder: id "+orderList.get(position).getId() );
//        Log.e(TAG, "onBindViewHolder: grand "+orderList.get(position).getGrandTotal() );

        holder.myorder_tv_title.setText(context.getResources().getString(R.string.priceAED)+orderList.get(position).getProductName().toString());
        holder.myorder_tv_price.setText(context.getResources().getString(R.string.priceAED)+orderList.get(position).getPrice().toString());
        holder.myorder_tv_price_dt.setText(context.getResources().getString(R.string.priceAED)+orderList.get(position).getPrice().toString());
        holder.myorder_tv_gTotal.setText("Grand Total "+orderList.get(position).getCost().toString());
        holder.myorder_tv_price_dt.setPaintFlags(holder.myorder_tv_price_dt.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
//        List<String> createdDate =orderList.get(position).getCreatedDate();
//        List<String> updatedDate =orderList.get(position).getUpdatedDate();
        /*StringBuilder c_date = new StringBuilder();
        StringBuilder u_date = new StringBuilder();
        for (int i=0;i<createdDate.size();i++){
            if (i < 3){
                c_date.append(createdDate.get(i)+"/");
                u_date.append(updatedDate.get(i)+"/");

            }

        }
        String c_date1 = c_date.substring(0, c_date.length() - 1);
        String u_date1 = u_date.substring(0, u_date.length() - 1);
        holder.myorder_order_date.setText(c_date1);
        holder.myorder_tv_deli_date.setText(u_date1);*/


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myorder_iv;
        TextView myorder_tv_title,myorder_tv_unit,myorder_tv_price,myorder_tv_price_dt,myorder_tv_gTotal;
        TextView myorder_order_date,myorder_orderId,myorder_tv_deli_date;
        RelativeLayout myorder_lay_main;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

//            myorder_iv=(ImageView) itemView.findViewById(R.id.myorder_iv);
//            myorder_tv_unit=(TextView)itemView.findViewById(R.id.myorder_tv_unit);
            myorder_tv_title=(TextView)itemView.findViewById(R.id.myorder_tv_title);
            myorder_tv_price=(TextView)itemView.findViewById(R.id.myorder_tv_price);
            myorder_tv_price_dt=(TextView)itemView.findViewById(R.id.myorder_tv_price_dt);

            myorder_tv_gTotal=(TextView)itemView.findViewById(R.id.myorder_tv_gTotal);
//            myorder_order_date=(TextView)itemView.findViewById(R.id.myorder_order_date);
//            myorder_tv_deli_date=(TextView)itemView.findViewById(R.id.myorder_tv_deli_date);
//            myorder_lay_main=(RelativeLayout)itemView.findViewById(R.id.myorder_lay_main);
        }


    }

    public void updateAdapter(List<OrdersItemsModel> orderList){
        this.orderList=orderList;
        notifyDataSetChanged();
    }


}
