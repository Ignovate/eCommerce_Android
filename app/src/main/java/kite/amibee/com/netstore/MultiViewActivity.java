package kite.amibee.com.netstore;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.adapter.MultiViewAdapter;
import kite.amibee.com.netstore.model.MultiViewModel;
import kite.amibee.com.netstore.model.MyCartModel;

public class MultiViewActivity extends AppCompatActivity {
    /*toolbar*/
    ImageView toolbar_menu_iv,toolbar_logo_iv,toolbar_back_iv,toolbar_cart_iv;
    TextView toolbar_tv_title;
    RelativeLayout toolbar_lay_cart;
    //toolbar
    RecyclerView delivery_cart_rv;
    List<MyCartModel> offers_list=new ArrayList<>();
    List<MultiViewModel> multiview_list=new ArrayList<>();
    MultiViewAdapter multiViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_view);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    public void init(){
        //toolbar
        toolbar_menu_iv=(ImageView)findViewById(R.id.toolbar_menu);
        toolbar_logo_iv=(ImageView)findViewById(R.id.toolbar_logo);
        toolbar_back_iv=(ImageView)findViewById(R.id.toolbar_back);
        toolbar_tv_title=(TextView)findViewById(R.id.toolbar_tv_title);
        toolbar_cart_iv=(ImageView)findViewById(R.id.toolbar_cart);
        toolbar_cart_iv.setVisibility(View.GONE);
        toolbar_tv_title.setText("Delivery");
        toolbar_menu_iv.setVisibility(View.GONE);
        toolbar_logo_iv.setVisibility(View.GONE);
        toolbar_back_iv.setVisibility(View.VISIBLE);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_lay_cart=(RelativeLayout)findViewById(R.id.toolbar_lay_cart);
        toolbar_lay_cart.setVisibility(View.GONE);
        toolbar_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //toolbar

        Drawable image1 = ContextCompat.getDrawable(this, R.drawable.sub_category_iv_1);
        Drawable image2 = ContextCompat.getDrawable(this, R.drawable.sub_category_iv_2);
        Drawable image3 = ContextCompat.getDrawable(this, R.drawable.sub_category_iv_3);
        Drawable image4 = ContextCompat.getDrawable(this, R.drawable.sub_category_iv_4);


        offers_list.add(new MyCartModel(image1,getResources().getString(R.string.babycare_desc_1),getResources().getString(R.string.babycare_price_1),getResources().getString(R.string.babycare_price_dt1)));
        offers_list.add(new MyCartModel(image2,getResources().getString(R.string.babycare_desc_2),getResources().getString(R.string.babycare_price_2),getResources().getString(R.string.babycare_price_dt2)));
        offers_list.add(new MyCartModel(image3,getResources().getString(R.string.babycare_desc_3),getResources().getString(R.string.babycare_price_3),getResources().getString(R.string.babycare_price_dt3)));
        offers_list.add(new MyCartModel(image4,getResources().getString(R.string.babycare_desc_4),getResources().getString(R.string.babycare_price_4),getResources().getString(R.string.babycare_price_dt4)));

//        offers_list.add(new MyCartModel(image1,getResources().getString(R.string.babycare_desc_1),getResources().getString(R.string.babycare_price_1),getResources().getString(R.string.babycare_price_dt1)));
//        offers_list.add(new MyCartModel(image2,getResources().getString(R.string.babycare_desc_2),getResources().getString(R.string.babycare_price_2),getResources().getString(R.string.babycare_price_dt2)));
//        offers_list.add(new MyCartModel(image3,getResources().getString(R.string.babycare_desc_3),getResources().getString(R.string.babycare_price_3),getResources().getString(R.string.babycare_price_dt3)));
//        offers_list.add(new MyCartModel(image4,getResources().getString(R.string.babycare_desc_4),getResources().getString(R.string.babycare_price_4),getResources().getString(R.string.babycare_price_dt4)));

        multiview_list.add(new MultiViewModel(offers_list,MultiViewModel.ItemType.ITEM_DELIVERY));
        multiview_list.add(new MultiViewModel(offers_list,MultiViewModel.ItemType.ITEM_PRICE));
//        multiview_list.add(new MultiViewModel(offers_list,MultiViewModel.ItemType.ITEM_PROMO));
        multiview_list.add(new MultiViewModel(new MyCartModel(image1,getResources().getString(R.string.babycare_desc_1),getResources().getString(R.string.babycare_price_1),getResources().getString(R.string.babycare_price_dt1)),MultiViewModel.ItemType.ITEM_LISTDETIALS));
        multiview_list.add(new MultiViewModel(new MyCartModel(image2,getResources().getString(R.string.babycare_desc_2),getResources().getString(R.string.babycare_price_2),getResources().getString(R.string.babycare_price_dt2)),MultiViewModel.ItemType.ITEM_LISTDETIALS));
        multiview_list.add(new MultiViewModel(new MyCartModel(image3,getResources().getString(R.string.babycare_desc_3),getResources().getString(R.string.babycare_price_3),getResources().getString(R.string.babycare_price_dt3)),MultiViewModel.ItemType.ITEM_LISTDETIALS));
        multiview_list.add(new MultiViewModel(new MyCartModel(image4,getResources().getString(R.string.babycare_desc_4),getResources().getString(R.string.babycare_price_4),getResources().getString(R.string.babycare_price_dt4)),MultiViewModel.ItemType.ITEM_LISTDETIALS));

        delivery_cart_rv=(RecyclerView)findViewById(R.id.multiview_cart_rv);
        delivery_cart_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        delivery_cart_rv.setItemAnimator(new DefaultItemAnimator());
        delivery_cart_rv.addItemDecoration(new SpacesItemDecoration(0,0, 0, 0));
        multiViewAdapter = new MultiViewAdapter(MultiViewActivity.this, multiview_list);
        delivery_cart_rv.setAdapter(multiViewAdapter);


    }
}
