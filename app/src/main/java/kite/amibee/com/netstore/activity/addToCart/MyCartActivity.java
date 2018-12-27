package kite.amibee.com.netstore.activity.addToCart;

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

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import kite.amibee.com.netstore.activity.delivery.DeliveryActivity;
import kite.amibee.com.netstore.MyApp;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SpacesItemDecoration;
import kite.amibee.com.netstore.adapter.MyCartAdapter;
import kite.amibee.com.netstore.model.pojo.AddressModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartDetailModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartList;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;

public class MyCartActivity extends AppCompatActivity {
    private static final String TAG = "MyCartActivity";
    TextView order_tv_1, order_tv_2, tv_cart_subTotal, tv_total_count;
    RecyclerView mycart_rv;
    MyCartAdapter myCartAdapter;
    ArrayList<AddToCartList> cart_list = new ArrayList<>();
    /*toolbar*/
    ImageView toolbar_menu_iv, toolbar_logo_iv, toolbar_back_iv, toolbar_cart_iv;
    TextView toolbar_tv_title,toolbar_cart_no_tv;
    RelativeLayout toolbar_lay_cart;

    //toolbar
    //ui
    RelativeLayout lay_bot_1, lay_checkOut;
    Api api;
    PreferencesHelper preferencesHelper;
    private View.OnClickListener onClickListener;
    int customerId;
    int productId;
    String quantity;
    int websiteId;
    int addressId;
    int quoteId;
    String cartStatus;
    AddressModel quoteAddress = new AddressModel();
    HashMap<Integer,Integer> cartValue= new HashMap<>();
    MyApp myApp;
    JsonObject jsonObject = new JsonObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

    }

    public void init() {
        preferencesHelper = new PreferencesHelper(this);
        myApp=(MyApp)getApplicationContext();
        api = new Api(this, "");
        customerId=preferencesHelper.getCusId();
        quoteId=preferencesHelper.getQuoteId();
        addressId=preferencesHelper.getAddressId();
        websiteId=preferencesHelper.getWebsiteId();
        if (getIntent().getExtras()==null){
            Log.d(TAG, "init: quoteId "+quoteId);
            if (quoteId > 0){
                api.cartAll(quoteId);
            }

        }else {
            Log.d(TAG, "init: intent else ");
            cartStatus=getIntent().getStringExtra("cartStatus");
            productId=getIntent().getIntExtra("productId",0);
            quantity=getIntent().getStringExtra("quantity");
            Log.w(TAG, "init: cartStatus "+cartStatus );
            Log.w(TAG, "onCreate: customerId "+customerId+"\n" +" productId "+productId+"\n" +" quantity "+quantity +"\n"+" websiteId "+websiteId );

            jsonObject.addProperty("customerId", customerId);
            jsonObject.addProperty("productId", productId);
            jsonObject.addProperty("quantity", quantity);
            jsonObject.addProperty("websiteId", websiteId);
            if (cartStatus.equals("AddNew")){
                if(quoteId > 0){

                }else {
                    jsonObject.addProperty("addressId", addressId);
                }

                Log.w(TAG, "init: cart addnew jsonObject "+jsonObject );
                api.addToCart(jsonObject);
            }else {
                Log.w(TAG, "init: cart cartUpdateOrReplace jsonObject "+jsonObject );
                api.cartUpdateOrReplace(jsonObject);

            }


        }

        onClick();
        //toolbar
        toolbar_menu_iv = (ImageView) findViewById(R.id.toolbar_menu);
        toolbar_logo_iv = (ImageView) findViewById(R.id.toolbar_logo);
        toolbar_back_iv = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_tv_title = (TextView) findViewById(R.id.toolbar_tv_title);
        toolbar_cart_no_tv=findViewById(R.id.toolbar_cart_no_tv);
        toolbar_tv_title.setText("My Cart");
        toolbar_menu_iv.setVisibility(View.GONE);
        toolbar_logo_iv.setVisibility(View.GONE);
        toolbar_back_iv.setVisibility(View.VISIBLE);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_lay_cart = (RelativeLayout) findViewById(R.id.toolbar_lay_cart);
        toolbar_lay_cart.setVisibility(View.VISIBLE);
        toolbar_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //toolbar

        order_tv_1 = (TextView) findViewById(R.id.order_tv_1);
        order_tv_2 = (TextView) findViewById(R.id.order_tv_2);
        order_tv_1.setText("CONTINUE SHOPPING");
        order_tv_2.setText("CHECKOUT");
        tv_cart_subTotal = findViewById(R.id.tv_cart_subTotal);
        tv_total_count = findViewById(R.id.tv_total_count);

        mycart_rv = (RecyclerView) findViewById(R.id.mycart_rv);
        mycart_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mycart_rv.setItemAnimator(new DefaultItemAnimator());
        mycart_rv.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 0));
        myCartAdapter = new MyCartAdapter(MyCartActivity.this, cart_list);
        mycart_rv.setAdapter(myCartAdapter);
        lay_bot_1 = (RelativeLayout) findViewById(R.id.lay_bot_1);
        lay_checkOut = (RelativeLayout) findViewById(R.id.lay_bot_2);


        lay_checkOut.setOnClickListener(onClickListener);
    }

    public void apiResponse(AddToCartDetailModel addToCartDetailModel) {
        cart_list = addToCartDetailModel.getQuoteOrderItems();
        try{
            if (cart_list!=null){
                Log.w(TAG, "apiResponse: cart_list "+cart_list.size());
                myCartAdapter.updateAdapter(cart_list);
                if (cart_list.size() >0){
                    for (int i=0;i<cart_list.size();i++){
                        int productId= Integer.parseInt(cart_list.get(i).getProductId());
                        int quantity= Integer.parseInt(cart_list.get(i).getQuantity());
                        cartValue.put(productId,quantity);
                    }
                    myApp.setCartValue(cartValue);
                }else {
                    //no cart item
                    cartValue.put(0,0);
                    myApp.setCartValue(cartValue);
                }


                Log.w(TAG, "apiResponse: myapp cartValue size "+myApp.getCartValue().size() );


                String subTotal = addToCartDetailModel.getSubTotal();
                Log.d(TAG, "apiResponse: subTotal " + subTotal);
                tv_cart_subTotal.setText("" + subTotal);
                int cartCount = addToCartDetailModel.getTotalItems();
                int quoteId=addToCartDetailModel.getId();
                Log.d(TAG, "apiResponse: cartCount "+cartCount);
                Log.d(TAG, "apiResponse: quoteId "+quoteId);
                preferencesHelper.setCartCount(cartCount);
                preferencesHelper.setQuoteId(quoteId);
                int cartPref=preferencesHelper.getCartCount();
                Log.d(TAG, "apiResponse: cartPref "+cartPref);
                tv_total_count.setText("Price (" + cartCount + " items)");
                toolbar_cart_no_tv.setText(""+cartCount);

                /*quoteAddress=addToCartDetailModel.getQuoteAddress();
                if (quoteAddress!=null){
                    Log.w(TAG, "apiResponse: "+quoteAddress.getId() );
                }*/
            }

        }catch (Exception e){
            Log.e(TAG, "apiResponse: cart exception "+e );
        }



    }

    private void onClick1() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toolbar_back_iv.hashCode() == view.hashCode()) {
                    finish();
                }
            }
        };
    }

    private void onClick() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lay_checkOut.hashCode() == view.hashCode()) {

                    Log.e(TAG, "onClick: customerId "+customerId +" productId "+productId +" quantity "+quantity +" websiteId "+websiteId);
                    Intent intent = new Intent(MyCartActivity.this, DeliveryActivity.class);
//                    intent.putExtra("quoteAddress", (Serializable) quoteAddress);
                    startActivity(intent);
                }
            }
        };
    }

    public void cartDelete(String productId,String quantity){
        quoteId=preferencesHelper.getQuoteId();
        Log.d(TAG, "cartDelete: productId "+productId +" quantity "+quantity);
        Log.d(TAG, "cartDelete: customerId "+customerId +" websiteId "+websiteId);
        Log.d(TAG, "cartDelete: quoteId "+quoteId);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("quoteId", quoteId);
        jsonObject.addProperty("customerId", customerId);
        jsonObject.addProperty("productId", productId);
        jsonObject.addProperty("quantity", 0);
        jsonObject.addProperty("websiteId", websiteId);
        Log.w(TAG, "cartDelete: jsonObject "+jsonObject );
        api.cartDelete(jsonObject);
    }
}

