package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import kite.amibee.com.netstore.R;

public class BabyCareCategoryAdapter extends RecyclerView.Adapter<BabyCareCategoryAdapter.ViewHolder> {
    private static final String TAG = "EpgDetailChannelAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    Integer[] trendsList;
    public BabyCareCategoryAdapter(Context context, Integer[] trendsList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.trendsList=trendsList;

       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = mInflater.inflate(R.layout.babycare_adap_category, parent, false);

            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.iv_adap_category.setImageResource(trendsList[position]);

        if (position==0){
            holder.lay_adap_category.setBackgroundResource(R.drawable.lay_leftbot_corner);
        }else if (trendsList.length -1 ==position){
            holder.lay_adap_category.setBackgroundResource(R.drawable.lay_rightbot_corner);
        }else {
            holder.lay_adap_category.setBackgroundResource(R.drawable.lay_normal_corner);
        }
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                trendsList[position]);
        Palette.generateAsync((icon), new Palette.PaletteAsyncListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onGenerated(Palette palette) {
                if (Build.VERSION.SDK_INT > 19) {
                    holder.lay_adap_category.setBackgroundTintList(ColorStateList.valueOf(palette.getVibrantColor(0)));
                }

//                holder.cv_adap_trends.setCardBackgroundColor(palette.getVibrantColor(0));
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.trendsList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_adap_category;
//        CardView cv_adap_trends;
        RelativeLayout lay_adap_category;
        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

            iv_adap_category=(ImageView)itemView.findViewById(R.id.iv_adap_category);
//            cv_adap_trends=(CardView)itemView.findViewById(R.id.cv_adap_trends);
            lay_adap_category=(RelativeLayout)itemView.findViewById(R.id.lay_adap_category);
        }
    }


}
