package kite.amibee.com.netstore.activity.product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SpacesItemDecoration;
import kite.amibee.com.netstore.TabAdapter;
import kite.amibee.com.netstore.adapter.LotionBrandFilterAdapter;
import kite.amibee.com.netstore.adapter.ProductFilterAdapter;
import kite.amibee.com.netstore.model.BabyCareOffersModel;
import kite.amibee.com.netstore.activity.category.ShopByCateActivity;
import kite.amibee.com.netstore.model.pojo.productAttr.Attributes;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;

public class ProductListActivity extends AppCompatActivity {
    private static final String TAG = "ProductListActivity";
    /*toolbar*/
    ImageView toolbar_menu_iv,toolbar_logo_iv,toolbar_back_iv;
    TextView toolbar_tv_title,toolbar_cart_no_tv;
    RelativeLayout toolbar_lay_cart;
    //toolbar

    RecyclerView lotion_rv_list;
    ProductFilterAdapter lotionFilterAdapter;
    List<Attributes> attriList=new ArrayList<>();
    //tabs
    FrameLayout lotion_frame_transparent;
    //tabs
    private String tabTitles[] = new String[] { "", "Brand", "Sort","Price" };
    private View uiView[]= new View[4];
    TextView tv;
    View v;
    private int[] imageResId = {
            R.drawable.filter_menu,
            R.drawable.filter_brand,
            R.drawable.filter_sort,
            R.drawable.filter_price};
    RelativeLayout home_card_shop;
    RelativeLayout lotion_lay_filter,lay_tab_layout;
    ViewPager viewPager;
    View tab_view_underline;
    Api apical;
    String title;
    PreferencesHelper preferencesHelper;
    int categoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotion_oils);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int cartCount=preferencesHelper.getCartCount();
        toolbar_cart_no_tv.setText(""+cartCount);
    }

    public void init(){
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            categoryId = Integer.parseInt(extras.getString("catId"));
            title=extras.getString("catName");
            Log.e(TAG, "bundle: catId "+categoryId +" title "+title );
            apical = new Api(this,"");
            apical.productList(categoryId);
        }else {
            categoryId=45;
            apical = new Api(this,"");
            apical.productList(categoryId);
        }
        preferencesHelper = new PreferencesHelper(this);



        toolbar_menu_iv=(ImageView)findViewById(R.id.toolbar_menu);
        toolbar_logo_iv=(ImageView)findViewById(R.id.toolbar_logo);
        toolbar_back_iv=(ImageView)findViewById(R.id.toolbar_back);
        toolbar_tv_title=(TextView)findViewById(R.id.toolbar_tv_title);
        toolbar_cart_no_tv=findViewById(R.id.toolbar_cart_no_tv);

        toolbar_tv_title.setText(title);
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

        lotion_rv_list=(RecyclerView)findViewById(R.id.lotion_rv_list);
        lotion_rv_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lotion_rv_list.setItemAnimator(new DefaultItemAnimator());
        lotion_rv_list.addItemDecoration(new SpacesItemDecoration(0,0, 0, 0));
        lotionFilterAdapter = new ProductFilterAdapter(ProductListActivity.this, attriList);
        lotion_rv_list.setAdapter(lotionFilterAdapter);




        // tabs
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabAdapter pagerAdapter =
                new TabAdapter(getSupportFragmentManager(), ProductListActivity.this);

        viewPager.setAdapter(pagerAdapter);
        lotion_frame_transparent=(FrameLayout)findViewById(R.id.lotion_frame_transparent);
        lotion_lay_filter=(RelativeLayout)findViewById(R.id.lotion_lay_filter);
        lay_tab_layout=(RelativeLayout)findViewById(R.id.lay_tab_layout);
        tab_view_underline=(View)findViewById(R.id.tab_view_underline);
        tab_view_underline.setVisibility(View.GONE);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            uiView[i]=getTabView(i);
            tab.setCustomView(uiView[i]);
        }
        tabLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e(TAG, "onClick: tab layout " );

                return false;
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabSelected: pos "+tab.getPosition() );
                lotion_frame_transparent.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                lay_tab_layout.setBackgroundResource(0);
                lay_tab_layout.setBackgroundColor(getResources().getColor(R.color.white));
                tv=uiView[tab.getPosition()].findViewById(R.id.textView);
                tv.setTextColor(getResources().getColor(R.color.blue));
                tab_view_underline.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabUnselected: "+tab.getPosition() );
                tv=uiView[tab.getPosition()].findViewById(R.id.textView);
                tv.setTextColor(getResources().getColor(R.color.black));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabReselected: "+tab.getPosition() );
                if (lotion_frame_transparent.getVisibility()==View.GONE){
                    lotion_frame_transparent.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                    lay_tab_layout.setBackgroundResource(0);
                    lay_tab_layout.setBackgroundColor(getResources().getColor(R.color.white));
                    tv=uiView[tab.getPosition()].findViewById(R.id.textView);
                    tv.setTextColor(getResources().getColor(R.color.blue));
                    tab_view_underline.setVisibility(View.VISIBLE);
                }
            }
        });

        lotion_frame_transparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lotion_frame_transparent.setVisibility(View.GONE);
                lay_tab_layout.setBackgroundResource(R.drawable.layout_shadow_botom);
                viewPager.setVisibility(View.GONE);
                tab_view_underline.setVisibility(View.GONE);
                for (int i=0;i<4;i++){
                    tv=uiView[i].findViewById(R.id.textView);
                    tv.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        //tabs


        home_card_shop=(RelativeLayout)findViewById(R.id.home_card_shop);
        home_card_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, ShopByCateActivity.class);
                startActivity(intent);
            }
        });

    }
    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(this).inflate(R.layout.tabs_test, null);
        tv = (TextView) v.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) v.findViewById(R.id.imgView);
        img.setImageResource(imageResId[position]);
        return v;
    }
    public void fullView(String id){
        Intent intent = new Intent(this,ProductFullViewActivity.class);
        intent.putExtra("attri",id);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getVisibility()==View.VISIBLE){
            lotion_frame_transparent.setVisibility(View.GONE);
            lay_tab_layout.setBackgroundResource(R.drawable.layout_shadow_botom);
            viewPager.setVisibility(View.GONE);
            tab_view_underline.setVisibility(View.GONE);
            for (int i=0;i<4;i++){
                tv=uiView[i].findViewById(R.id.textView);
                tv.setTextColor(getResources().getColor(R.color.black));
            }
        }else {
            super.onBackPressed();
        }

    }

    public void apiResponse(List<Attributes> homeAllCategories){
        attriList=homeAllCategories;
        lotionFilterAdapter.updateAdapter(attriList);
        Log.e(TAG, "apiResponse: offers_list "+attriList.size() );

    }

    public void priceFilter(long min ,long max){
        Log.e(TAG, "priceFilter: min "+min+" max "+max +" categoryId "+categoryId );
        onBackPressed();
        apical.productPriceFilter(categoryId,min,max);

    }
    public void brandFilter(int min ,int max){

    }
    public void sortFilter(String filter_name){
        Log.e(TAG, "sortFilter: filter_name "+filter_name );

        if (filter_name.equals("lowtohigh")){
            List<Attributes> list=new ArrayList<>();
            for (int i=0;i<attriList.size();i++){
                list.add(new Attributes("lowtohigh",attriList.get(i).getCategoryId(),attriList.get(i).getProductId(),attriList.get(i).getPrice(),attriList.get(i).getSpecialPrice(),attriList.get(i).getImageUrl(),attriList.get(i).getImageLabel()));
            }
            Collections.sort(list);
            lotionFilterAdapter.updateAdapter(list);
            onBackPressed();

        }else if (filter_name.equals("hightolow")){
            List<Attributes> list=new ArrayList<>();
            for (int i=0;i<attriList.size();i++){
                list.add(new Attributes("hightolow",attriList.get(i).getCategoryId(),attriList.get(i).getProductId(),attriList.get(i).getPrice(),attriList.get(i).getSpecialPrice(),attriList.get(i).getImageUrl(),attriList.get(i).getImageLabel()));
            }
            Collections.sort(list);
            lotionFilterAdapter.updateAdapter(list);
            onBackPressed();
        }

    }


}

