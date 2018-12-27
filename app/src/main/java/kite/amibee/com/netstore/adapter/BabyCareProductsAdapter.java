package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kite.amibee.com.netstore.R;

public class BabyCareProductsAdapter extends RecyclerView.Adapter<BabyCareProductsAdapter.ViewHolder> {
    private static final String TAG = "EpgDetailChannelAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    Integer[] exclusiveList;
    public BabyCareProductsAdapter(Context context, Integer[] exclusiveList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.exclusiveList=exclusiveList;

       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.babycare_adap_products, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.iv_adap_products.setImageResource(exclusiveList[position]);
    }

    @Override
    public int getItemCount() {
        return this.exclusiveList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_adap_products;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

            iv_adap_products=(ImageView)itemView.findViewById(R.id.iv_adap_products);
        }
    }


}
