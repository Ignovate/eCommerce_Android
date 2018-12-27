package kite.amibee.com.netstore;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import kite.amibee.com.netstore.activity.product.ProductListActivity;
import kite.amibee.com.netstore.adapter.BabyCareCategoryAdapter;
import kite.amibee.com.netstore.adapter.BabyCareOffersAdapter;
import kite.amibee.com.netstore.adapter.BabyCareProductsAdapter;
import kite.amibee.com.netstore.adapter.HomeSliderAdapter;
import kite.amibee.com.netstore.model.BabyCareOffersModel;
import kite.amibee.com.netstore.activity.category.ShopByCateActivity;
import me.relex.circleindicator.CircleIndicator;

public class BabyCareActivity extends AppCompatActivity {
    private static final String TAG = "BabyCareActivity";
    /*toolbar*/
    ImageView toolbar_menu_iv,toolbar_logo_iv,toolbar_back_iv,toolbar_cart_iv;
    TextView toolbar_tv_title;
    //toolbar
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] babycare_slide= {
            R.drawable.babycare_slide,
            R.drawable.babycare_slide_1,
            R.drawable.babycare_slide,
            R.drawable.babycare_slide_1};
    private static final Integer[] products_list_value= {
            R.drawable.babycare_products_1,
            R.drawable.babycare_products_2,
            R.drawable.babycare_products_1,
            R.drawable.babycare_products_2};
    private static final Integer[] offers_list_img_value= {
            R.drawable.babycare_offer_1,
            R.drawable.babycare_offer_2,
            R.drawable.babycare_offer_3,
            R.drawable.babycare_offer_4,
            R.drawable.babycare_offer_1,
            R.drawable.babycare_offer_2,
            R.drawable.babycare_offer_3,
            R.drawable.babycare_offer_4,
            };

    private static final Integer[] category_list_img_value= {
            R.drawable.babycare_products_1,
            R.drawable.babycare_products_2,
            R.drawable.babycare_products_1,
            R.drawable.babycare_products_2
    };
    List<BabyCareOffersModel> offers_list=new ArrayList<>();
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    RecyclerView b_care_rv_products,b_care_rv_offers,b_care_rv_category;
    BabyCareProductsAdapter babyCareProductsAdapter;
    BabyCareOffersAdapter babyCareOffersAdapter;
    BabyCareCategoryAdapter babyCareCategoryAdapter;

    RelativeLayout home_card_shop;
    Intent intent = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_care);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        carosalInit();
        intent = getIntent();
        uiInit();
    }

    public void uiInit(){

        toolbar_menu_iv=(ImageView)findViewById(R.id.toolbar_menu);
        toolbar_logo_iv=(ImageView)findViewById(R.id.toolbar_logo);
        toolbar_back_iv=(ImageView)findViewById(R.id.toolbar_back);
        toolbar_tv_title=(TextView)findViewById(R.id.toolbar_tv_title);
        String title=intent.getStringExtra("product-name");
        Log.e(TAG, "uiInit: title "+title );
        toolbar_tv_title.setText(title);
        toolbar_menu_iv.setVisibility(View.GONE);
        toolbar_logo_iv.setVisibility(View.GONE);
        toolbar_back_iv.setVisibility(View.VISIBLE);
        toolbar_tv_title.setVisibility(View.VISIBLE);

        toolbar_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        b_care_rv_products=(RecyclerView)findViewById(R.id.babycare_rv_products);
        b_care_rv_products.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        b_care_rv_products.setItemAnimator(new DefaultItemAnimator());
        b_care_rv_products.addItemDecoration(new SpacesItemDecoration(0,10, 0, 0));
        babyCareProductsAdapter = new BabyCareProductsAdapter(getApplicationContext(), products_list_value);
        b_care_rv_products.setAdapter(babyCareProductsAdapter);

        offers_list.add(new BabyCareOffersModel(getResources().getString(R.string.babycare_desc_1),getResources().getString(R.string.babycare_price_1),getResources().getString(R.string.babycare_price_dt1)));
        offers_list.add(new BabyCareOffersModel(getResources().getString(R.string.babycare_desc_2),getResources().getString(R.string.babycare_price_2),getResources().getString(R.string.babycare_price_dt2)));
        offers_list.add(new BabyCareOffersModel(getResources().getString(R.string.babycare_desc_3),getResources().getString(R.string.babycare_price_3),getResources().getString(R.string.babycare_price_dt3)));
        offers_list.add(new BabyCareOffersModel(getResources().getString(R.string.babycare_desc_4),getResources().getString(R.string.babycare_price_4),getResources().getString(R.string.babycare_price_dt4)));
        offers_list.add(new BabyCareOffersModel(getResources().getString(R.string.babycare_desc_1),getResources().getString(R.string.babycare_price_1),getResources().getString(R.string.babycare_price_dt1)));
        offers_list.add(new BabyCareOffersModel(getResources().getString(R.string.babycare_desc_2),getResources().getString(R.string.babycare_price_2),getResources().getString(R.string.babycare_price_dt2)));
        offers_list.add(new BabyCareOffersModel(getResources().getString(R.string.babycare_desc_3),getResources().getString(R.string.babycare_price_3),getResources().getString(R.string.babycare_price_dt3)));
        offers_list.add(new BabyCareOffersModel(getResources().getString(R.string.babycare_desc_4),getResources().getString(R.string.babycare_price_4),getResources().getString(R.string.babycare_price_dt4)));
        b_care_rv_offers=(RecyclerView)findViewById(R.id.babycare_rv_offers);
        b_care_rv_offers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        b_care_rv_offers.setItemAnimator(new DefaultItemAnimator());
        b_care_rv_offers.addItemDecoration(new SpacesItemDecoration(0,15, 0, 0));
        babyCareOffersAdapter = new BabyCareOffersAdapter(BabyCareActivity.this, offers_list_img_value,offers_list);
        b_care_rv_offers.setAdapter(babyCareOffersAdapter);


        b_care_rv_category=(RecyclerView)findViewById(R.id.babycare_rv_category);
        b_care_rv_category.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        b_care_rv_category.setItemAnimator(new DefaultItemAnimator());
        b_care_rv_category.addItemDecoration(new SpacesItemDecoration(0,10, 0, 0));
        babyCareCategoryAdapter = new BabyCareCategoryAdapter(getApplicationContext(), category_list_img_value);
        b_care_rv_category.setAdapter(babyCareCategoryAdapter);

        home_card_shop=(RelativeLayout)findViewById(R.id.home_card_shop);
        home_card_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabyCareActivity.this, ShopByCateActivity.class);
                startActivity(intent);
            }
        });

    }
    private void carosalInit() {
        for(int i=0;i<babycare_slide.length;i++)
            XMENArray.add(babycare_slide[i]);

        mPager = (ViewPager) findViewById(R.id.babycare_viewpager);
        mPager.setAdapter(new HomeSliderAdapter(BabyCareActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == babycare_slide.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);
    }

    public void productsClick(){
        Intent intent= new Intent(this,ProductListActivity.class);
        startActivity(intent);
    }
}
