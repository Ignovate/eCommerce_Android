package kite.amibee.com.netstore.activity.product;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SelectAddressActivity;
import kite.amibee.com.netstore.activity.addToCart.MyCartActivity;
import kite.amibee.com.netstore.model.pojo.AddressSingleUserModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartDetailModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartList;
import kite.amibee.com.netstore.model.pojo.productAttr.ProductFullAttrModel;
import kite.amibee.com.netstore.model.pojo.productAttr.ProductFullImageModel;
import kite.amibee.com.netstore.model.pojo.productAttr.ProductFullPriceModel;
import kite.amibee.com.netstore.model.pojo.productAttr.ProductFullStockModel;
import kite.amibee.com.netstore.model.pojo.productAttr.ProductFullViewModel;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.Utils;
import kite.amibee.com.netstore.util.api.Api;

public class ProductFullViewActivity extends AppCompatActivity {
    private static final String TAG = "ProductFullViewActivity";
    /*toolbar*/
    ImageView toolbar_menu_iv, toolbar_logo_iv, toolbar_back_iv, toolbar_cart_iv, iv_filter_full_view,
            iv_product_small_1, iv_product_small_2;
    TextView toolbar_tv_title, filter_stock_max, filter_stock_min, filter_stock_num;
    RelativeLayout toolbar_lay_cart,filter_lay_flip_1;
    //toolbar
    //ui
    TextView order_tv_1, order_tv_2, toolbar_cart_no_tv;
    RelativeLayout lay_AddToCart, lay_bot_2;
    Api apical;
    TextView tv_product_name, tv_usage_desc_filter, tv_product_desc_filter, tv_product_price, tv_product_price_dt;
    private View.OnClickListener onClickListener;
    int stockCountMax = 5;
    int stock = 0;
    Utils utils = new Utils();
    ProductFullAttrModel product_disp;
    ProductFullStockModel stockList;
    ProductFullPriceModel priceList;
    ProductFullViewModel apiResponseData;
    PreferencesHelper preferencesHelper;
    int customerId;
    int addressId=0;
    int websiteId=0;
    int productId=0;
    int cartCount;
    int quoteId=0;

