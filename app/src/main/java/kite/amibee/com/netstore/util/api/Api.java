package kite.amibee.com.netstore.util.api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.List;

import kite.amibee.com.netstore.activity.delivery.DeliveryActivity;
import kite.amibee.com.netstore.activity.orders.MyOrdersActivity;
import kite.amibee.com.netstore.activity.orders.MyOrdersDetailsActivity;
import kite.amibee.com.netstore.activity.product.ProductFullViewActivity;
import kite.amibee.com.netstore.R;
import kite.amibee.com.netstore.SelectAddressActivity;
import kite.amibee.com.netstore.activity.addToCart.MyCartActivity;
import kite.amibee.com.netstore.activity.product.ProductListActivity;
import kite.amibee.com.netstore.activity.category.ShopByCateActivity;
import kite.amibee.com.netstore.activity.home.HomeActivity;
import kite.amibee.com.netstore.activity.register.SignInActivity;
import kite.amibee.com.netstore.activity.register.SignUpActivity;
import kite.amibee.com.netstore.model.pojo.AddressModel;
import kite.amibee.com.netstore.model.pojo.AddressSingleUserModel;
import kite.amibee.com.netstore.model.pojo.PlaceOrderModel;
import kite.amibee.com.netstore.model.pojo.addtocart.AddToCartDetailModel;
import kite.amibee.com.netstore.model.pojo.category.Categories;
import kite.amibee.com.netstore.model.pojo.home.HomeAllCategories;
import kite.amibee.com.netstore.model.pojo.orders.OrdersModel;
import kite.amibee.com.netstore.model.pojo.productAttr.Attributes;
import kite.amibee.com.netstore.model.pojo.productAttr.ProductFullViewModel;
import kite.amibee.com.netstore.model.pojo.signin.SignInModel;
import kite.amibee.com.netstore.util.Constants;
import kite.amibee.com.netstore.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Api {
    private static final String TAG = "Api";
    Context context;
    ApiInterface apiService;
    Utils utils;
    public Api(Context context,String apiTag){
        Log.e(TAG, "Api: apiTag "+apiTag );
        this.context=context;
        if (apiTag.equals(Constants.ATTR)){
            apiService =ApiClient1.getClient(apiTag).create(ApiInterface.class);
        }else {
            apiService =ApiClient.getClient(apiTag).create(ApiInterface.class);
        }
        utils= new Utils();
        utils.progressSet((Activity) context);
    }
    public Api(Context context){
        this.context=context;
        apiService =ApiClient.getClient("").create(ApiInterface.class);
        utils= new Utils();
        utils.progressSet((Activity) context);
    }

    public void cateList(){
        utils.progressShow();
        Call<List<Categories>> call = apiService.category();
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>>call, Response<List<Categories>> response) {
                utils.progressHide();
                List<Categories> apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "response1 cateList " + apiResponse.size());
                    if (context instanceof ShopByCateActivity){
                        ((ShopByCateActivity)context).apiResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }



            }

            @Override
            public void onFailure(Call<List<Categories>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG,"onFailure "+ t.toString());
                utils.progressHide();
            }
        });
    }
    public void cateSearchList(String search){
        utils.progressShow();
        Call<List<Categories>> call = apiService.categorySearchList(search);
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>>call, Response<List<Categories>> response) {
                utils.progressHide();
                List<Categories> apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "response1 cateSearchList " + apiResponse.size());
                    if (context instanceof ShopByCateActivity){
                        ((ShopByCateActivity)context).apiResponseSearch(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }



            }

            @Override
            public void onFailure(Call<List<Categories>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG,"onFailure "+ t.toString());
                utils.progressHide();
            }
        });
    }

    public void homeAllList(){
        utils.progressShow();
        Call<List<HomeAllCategories>> call = apiService.homeCategory();
        call.enqueue(new Callback<List<HomeAllCategories>>() {
            @Override
            public void onResponse(Call<List<HomeAllCategories>>call, Response<List<HomeAllCategories>> response) {
                utils.progressHide();
                List<HomeAllCategories> apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "response1 homeAllList " + apiResponse.size());
                    if (context instanceof HomeActivity){
                        ((HomeActivity)context).apiResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }



            }

            @Override
            public void onFailure(Call<List<HomeAllCategories>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }
    public void productList(int id){
        utils.progressShow();
        Call<List<Attributes>> call = apiService.productList(id);
        call.enqueue(new Callback<List<Attributes>>() {
            @Override
            public void onResponse(Call<List<Attributes>>call, Response<List<Attributes>> response) {
                utils.progressHide();
                List<Attributes> apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  productArrt "+apiResponse.size());
                    if (context instanceof ProductListActivity){
                        ((ProductListActivity)context).apiResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }




            }

            @Override
            public void onFailure(Call<List<Attributes>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }

    public void productPriceFilter(int cateId,long minPrice,long maxPrice){
        utils.progressShow();
        Call<List<Attributes>> call = apiService.productPriceFilter(cateId,minPrice,maxPrice);
        call.enqueue(new Callback<List<Attributes>>() {
            @Override
            public void onResponse(Call<List<Attributes>>call, Response<List<Attributes>> response) {
                utils.progressHide();
                List<Attributes> apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "response1 productPriceFilter " + apiResponse.size());
                    if (context instanceof ProductListActivity){
                        ((ProductListActivity)context).apiResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }



            }

            @Override
            public void onFailure(Call<List<Attributes>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG,"onFailure "+ t.toString());
                utils.progressHide();
            }
        });
    }

    public void productDetailsview(String id){
        utils.progressShow();
        Call<ProductFullViewModel> call = apiService.productAttrSingle(id);
        call.enqueue(new Callback<ProductFullViewModel>() {
            @Override
            public void onResponse(Call<ProductFullViewModel>call, Response<ProductFullViewModel> response) {
                utils.progressHide();
                ProductFullViewModel apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  productDetailsview "+apiResponse );
                    if (context instanceof ProductFullViewActivity){
                        ((ProductFullViewActivity)context).apiResponse(apiResponse);
                    }

                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }





            }

            @Override
            public void onFailure(Call<ProductFullViewModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }

    public void addToCart(JsonObject jsonObject){
        utils.progressShow();
        Call<AddToCartDetailModel> call = apiService.addToCart(jsonObject);
        call.enqueue(new Callback<AddToCartDetailModel>() {
            @Override
            public void onResponse(Call<AddToCartDetailModel>call, Response<AddToCartDetailModel> response) {
                utils.progressHide();

                try{
                    AddToCartDetailModel apiResponse= response.body();
                    if (apiResponse!=null){
                        Log.e(TAG, "onResponse:  addToCart "+apiResponse );
                        if (context instanceof MyCartActivity){
                            ((MyCartActivity)context).apiResponse(apiResponse);
                        }else if (context instanceof DeliveryActivity){
                            ((DeliveryActivity)context).apiResponse(apiResponse);
                        }
                    }else {
                        utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                    }
                }catch (Exception e){
                    Log.e(TAG, "addToCart: Exception "+e );
                }






            }

            @Override
            public void onFailure(Call<AddToCartDetailModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }

    public void cartUpdateOrReplace(JsonObject jsonObject){
        utils.progressShow();
        Call<AddToCartDetailModel> call = apiService.cartUpdateOrReplace(jsonObject);
        call.enqueue(new Callback<AddToCartDetailModel>() {
            @Override
            public void onResponse(Call<AddToCartDetailModel>call, Response<AddToCartDetailModel> response) {
                utils.progressHide();

                try{
                    AddToCartDetailModel apiResponse= response.body();
                    if (apiResponse!=null){
                        Log.e(TAG, "onResponse:  addToCart "+apiResponse );
                        if (context instanceof MyCartActivity){
                            ((MyCartActivity)context).apiResponse(apiResponse);
                        }
                    }else {
                        utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                    }
                }catch (Exception e){
                    Log.e(TAG, "addToCart: Exception "+e );
                }

            }

            @Override
            public void onFailure(Call<AddToCartDetailModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }

    public void cartAll(int quoteId ){
        utils.progressShow();
        Call<AddToCartDetailModel> call = apiService.cartAll(quoteId);
        call.enqueue(new Callback<AddToCartDetailModel>() {
            @Override
            public void onResponse(Call<AddToCartDetailModel>call, Response<AddToCartDetailModel> response) {
                utils.progressHide();
                AddToCartDetailModel apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  addToCart "+apiResponse );
                    if (context instanceof MyCartActivity){
                        ((MyCartActivity)context).apiResponse(apiResponse);
                    }else if (context instanceof DeliveryActivity){
                        ((DeliveryActivity)context).apiResponse(apiResponse);
                    }else if (context instanceof ProductFullViewActivity){
                        ((ProductFullViewActivity)context).apiCartReadResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }





            }

            @Override
            public void onFailure(Call<AddToCartDetailModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }

    public void cartDelete(JsonObject jsonObject){
        utils.progressShow();
        Call<AddToCartDetailModel> call = apiService.cartDelete(jsonObject);
        call.enqueue(new Callback<AddToCartDetailModel>() {
            @Override
            public void onResponse(Call<AddToCartDetailModel>call, Response<AddToCartDetailModel> response) {
                utils.progressHide();
                AddToCartDetailModel apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  addToCart "+apiResponse );
                    if (context instanceof MyCartActivity){
                        ((MyCartActivity)context).apiResponse(apiResponse);
                    }else if (context instanceof DeliveryActivity){
                        ((DeliveryActivity)context).apiResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }





            }

            @Override
            public void onFailure(Call<AddToCartDetailModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }
    public void orders(int cusId){
        utils.progressShow();
        Log.e(TAG, "orders: cusId "+cusId );
        Call<List<OrdersModel>> call = apiService.orders(cusId);
        call.enqueue(new Callback<List<OrdersModel>>() {
            @Override
            public void onResponse(Call<List<OrdersModel>>call, Response<List<OrdersModel>> response) {
                utils.progressHide();
                List<OrdersModel> apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  orders "+apiResponse );
                    if (context instanceof MyOrdersActivity){
                        ((MyOrdersActivity)context).apiResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }





            }

            @Override
            public void onFailure(Call<List<OrdersModel>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }
    public void ordersDetails(int orderId){
        utils.progressShow();
        Log.e(TAG, "ordersDetails: orderId "+orderId );
        Call<OrdersModel> call = apiService.ordersDetails(orderId);
        call.enqueue(new Callback<OrdersModel>() {
            @Override
            public void onResponse(Call<OrdersModel>call, Response<OrdersModel> response) {
                utils.progressHide();
                OrdersModel apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  orders "+apiResponse );
                    if (context instanceof MyOrdersDetailsActivity){
                        ((MyOrdersDetailsActivity)context).apiResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }





            }

            @Override
            public void onFailure(Call<OrdersModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });
    }
    public void signIn(JsonObject jsonObject){
        utils.progressShow();

        Call<SignInModel> call = apiService.signIn(jsonObject);
        call.enqueue(new Callback<SignInModel>() {
            @Override
            public void onResponse(Call<SignInModel>call, Response<SignInModel> response) {
                utils.progressHide();
                SignInModel apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  signIn "+apiResponse.getMessage() );
                    if (context instanceof SignInActivity){
                        ((SignInActivity)context).apiResponse(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }





            }

            @Override
            public void onFailure(Call<SignInModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });


    }

    public void signUp(JsonObject jsonObject){
        utils.progressShow();
        Log.e(TAG, "signUp: jsonObject "+jsonObject );
        Call<SignInModel> call = apiService.signUp(jsonObject);
        call.enqueue(new Callback<SignInModel>() {
            @Override
            public void onResponse(Call<SignInModel>call, Response<SignInModel> response) {
                utils.progressHide();
                Log.e(TAG, "onResponse: cal "+response.body() );
                SignInModel apiResponse= response.body();
                try {
                    if (apiResponse!=null){
                        Log.e(TAG, "onResponse:  apiResponse "+apiResponse );
                        Log.e(TAG, "onResponse:  Message "+apiResponse.getMessage() );
                        if (context instanceof SignUpActivity){
                            ((SignUpActivity)context).apiResponse(apiResponse);
                        }
                    }else {
                        utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                    }
                }catch (Exception e){
                    Log.e(TAG, "signUp: Exception "+e );
                }


            }

            @Override
            public void onFailure(Call<SignInModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });


    }

    public void addressAdd(JsonObject jsonObject){
        utils.progressShow();
        Log.e(TAG, "addressAdd: jsonObject "+jsonObject );
        Call<AddressModel> call = apiService.addressAdd(jsonObject);
        call.enqueue(new Callback<AddressModel>() {
            @Override
            public void onResponse(Call<AddressModel>call, Response<AddressModel> response) {
                utils.progressHide();
                AddressModel apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  addressAdd "+apiResponse );
                    if (context instanceof SelectAddressActivity){
                        ((SelectAddressActivity)context).apiResponseAddAdress(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }





            }

            @Override
            public void onFailure(Call<AddressModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "onFailure "+t.toString());
                utils.progressHide();
            }
        });


    }

    public void addressSingleUser(int cusId){
        utils.progressShow();
        Log.w(TAG, "addressList: cusId "+cusId );
        Call<List<AddressSingleUserModel>> call = apiService.addressSingleUserList(cusId);
        call.enqueue(new Callback<List<AddressSingleUserModel>>() {
            @Override
            public void onResponse(Call<List<AddressSingleUserModel>>call, Response<List<AddressSingleUserModel>> response) {
                utils.progressHide();
                List<AddressSingleUserModel> apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  AddressListModel "+apiResponse );
                    if (context instanceof SelectAddressActivity){
                        ((SelectAddressActivity)context).apiResponseAdressList(apiResponse);
                    }else if (context instanceof ProductFullViewActivity){
                        ((ProductFullViewActivity)context).apiResponseAdressList(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }

            }

            @Override
            public void onFailure(Call<List<AddressSingleUserModel>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                utils.progressHide();
            }
        });


    }
    public void placeOrder(int quoteid){
        utils.progressShow();
        Log.e(TAG, "placeOrder: quoteid "+quoteid );
        Call<PlaceOrderModel> call = apiService.placeOrder(quoteid);
        call.enqueue(new Callback<PlaceOrderModel>() {
            @Override
            public void onResponse(Call<PlaceOrderModel>call, Response<PlaceOrderModel> response) {
                utils.progressHide();
                PlaceOrderModel apiResponse= response.body();
                if (apiResponse!=null){
                    Log.e(TAG, "onResponse:  signIn "+apiResponse );
                    if (context instanceof DeliveryActivity){
                        ((DeliveryActivity)context).apiResponsePlaceOrder(apiResponse);
                    }
                }else {
                    utils.showMessage(context.getResources().getString(R.string.no_response), (Activity) context);
                }





            }

            @Override
            public void onFailure(Call<PlaceOrderModel>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "onFailure "+t.toString());
                utils.progressHide();
            }
        });


    }

}
