package kite.amibee.com.netstore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import kite.amibee.com.netstore.model.MultiViewModel;
import kite.amibee.com.netstore.model.MyCartModel;

public class MultiViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MultiViewAdapter";
    private static final int ITEM_DELIVERY = 0;
    private static final int ITEM_PRICE = 1;
    private static final int ITEM_PROMO = 2;
    private static final int ITEM_LISTDETIALS = 3;
    private LayoutInflater mInflater;
    View view;
    // data is passed into the constructor
    Context context;
    List<MultiViewModel> multiList;
    public MultiViewAdapter(Context context,List<MultiViewModel> multiList) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.multiList=multiList;
       
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_DELIVERY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiview_deleivery, parent, false);
            return new MyCartDeliveryViewHolder(view);
        } else if (viewType == ITEM_PRICE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiview_price_details, parent, false);
            return new MyCartPriceViewHolder(view);
        } else if (viewType == ITEM_PROMO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiview_promocode, parent, false);
//            view.setVisibility(View.GONE);
            return new MyCartPromoViewHolder(view);
        }  else if (viewType == ITEM_LISTDETIALS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiview_mycart_adap_list, parent, false);
            return new MyCartViewHolder(view);
        }  else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case ITEM_DELIVERY:
                initDelivery((MyCartDeliveryViewHolder)holder, position);
                break;
            case ITEM_PRICE:
                initPrice((MyCartPriceViewHolder) holder, position);
                break;
            case ITEM_PROMO:
                if (holder!=null){
                    initPromo((MyCartPromoViewHolder) holder, position);
                }

                break;
            case ITEM_LISTDETIALS:

                    initDetailsList((MyCartViewHolder) holder, position);


                break;
            default:
                break;
        }



        /*holder.card_adap_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LotionOilsActivity)context).fullView();
            }
        });*/
    }


    private void initDelivery(MyCartDeliveryViewHolder holder, int pos) {
//        holder.item.setText(itemList.get(pos).getName());
    }
    private void initPrice(MyCartPriceViewHolder holder, int pos) {
//        holder.item.setText(itemList.get(pos).getName());
    }
    private void initPromo(MyCartPromoViewHolder holder, int pos) {
//        holder.item.setText(itemList.get(pos).getName());
        holder.delivery_promo_lay.setVisibility(View.GONE);
    }
    private void initDetailsList(MyCartViewHolder holder, int i) {
        Log.e(TAG, "initDetailsList: position "+i );
        MyCartModel myCartList = (MyCartModel) multiList.get(i).getObject();
//        List<MyCartModel> myCartList= (List<MyCartModel>) multiList.get(position).getAllist();
//        Log.e(TAG, "initDetailsList: size "+myCartList.size() );
//        for (int i=0;i<myCartList.size();i++){
            Log.e(TAG, "initDetailsList: desc "+multiList.get(i).getObject().toString() );
            holder.sub_cate_iv.setImageDrawable(myCartList.getImage());
            holder.sub_cate_tv_title.setText(myCartList.getDesc().toString());
            holder.sub_cate_tv_price.setText(myCartList.getPrice().toString());
            holder.sub_cate_tv_price_dt.setText(myCartList.getPrice_dt().toString());
//        }


    }

    @Override
    public int getItemCount() {
        return multiList.size();
    }

    public class MyCartViewHolder extends RecyclerView.ViewHolder {
        ImageView sub_cate_iv,sub_cate_fav;
        TextView sub_cate_tv_title,sub_cate_tv_unit,sub_cate_tv_price,sub_cate_tv_price_dt;
        RelativeLayout card_adap_offers;
        @SuppressLint("NewApi")
        MyCartViewHolder(View itemView) {
            super(itemView);

            sub_cate_iv=(ImageView) itemView.findViewById(R.id.sub_cate_iv);
            sub_cate_fav=(ImageView) itemView.findViewById(R.id.sub_cate_fav);
            sub_cate_tv_unit=(TextView)itemView.findViewById(R.id.sub_cate_tv_unit);
            sub_cate_tv_title=(TextView)itemView.findViewById(R.id.sub_cate_tv_title);
            sub_cate_tv_price=(TextView)itemView.findViewById(R.id.sub_cate_tv_price);
            sub_cate_tv_price_dt=(TextView)itemView.findViewById(R.id.sub_cate_tv_price_dt);
            card_adap_offers=(RelativeLayout)itemView.findViewById(R.id.card_adap_offers);
        }
    }

    @Override
    public int getItemViewType(int position) {
        MultiViewModel item = multiList.get(position);
        if (item.getType() == MultiViewModel.ItemType.ITEM_DELIVERY) {
            return ITEM_DELIVERY;
        } else if (item.getType() == MultiViewModel.ItemType.ITEM_PRICE) {
            return ITEM_PRICE;
        } else if (item.getType() == MultiViewModel.ItemType.ITEM_PROMO) {
            return ITEM_PROMO;
        } else if (item.getType() == MultiViewModel.ItemType.ITEM_LISTDETIALS) {
            return ITEM_LISTDETIALS;
        } else {
            return -1;
        }
    }

    public class MyCartDeliveryViewHolder extends RecyclerView.ViewHolder {

        @SuppressLint("NewApi")
        MyCartDeliveryViewHolder(View itemView) {
            super(itemView);


        }
    }

    public class MyCartPriceViewHolder extends RecyclerView.ViewHolder {

        @SuppressLint("NewApi")
        MyCartPriceViewHolder(View itemView) {
            super(itemView);


        }
    }

    public class MyCartPromoViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout delivery_promo_lay;
        @SuppressLint("NewApi")
        MyCartPromoViewHolder(View itemView) {
            super(itemView);
            delivery_promo_lay=(RelativeLayout)itemView.findViewById(R.id.delivery_promo_lay);

        }
    }


}
