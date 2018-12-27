package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.activity.orders.MyOrdersActivity;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.pojo.orders.OrdersItemsModel;
import kite.amibee.com.netstore.model.pojo.orders.OrdersModel;

public class MyOrderAdapterMain extends RecyclerView.Adapter<MyOrderAdapterMain.ViewHolder> {
    private static final String TAG = "MyOrderAdapterMain";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<OrdersModel> orderList = new ArrayList<>();
    public MyOrderAdapterMain(Context context, List<OrdersModel> orderList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.orderList=orderList;
       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.myorder_adap_list_test, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.myorder_orderId.setText(orderList.get(position).getId().toString());
                List<String> createdDate =orderList.get(position).getCreatedDate();
        List<String> updatedDate =orderList.get(position).getUpdatedDate();
        StringBuilder c_date = new StringBuilder();
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
        holder.myorder_tv_deli_date.setText(u_date1);

        List<OrdersItemsModel> orderItemList =orderList.get(position).getSaleOrderItems();
        Log.w(TAG, "onBindViewHolder: orderItemList "+orderItemList );
        if (orderItemList!=null){
            Log.d(TAG, "onBindViewHolder: orderItemList "+orderItemList.size() );
        }
        MyOrderAdapter itemListDataAdapter = new MyOrderAdapter(context, orderItemList);

        holder.recycler_view_list.setHasFixedSize(true);
        holder.recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.recycler_view_list.setAdapter(itemListDataAdapter);


        holder.recycler_view_list.setNestedScrollingEnabled(false);

        holder.myorders_lay_orderid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderId=orderList.get(position).getId().toString();
                Log.d(TAG, "onClick: order id "+orderId);
                ((MyOrdersActivity)context).detailsShow(orderId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myorder_iv;
        TextView myorder_orderId;
        RecyclerView recycler_view_list;
        RelativeLayout myorders_lay_orderid;
        TextView myorder_order_date,myorder_tv_deli_date;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);
            myorder_order_date=(TextView)itemView.findViewById(R.id.myorder_order_date);
            myorder_tv_deli_date=(TextView)itemView.findViewById(R.id.myorder_tv_deli_date);

            myorder_orderId=(TextView)itemView.findViewById(R.id.myorder_orderId);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.rv_subview);
            myorders_lay_orderid= view.findViewById(R.id.myorders_lay_orderid);
        }


    }

    public void updateAdapter(List<OrdersModel> orderList){
        this.orderList=orderList;
        notifyDataSetChanged();
    }


}
