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

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SpacesItemDecoration;
import kite.amibee.com.netstore.activity.addToCart.MyCartActivity;
import kite.amibee.com.netstore.adapter.MyOrderDetailsCateAdapter;
import kite.amibee.com.netstore.model.pojo.AddressModel1;
import kite.amibee.com.netstore.model.pojo.orders.OrdersItemsModel;
import kite.amibee.com.netstore.model.pojo.orders.OrdersModel;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;

public class MyOrdersDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MyOrdersDetailsActivity";
    /*toolbar*/
    ImageView toolbar_menu_iv, toolbar_logo_iv, toolbar_back_iv, toolbar_cart_iv;
    TextView toolbar_tv_title, myorder_details_order_date, myorder_details_orderId, myorder_details_tv_deli_date,toolbar_cart_no_tv;
    TextView myorder_details_price_tv_sub_tot_price,myorder_details_price_tv_tax_price,
            myorder_details_price_tv_shipping_price,myorder_details_price_tv_tot_price,myorder_details_price_tv_gtot_price,
            tv_sale_address,tv_sale_address_name,tv_sale_address_pin;
    RelativeLayout toolbar_lay_cart;
    SearchView home_search_view;
    //toolbar
    RecyclerView myorder_details_rv;
    List<OrdersItemsModel> orderDetails_list = new ArrayList<>();
    MyOrderDetailsCateAdapter myOrderDetailsCateAdapter;
    RelativeLayout deliver_lay_ch_addr;
    Api api;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_details);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    public void init() {
        preferencesHelper = new PreferencesHelper(this);
        api = new Api(this);
        String orderId = getIntent().getStringExtra("orderId");
        Log.e(TAG, "init: orderId " + orderId);
        api.ordersDetails(Integer.parseInt(orderId));
        //toolbar
        toolbar_menu_iv = (ImageView) findViewById(R.id.toolbar_menu);
        toolbar_logo_iv = (ImageView) findViewById(R.id.toolbar_logo);
        toolbar_back_iv = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_tv_title = (TextView) findViewById(R.id.toolbar_tv_title);
        toolbar_tv_title.setText("Orders Details");
        toolbar_menu_iv.setVisibility(View.GONE);
        toolbar_logo_iv.setVisibility(View.GONE);
        toolbar_back_iv.setVisibility(View.VISIBLE);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_lay_cart = (RelativeLayout) findViewById(R.id.toolbar_lay_cart);
        toolbar_lay_cart.setVisibility(View.VISIBLE);
        toolbar_cart_no_tv=findViewById(R.id.toolbar_cart_no_tv);
        toolbar_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        home_search_view = (SearchView) findViewById(R.id.home_search_view);
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
        toolbar_cart_iv = (ImageView) findViewById(R.id.toolbar_cart);
        toolbar_cart_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyOrdersDetailsActivity.this, MyCartActivity.class);
                startActivity(intent);
            }
        });
        //toolbar
        myorder_details_order_date = findViewById(R.id.myorder_details_order_date);
        myorder_details_orderId = findViewById(R.id.myorder_details_orderId);
        myorder_details_tv_deli_date = findViewById(R.id.myorder_details_tv_deli_date);

        myorder_details_price_tv_sub_tot_price = findViewById(R.id.myorder_details_price_tv_sub_tot_price);
        myorder_details_price_tv_tax_price = findViewById(R.id.myorder_details_price_tv_tax_price);
        myorder_details_price_tv_shipping_price = findViewById(R.id.myorder_details_price_tv_shipping_price);
        myorder_details_price_tv_tot_price = findViewById(R.id.myorder_details_price_tv_tot_price);
        myorder_details_price_tv_gtot_price = findViewById(R.id.myorder_details_price_tv_gtot_price);



        myorder_details_rv = (RecyclerView) findViewById(R.id.myorder_details_rv);
        myorder_details_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myorder_details_rv.setItemAnimator(new DefaultItemAnimator());
        myorder_details_rv.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 0));
        myOrderDetailsCateAdapter = new MyOrderDetailsCateAdapter(MyOrdersDetailsActivity.this, orderDetails_list);
        myorder_details_rv.setAdapter(myOrderDetailsCateAdapter);

        tv_sale_address= findViewById(R.id.tv_sale_address);
        tv_sale_address_name= findViewById(R.id.tv_sale_address_name);
        tv_sale_address_pin= findViewById(R.id.tv_sale_address_pin);


    }

    @Override
    protected void onResume() {
        super.onResume();
        int cartCount=preferencesHelper.getCartCount();
        toolbar_cart_no_tv.setText(""+cartCount);
    }

    public void apiResponse(OrdersModel responseData) {
        orderDetails_list = responseData.getSaleOrderItems();
        myOrderDetailsCateAdapter.updateAdapter(orderDetails_list);

        List<String> createdDate = responseData.getCreatedDate();
        List<String> updatedDate = responseData.getUpdatedDate();
        StringBuilder c_date = new StringBuilder();
        StringBuilder u_date = new StringBuilder();
        for (int i = 0; i < createdDate.size(); i++) {
            if (i < 3) {
                c_date.append(createdDate.get(i) + "/");
                u_date.append(updatedDate.get(i) + "/");

            }

        }
        String c_date1 = c_date.substring(0, c_date.length() - 1);
        String u_date1 = u_date.substring(0, u_date.length() - 1);
        myorder_details_order_date.setText(c_date1);
        myorder_details_tv_deli_date.setText(u_date1);
        myorder_details_orderId.setText(responseData.getId());
        myorder_details_price_tv_sub_tot_price.setText(getResources().getString(R.string.priceAED)+responseData.getSubTotal());
        myorder_details_price_tv_tax_price.setText(getResources().getString(R.string.priceAED)+responseData.getTaxAmount());
//        myorder_details_price_tv_tot_price.setText(ordersModel.getGrandTotal());
        myorder_details_price_tv_shipping_price.setText(getResources().getString(R.string.priceAED)+responseData.getShippingAmount());
        myorder_details_price_tv_gtot_price.setText(getResources().getString(R.string.priceAED)+responseData.getGrandTotal());

        AddressModel1 saleAddress= responseData.getSaleAddress();
        if (saleAddress!=null){
            tv_sale_address_name.setText(saleAddress.getFirstName());
            tv_sale_address.setText(saleAddress.getStreet());
            tv_sale_address_pin.setText(saleAddress.getPostCode());
        }else {

        }


    }
}
