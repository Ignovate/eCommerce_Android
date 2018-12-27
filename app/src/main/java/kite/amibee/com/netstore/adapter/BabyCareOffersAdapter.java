package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kite.amibee.com.netstore.BabyCareActivity;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.model.BabyCareOffersModel;

public class BabyCareOffersAdapter extends RecyclerView.Adapter<BabyCareOffersAdapter.ViewHolder> {
    private static final String TAG = "BabyCareOffersAdapter";

    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    Integer[] offersImgList;
    List<BabyCareOffersModel> offersNameList;
    public BabyCareOffersAdapter(Context context, Integer[] offersImgList, List<BabyCareOffersModel> offersNameList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.offersImgList=offersImgList;
        this.offersNameList=offersNameList;
       
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = mInflater.inflate(R.layout.babycare_adap_offers, parent, false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.iv_baby_offers.setImageResource(offersImgList[position]);
        holder.tv_baby_offers_desc.setText(offersNameList.get(position).getDesc().toString());
        holder.tv_baby_offers_price.setText(offersNameList.get(position).getPrice().toString());
        holder.tv_baby_offers_price_dt.setText(offersNameList.get(position).getPrice_dt().toString());

        holder.iv_baby_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BabyCareActivity)context).productsClick();
            }
        });

        /*platte color */
        /*Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                offersImgList[position]);
        Palette.generateAsync((icon), new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                holder.card_adap_offers.setCardBackgroundColor(palette.getLightMutedColor(0));
            }
        });*/

        /*if (position % 2==0){
            holder.card_adap_offers.setCardBackgroundColor(context.getResources().getColor(R.color.home_card_offers_1));
        }else {
            holder.card_adap_offers.setCardBackgroundColor(context.getResources().getColor(R.color.home_card_offers_2));
        }*/
    }

    @Override
    public int getItemCount() {
        return this.offersImgList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_baby_offers;
        TextView tv_baby_offers_desc,tv_baby_offers_price,tv_baby_offers_price_dt;

        @SuppressLint("NewApi")
        ViewHolder(View itemView) {
            super(itemView);

            iv_baby_offers=(ImageView) itemView.findViewById(R.id.iv_baby_offers);
            tv_baby_offers_desc=(TextView)itemView.findViewById(R.id.tv_baby_offers_desc);
            tv_baby_offers_price=(TextView)itemView.findViewById(R.id.tv_baby_offers_price);
            tv_baby_offers_price_dt=(TextView)itemView.findViewById(R.id.tv_baby_offers_price_dt);
        }
    }


}