    HashMap<Integer,Integer> cartValue= new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_full_view);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

    }

    public void init() {
        String id = getIntent().getStringExtra("attri");
        productId= Integer.parseInt(id);
        Log.d(TAG, "bundle product id " + productId);

        preferencesHelper = new PreferencesHelper(this);
        customerId=preferencesHelper.getCusId();

        quoteId=preferencesHelper.getQuoteId();
        apical = new Api(this, "");
        apical.productDetailsview(id);


        onClick();
        toolbar_menu_iv = (ImageView) findViewById(R.id.toolbar_menu);
        toolbar_logo_iv = (ImageView) findViewById(R.id.toolbar_logo);
        toolbar_back_iv = (ImageView) findViewById(R.id.toolbar_back);
        toolbar_tv_title = (TextView) findViewById(R.id.toolbar_tv_title);
        toolbar_cart_no_tv = findViewById(R.id.toolbar_cart_no_tv);
        filter_lay_flip_1=findViewById(R.id.filter_lay_flip_1);
        tv_product_price = findViewById(R.id.tv_product_price);
        tv_product_price_dt = findViewById(R.id.tv_product_price_dt);
        toolbar_lay_cart = (RelativeLayout) findViewById(R.id.toolbar_lay_cart);
        toolbar_lay_cart.setVisibility(View.VISIBLE);
        toolbar_menu_iv.setVisibility(View.GONE);
        toolbar_logo_iv.setVisibility(View.GONE);
        toolbar_back_iv.setVisibility(View.VISIBLE);
        toolbar_tv_title.setVisibility(View.GONE);


///
        order_tv_1 = (TextView) findViewById(R.id.order_tv_1);
        order_tv_2 = (TextView) findViewById(R.id.order_tv_2);
//        order_tv_1.setText("");
        lay_AddToCart = (RelativeLayout) findViewById(R.id.lay_bot_1);
        lay_bot_2 = (RelativeLayout) findViewById(R.id.lay_bot_2);

        tv_product_desc_filter = (TextView) findViewById(R.id.tv_product_desc_filter);
        tv_product_name = (TextView) findViewById(R.id.tv_product_name);
        tv_usage_desc_filter = (TextView) findViewById(R.id.tv_usage_desc_filter);
        filter_stock_min = findViewById(R.id.filter_stock_min);
        filter_stock_max = findViewById(R.id.filter_stock_max);
        filter_stock_num = (TextView) findViewById(R.id.filter_stock_num);

        iv_product_small_2 = findViewById(R.id.iv_product_small_2);
        iv_filter_full_view = findViewById(R.id.filter_full_view);
        iv_product_small_1 = findViewById(R.id.iv_product_small_1);

        toolbar_back_iv.setOnClickListener(onClickListener);
        filter_stock_min.setOnClickListener(onClickListener);
        filter_stock_max.setOnClickListener(onClickListener);
        lay_AddToCart.setOnClickListener(onClickListener);
        stock= Integer.parseInt(filter_stock_num.getText().toString());

    }

    @Override
    protected void onResume() {
        super.onResume();
        cartCount = preferencesHelper.getCartCount();
        toolbar_cart_no_tv.setText("" + cartCount);
    }

    public void apiResponse(ProductFullViewModel productAttri) {
        if (productAttri==null){
            Log.e(TAG, "apiResponse: productAttri "+productAttri );
            return;
        }

        try{
            apiResponseData = productAttri;
            product_disp = productAttri.getAttrEntity();
            stockList = productAttri.getInventoryEntity();
            priceList = productAttri.getPriceEntity();
            websiteId= Integer.parseInt(productAttri.getWebsiteId());
            preferencesHelper.setWebsiteId(websiteId);
            tv_product_name.setText(product_disp.getName());
            tv_product_desc_filter.setText(product_disp.getDescription());
            tv_usage_desc_filter.setText(product_disp.getUsage());
            tv_product_price.setText("AED "+priceList.getSplPrice());
            Log.d(TAG, "apiResponse: dt_price "+priceList.getPrice() );
            tv_product_price_dt.setText("AED "+priceList.getPrice());
            tv_product_price_dt.setPaintFlags(tv_product_price_dt.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
            ProductFullImageModel image = productAttri.getImage();

            String url = image.getImage();
            String thumbnail1 = image.getThumbnail1();
            String thumbnail2 = image.getThumbnail2();
            Glide.with(this).
                    load(url).
                    transition(DrawableTransitionOptions.withCrossFade()).
                    listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            iv_filter_full_view.setImageResource(R.drawable.logo);

                            return true;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            return false;
                        }

                    }).
                    into(iv_filter_full_view);

            Glide.with(this).
                    load(thumbnail1).
                    transition(DrawableTransitionOptions.withCrossFade()).
                    listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            iv_product_small_1.setImageResource(R.drawable.logo);
                            filter_lay_flip_1.setBackgroundResource(R.drawable.lay_filter_flip_gradiant);
                            return true;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            filter_lay_flip_1.setBackgroundResource(R.drawable.lay_filter_flip_gradiant);
                            return false;
                        }

                    }).
                    into(iv_product_small_1);

            Glide.with(this).
                    load(thumbnail2).
                    transition(DrawableTransitionOptions.withCrossFade()).
                    listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            iv_product_small_2.setImageResource(R.drawable.logo);
                            return true;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }

                    }).
                    into(iv_product_small_2);
        }catch (Exception e){
            Log.e(TAG, "apiResponse: Exception "+e );
        }


    }

    private void onClick() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toolbar_back_iv.hashCode() == view.hashCode()) {
                    finish();
                } else if (lay_AddToCart.hashCode() == view.hashCode()) {
                    cartSend();
                } else if (filter_stock_max.hashCode() == view.hashCode()) {
                    if (stock < stockCountMax) {
                        stock = stock + 1;
                        filter_stock_num.setText("" + stock);
                    } else {
                        utils.showMessage(getResources().getString(R.string.product_max_count), ProductFullViewActivity.this);
                    }
                } else if (filter_stock_min.hashCode() == view.hashCode()) {
                    if (stock > 0) {
                        stock = stock - 1;
                        filter_stock_num.setText("" + stock);
                    }

                }
            }
        };
    }

    public void cartSend() {
        quoteId=preferencesHelper.getQuoteId();
        Log.d(TAG, "cartSend: quoteId "+quoteId);
        if (quoteId > 0){
            apical.cartAll(quoteId);
        }else {
            Log.w(TAG, "cartSend: address fetch cal" );
            addressId=preferencesHelper.getAddressId();
            Log.w(TAG, "cartSend: addressId "+addressId );
            if (addressId > 0){
                cartAdd("AddNew");

            }else {
                // fetech address for current user
                apical.addressSingleUser(customerId);

            }

        }


    }

    public void cartAdd(String status){
        Log.w(TAG, "cartAdd: status "+status );
        Intent intent = new Intent(ProductFullViewActivity.this, MyCartActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("quantity", String.valueOf(stock));
        intent.putExtra("cartStatus", status);
        Log.w(TAG, "cartAdd: productId "+productId +"\n"+" stock "+String.valueOf(stock) );
        startActivity(intent);
    }

    public void apiResponseAdressList(List<AddressSingleUserModel> apiDataAddreList){
        if (apiDataAddreList!=null && apiDataAddreList.size() > 0){
            Log.e(TAG, "apiResponseAdressList: addressList "+apiDataAddreList.size() );

                addressId =apiDataAddreList.get(0).getAddressId();
                preferencesHelper.setAddressId(addressId);

                Log.d(TAG, "cartSend: pref addressId " + preferencesHelper.getAddressId());
                Log.d(TAG, "cartSend: customerId " + customerId);
                Log.d(TAG, "cartSend: addressId " + addressId);

            cartAdd("AddNew");

        } else {
            dialogCal();

        }

    }
    public void apiCartReadResponse(AddToCartDetailModel addToCartDetailModel) {

        ArrayList<AddToCartList> cart_list = addToCartDetailModel.getQuoteOrderItems();
        try{
            if (cart_list!=null){
                Log.w(TAG, "apiResponse: cart_list "+cart_list.size());
                cartValue.clear();
                for (int i=0;i<cart_list.size();i++){
                    int productId= Integer.parseInt(cart_list.get(i).getProductId());
                    int quantity= Integer.parseInt(cart_list.get(i).getQuantity());
                    cartValue.put(productId,quantity);
                }
//                preferencesHelper.setCartCount(cartCount);
//                preferencesHelper.setQuoteId(quoteId);
                int cartPref=preferencesHelper.getCartCount();
                Log.d(TAG, "apiResponse: cartPref "+cartPref);
                if (cartValue!=null && cartValue.size() >0 ){
                    Log.d(TAG, "apiCartReadResponse: true ");
                    Log.w(TAG, "apiCartReadResponse: product status "+cartValue.containsKey(productId));
                    if (cartValue.containsKey(productId)){
                        dialogCart();
                    }else {
                        // product not exists in cart
                        cartAdd("AddNew");
                    }
                }else {
                    Log.w(TAG, "apiCartReadResponse: else cartValue "+cartValue );
                    cartAdd("AddNew");
                }



            }else {
                Log.w(TAG, "apiResponse: else cart_list "+cart_list);
            }

        }catch (Exception e){
            Log.e(TAG, "apiResponse: cart exception "+e );
        }

    }


    public void dialogCal(){
        Log.d(TAG, "dialogCal: ");
        AlertDialog alertDialog = null;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You must add Address before add cart");



        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
                        startActivity(intent);


                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void dialogCart(){
        Log.d(TAG, "dialogCart: ");
        AlertDialog alertDialog = null;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to cart Replace or Update?");



        alertDialogBuilder.setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                       cartAdd("AddNew");


                    }
                });
        alertDialogBuilder.setNeutralButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {



            }
        });

        alertDialogBuilder.setNegativeButton("Replace",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cartAdd("Replace");
            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

}