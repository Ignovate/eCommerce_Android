package kite.amibee.com.netstore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;

import kite.amibee.com.netstore.activity.product.ProductListActivity;

public class TabFragmentPrice extends Fragment {
    private static final String TAG = "TabFragmentPrice";
    RelativeLayout lay_FilterCancel,lay_FilterApply;
    private View.OnClickListener onClickListener;
    long priceMin=0,priceMax=0;
    MyRangeSeekbar myRangeSeekbar;
    TextView tv_min_price,tv_max_price;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_price, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);


    }

    public void init(View view){
        onClick();
        tv_max_price=view.findViewById(R.id.tv_max_price);
        tv_min_price = view.findViewById(R.id.tv_min_price);
        lay_FilterCancel=view.findViewById(R.id.lay_bot_1);
        lay_FilterApply=view.findViewById(R.id.lay_bot_2);
        myRangeSeekbar =(MyRangeSeekbar)view.findViewById(R.id.rangeSeekbar8);
        myRangeSeekbar.setMinStartValue(0f);
        myRangeSeekbar.setMaxStartValue(100f);

        lay_FilterCancel.setOnClickListener(onClickListener);
        lay_FilterApply.setOnClickListener(onClickListener);

        myRangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                priceMin=  minValue.longValue();
                priceMax=  maxValue.longValue();
                Log.e(TAG, "valueChanged: priceMin "+priceMin +" priceMax "+priceMax );
                tv_max_price.setText("AED "+priceMax);
                tv_min_price.setText("AED "+priceMin);
            }
        });



    }

    public void onClick(){
        onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: cal " );
                if (lay_FilterApply.hashCode() == view.hashCode()){
                    Log.e(TAG, "onClick: cal filter " );
                    ((ProductListActivity)getActivity()).priceFilter(priceMin,priceMax);
                }else if (lay_FilterCancel.hashCode()== view.hashCode()){
                    ((ProductListActivity)getActivity()).onBackPressed();
                }
            }
        };
    }
}
