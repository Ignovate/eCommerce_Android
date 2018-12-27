package kite.amibee.com.netstore.activity.home;

import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import kite.amibee.com.netstore.BabyCareActivity;
import kite.amibee.com.netstore.MyApp;
import kite.amibee.com.netstore.activity.orders.MyOrdersActivity;
import kite.amibee.com.netstore.MyWishListActivity;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SelectAddressActivity;
import kite.amibee.com.netstore.SpacesItemDecoration;
import kite.amibee.com.netstore.activity.product.ProductListActivity;
import kite.amibee.com.netstore.activity.register.SignInActivity;
import kite.amibee.com.netstore.adapter.HomeBannersMainAdapter;
import kite.amibee.com.netstore.adapter.HomeExclusiveAdapter;
import kite.amibee.com.netstore.adapter.HomeOffersAdapter;
import kite.amibee.com.netstore.adapter.HomeSliderAdapter;
import kite.amibee.com.netstore.adapter.HomeTrendsAdapter;
import kite.amibee.com.netstore.activity.category.ShopByCateActivity;
import kite.amibee.com.netstore.model.pojo.home.HomeAllCategories;
import kite.amibee.com.netstore.model.pojo.home.HomeAllResponse;
import kite.amibee.com.netstore.model.pojo.home.HomeBannerImages;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;
import me.relex.circleindicator.CircleIndicator;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";

    private ViewPager mPager;
    private static int currentPage = 0;
    private  List<HomeBannerImages> exclusive_list_value= new ArrayList<>();
    private  List<HomeBannerImages> trends_list_img_value= new ArrayList<>();
    private  List<HomeBannerImages> offers_list= new ArrayList<>();
    List<HomeBannerImages> cateSingleList;
    RecyclerView rv_home_banners;
    RecyclerView home_rv_exclusive, home_rv_offers, home_rv_trending;
    kite.amibee.com.netstore.adapter.HomeExclusiveAdapter HomeExclusiveAdapter;
    HomeOffersAdapter homeOffersAdapter;
    HomeTrendsAdapter homeTrendsAdapter;
    HomeBannersMainAdapter homeBannersMainAdapter;
    List<HomeAllCategories> bannersList = new ArrayList<>();
    ImageView toolbar_menu;
    NavigationView navigationView;
    View navHeaderView;
    DrawerLayout drawer;
    RelativeLayout home_card_shop;
    TextView toolbar_cart_no_tv,user_tv_name,user_tv_mobile,user_tv_mailid,
            tv_banner_offers,tv_banner_trends,tv_banner_exclusive;
    Api apical;
    private View.OnClickListener onClickListener;
    //
    Map<String, List<HomeAllResponse>> catelistMap = new HashMap<>();

    PreferencesHelper preferencesHelper;
    MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        uiInit();

    }

    public void uiInit() {
        apical = new Api(this,"");
        apical.homeAllList();
        preferencesHelper= new PreferencesHelper(this);
        myApp = (MyApp) getApplicationContext();
        myApp.setContext(getApplicationContext());
        clickListener();


        //drawer
        toolbar_menu = (ImageView) findViewById(R.id.toolbar_menu);
        toolbar_cart_no_tv=findViewById(R.id.toolbar_cart_no_tv);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeaderView=navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar_menu.setOnClickListener(onClickListener);
        //drawer
        home_card_shop = (RelativeLayout) findViewById(R.id.home_card_shop);
        home_card_shop.setOnClickListener(onClickListener);

        /*rv_home_banners = (RecyclerView) findViewById(R.id.rv_home_banners);
        rv_home_banners.setHasFixedSize(true);
        homeBannersMainAdapter = new HomeBannersMainAdapter(HomeActivity.this, bannersList);

        rv_home_banners.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_home_banners.setAdapter(homeBannersMainAdapter);*/

        home_rv_exclusive = (RecyclerView) findViewById(R.id.home_rv_exclusive);
        home_rv_exclusive.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        home_rv_exclusive.setItemAnimator(new DefaultItemAnimator());
        home_rv_exclusive.addItemDecoration(new SpacesItemDecoration(0, 10, 0, 0));
        HomeExclusiveAdapter = new HomeExclusiveAdapter(HomeActivity.this, exclusive_list_value);
        home_rv_exclusive.setAdapter(HomeExclusiveAdapter);


        home_rv_offers = (RecyclerView) findViewById(R.id.home_rv_offers);
        home_rv_offers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        home_rv_offers.setItemAnimator(new DefaultItemAnimator());
        home_rv_offers.addItemDecoration(new SpacesItemDecoration(0, 15, 0, 0));
        homeOffersAdapter = new HomeOffersAdapter(HomeActivity.this,  offers_list);
        home_rv_offers.setAdapter(homeOffersAdapter);


        home_rv_trending = (RecyclerView) findViewById(R.id.home_rv_trending);
        home_rv_trending.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        home_rv_trending.setItemAnimator(new DefaultItemAnimator());
        home_rv_trending.addItemDecoration(new SpacesItemDecoration(0, 10, 0, 0));
        homeTrendsAdapter = new HomeTrendsAdapter(HomeActivity.this, trends_list_img_value);
        home_rv_trending.setAdapter(homeTrendsAdapter);

        user_tv_name=navHeaderView.findViewById(R.id.user_tv_name);
        user_tv_mailid=navHeaderView.findViewById(R.id.user_tv_mailid);
        user_tv_mobile=navHeaderView.findViewById(R.id.user_tv_mobile);

        tv_banner_offers=findViewById(R.id.tv_banner_offers);
        tv_banner_exclusive=findViewById(R.id.tv_banner_exclusive);
        tv_banner_trends=findViewById(R.id.tv_banner_trends);

        String userName = preferencesHelper.getUserName();
        String email = preferencesHelper.getEmail();
        int mobile = preferencesHelper.getMobile();

        Log.e(TAG, "uiInit: userName "+userName +" email "+email+" mobile "+mobile);
//        Log.e(TAG, "uiInit: user_tv_name "+user_tv_name +" user_tv_mailid "+user_tv_mailid+" user_tv_mobile "+user_tv_mobile);

        user_tv_name.setText(userName);
        user_tv_mailid.setText(email);
        user_tv_mobile.setText(""+mobile);


    }

    @Override
    protected void onResume() {
        super.onResume();
        int cartCount=preferencesHelper.getCartCount();
        toolbar_cart_no_tv.setText(""+cartCount);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_order) {
            // Handle the camera action
            Intent in = new Intent(HomeActivity.this, MyOrdersActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_my_addre) {
            Intent in = new Intent(HomeActivity.this, SelectAddressActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_mywishlist) {
            Intent in = new Intent(HomeActivity.this, MyWishListActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_logout) {
            if (preferencesHelper.getUserRegister()){
                preferencesHelper.putUserRegister(false);
                preferencesHelper.clear();
                Intent in = new Intent(HomeActivity.this, SignInActivity.class);
                startActivity(in);
                finish();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void carosalInit() {

        if (cateSingleList==null){
            cateSingleList = new ArrayList<>();
        }
        Log.e(TAG, "carosalInit: cateSingleList "+cateSingleList.size() );

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new HomeSliderAdapter(HomeActivity.this, cateSingleList));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == cateSingleList.size()) {
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


    public void productsClick(String name) {
        Intent intent = new Intent(this, BabyCareActivity.class);
        intent.putExtra("product-name",name);
        startActivity(intent);
    }


    public void apiResponse(List<HomeAllCategories> homeAllCategories) {
        List<HomeAllCategories> homeAllCategories1=homeAllCategories;
//            homeBannersMainAdapter.updateAdapter(homeAllCategories1);
        for (int i=0;i<5;i++){
            if (i==0){
                cateSingleList=homeAllCategories1.get(i).getBannerImages();
                carosalInit();
            }else if (i==1){
                String title=homeAllCategories1.get(i).layoutName;
                tv_banner_exclusive.setText(title);
                exclusive_list_value=homeAllCategories1.get(i).getBannerImages();
                HomeExclusiveAdapter.updateAdapter(exclusive_list_value);
            }else if (i==2){
                String title=homeAllCategories1.get(i).layoutName;
                tv_banner_offers.setText(title);
                offers_list=homeAllCategories1.get(i).getBannerImages();
                Log.e(TAG, "apiResponse: offers_list "+offers_list.size() );
                homeOffersAdapter.updateAdapter(offers_list);
            }else if (i==3){
                String title=homeAllCategories1.get(i).layoutName;
                tv_banner_trends.setText(title);
                trends_list_img_value=homeAllCategories1.get(i).getBannerImages();
                Log.e(TAG, "apiResponse: trends_list_img_value "+trends_list_img_value.size() );
                homeTrendsAdapter.updateAdapter(trends_list_img_value);
            }




        }

        Log.e(TAG, "apiResponse: responses "+homeAllCategories1.size() );


    }

    public void clickListener(){
        onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toolbar_menu.hashCode()==view.hashCode()){
                    drawer.openDrawer(GravityCompat.START);
                }else if(home_card_shop.hashCode()==view.hashCode()){
                    Intent intent = new Intent(HomeActivity.this, ShopByCateActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    public void sendCatId(int id,String catName){
        String catId= String.valueOf(id);
        Log.e(TAG, "sendCatId: id "+id );
        Intent intent = new Intent(HomeActivity.this, ProductListActivity.class);
        intent.putExtra("catId",catId);
        intent.putExtra("catName",catName);
        startActivity(intent);
    }
}

