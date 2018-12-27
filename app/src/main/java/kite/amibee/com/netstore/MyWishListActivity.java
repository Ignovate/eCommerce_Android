package kite.amibee.com.netstore;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.activity.addToCart.MyCartActivity;
import kite.amibee.com.netstore.adapter.MyWishListAdapter;
import kite.amibee.com.netstore.model.MyCartModel;

public class MyWishListActivity extends AppCompatActivity {
    /*toolbar*/
    ImageView toolbar_menu_iv,toolbar_logo_iv,toolbar_back_iv,toolbar_cart_iv;
    TextView toolbar_tv_title;
    RelativeLayout toolbar_lay_cart;
    SearchView home_search_view;
    //toolbar
    //ui
    TextView order_tv_1,order_tv_2;
    RecyclerView mywish_rv;
    MyWishListAdapter myWishListAdapter;
    List<MyCartModel> offers_list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);
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
        toolbar_tv_title.setText("My Wishlist");
        toolbar_menu_iv.setVisibility(View.GONE);
        toolbar_logo_iv.setVisibility(View.GONE);
        toolbar_back_iv.setVisibility(View.VISIBLE);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_lay_cart=(RelativeLayout)findViewById(R.id.toolbar_lay_cart);
        toolbar_lay_cart.setVisibility(View.VISIBLE);
        toolbar_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        home_search_view=(SearchView)findViewById(R.id.home_search_view);
        home_search_view.setVisibility(View.VISIBLE);
        home_search_view.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toolbar_tv_title.setVisibility(View.VISIBLE);
                return false;
            }
        });
        home_search_view.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar_tv_title.setVisibility(View.GONE);
            }
        });
        toolbar_cart_iv=(ImageView)findViewById(R.id.toolbar_cart);
        toolbar_cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyWishListActivity.this, MyCartActivity.class);
                startActivity(intent);
            }
        });
        //toolbar


        Drawable image1 = ContextCompat.getDrawable(this, R.drawable.mywish_1);
        Drawable image2 = ContextCompat.getDrawable(this, R.drawable.mywish_2);
        Drawable image3 = ContextCompat.getDrawable(this, R.drawable.mywish_3);


        offers_list.add(new MyCartModel(image1,getResources().getString(R.string.babycare_desc_1),getResources().getString(R.string.babycare_price_1),getResources().getString(R.string.babycare_price_dt1)));
        offers_list.add(new MyCartModel(image2,getResources().getString(R.string.babycare_desc_2),getResources().getString(R.string.babycare_price_2),getResources().getString(R.string.babycare_price_dt2)));
        offers_list.add(new MyCartModel(image3,getResources().getString(R.string.babycare_desc_3),getResources().getString(R.string.babycare_price_3),getResources().getString(R.string.babycare_price_dt3)));


        mywish_rv=(RecyclerView)findViewById(R.id.mywish_rv);
        mywish_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mywish_rv.setItemAnimator(new DefaultItemAnimator());
        mywish_rv.addItemDecoration(new SpacesItemDecoration(0,0, 0, 0));
        myWishListAdapter = new MyWishListAdapter(MyWishListActivity.this, offers_list);
        mywish_rv.setAdapter(myWishListAdapter);

    }
}
