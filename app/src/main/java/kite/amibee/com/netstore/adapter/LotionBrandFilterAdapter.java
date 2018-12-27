package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kite.amibee.com.netstore.R;

public class LotionBrandFilterAdapter extends RecyclerView.Adapter<LotionBrandFilterAdapter.ViewHolder> {
    private static final String TAG = "LotionBrandFilterAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;

    List<String> brandList;
    public LotionBrandFilterAdapter(Context context, List<String> brandList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.brandList=brandList;
       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.filter_brand_adap_list, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tv_filter_brand.setText(brandList.get(position));
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_filter_brand;

        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);
            tv_filter_brand=(TextView)itemView.findViewById(R.id.tv_filter_brand);

        }
    }


}
