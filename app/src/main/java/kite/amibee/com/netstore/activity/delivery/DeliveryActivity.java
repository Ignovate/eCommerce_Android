package kite.amibee.com.netstore.activity.delivery;

import android.content.Intent;
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

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SelectAddressActivity;
import kite.amibee.com.netstore.SpacesItemDecoration;
import kite.amibee.com.netstore.adapter.DeliveryCartAdapter;
import kite.amibee.com.netstore.model.MyCartModel;
import kite.amibee.com.netstore.model.pojo.AddressModel1;
import kite.amibee.com.netstore.model.pojo.PlaceOrderModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartDetailModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartList;
import kite.amibee.com.netstore.activity.orders.MyOrdersActivity;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;

public class DeliveryActivity extends AppCompatActivity {
    private static final String TAG = "DeliveryActivity";
    /*toolbar*/
    ImageView toolbar_menu_iv,toolbar_logo_iv,toolbar_back_iv,toolbar_cart_iv;
    TextView toolbar_tv_title,delivery_price_tv_sub_tot_price,delivery_price_tv_tax_price,delivery_price_tv_shipping_price,
            delivery_price_tv_tot_price,delivery_price_tv_gtot_price;
    TextView tv_addess_name,tv_address_full_name;
    RelativeLayout toolbar_lay_cart;
    //toolbar
    RecyclerView delivery_cart_rv;
    List<MyCartModel> offers_list=new ArrayList<>();
    DeliveryCartAdapter deliveryCartAdapter;
    RelativeLayout deliver_lay_ch_addr,lay_bot_1;
    ArrayList<AddToCartList> cart_list = new ArrayList<>();
    Api api;
    private View.OnClickListener onClickListener;
    PreferencesHelper preferencesHelper;
    AddressModel1 quoteAddress = new AddressModel1();
    //
    String name="";
    String streetName="";
    String countryId="";
    String regId="";
    int quoteId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        init();
    }

    public void init(){
//        quoteAddress = (AddressModel) getIntent().getSerializableExtra("quoteAddress");

        preferencesHelper= new PreferencesHelper(this);
        quoteId=preferencesHelper.getQuoteId();
        Log.e(TAG, "init: quoteId "+quoteId);
        api = new Api(this, "");
        api.cartAll(quoteId);
        onClick();

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
        delivery_cart_rv=(RecyclerView)findViewById(R.id.delivery_cart_rv);
        delivery_cart_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        delivery_cart_rv.setItemAnimator(new DefaultItemAnimator());
        delivery_cart_rv.addItemDecoration(new SpacesItemDecoration(0,0, 0, 0));
        deliveryCartAdapter = new DeliveryCartAdapter(DeliveryActivity.this, cart_list);
        delivery_cart_rv.setAdapter(deliveryCartAdapter);

        deliver_lay_ch_addr =(RelativeLayout)findViewById(R.id.deliver_lay_ch_addr);
        deliver_lay_ch_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryActivity.this,SelectAddressActivity.class);
                startActivity(intent);
            }
        });

        lay_bot_1=(RelativeLayout)findViewById(R.id.lay_bot_1);
        lay_bot_1.setOnClickListener(onClickListener);

        delivery_price_tv_sub_tot_price=findViewById(R.id.delivery_price_tv_sub_tot_price);
        delivery_price_tv_tax_price=findViewById(R.id.delivery_price_tv_tax_price);
        delivery_price_tv_shipping_price=findViewById(R.id.delivery_price_tv_shipping_price);
        delivery_price_tv_tot_price=findViewById(R.id.delivery_price_tv_tot_price);
        delivery_price_tv_gtot_price=findViewById(R.id.delivery_price_tv_gtot_price);

        tv_address_full_name=findViewById(R.id.tv_address_full_name);
        tv_addess_name=findViewById(R.id.tv_addess_name);


    }

    public void apiResponse(AddToCartDetailModel addToCartDetailModel) {
        cart_list = addToCartDetailModel.getQuoteOrderItems();
        deliveryCartAdapter.updateAdapter(cart_list);
        String subTotal = addToCartDetailModel.getSubTotal();
        String tax = addToCartDetailModel.getTaxAmount();
        String shippingPrice = addToCartDetailModel.getShippingAmount();
        String totPrice = addToCartDetailModel.getSubTotal();
        String grandTotal = addToCartDetailModel.getGrandTotal();

        delivery_price_tv_sub_tot_price.setText(subTotal);
        delivery_price_tv_tax_price.setText(tax);
        delivery_price_tv_shipping_price.setText(shippingPrice);
        delivery_price_tv_tot_price.setText(totPrice);
        delivery_price_tv_gtot_price.setText(grandTotal);
        quoteAddress=addToCartDetailModel.getQuoteAddress();
        if (quoteAddress!=null){
             name=quoteAddress.getFirstName();
             streetName=quoteAddress.getStreet();
             countryId = quoteAddress.getCountry();
             regId=quoteAddress.getRegion();
            Log.d(TAG, "init: name "+name +" streetName "+streetName +"countryId "+countryId );
        }else {
            Log.e(TAG, "apiResponse: quoteAddress "+quoteAddress );
        }
        tv_addess_name.setText(name);
        tv_address_full_name.setText(streetName +"\n"+countryId+"\n"+regId+"\n");


    }


    public void onClick(){
         onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: placeoreder quoteId "+quoteId );
                api.placeOrder(quoteId);


            }
        };
    }

    public void apiResponsePlaceOrder(PlaceOrderModel apiResponse){
        String status=apiResponse.getMessage();
        if (status.equals("SUCCESS")){
            preferencesHelper.setQuoteId(0);
            preferencesHelper.setCartCount(0);
            Intent intent= new Intent(DeliveryActivity.this,MyOrdersActivity.class);
            startActivity(intent);
        }

    }
}
