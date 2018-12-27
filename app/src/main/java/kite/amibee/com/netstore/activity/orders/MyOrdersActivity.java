package kite.amibee.com.netstore.activity.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kite.amibee.com.netstore.PaymentActivity;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SpacesItemDecoration;
import kite.amibee.com.netstore.activity.addToCart.MyCartActivity;
import kite.amibee.com.netstore.adapter.MyOrderAdapterMain;
import kite.amibee.com.netstore.model.MyOrderProductModel;
import kite.amibee.com.netstore.model.pojo.orders.OrdersModel;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;

public class MyOrdersActivity extends AppCompatActivity {
    private static final String TAG = "MyOrdersActivity";
    /*toolbar*/
    ImageView toolbar_menu_iv,toolbar_logo_iv,toolbar_back_iv,toolbar_cart_iv;
    TextView toolbar_tv_title,toolbar_cart_no_tv;
    RelativeLayout toolbar_lay_cart,lay_bot_1;
    SearchView home_search_view;
    //toolbar
    //ui
    TextView order_tv_1,order_tv_2;
    RecyclerView myorder_rv;
    MyOrderAdapterMain myOrderAdapterMain;
    List<MyOrderProductModel> offers_list=new ArrayList<>();
    List<OrdersModel> order_list=new ArrayList<>();
    Api api;
    PreferencesHelper preferencesHelper;
    private View.OnClickListener onClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    public void init(){
        onClick();
        preferencesHelper= new PreferencesHelper(this);
        int cusId =preferencesHelper.getCusId();
        Log.e(TAG, "init: cusId "+cusId );
        api = new Api(this, "");
        api.orders(cusId);
        //toolbar
        toolbar_menu_iv=(ImageView)findViewById(R.id.toolbar_menu);
        toolbar_logo_iv=(ImageView)findViewById(R.id.toolbar_logo);
        toolbar_back_iv=(ImageView)findViewById(R.id.toolbar_back);
        toolbar_tv_title=(TextView)findViewById(R.id.toolbar_tv_title);
        toolbar_cart_no_tv=findViewById(R.id.toolbar_cart_no_tv);

        toolbar_tv_title.setText("My Orders");
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
                Intent intent = new Intent(MyOrdersActivity.this, MyCartActivity.class);
                startActivity(intent);
            }
        });
        //toolbar
        myorder_rv=(RecyclerView)findViewById(R.id.myorder_rv);
        myorder_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myorder_rv.setItemAnimator(new DefaultItemAnimator());
        myorder_rv.addItemDecoration(new SpacesItemDecoration(0,0, 0, 0));
        myOrderAdapterMain = new MyOrderAdapterMain(MyOrdersActivity.this, order_list);
        myorder_rv.setAdapter(myOrderAdapterMain);

        lay_bot_1=findViewById(R.id.lay_bot_1);
        lay_bot_1.setOnClickListener(onClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        int cartCount=preferencesHelper.getCartCount();
        toolbar_cart_no_tv.setText(""+cartCount);
    }

    public void detailsShow(String orderId){
        Intent in = new Intent(MyOrdersActivity.this,MyOrdersDetailsActivity.class);
        in.putExtra("orderId",orderId);
        startActivity(in);
    }

    public void apiResponse(List<OrdersModel> apidata){
        order_list=apidata;
        Log.d(TAG, "apiResponse: order_list "+order_list.size());
        myOrderAdapterMain.updateAdapter(order_list);

    }

    private void onClick(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyOrdersActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        };
    }
}
